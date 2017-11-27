package net.geoprism.email;

@com.runwaysdk.business.ClassSignature(hash = 1711803678)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidEmailSettings.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class InvalidEmailSettingsBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "net.geoprism.email.InvalidEmailSettings";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = 1711803678;
  
  public InvalidEmailSettingsBase()
  {
    super();
  }
  
  public InvalidEmailSettingsBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public InvalidEmailSettingsBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public InvalidEmailSettingsBase(java.lang.Throwable cause)
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.email.InvalidEmailSettings.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{id}", this.getId());
    return message;
  }
  
}
