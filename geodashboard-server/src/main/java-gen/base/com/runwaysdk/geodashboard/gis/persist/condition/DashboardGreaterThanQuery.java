package com.runwaysdk.geodashboard.gis.persist.condition;

@com.runwaysdk.business.ClassSignature(hash = -1868469060)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DashboardGreaterThan.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  class DashboardGreaterThanQuery extends com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitiveQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public DashboardGreaterThanQuery(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public DashboardGreaterThanQuery(com.runwaysdk.query.ValueQuery valueQuery)
  {
    super(valueQuery);
    if (this.getComponentQuery() == null)
    {
      com.runwaysdk.business.BusinessQuery businessQuery = new com.runwaysdk.business.BusinessQuery(valueQuery, this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public String getClassType()
  {
    return com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThan.CLASS;
  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.runwaysdk.query.OIterator<? extends DashboardGreaterThan> getIterator()
  {
    this.checkNotUsedInValueQuery();
    String sqlStmt;
    if (_limit != null && _skip != null)
    {
      sqlStmt = this.getComponentQuery().getSQL(_limit, _skip);
    }
    else
    {
      sqlStmt = this.getComponentQuery().getSQL();
    }
    java.util.Map<String, com.runwaysdk.query.ColumnInfo> columnInfoMap = this.getComponentQuery().getColumnInfoMap();

    java.sql.ResultSet results = com.runwaysdk.dataaccess.database.Database.query(sqlStmt);
    return new com.runwaysdk.business.BusinessIterator<DashboardGreaterThan>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface DashboardGreaterThanQueryReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitiveQuery.DashboardPrimitiveQueryReferenceIF
  {


    public com.runwaysdk.query.BasicCondition EQ(com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThan dashboardGreaterThan);

    public com.runwaysdk.query.BasicCondition NE(com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThan dashboardGreaterThan);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class DashboardGreaterThanQueryReference extends com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitiveQuery.DashboardPrimitiveQueryReference
 implements DashboardGreaterThanQueryReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public DashboardGreaterThanQueryReference(com.runwaysdk.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.runwaysdk.query.BasicCondition EQ(com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThan dashboardGreaterThan)
    {
      if(dashboardGreaterThan == null) return this.EQ((java.lang.String)null);
      return this.EQ(dashboardGreaterThan.getId());
    }

    public com.runwaysdk.query.BasicCondition NE(com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThan dashboardGreaterThan)
    {
      if(dashboardGreaterThan == null) return this.NE((java.lang.String)null);
      return this.NE(dashboardGreaterThan.getId());
    }

  }

/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface DashboardGreaterThanQueryMultiReferenceIF extends com.runwaysdk.generation.loader.Reloadable, com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitiveQuery.DashboardPrimitiveQueryMultiReferenceIF
  {


    public com.runwaysdk.query.Condition containsAny(com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThan ... dashboardGreaterThan);
    public com.runwaysdk.query.Condition notContainsAny(com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThan ... dashboardGreaterThan);
    public com.runwaysdk.query.Condition containsAll(com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThan ... dashboardGreaterThan);
    public com.runwaysdk.query.Condition notContainsAll(com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThan ... dashboardGreaterThan);
    public com.runwaysdk.query.Condition containsExactly(com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThan ... dashboardGreaterThan);
  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class DashboardGreaterThanQueryMultiReference extends com.runwaysdk.geodashboard.gis.persist.condition.DashboardPrimitiveQuery.DashboardPrimitiveQueryMultiReference
 implements DashboardGreaterThanQueryMultiReferenceIF
, com.runwaysdk.generation.loader.Reloadable
  {

  public DashboardGreaterThanQueryMultiReference(com.runwaysdk.dataaccess.MdAttributeMultiReferenceDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, String mdMultiReferenceTableName, com.runwaysdk.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.runwaysdk.query.ComponentQuery rootQuery, java.util.Set<com.runwaysdk.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, mdMultiReferenceTableName, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }



    public com.runwaysdk.query.Condition containsAny(com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThan ... dashboardGreaterThan)  {

      String[] itemIdArray = new String[dashboardGreaterThan.length]; 

      for (int i=0; i<dashboardGreaterThan.length; i++)
      {
        itemIdArray[i] = dashboardGreaterThan[i].getId();
      }

      return this.containsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAny(com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThan ... dashboardGreaterThan)  {

      String[] itemIdArray = new String[dashboardGreaterThan.length]; 

      for (int i=0; i<dashboardGreaterThan.length; i++)
      {
        itemIdArray[i] = dashboardGreaterThan[i].getId();
      }

      return this.notContainsAny(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsAll(com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThan ... dashboardGreaterThan)  {

      String[] itemIdArray = new String[dashboardGreaterThan.length]; 

      for (int i=0; i<dashboardGreaterThan.length; i++)
      {
        itemIdArray[i] = dashboardGreaterThan[i].getId();
      }

      return this.containsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition notContainsAll(com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThan ... dashboardGreaterThan)  {

      String[] itemIdArray = new String[dashboardGreaterThan.length]; 

      for (int i=0; i<dashboardGreaterThan.length; i++)
      {
        itemIdArray[i] = dashboardGreaterThan[i].getId();
      }

      return this.notContainsAll(itemIdArray);
  }

    public com.runwaysdk.query.Condition containsExactly(com.runwaysdk.geodashboard.gis.persist.condition.DashboardGreaterThan ... dashboardGreaterThan)  {

      String[] itemIdArray = new String[dashboardGreaterThan.length]; 

      for (int i=0; i<dashboardGreaterThan.length; i++)
      {
        itemIdArray[i] = dashboardGreaterThan[i].getId();
      }

      return this.containsExactly(itemIdArray);
  }
  }
}