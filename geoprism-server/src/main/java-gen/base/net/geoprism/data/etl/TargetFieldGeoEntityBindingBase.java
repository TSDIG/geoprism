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
package net.geoprism.data.etl;

@com.runwaysdk.business.ClassSignature(hash = 1680641141)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TargetFieldGeoEntityBinding.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class TargetFieldGeoEntityBindingBase extends net.geoprism.data.etl.TargetFieldBinding 
{
  public final static String CLASS = "net.geoprism.data.etl.TargetFieldGeoEntityBinding";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String LATITUDEATTRIBUTENAME = "latitudeAttributeName";
  public static java.lang.String LONGITUDEATTRIBUTENAME = "longitudeAttributeName";
  public static java.lang.String USECOORDINATESFORLOCATIONASSIGNMENT = "useCoordinatesForLocationAssignment";
  private static final long serialVersionUID = 1680641141;
  
  public TargetFieldGeoEntityBindingBase()
  {
    super();
  }
  
  public com.runwaysdk.system.gis.geo.GeoEntity getGeoEntity()
  {
    if (getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.gis.geo.GeoEntity.get(getValue(GEOENTITY));
    }
  }
  
  public String getGeoEntityId()
  {
    return getValue(GEOENTITY);
  }
  
  public void validateGeoEntity()
  {
    this.validateAttribute(GEOENTITY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getGeoEntityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.data.etl.TargetFieldGeoEntityBinding.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(GEOENTITY);
  }
  
  public void setGeoEntity(com.runwaysdk.system.gis.geo.GeoEntity value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value.getOid());
    }
  }
  
  public String getLatitudeAttributeName()
  {
    return getValue(LATITUDEATTRIBUTENAME);
  }
  
  public void validateLatitudeAttributeName()
  {
    this.validateAttribute(LATITUDEATTRIBUTENAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getLatitudeAttributeNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.data.etl.TargetFieldGeoEntityBinding.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(LATITUDEATTRIBUTENAME);
  }
  
  public void setLatitudeAttributeName(String value)
  {
    if(value == null)
    {
      setValue(LATITUDEATTRIBUTENAME, "");
    }
    else
    {
      setValue(LATITUDEATTRIBUTENAME, value);
    }
  }
  
  public String getLongitudeAttributeName()
  {
    return getValue(LONGITUDEATTRIBUTENAME);
  }
  
  public void validateLongitudeAttributeName()
  {
    this.validateAttribute(LONGITUDEATTRIBUTENAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getLongitudeAttributeNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.data.etl.TargetFieldGeoEntityBinding.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(LONGITUDEATTRIBUTENAME);
  }
  
  public void setLongitudeAttributeName(String value)
  {
    if(value == null)
    {
      setValue(LONGITUDEATTRIBUTENAME, "");
    }
    else
    {
      setValue(LONGITUDEATTRIBUTENAME, value);
    }
  }
  
  public Boolean getUseCoordinatesForLocationAssignment()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(USECOORDINATESFORLOCATIONASSIGNMENT));
  }
  
  public void validateUseCoordinatesForLocationAssignment()
  {
    this.validateAttribute(USECOORDINATESFORLOCATIONASSIGNMENT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF getUseCoordinatesForLocationAssignmentMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.data.etl.TargetFieldGeoEntityBinding.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF)mdClassIF.definesAttribute(USECOORDINATESFORLOCATIONASSIGNMENT);
  }
  
  public void setUseCoordinatesForLocationAssignment(Boolean value)
  {
    if(value == null)
    {
      setValue(USECOORDINATESFORLOCATIONASSIGNMENT, "");
    }
    else
    {
      setValue(USECOORDINATESFORLOCATIONASSIGNMENT, java.lang.Boolean.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static TargetFieldGeoEntityBindingQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    TargetFieldGeoEntityBindingQuery query = new TargetFieldGeoEntityBindingQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static TargetFieldGeoEntityBinding get(String oid)
  {
    return (TargetFieldGeoEntityBinding) com.runwaysdk.business.Business.get(oid);
  }
  
  public static TargetFieldGeoEntityBinding getByKey(String key)
  {
    return (TargetFieldGeoEntityBinding) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static TargetFieldGeoEntityBinding lock(java.lang.String oid)
  {
    TargetFieldGeoEntityBinding _instance = TargetFieldGeoEntityBinding.get(oid);
    _instance.lock();
    
    return _instance;
  }
  
  public static TargetFieldGeoEntityBinding unlock(java.lang.String oid)
  {
    TargetFieldGeoEntityBinding _instance = TargetFieldGeoEntityBinding.get(oid);
    _instance.unlock();
    
    return _instance;
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
