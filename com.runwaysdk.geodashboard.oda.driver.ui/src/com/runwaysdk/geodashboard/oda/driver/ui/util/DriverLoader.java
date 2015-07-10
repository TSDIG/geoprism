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
package com.runwaysdk.geodashboard.oda.driver.ui.util;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.OdaException;

import com.runwaysdk.geodashboard.oda.driver.Connection;
import com.runwaysdk.geodashboard.oda.driver.ui.ssl.SecureKeystoreManager;

public class DriverLoader
{

  private DriverLoader()
  {
  }

  public static Connection getConnection(Properties props) throws OdaException
  {
    /*
     * Before a connection can be attempted we must ensure that the SSLContext has been configured
     */
    SecureKeystoreManager.getInstance().configureSSLContext();

    Connection connection = new Connection();
    connection.open(props);

    return connection;
  }

  public static void testConnection(String url, String userName, String password, Properties props) throws OdaException
  {
    props.setProperty(Constants.ODAURL, url);
    props.setProperty(Constants.ODAUser, userName);
    props.setProperty(Constants.ODAPassword, password);

    Connection connection = DriverLoader.getConnection(props);
    connection.close();
  }

}
