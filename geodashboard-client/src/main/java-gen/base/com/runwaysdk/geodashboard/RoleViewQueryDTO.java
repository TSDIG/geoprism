package com.runwaysdk.geodashboard;

@com.runwaysdk.business.ClassSignature(hash = 378413722)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to RoleView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public class RoleViewQueryDTO extends com.runwaysdk.business.ViewQueryDTO
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 378413722;

  protected RoleViewQueryDTO(String type)
  {
    super(type);
  }

@SuppressWarnings("unchecked")
public java.util.List<? extends com.runwaysdk.geodashboard.RoleViewDTO> getResultSet()
{
  return (java.util.List<? extends com.runwaysdk.geodashboard.RoleViewDTO>)super.getResultSet();
}
}