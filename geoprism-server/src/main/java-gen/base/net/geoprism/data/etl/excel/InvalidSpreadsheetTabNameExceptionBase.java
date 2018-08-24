package net.geoprism.data.etl.excel;

@com.runwaysdk.business.ClassSignature(hash = -209147927)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidSpreadsheetTabNameException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class InvalidSpreadsheetTabNameExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "net.geoprism.data.etl.excel.InvalidSpreadsheetTabNameException";
  public static java.lang.String ID = "id";
  public static java.lang.String TABLABEL = "tabLabel";
  private static final long serialVersionUID = -209147927;
  
  public InvalidSpreadsheetTabNameExceptionBase()
  {
    super();
  }
  
  public InvalidSpreadsheetTabNameExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public InvalidSpreadsheetTabNameExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public InvalidSpreadsheetTabNameExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.data.etl.excel.InvalidSpreadsheetTabNameException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public String getTabLabel()
  {
    return getValue(TABLABEL);
  }
  
  public void validateTabLabel()
  {
    this.validateAttribute(TABLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeTextDAOIF getTabLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.data.etl.excel.InvalidSpreadsheetTabNameException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeTextDAOIF)mdClassIF.definesAttribute(TABLABEL);
  }
  
  public void setTabLabel(String value)
  {
    if(value == null)
    {
      setValue(TABLABEL, "");
    }
    else
    {
      setValue(TABLABEL, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{id}", this.getId());
    message = replace(message, "{tabLabel}", this.getTabLabel());
    return message;
  }
  
}
