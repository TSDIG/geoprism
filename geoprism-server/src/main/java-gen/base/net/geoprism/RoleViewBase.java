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
package net.geoprism;

@com.runwaysdk.business.ClassSignature(hash = -1157655686)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to RoleView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class RoleViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "net.geoprism.RoleView";
  public static java.lang.String ASSIGNED = "assigned";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String GROUPNAME = "groupName";
  public static java.lang.String ID = "id";
  public static java.lang.String ROLEID = "roleId";
  private static final long serialVersionUID = -1157655686;
  
  public RoleViewBase()
  {
    super();
  }
  
  public Boolean getAssigned()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ASSIGNED));
  }
  
  public void validateAssigned()
  {
    this.validateAttribute(ASSIGNED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF getAssignedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.RoleView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF)mdClassIF.definesAttribute(ASSIGNED);
  }
  
  public void setAssigned(Boolean value)
  {
    if(value == null)
    {
      setValue(ASSIGNED, "");
    }
    else
    {
      setValue(ASSIGNED, java.lang.Boolean.toString(value));
    }
  }
  
  public String getDisplayLabel()
  {
    return getValue(DISPLAYLABEL);
  }
  
  public void validateDisplayLabel()
  {
    this.validateAttribute(DISPLAYLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeTextDAOIF getDisplayLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.RoleView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeTextDAOIF)mdClassIF.definesAttribute(DISPLAYLABEL);
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
  
  public String getGroupName()
  {
    return getValue(GROUPNAME);
  }
  
  public void validateGroupName()
  {
    this.validateAttribute(GROUPNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeTextDAOIF getGroupNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.RoleView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeTextDAOIF)mdClassIF.definesAttribute(GROUPNAME);
  }
  
  public void setGroupName(String value)
  {
    if(value == null)
    {
      setValue(GROUPNAME, "");
    }
    else
    {
      setValue(GROUPNAME, value);
    }
  }
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.RoleView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public String getRoleId()
  {
    return getValue(ROLEID);
  }
  
  public void validateRoleId()
  {
    this.validateAttribute(ROLEID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getRoleIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.RoleView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ROLEID);
  }
  
  public void setRoleId(String value)
  {
    if(value == null)
    {
      setValue(ROLEID, "");
    }
    else
    {
      setValue(ROLEID, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static RoleView get(String id)
  {
    return (RoleView) com.runwaysdk.business.View.get(id);
  }
  
  public static net.geoprism.RoleView[] getAdminRoles(net.geoprism.GeoprismUser user)
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.RoleView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.lang.String getCurrentRoles()
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.RoleView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static net.geoprism.RoleView[] getDashboardRoles(net.geoprism.GeoprismUser user)
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.RoleView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static net.geoprism.RoleView[] getRoles(net.geoprism.GeoprismUser user)
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.RoleView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
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
