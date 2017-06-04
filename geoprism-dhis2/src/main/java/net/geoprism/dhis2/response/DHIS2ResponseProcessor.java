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
package net.geoprism.dhis2.response;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runwaysdk.Pair;

public class DHIS2ResponseProcessor
{
  private static Logger logger = LoggerFactory.getLogger(DHIS2ResponseProcessor.class);
  
  public static void validateImportSummaryResponse(JSONObject response)
  {
//    {
//      "ignored": 0,
//      "responseType": "ImportSummaries",
//      "deleted": 0,
//      "imported": 0,
//      "importSummaries": [
//        {
//          "responseType": "ImportSummary",
//          "importCount": {
//            "ignored": 0,
//            "deleted": 0,
//            "imported": 0,
//            "updated": 0
//          },
//          "conflicts": [
//            {
//              "value": "No org unit ID in tracked entity instance object."
//            }
//          ],
//          "status": "SUCCESS"
//        },
//    }
    
    if (!response.has("responseType") || !response.getString("responseType").equals("ImportSummaries"))
    {
      DHIS2UnexpectedResponseException ex = new DHIS2UnexpectedResponseException();
      ex.setDhis2Response(response.toString()); // TODO : We need to be very careful about putting the entire response in here because the response could be very large. Also its raw JSON.
      throw ex;
    }
    
    JSONArray summaries = response.getJSONArray("importSummaries");
    
    for (int i = 0; i < summaries.length(); ++i)
    {
      JSONObject summary = summaries.getJSONObject(i);
      
      String status = summary.getString("status");
      if (!status.equals("SUCCESS"))
      {
        DHIS2UnexpectedResponseException ex = new DHIS2UnexpectedResponseException();
        ex.setDhis2Response(summary.toString()); // TODO : We need to be very careful about putting the entire response in here because the response could be very large. Also its raw JSON.
        throw ex;
      }
      
      if (summary.has("conflicts"))
      {
        JSONArray conflicts = summary.getJSONArray("conflicts");
        
        for (int j = 0; j < conflicts.length(); ++j)
        {
          JSONObject conflict = conflicts.getJSONObject(j);
          String value = conflict.getString("value");
          
          if (value.contains("No org unit"))
          {
            DHIS2UnexpectedResponseException ex = new DHIS2UnexpectedResponseException();
            ex.setDhis2Response(value);
            throw ex;
          }
        }
      }
      
      if (summary.has("enrollments"))
      {
        JSONObject enrollments = summary.getJSONObject("enrollments");
        
        validateImportSummaryResponse(enrollments);
      }
    }
  }
  
