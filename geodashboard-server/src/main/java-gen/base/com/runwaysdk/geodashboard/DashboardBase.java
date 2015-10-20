package com.runwaysdk.geodashboard;

@com.runwaysdk.business.ClassSignature(hash = -1283458302)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Dashboard.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DashboardBase extends com.runwaysdk.business.Business implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "com.runwaysdk.geodashboard.Dashboard";
  public static java.lang.String COUNTRY = "country";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DASHBOARDROLE = "dashboardRole";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  private com.runwaysdk.business.Struct displayLabel = null;
  
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FILTERDATE = "filterDate";
  public static java.lang.String FROMDATE = "fromDate";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String MAPTHUMBNAIL = "mapThumbnail";
  public static java.lang.String NAME = "name";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String REMOVABLE = "removable";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TODATE = "toDate";
  public static java.lang.String TYPE = "type";
  private static final long serialVersionUID = -1283458302;
  
  public DashboardBase()
  {
    super();
    displayLabel = super.getStruct("displayLabel");
  }
  
  public com.runwaysdk.system.gis.geo.GeoEntity getCountry()
  {
    if (getValue(COUNTRY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.gis.geo.GeoEntity.get(getValue(COUNTRY));
    }
  }
  
  public String getCountryId()
  {
    return getValue(COUNTRY);
  }
  
  public void validateCountry()
  {
    this.validateAttribute(COUNTRY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getCountryMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.Dashboard.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(COUNTRY);
  }
  
  public void setCountry(com.runwaysdk.system.gis.geo.GeoEntity value)
  {
    if(value == null)
    {
      setValue(COUNTRY, "");
    }
    else
    {
      setValue(COUNTRY, value.getId());
    }
  }
  
  public java.util.Date getCreateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
  }
  
  public void validateCreateDate()
  {
    this.validateAttribute(CREATEDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF getCreateDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.Dashboard.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF)mdClassIF.definesAttribute(CREATEDATE);
  }
  
  public com.runwaysdk.system.SingleActor getCreatedBy()
  {
    if (getValue(CREATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActor.get(getValue(CREATEDBY));
    }
  }
  
  public String getCreatedById()
  {
    return getValue(CREATEDBY);
  }
  
  public void validateCreatedBy()
  {
    this.validateAttribute(CREATEDBY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getCreatedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.Dashboard.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(CREATEDBY);
  }
  
  public com.runwaysdk.system.Roles getDashboardRole()
  {
    if (getValue(DASHBOARDROLE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.Roles.get(getValue(DASHBOARDROLE));
    }
  }
  
  public String getDashboardRoleId()
  {
    return getValue(DASHBOARDROLE);
  }
  
  public void validateDashboardRole()
  {
    this.validateAttribute(DASHBOARDROLE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getDashboardRoleMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.Dashboard.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(DASHBOARDROLE);
  }
  
  public void setDashboardRole(com.runwaysdk.system.Roles value)
  {
    if(value == null)
    {
      setValue(DASHBOARDROLE, "");
    }
    else
    {
      setValue(DASHBOARDROLE, value.getId());
    }
  }
  
  public com.runwaysdk.geodashboard.DashboardDisplayLabel getDisplayLabel()
  {
    return (com.runwaysdk.geodashboard.DashboardDisplayLabel) displayLabel;
  }
  
  public void validateDisplayLabel()
  {
    this.validateAttribute(DISPLAYLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeLocalCharacterDAOIF getDisplayLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.Dashboard.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeLocalCharacterDAOIF)mdClassIF.definesAttribute(DISPLAYLABEL);
  }
  
  public com.runwaysdk.system.metadata.MdDomain getEntityDomain()
  {
    if (getValue(ENTITYDOMAIN).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdDomain.get(getValue(ENTITYDOMAIN));
    }
  }
  
  public String getEntityDomainId()
  {
    return getValue(ENTITYDOMAIN);
  }
  
  public void validateEntityDomain()
  {
    this.validateAttribute(ENTITYDOMAIN);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getEntityDomainMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.Dashboard.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(ENTITYDOMAIN);
  }
  
  public void setEntityDomain(com.runwaysdk.system.metadata.MdDomain value)
  {
    if(value == null)
    {
      setValue(ENTITYDOMAIN, "");
    }
    else
    {
      setValue(ENTITYDOMAIN, value.getId());
    }
  }
  
  public com.runwaysdk.system.metadata.MdAttribute getFilterDate()
  {
    if (getValue(FILTERDATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdAttribute.get(getValue(FILTERDATE));
    }
  }
  
  public String getFilterDateId()
  {
    return getValue(FILTERDATE);
  }
  
  public void validateFilterDate()
  {
    this.validateAttribute(FILTERDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getFilterDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.Dashboard.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(FILTERDATE);
  }
  
  public void setFilterDate(com.runwaysdk.system.metadata.MdAttribute value)
  {
    if(value == null)
    {
      setValue(FILTERDATE, "");
    }
    else
    {
      setValue(FILTERDATE, value.getId());
    }
  }
  
  public java.util.Date getFromDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(FROMDATE));
  }
  
  public void validateFromDate()
  {
    this.validateAttribute(FROMDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateDAOIF getFromDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.Dashboard.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateDAOIF)mdClassIF.definesAttribute(FROMDATE);
  }
  
  public void setFromDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(FROMDATE, "");
    }
    else
    {
      setValue(FROMDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.Dashboard.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public String getKeyName()
  {
    return getValue(KEYNAME);
  }
  
  public void validateKeyName()
  {
    this.validateAttribute(KEYNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getKeyNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.Dashboard.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(KEYNAME);
  }
  
  public void setKeyName(String value)
  {
    if(value == null)
    {
      setValue(KEYNAME, "");
    }
    else
    {
      setValue(KEYNAME, value);
    }
  }
  
  public java.util.Date getLastUpdateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
  }
  
  public void validateLastUpdateDate()
  {
    this.validateAttribute(LASTUPDATEDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF getLastUpdateDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.Dashboard.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF)mdClassIF.definesAttribute(LASTUPDATEDATE);
  }
  
  public com.runwaysdk.system.SingleActor getLastUpdatedBy()
  {
    if (getValue(LASTUPDATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActor.get(getValue(LASTUPDATEDBY));
    }
  }
  
  public String getLastUpdatedById()
  {
    return getValue(LASTUPDATEDBY);
  }
  
  public void validateLastUpdatedBy()
  {
    this.validateAttribute(LASTUPDATEDBY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getLastUpdatedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.Dashboard.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(LASTUPDATEDBY);
  }
  
  public com.runwaysdk.system.Users getLockedBy()
  {
    if (getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.Users.get(getValue(LOCKEDBY));
    }
  }
  
  public String getLockedById()
  {
    return getValue(LOCKEDBY);
  }
  
  public void validateLockedBy()
  {
    this.validateAttribute(LOCKEDBY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getLockedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.Dashboard.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(LOCKEDBY);
  }
  
  public byte[] getMapThumbnail()
  {
    return getBlob(MAPTHUMBNAIL);
  }
  
  public void validateMapThumbnail()
  {
    this.validateAttribute(MAPTHUMBNAIL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeBlobDAOIF getMapThumbnailMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.Dashboard.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeBlobDAOIF)mdClassIF.definesAttribute(MAPTHUMBNAIL);
  }
  
  public void setMapThumbnail(byte[] value)
  {
    if(value == null)
    {
      setValue(MAPTHUMBNAIL, "");
    }
    else
    {
      setBlob(MAPTHUMBNAIL, value);
    }
  }
  
  public String getName()
  {
    return getValue(NAME);
  }
  
  public void validateName()
  {
    this.validateAttribute(NAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.Dashboard.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(NAME);
  }
  
  public void setName(String value)
  {
    if(value == null)
    {
      setValue(NAME, "");
    }
    else
    {
      setValue(NAME, value);
    }
  }
  
  public com.runwaysdk.system.Actor getOwner()
  {
    if (getValue(OWNER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.Actor.get(getValue(OWNER));
    }
  }
  
  public String getOwnerId()
  {
    return getValue(OWNER);
  }
  
  public void validateOwner()
  {
    this.validateAttribute(OWNER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getOwnerMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.Dashboard.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(OWNER);
  }
  
  public void setOwner(com.runwaysdk.system.Actor value)
  {
    if(value == null)
    {
      setValue(OWNER, "");
    }
    else
    {
      setValue(OWNER, value.getId());
    }
  }
  
  public Boolean getRemovable()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(REMOVABLE));
  }
  
  public void validateRemovable()
  {
    this.validateAttribute(REMOVABLE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF getRemovableMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.Dashboard.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF)mdClassIF.definesAttribute(REMOVABLE);
  }
  
  public void setRemovable(Boolean value)
  {
    if(value == null)
    {
      setValue(REMOVABLE, "");
    }
    else
    {
      setValue(REMOVABLE, java.lang.Boolean.toString(value));
    }
  }
  
  public Long getSeq()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SEQ));
  }
  
  public void validateSeq()
  {
    this.validateAttribute(SEQ);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeLongDAOIF getSeqMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.Dashboard.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeLongDAOIF)mdClassIF.definesAttribute(SEQ);
  }
  
  public String getSiteMaster()
  {
    return getValue(SITEMASTER);
  }
  
  public void validateSiteMaster()
  {
    this.validateAttribute(SITEMASTER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getSiteMasterMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.Dashboard.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(SITEMASTER);
  }
  
  public java.util.Date getToDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(TODATE));
  }
  
  public void validateToDate()
  {
    this.validateAttribute(TODATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateDAOIF getToDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.Dashboard.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateDAOIF)mdClassIF.definesAttribute(TODATE);
  }
  
  public void setToDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(TODATE, "");
    }
    else
    {
      setValue(TODATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public String getType()
  {
    return getValue(TYPE);
  }
  
  public void validateType()
  {
    this.validateAttribute(TYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(com.runwaysdk.geodashboard.Dashboard.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(TYPE);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static DashboardQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    DashboardQuery query = new DashboardQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public com.runwaysdk.geodashboard.DashboardMetadata addMetadata(com.runwaysdk.geodashboard.MetadataWrapper metadataWrapper)
  {
    return (com.runwaysdk.geodashboard.DashboardMetadata) addChild(metadataWrapper, com.runwaysdk.geodashboard.DashboardMetadata.CLASS);
  }
  
  public void removeMetadata(com.runwaysdk.geodashboard.MetadataWrapper metadataWrapper)
  {
    removeAllChildren(metadataWrapper, com.runwaysdk.geodashboard.DashboardMetadata.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends com.runwaysdk.geodashboard.MetadataWrapper> getAllMetadata()
  {
    return (com.runwaysdk.query.OIterator<? extends com.runwaysdk.geodashboard.MetadataWrapper>) getChildren(com.runwaysdk.geodashboard.DashboardMetadata.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends com.runwaysdk.geodashboard.DashboardMetadata> getAllMetadataRel()
  {
    return (com.runwaysdk.query.OIterator<? extends com.runwaysdk.geodashboard.DashboardMetadata>) getChildRelationships(com.runwaysdk.geodashboard.DashboardMetadata.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends com.runwaysdk.geodashboard.DashboardMetadata> getMetadataRel(com.runwaysdk.geodashboard.MetadataWrapper metadataWrapper)
  {
    return (com.runwaysdk.query.OIterator<? extends com.runwaysdk.geodashboard.DashboardMetadata>) getRelationshipsWithChild(metadataWrapper, com.runwaysdk.geodashboard.DashboardMetadata.CLASS);
  }
  
  public static Dashboard get(String id)
  {
    return (Dashboard) com.runwaysdk.business.Business.get(id);
  }
  
  public static Dashboard getByKey(String key)
  {
    return (Dashboard) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public void applyConditions(com.runwaysdk.geodashboard.gis.persist.condition.DashboardCondition[] conditions)
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void applyConditions(java.lang.String id, com.runwaysdk.geodashboard.gis.persist.condition.DashboardCondition[] conditions)
  {
    Dashboard _instance = Dashboard.get(id);
    _instance.applyConditions(conditions);
  }
  
  public void applyGlobalConditions(com.runwaysdk.geodashboard.gis.persist.condition.DashboardCondition[] conditions)
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void applyGlobalConditions(java.lang.String id, com.runwaysdk.geodashboard.gis.persist.condition.DashboardCondition[] conditions)
  {
    Dashboard _instance = Dashboard.get(id);
    _instance.applyGlobalConditions(conditions);
  }
  
  public void applyWithOptions(java.lang.String[] userIds, java.lang.String name)
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void applyWithOptions(java.lang.String id, java.lang.String[] userIds, java.lang.String name)
  {
    Dashboard _instance = Dashboard.get(id);
    _instance.applyWithOptions(userIds, name);
  }
  
  public static void assignUsers(java.lang.String dashboardId, java.lang.String[] userIds)
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public com.runwaysdk.geodashboard.Dashboard clone(java.lang.String name)
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final com.runwaysdk.geodashboard.Dashboard clone(java.lang.String id, java.lang.String name)
  {
    Dashboard _instance = Dashboard.get(id);
    return _instance.clone(name);
  }
  
  public static com.runwaysdk.geodashboard.Dashboard create(com.runwaysdk.geodashboard.Dashboard dto)
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public void generateThumbnailImage()
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void generateThumbnailImage(java.lang.String id)
  {
    Dashboard _instance = Dashboard.get(id);
    _instance.generateThumbnailImage();
  }
  
  public com.runwaysdk.geodashboard.GeodashboardUser[] getAllDashboardUsers()
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final com.runwaysdk.geodashboard.GeodashboardUser[] getAllDashboardUsers(java.lang.String id)
  {
    Dashboard _instance = Dashboard.get(id);
    return _instance.getAllDashboardUsers();
  }
  
  public java.lang.String getAllDashboardUsersJSON()
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.lang.String getAllDashboardUsersJSON(java.lang.String id)
  {
    Dashboard _instance = Dashboard.get(id);
    return _instance.getAllDashboardUsersJSON();
  }
  
  public static java.lang.String[] getCategoryInputSuggestions(java.lang.String mdAttributeId, java.lang.String geoNodeId, java.lang.String universalId, java.lang.String aggregationVal, java.lang.String text, java.lang.Integer limit, com.runwaysdk.geodashboard.gis.persist.condition.DashboardCondition[] conditions)
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.geodashboard.ontology.Classifier[] getClassifierRoots(java.lang.String mdAttributeId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.geodashboard.ontology.Classifier[] getClassifierSuggestions(java.lang.String mdAttributeId, java.lang.String text, java.lang.Integer limit)
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.lang.String getClassifierTree(java.lang.String mdAttributeId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public com.runwaysdk.geodashboard.gis.persist.condition.DashboardCondition[] getConditions()
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final com.runwaysdk.geodashboard.gis.persist.condition.DashboardCondition[] getConditions(java.lang.String id)
  {
    Dashboard _instance = Dashboard.get(id);
    return _instance.getConditions();
  }
  
  public java.lang.String getConditionsJSON()
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.lang.String getConditionsJSON(java.lang.String id)
  {
    Dashboard _instance = Dashboard.get(id);
    return _instance.getConditionsJSON();
  }
  
  public com.runwaysdk.query.ValueQuery getGeoEntitySuggestions(java.lang.String text, java.lang.Integer limit)
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final com.runwaysdk.query.ValueQuery getGeoEntitySuggestions(java.lang.String id, java.lang.String text, java.lang.Integer limit)
  {
    Dashboard _instance = Dashboard.get(id);
    return _instance.getGeoEntitySuggestions(text, limit);
  }
  
  public com.runwaysdk.system.gis.geo.GeoNode[] getGeoNodes(com.runwaysdk.system.metadata.MdAttribute thematicAttribute)
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final com.runwaysdk.system.gis.geo.GeoNode[] getGeoNodes(java.lang.String id, com.runwaysdk.system.metadata.MdAttribute thematicAttribute)
  {
    Dashboard _instance = Dashboard.get(id);
    return _instance.getGeoNodes(thematicAttribute);
  }
  
  public java.lang.String getMapId()
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.lang.String getMapId(java.lang.String id)
  {
    Dashboard _instance = Dashboard.get(id);
    return _instance.getMapId();
  }
  
  public static com.runwaysdk.geodashboard.DashboardQuery getSortedDashboards()
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public com.runwaysdk.system.metadata.MdClass[] getSortedTypes()
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final com.runwaysdk.system.metadata.MdClass[] getSortedTypes(java.lang.String id)
  {
    Dashboard _instance = Dashboard.get(id);
    return _instance.getSortedTypes();
  }
  
  public static java.lang.String[] getTextSuggestions(java.lang.String mdAttributeId, java.lang.String text, java.lang.Integer limit)
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public java.lang.Boolean hasAccess()
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.lang.Boolean hasAccess(java.lang.String id)
  {
    Dashboard _instance = Dashboard.get(id);
    return _instance.hasAccess();
  }
  
  public java.lang.Boolean hasReport()
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.lang.Boolean hasReport(java.lang.String id)
  {
    Dashboard _instance = Dashboard.get(id);
    return _instance.hasReport();
  }
  
  public void setBaseLayerState(java.lang.String baseLayerState)
  {
    String msg = "This method should never be invoked.  It should be overwritten in com.runwaysdk.geodashboard.Dashboard.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void setBaseLayerState(java.lang.String id, java.lang.String baseLayerState)
  {
    Dashboard _instance = Dashboard.get(id);
    _instance.setBaseLayerState(baseLayerState);
  }
  
  public static Dashboard lock(java.lang.String id)
  {
    Dashboard _instance = Dashboard.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static Dashboard unlock(java.lang.String id)
  {
    Dashboard _instance = Dashboard.get(id);
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
