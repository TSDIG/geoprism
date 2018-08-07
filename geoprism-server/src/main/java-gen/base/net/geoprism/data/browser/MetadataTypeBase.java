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
package net.geoprism.data.browser;

@com.runwaysdk.business.ClassSignature(hash = -1371635942)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MetadataType.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class MetadataTypeBase extends com.runwaysdk.business.View 
{
  public final static String CLASS = "net.geoprism.data.browser.MetadataType";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String OID = "oid";
  public static java.lang.String PARENTTYPEID = "parentTypeId";
  public static java.lang.String TYPEID = "typeId";
  public static java.lang.String TYPENAME = "typeName";
  public static java.lang.String TYPEPACKAGE = "typePackage";
  private static final long serialVersionUID = -1371635942;
  
  public MetadataTypeBase()
  {
    super();
  }
  
  public String getDisplayLabel()
  {
    return getValue(DISPLAYLABEL);
  }
  
  public void validateDisplayLabel()
  {
    this.validateAttribute(DISPLAYLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getDisplayLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.data.browser.MetadataType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(DISPLAYLABEL);
  }
  
  public void setDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(DISPLAYLABEL, "");
    }
    else
    {
      setValue(DISPLAYLABEL, value);
    }
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.data.browser.MetadataType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(OID);
  }
  
  public String getParentTypeId()
  {
    return getValue(PARENTTYPEID);
  }
  
  public void validateParentTypeId()
  {
    this.validateAttribute(PARENTTYPEID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getParentTypeIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.data.browser.MetadataType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(PARENTTYPEID);
  }
  
  public void setParentTypeId(String value)
  {
    if(value == null)
    {
      setValue(PARENTTYPEID, "");
    }
    else
    {
      setValue(PARENTTYPEID, value);
    }
  }
  
  public String getTypeId()
  {
    return getValue(TYPEID);
  }
  
  public void validateTypeId()
  {
    this.validateAttribute(TYPEID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getTypeIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.data.browser.MetadataType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(TYPEID);
  }
  
  public void setTypeId(String value)
  {
    if(value == null)
    {
      setValue(TYPEID, "");
    }
    else
    {
      setValue(TYPEID, value);
    }
  }
  
  public String getTypeName()
  {
    return getValue(TYPENAME);
  }
  
  public void validateTypeName()
  {
    this.validateAttribute(TYPENAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getTypeNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.data.browser.MetadataType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(TYPENAME);
  }
  
  public void setTypeName(String value)
  {
    if(value == null)
    {
      setValue(TYPENAME, "");
    }
    else
    {
      setValue(TYPENAME, value);
    }
  }
  
  public String getTypePackage()
  {
    return getValue(TYPEPACKAGE);
  }
  
  public void validateTypePackage()
  {
    this.validateAttribute(TYPEPACKAGE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getTypePackageMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.data.browser.MetadataType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(TYPEPACKAGE);
  }
  
  public void setTypePackage(String value)
  {
    if(value == null)
    {
      setValue(TYPEPACKAGE, "");
    }
    else
    {
      setValue(TYPEPACKAGE, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static MetadataType get(String oid)
  {
    return (MetadataType) com.runwaysdk.business.View.get(oid);
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
