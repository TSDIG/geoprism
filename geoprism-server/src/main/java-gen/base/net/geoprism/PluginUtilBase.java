package net.geoprism;

@com.runwaysdk.business.ClassSignature(hash = 356278871)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PluginUtil.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class PluginUtilBase extends com.runwaysdk.business.Util implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "net.geoprism.PluginUtil";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = 356278871;
  
  public PluginUtilBase()
  {
    super();
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(net.geoprism.PluginUtil.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static PluginUtil get(String id)
  {
    return (PluginUtil) com.runwaysdk.business.Util.get(id);
  }
  
  public static java.lang.Boolean isDHIS2Enabled()
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.PluginUtil.java";
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
