package com.runwaysdk.geodashboard;

@com.runwaysdk.business.ClassSignature(hash = -283334720)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to RoleView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class RoleViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "com.runwaysdk.geodashboard.RoleView";
  public static java.lang.String ASSIGNED = "assigned";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String GROUPNAME = "groupName";
  public static java.lang.String ID = "id";
  public static java.lang.String ROLEID = "roleId";
  private static final long serialVersionUID = -283334720;
  
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getAssignedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.RoleView.CLASS);
    return mdClassIF.definesAttribute(ASSIGNED);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getDisplayLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.RoleView.CLASS);
    return mdClassIF.definesAttribute(DISPLAYLABEL);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getGroupNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.RoleView.CLASS);
    return mdClassIF.definesAttribute(GROUPNAME);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.RoleView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getRoleId()
  {
    return getValue(ROLEID);
  }
  
  public void validateRoleId()
  {
    this.validateAttribute(ROLEID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getRoleIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.RoleView.CLASS);
    return mdClassIF.definesAttribute(ROLEID);
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
  
  public static com.runwaysdk.geodashboard.RoleView[] getAdminRoles(com.runwaysdk.geodashboard.GeodashboardUser user)
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.RoleView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.geodashboard.RoleView[] getDashboardRoles(com.runwaysdk.geodashboard.GeodashboardUser user)
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.RoleView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.geodashboard.RoleView[] getRoles(com.runwaysdk.geodashboard.GeodashboardUser user)
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.RoleView.java";
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
