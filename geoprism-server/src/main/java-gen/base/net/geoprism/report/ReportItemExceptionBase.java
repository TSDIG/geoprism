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
package net.geoprism.report;

@com.runwaysdk.business.ClassSignature(hash = 609565586)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ReportItemException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class ReportItemExceptionBase extends com.runwaysdk.business.SmartException 
{
  public final static String CLASS = "net.geoprism.report.ReportItemException";
  public static java.lang.String OID = "oid";
  private static final long serialVersionUID = 609565586;
  
  public ReportItemExceptionBase()
  {
    super();
  }
  
  public ReportItemExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public ReportItemExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public ReportItemExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getOid()
  {
    return getValue(OID);
  }
  
  public void validateId()
  {
    this.validateAttribute(OID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getOidMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.report.ReportItemException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(OID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{oid}", this.getOid());
    return message;
  }
  
}
