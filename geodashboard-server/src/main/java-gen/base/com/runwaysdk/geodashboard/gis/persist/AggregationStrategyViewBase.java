package com.runwaysdk.geodashboard.gis.persist;

@com.runwaysdk.business.ClassSignature(hash = 1285857495)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AggregationStrategyView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class AggregationStrategyViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "com.runwaysdk.geodashboard.gis.persist.AggregationStrategyView";
  public static java.lang.String AGGREGATIONTYPE = "aggregationType";
  public static java.lang.String AVAILABLEGEOMETRYTYPES = "availableGeometryTypes";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String VALUE = "value";
  private static final long serialVersionUID = 1285857495;
  
  public AggregationStrategyViewBase()
  {
    super();
  }
  
  public String getAggregationType()
  {
    return getValue(AGGREGATIONTYPE);
  }
  
  public void validateAggregationType()
  {
    this.validateAttribute(AGGREGATIONTYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeTextDAOIF getAggregationTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.gis.persist.AggregationStrategyView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeTextDAOIF)mdClassIF.definesAttribute(AGGREGATIONTYPE);
  }
  
  public void setAggregationType(String value)
  {
    if(value == null)
    {
      setValue(AGGREGATIONTYPE, "");
    }
    else
    {
      setValue(AGGREGATIONTYPE, value);
    }
  }
  
  public String getAvailableGeometryTypes()
  {
    return getValue(AVAILABLEGEOMETRYTYPES);
  }
  
  public void validateAvailableGeometryTypes()
  {
    this.validateAttribute(AVAILABLEGEOMETRYTYPES);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeTextDAOIF getAvailableGeometryTypesMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.gis.persist.AggregationStrategyView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeTextDAOIF)mdClassIF.definesAttribute(AVAILABLEGEOMETRYTYPES);
  }
  
  public void setAvailableGeometryTypes(String value)
  {
    if(value == null)
    {
      setValue(AVAILABLEGEOMETRYTYPES, "");
    }
    else
    {
      setValue(AVAILABLEGEOMETRYTYPES, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.gis.persist.AggregationStrategyView.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.gis.persist.AggregationStrategyView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public String getValue()
  {
    return getValue(VALUE);
  }
  
  public void validateValue()
  {
    this.validateAttribute(VALUE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeTextDAOIF getValueMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.gis.persist.AggregationStrategyView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeTextDAOIF)mdClassIF.definesAttribute(VALUE);
  }
  
  public void setValue(String value)
  {
    if(value == null)
    {
      setValue(VALUE, "");
    }
    else
    {
      setValue(VALUE, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static AggregationStrategyView get(String id)
  {
    return (AggregationStrategyView) com.runwaysdk.business.View.get(id);
  }
  
  public static com.runwaysdk.geodashboard.gis.persist.AggregationStrategyView[] getAggregationStrategies(java.lang.String nodeId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.gis.persist.AggregationStrategyView.java";
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
