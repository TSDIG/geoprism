/**
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.
 */
package net.geoprism.dhis2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Savepoint;
import java.util.Iterator;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runwaysdk.dataaccess.DuplicateDataException;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.transaction.Transaction;

import net.geoprism.account.ExternalProfile;
import net.geoprism.account.OauthServer;
import net.geoprism.dhis2.response.DHIS2ConflictException;

public class DHIS2HTTPConnector
{
  private String OAUTH_KEY_NAME = "geoprism-dhis2";
  
  private Logger logger = LoggerFactory.getLogger(DHIS2HTTPConnector.class);
  
  private HttpClient client;
  
  private String serverurl;
  
  private String externalUrl;
  
  public static final String CLIENT_ID = "geoprism";
  
  public static final String SECRET = "1e6db50c-0fee-11e5-98d0-3c15c2c6caf6";
  
  private String accessToken;
  
  private String refreshToken;
  
  private String username;
  
  private String password;
  
  public DHIS2HTTPConnector()
  {
    
  }
  
  public String getAccessToken()
  {
    return accessToken;
  }
  
  public String getServerUrl()
  {
    return serverurl;
  }
  
  public void getUrlsFromOauthCredentialsIfNotExist()
  {
    if (this.serverurl == null)
    {
      OauthServer oauth = OauthServer.getByKey(OAUTH_KEY_NAME);
      String profileLoc = oauth.getProfileLocation();
      
      // TODO : I think we can combine serverurl and externalurl into the same variable.
      this.serverurl = profileLoc.substring(0, profileLoc.length() - 7);
      this.externalUrl = this.serverurl;
    }
  }
  
  public void setServerUrl(String url)
  {
    this.serverurl = url;
  }
  
  public void setServerExternalUrl(String url)
  {
    this.externalUrl = url;
  }
  
  public void setCredentials(String username, String password)
  {
    this.username = username;
    this.password = password;
  }
  
  synchronized public void initialize()
  {
    this.client = new HttpClient();
    this.accessToken = ExternalProfile.getAccessToken();
    
    if (!isInitialized() && username != null && password != null)
    {
      createOauthData(username, password);
      
      logIn(username, password);
    }
    
    if (!isInitialized())
    {
      throw new RuntimeException("Unable to log into DHIS2.");
    }
  }
  
  public boolean isInitialized()
  {
    return client != null && accessToken != null;
  }
  
  @Transaction
  public void createOauthData(String username, String password)
  {
    try
    {
      createOauthClient(username, password);
    }
    catch (DHIS2ConflictException e)
    {
      // If it threw an error because the oauth client already exists, ignore it.
      if (!e.isDuplicateGeoprismOauth())
      {
        throw e;
      }
    }
    
    Savepoint sp = Database.setSavepoint();
    
    try
    {
      OauthServer oauth = new OauthServer();
      oauth.setKeyName(OAUTH_KEY_NAME);
      oauth.getDisplayLabel().setValue("DHIS2");
      oauth.setAuthorizationLocation(externalUrl + "/uaa/oauth/authorize");
      oauth.setTokenLocation(externalUrl + "/uaa/oauth/token");
      oauth.setProfileLocation(externalUrl + "/api/me");
      oauth.setClientId(DHIS2HTTPConnector.CLIENT_ID);
      oauth.setSecretKey(DHIS2HTTPConnector.SECRET);
      oauth.setServerType("DHIS2");
      oauth.apply();
    }
    catch (DuplicateDataException ex)
    {
      Database.rollbackSavepoint(sp);
    }
    catch (RuntimeException ex)
    {
      Database.rollbackSavepoint(sp);
      throw ex;
    }
    finally
    {
      Database.releaseSavepoint(sp);
    }
  }
  
