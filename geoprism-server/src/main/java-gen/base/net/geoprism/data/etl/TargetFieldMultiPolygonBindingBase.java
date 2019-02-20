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

@com.runwaysdk.business.ClassSignature(hash = -1062841285)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TargetFieldMultiPolygonBinding.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class TargetFieldMultiPolygonBindingBase extends net.geoprism.data.etl.TargetFieldCoordinateBinding 
{
  public final static String CLASS = "net.geoprism.data.etl.TargetFieldMultiPolygonBinding";
  private static final long serialVersionUID = -1062841285;
  
  public TargetFieldMultiPolygonBindingBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static TargetFieldMultiPolygonBindingQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    TargetFieldMultiPolygonBindingQuery query = new TargetFieldMultiPolygonBindingQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static TargetFieldMultiPolygonBinding get(String oid)
  {
    return (TargetFieldMultiPolygonBinding) com.runwaysdk.business.Business.get(oid);
  }
  
  public static TargetFieldMultiPolygonBinding getByKey(String key)
  {
    return (TargetFieldMultiPolygonBinding) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static TargetFieldMultiPolygonBinding lock(java.lang.String oid)
  {
    TargetFieldMultiPolygonBinding _instance = TargetFieldMultiPolygonBinding.get(oid);
    _instance.lock();
    
    return _instance;
  }
  
  public static TargetFieldMultiPolygonBinding unlock(java.lang.String oid)
  {
    TargetFieldMultiPolygonBinding _instance = TargetFieldMultiPolygonBinding.get(oid);
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
