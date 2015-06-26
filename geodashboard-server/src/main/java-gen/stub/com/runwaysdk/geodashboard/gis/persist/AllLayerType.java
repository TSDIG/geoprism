package com.runwaysdk.geodashboard.gis.persist;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by RunwaySDK
 */
@com.runwaysdk.business.ClassSignature(hash = -1044860572)
public enum AllLayerType implements com.runwaysdk.business.BusinessEnumeration, com.runwaysdk.generation.loader.Reloadable
{
  BASIC(),
  
  BASICPOINT(),
  
  BASICPOLYGON(),
  
  BUBBLE(),
  
  CATEGORY(),
  
  CATEGORYPOINT(),
  
  CATEGORYPOLYGON(),
  
  GRADIENT(),
  
  GRADIENTPOINT(),
  
  GRADIENTPOLYGON();
  
  public static final java.lang.String CLASS = "com.runwaysdk.geodashboard.gis.persist.AllLayerType";
  private com.runwaysdk.geodashboard.gis.persist.LayerType enumeration;
  
  private synchronized void loadEnumeration()
  {
    com.runwaysdk.geodashboard.gis.persist.LayerType enu = com.runwaysdk.geodashboard.gis.persist.LayerType.getEnumeration(this.name());
    setEnumeration(enu);
  }
  
  private synchronized void setEnumeration(com.runwaysdk.geodashboard.gis.persist.LayerType enumeration)
  {
    this.enumeration = enumeration;
  }
  
  public java.lang.String getId()
  {
    loadEnumeration();
    return enumeration.getId();
  }
  
  public java.lang.String getEnumName()
  {
    loadEnumeration();
    return enumeration.getEnumName();
  }
  
  public java.lang.String getDisplayLabel()
  {
    loadEnumeration();
    return enumeration.getDisplayLabel().getValue(com.runwaysdk.session.Session.getCurrentLocale());
  }
  
  public static AllLayerType get(String id)
  {
    for (AllLayerType e : AllLayerType.values())
    {
      if (e.getId().equals(id))
      {
        return e;
      }
    }
    return null;
  }
  
}