  public static void validateTypeReportResponse(JSONObject response, boolean errorOnAlreadyExists)
  {
//  {
//  "status": "ERROR",
//  "typeReports": [
//    {
//      "klass": "org.hisp.dhis.trackedentity.TrackedEntity",
//      "stats": {
//        "total": 1,
//        "created": 0,
//        "updated": 0,
//        "deleted": 0,
//        "ignored": 1
//      },
//      "objectReports": [
//        {
//          "klass": "org.hisp.dhis.trackedentity.TrackedEntity",
//          "index": 0,
//          "errorReports": [
//            {
//              "message": "Property `name`Â with value `Labor Force By State 2015` on object Labor Force By State 2015 [Lg3bG0HelP0] (TrackedEntity)Â already exists on object Yw2zJnEX0o4.",
//              "mainKlass": "org.hisp.dhis.trackedentity.TrackedEntity",
//              "errorCode": "E5003"
//            }
//          ]
//        }
//      ]
//    }
//  ],
//  "stats": {
//    "total": 1,
//    "created": 0,
//    "updated": 0,
//    "deleted": 0,
//    "ignored": 1
//  }
//}
    if (!response.has("status"))
    {
      DHIS2UnexpectedResponseException ex = new DHIS2UnexpectedResponseException();
      ex.setDhis2Response(response.toString()); // TODO : We need to be very careful about putting the entire response in here because the response could be very large. Also its raw JSON.
      throw ex;
    }
    
    List<String> attrErrs = new ArrayList<String>();
    
    if (response.getString("status").equals("ERROR"))
    {
      JSONArray typeReports = response.getJSONArray("typeReports");
      
      for (int i = 0; i < typeReports.length(); ++i)
      {
        JSONObject typeReport = typeReports.getJSONObject(i);
        
        String klass = typeReport.getString("klass");
        
        if (typeReport.has("objectReports")) // It may not have an objectReport if this particular typeReport completed successfully.
        {
          JSONArray objectReports = typeReport.getJSONArray("objectReports");
          
          for (int j = 0; j < objectReports.length(); ++j)
          {
            JSONObject objectReport = objectReports.getJSONObject(j);
            
            JSONArray errorReports = objectReport.getJSONArray("errorReports");
            
            for (int k = 0; k < errorReports.length(); ++k)
            {
              JSONObject errorReport = errorReports.getJSONObject(k);
              
              String errorCode = errorReport.getString("errorCode");
              String message = errorReport.getString("message");
              String mainKlass = errorReport.getString("mainKlass");
  
              if (errorCode.equals("E5003")) // Duplicate data ex
              {
                if (mainKlass.equals("org.hisp.dhis.trackedentity.TrackedEntityAttribute"))
                {
                  attrErrs.add(message);
                }
                else
                {
                  Pair<String, String> pair = parseDuplicateDataMsg(message);
                  
                  DHIS2DuplicateDataException ex = new DHIS2DuplicateDataException();
                  ex.setDataType(mainKlass);
                  ex.setPropertyName(pair.getFirst());
                  ex.setPropertyValue(pair.getSecond());
                  throw ex;
                }
              }
              else if (errorCode.equals("E4001"))
              {
                String attrLen;
                Pattern p = Pattern.compile("but given length was ([0-9]*)\\.");
                Matcher m = p.matcher(message);
                if (m.find())
                {
                  attrLen = m.group(1);
                }
                else
                {
                  // If we get some weird message back that doesn't match our regex
                  DHIS2UnexpectedResponseException ex = new DHIS2UnexpectedResponseException();
                  ex.setDhis2Response(message);
                  throw ex;
                }
                
                
                DHIS2AttributeLengthException ex = new DHIS2AttributeLengthException();
                ex.setAttrLen(attrLen);
                throw ex;
              }
              else
              {
                DHIS2UnexpectedResponseException ex = new DHIS2UnexpectedResponseException();
                ex.setDhis2Response(message);
                throw ex;
              }
            }
          }
        }
      }
    }
    
    if (errorOnAlreadyExists && attrErrs.size() > 0)
    {
      Set<String> attrNames = new HashSet<String>();
      for (String msg : attrErrs)
      {
        Pair<String, String> pair = parseDuplicateDataMsg(msg);
        
        String attrName = pair.getSecond();
        
        if (attrName != null)
        {
          attrNames.add(attrName);
        }
      }
      
      DHIS2DuplicateAttributeException ex = new DHIS2DuplicateAttributeException();
      ex.setDhis2Attrs(StringUtils.join(attrNames, ", "));
      throw ex;
    }
  }
  
  /**
   * Parses the DHIS2 duplicate data message and returns a pair where the property name is first and the property value is second.
   */
  public static Pair<String, String> parseDuplicateDataMsg(String msg)
  {
    // Property `name` with value `AGYW’s surname` on object AGYW’s surname [imcsp063qb8] (OptionSet) already exists on object irje6etwqpy.
    // A [option set] already exists with name [AGYW’s surname]
    
    // This pattern contains non breaking space so just be aware that you can have it not match because sometimes what looks like a space is not actually a space
    Pattern p = Pattern.compile("Property `(.*?)` with value `(.*?)` on object (.*?) already exists on object (.*?)\\.");
    Matcher m = p.matcher(msg);
    
    if (m.find())
    {
      String propertyName = m.group(1);
      String propertyValue = m.group(2);
      
      return new Pair<String, String>(propertyName, propertyValue);
    }
    else
    {
      // If we get some weird message back that doesn't match our regex
      DHIS2UnexpectedResponseException ex = new DHIS2UnexpectedResponseException();
      ex.setDhis2Response(msg);
      throw ex;
    }
  }
}