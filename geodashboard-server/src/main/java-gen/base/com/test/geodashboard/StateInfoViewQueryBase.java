package com.test.geodashboard;

@com.runwaysdk.business.ClassSignature(hash = 1754242141)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to StateInfoView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class StateInfoViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public StateInfoViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public StateInfoViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return com.test.geodashboard.StateInfoView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(com.test.geodashboard.StateInfoView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(com.test.geodashboard.StateInfoView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getViewRank()
  {
    return getViewRank(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getViewRank(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(com.test.geodashboard.StateInfoView.VIEWRANK, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getViewRank(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(com.test.geodashboard.StateInfoView.VIEWRANK, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDouble getViewRatio()
  {
    return getViewRatio(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getViewRatio(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(com.test.geodashboard.StateInfoView.VIEWRATIO, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getViewRatio(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(com.test.geodashboard.StateInfoView.VIEWRATIO, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends StateInfoView> getIterator()
  {
    com.runwaysdk.query.ValueIterator valueIterator;
    if (_pageSize != null && _pageNumber != null)
    {
      valueIterator = (com.runwaysdk.query.ValueIterator<com.runwaysdk.dataaccess.ValueObject>)this.getComponentQuery().getIterator(_pageSize, _pageNumber);
    }
    else
    {
      valueIterator = (com.runwaysdk.query.ValueIterator<com.runwaysdk.dataaccess.ValueObject>)this.getComponentQuery().getIterator();
    }
    return new com.runwaysdk.query.ViewIterator<StateInfoView>(this.getMdClassIF(), valueIterator);
  }

}
