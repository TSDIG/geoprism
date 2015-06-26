package com.runwaysdk.geodashboard.gis.persist;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by RunwaySDK
 */
@com.runwaysdk.business.ClassSignature(hash = 309874916)
public enum AllLayerTypeDTO implements com.runwaysdk.business.EnumerationDTOIF, com.runwaysdk.generation.loader.Reloadable
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
  
  public final static String CLASS = "com.runwaysdk.geodashboard.gis.persist.AllLayerType";
  
  
  public com.runwaysdk.geodashboard.gis.persist.LayerTypeDTO item(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    return (com.runwaysdk.geodashboard.gis.persist.LayerTypeDTO) clientRequest.getEnumeration(com.runwaysdk.geodashboard.gis.persist.AllLayerTypeDTO.CLASS, this.name());
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<com.runwaysdk.geodashboard.gis.persist.LayerTypeDTO> items(com.runwaysdk.constants.ClientRequestIF clientRequest, AllLayerTypeDTO ... items)
  {
    java.lang.String[] itemNames = new java.lang.String[items.length];
    for(int i=0; i<items.length; i++)
    {
      itemNames[i] = items[i].name();
    }
    return (java.util.List<com.runwaysdk.geodashboard.gis.persist.LayerTypeDTO>) clientRequest.getEnumerations(com.runwaysdk.geodashboard.gis.persist.AllLayerTypeDTO.CLASS, itemNames);
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<com.runwaysdk.geodashboard.gis.persist.LayerTypeDTO> allItems(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    return (java.util.List<com.runwaysdk.geodashboard.gis.persist.LayerTypeDTO>) clientRequest.getAllEnumerations(com.runwaysdk.geodashboard.gis.persist.AllLayerTypeDTO.CLASS);
  }
  
  public java.lang.String getName()
  {
    return this.name();
  }
}