  /**
   * Uses the DHIS2 REST API to register a new OAuth configuration for Geoprism
   */
  // curl -X POST -H "Content-Type: application/json" -d '{ "name" : "OAuth2 Demo Client", "cid" : "demo", "secret" : "1e6db50c-0fee-11e5-98d0-3c15c2c6caf6", "grantTypes" : [ "password", "refresh_token", "authorization_code" ], "redirectUris" : [ "http://www.example.org" ]}' -u admin:district 
  private void createOauthClient(String username, String password)
  {
    HttpClient client = new HttpClient();
    
    client.getParams().setAuthenticationPreemptive(true);
    Credentials defaultcreds = new UsernamePasswordCredentials(username, password);
    client.getState().setCredentials(AuthScope.ANY, defaultcreds);
    
    try
    {
      PostMethod post = new PostMethod(this.serverurl + "api/oAuth2Clients");
      
      post.setRequestHeader("Content-Type", "application/json");
      
      String body = "{ \"name\" : \"Geoprism\", \"cid\" : \"" + CLIENT_ID + "\", \"secret\" : \"" + SECRET + "\", \"grantTypes\" : [ \"password\", \"refresh_token\", \"authorization_code\" ], \"redirectUris\" : [ \"\" ]}";
      post.setRequestEntity(new StringRequestEntity(body, null, null));

      JSONObject response = new JSONObject();
      int statusCode = httpRequest(client, post, response);

      if (statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_CREATED)
      {
        
      }
      else if (statusCode == HttpStatus.SC_CONFLICT)
      {
        throw new DHIS2ConflictException(response);
      }
      else
      {
        if (response.has("message"))
        {
          String message = response.getString("message");

          throw new RuntimeException(message);
        }

        String message = "Unable to create OAuth client.  Ensure that your login credentials are correct.";
        throw new RuntimeException(message);
      }
    }
    catch (JSONException | UnsupportedEncodingException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  // curl -X POST -H "Accept: application/json" -u demo:$SECRET $SERVER/uaa/oauth/token -d grant_type=password -d username=admin -d password=district
  public void logIn(String username, String password)
  {
    HttpClient client = new HttpClient();
    
    client.getParams().setAuthenticationPreemptive(true);
    Credentials defaultcreds = new UsernamePasswordCredentials("geoprism", SECRET);
    client.getState().setCredentials(AuthScope.ANY, defaultcreds);

    try
    {
      PostMethod post = new PostMethod(this.serverurl + "uaa/oauth/token");
      post.setRequestHeader("Accept", "application/json");
      post.addParameter("grant_type", "password");
      post.addParameter("username", username);
      post.addParameter("password", password);
      post.addParameter("format", "json");

      JSONObject json = new JSONObject();
      int statusCode = httpRequest(client, post, json);

      if (statusCode == HttpStatus.SC_OK)
      {
        this.accessToken = json.getString("access_token");
        this.refreshToken = json.getString("refresh_token");
      }
      else
      {
        if (json.has("message"))
        {
          String message = json.getString("message");

          throw new RuntimeException(message);
        }

        String message = "Unable to log into DHIS2. Ensure your credentials are correct.";
        throw new RuntimeException(message);
      }
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  public JSONObject httpGet(String url, NameValuePair[] params)
  {
    if (!isInitialized())
    {
      initialize();
    }
    
    GetMethod get = new GetMethod(this.getServerUrl() + url);
    
    get.setRequestHeader("Authorization", "Bearer " + this.getAccessToken());
    get.setRequestHeader("Accept", "application/json");
    
    get.setQueryString(params);
    
    JSONObject response = new JSONObject();
    int statusCode = this.httpRequest(this.client, get, response);
    
    if (statusCode != HttpStatus.SC_OK)
    {
      throw new RuntimeException("DHIS2 returned unexpected status code [" + statusCode + "].");
    }
    
    return response;
  }
  
  public JSONObject httpPost(String url, String body)
  {
    if (!isInitialized())
    {
      initialize();
    }
    
    try
    {
      PostMethod post = new PostMethod(this.serverurl + url);
      
      post.setRequestHeader("Authorization", "Bearer " + this.getAccessToken());
      post.setRequestHeader("Content-Type", "application/json");
      
      post.setRequestEntity(new StringRequestEntity(body, null, null));

      JSONObject response = new JSONObject();
      int statusCode = httpRequest(this.client, post, response);

      if (statusCode != HttpStatus.SC_OK && statusCode != HttpStatus.SC_CREATED)
      {
        throw new RuntimeException("DHIS2 returned unexpected status code [" + statusCode + "].");
      }
      
      return response;
    }
    catch (JSONException | UnsupportedEncodingException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  public int httpRequest(HttpClient client, HttpMethod method, JSONObject response)
  {
    String sResponse = null;
    try
    {
      this.logger.info("Sending request to " + method.getURI());

      // Execute the method.
      int statusCode = client.executeMethod(method);
      
      // Follow Redirects
      if (statusCode == HttpStatus.SC_MOVED_TEMPORARILY || statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_TEMPORARY_REDIRECT || statusCode == HttpStatus.SC_SEE_OTHER)
      {
        this.logger.info("Redirected [" + statusCode + "] to [" + method.getResponseHeader("location").getValue() + "].");
        method.setURI(new URI(method.getResponseHeader("location").getValue(), true, method.getParams().getUriCharset()));
        method.releaseConnection();
        return httpRequest(client, method, response);
      }
      
      // TODO : we might blow the memory stack here, read this as a stream somehow if possible.
      Header contentTypeHeader = method.getResponseHeader("Content-Type");
      if (contentTypeHeader == null)
      {
        sResponse = new String(method.getResponseBody(), "UTF-8");
      }
      else
      {
        sResponse = method.getResponseBodyAsString();
      }
      
      if (sResponse.length() < 1000)
      {
        this.logger.info("Response string = '" + sResponse + "'.");
      }
      else
      {
        this.logger.info("Receieved a very large response.");
      }

      JSONObject jsonResp;
      if (sResponse.startsWith("["))
      {
        jsonResp = new JSONArray(sResponse).getJSONObject(0);
      }
      else if (sResponse.startsWith("{"))
      {
        jsonResp = new JSONObject(sResponse);
      }
      else
      {
        jsonResp = new JSONObject();
        jsonResp.put("errorCode", statusCode);
        jsonResp.put("message", sResponse);
      }

      @SuppressWarnings("unchecked")
      Iterator<String> it = jsonResp.keys();
      while (it.hasNext())
      {
        String key = it.next();
        response.put(key, jsonResp.get(key));
      }

      return statusCode;
    }
    catch (HttpException e)
    {
      throw new RuntimeException(e);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
    finally
    {
      method.releaseConnection();
    }
  }
}