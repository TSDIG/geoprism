/**
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.
 */
package com.runwaysdk.geodashboard.gis.persist;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.RunwayException;
import com.runwaysdk.business.SmartException;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.transaction.AbortIfProblem;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.geodashboard.SessionParameterFacade;
import com.runwaysdk.geodashboard.gis.geoserver.GeoserverBatch;
import com.runwaysdk.geodashboard.gis.geoserver.GeoserverFacade;
import com.runwaysdk.geodashboard.gis.geoserver.GeoserverProperties;
import com.runwaysdk.geodashboard.gis.model.FeatureStrategy;
import com.runwaysdk.geodashboard.gis.model.FeatureType;
import com.runwaysdk.geodashboard.gis.model.Layer;
import com.runwaysdk.geodashboard.gis.persist.condition.DashboardCondition;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;
import com.runwaysdk.session.SessionIF;
import com.runwaysdk.system.gis.geo.Universal;
import com.runwaysdk.system.gis.geo.UniversalQuery;
import com.runwaysdk.util.IDGenerator;

public abstract class DashboardLayer extends DashboardLayerBase implements com.runwaysdk.generation.loader.Reloadable, Layer
{
  private static final long        serialVersionUID = 1992575686;

  public static final String       DB_VIEW_PREFIX   = "lv_";

  public static final Log          log              = LogFactory.getLog(DashboardLayer.class);

  public boolean                   viewHasData      = true;

  private List<DashboardCondition> conditions       = null;

  public abstract ValueQuery getViewQuery();

  public abstract JSONObject toJSON();

  protected abstract DashboardLayer newInstance();

  public void setConditions(List<DashboardCondition> conditions)
  {
    this.conditions = conditions;
  }

  public List<DashboardCondition> getConditions()
  {
    return this.conditions;
  }

  @Override
  public FeatureStrategy getFeatureStrategy()
  {
    AllLayerType type = this.getLayerType().get(0);
    return FeatureStrategy.valueOf(type.name());
  }

  public boolean viewHasData()
  {
    return viewHasData;
  }

  public String generateViewName()
  {
    SessionIF session = Session.getCurrentSession();

    if (session != null)
    {
      String sessionId = session.getId();

      // The max length for a postgres table name is 63 characters, and as a
      // result our metadata is set at max length 63
      // as well.

      String vn = DB_VIEW_PREFIX + sessionId + "_" + IDGenerator.nextID().substring(0, 10);

      return vn;
    }

    return DB_VIEW_PREFIX + IDGenerator.nextID().substring(0, 10);
  }

  @Override
  public String applyWithStyle(DashboardStyle style, String mapId, DashboardCondition[] conditions)
  {
    this.applyAll(style, mapId, conditions);

    return this.publish();
  }

  protected String publish()
  {
    /*
     * We have to make sure that the transaction has ended before we can publish to geoserver, otherwise our database
     * view won't exist yet.
     */
    GeoserverBatch batch = new GeoserverBatch();

    this.publish(batch);

    GeoserverFacade.pushUpdates(batch);

    try
    {
      JSONObject json = this.toJSON();

      JSONArray jsonArray = new JSONArray();
      List<? extends DashboardStyle> styles = this.getStyles();
      for (int i = 0; i < styles.size(); ++i)
      {
        DashboardStyle stile = styles.get(i);
        jsonArray.put(stile.toJSON());
      }
      json.put("styles", jsonArray);

      return json.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  @Transaction
  @AbortIfProblem
  public void applyAll(DashboardStyle style, String mapId, DashboardCondition[] conditions)
  {
    boolean isNew = this.isNew();

    // We have to generate a new viewName for us on every apply because
    // otherwise there's browser-side caching that
    // won't show the new style update.
    String vn = generateViewName();
    this.setViewName(vn);
    this.setDashboardMap(DashboardMap.get(mapId));
    this.setVirtual(true);

    style.setName(this.getViewName());

    if (conditions != null)
    {
      this.conditions = Arrays.asList(conditions);
    }

    style.apply();

    this.apply();

    // Create hasLayer and hasStyle relationships
    if (isNew)
    {
      DashboardMap map = DashboardMap.get(mapId);

      HasLayer hasLayer = map.addHasLayer(this);
      hasLayer.setLayerIndex(map.getMaxIndex() + 1);
      hasLayer.apply();

      HasStyle hasStyle = this.addHasStyle(style);
      hasStyle.apply();

      map.reorderLayers();
    }

    this.validate();
  }

  /**
   * Validate the layer data
   */
  public void validate()
  {
    try
    {
      // Ensure the generated query is valid and executes
      Database.query(this.getViewQuery().getSQL());

      // Create the database view
      createDatabaseView(true);

      // Ensure there is a valid bounding box
      GeoserverFacade.getBBOX(this.getViewName());
    }
    catch (RunwayException e)
    {
      throw e;
    }
    catch (SmartException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      // If this happens it means the SQL generated wrong and coding will be
      // required to fix.
      throw new ProgrammingErrorException(e);
    }
  }

  public void createDatabaseView(boolean force)
  {
    String sql = this.getViewQuery().getSQL();

    Database.dropView(this.getViewName(), sql, false);

    Database.createView(this.getViewName(), sql);
  }

  /**
   * Publishes the layer and all its styles to GeoServer, creating a new database view that GeoServer will read, if it
   * does not exist yet.
   */
  public void publish(GeoserverBatch batch)
  {
    batch.addLayerToDrop(this);

    createDatabaseView(true);

    if (viewHasData)
    {
      batch.addLayerToPublish(this);
    }
  }

  /**
   * For easy reference, the name of the SLD is the same as the db view name. The .sld extension is automatically added
   * 
   * @return
   */
  public String getSLDName()
  {
    return this.getViewName();
  }

  /**
   * Returns the File object associated with the SLD for this view.
   * 
   * @return
   */
  public File getSLDFile()
  {
    String path = GeoserverProperties.getGeoserverSLDDir();
    String sld = path + this.getSLDName() + GeoserverProperties.SLD_EXTENSION;
    return new File(sld);
  }

  public boolean isPublished()
  {
    return GeoserverFacade.layerExists(this.getViewName());
  }

  @Override
  public String getViewName()
  {
    if (!SessionParameterFacade.containsKey(this.getId()))
    {
      SessionParameterFacade.put(this.getId(), this.generateViewName());
    }

    return SessionParameterFacade.get(this.getId());
  }

  public void setViewName(String value)
  {
    SessionParameterFacade.put(this.getId(), value);
  }

  @Override
  protected String buildKey()
  {
    if (this.getDashboardMapId() != null && this.getDashboardMapId().length() > 0)
    {
      return this.getDashboardMapId() + "-" + this.getName();
    }

    /*
     * The apply will fail because dashboard map is a required field. However, in order to give the user a better error
     * message we still need to populate the key with value.
     */
    return this.getId();
  }

  //
  // @Override
  // public void setGeoEntity(MdAttributeReference value)
  // {
  // if (value.getMdBusiness().definesType().equals(GeoEntity.CLASS))
  // {
  // super.setGeoEntity(value);
  // }
  // else
  // {
  // throw new ProgrammingErrorException("The attribute [" + DashboardLayer.GEOENTITY +
  // "] can only reference an MdAttributeReference to [" + GeoEntity.CLASS + "]");
  // }
  // }

  @Override
  public void lock()
  {
    for (DashboardStyle style : this.getAllHasStyle())
    {
      style.lock();
    }

    super.lock();
  }

  @Override
  public void unlock()
  {
    for (DashboardStyle style : this.getAllHasStyle())
    {
      style.unlock();
    }

    super.unlock();
  }

  @Override
  @Transaction
  public void delete()
  {
    for (DashboardStyle style : this.getAllHasStyle())
    {
      style.delete();
    }

    super.delete();
  }

  @Override
  public List<? extends DashboardStyle> getStyles()
  {
    return this.getAllHasStyle().getAll();
  }

  public static UniversalQuery getSortedUniversals()
  {
    QueryFactory f = new QueryFactory();
    UniversalQuery q = new UniversalQuery(f);

    Universal root = Universal.getRoot();
    q.WHERE(q.getId().NE(root.getId()));

    q.ORDER_BY_ASC(q.getDisplayLabel().localize());

    return q;
  }

  @Override
  @Transaction
  public void updateLegend(Integer legendXPosition, Integer legendYPosition, Boolean groupedInLegend)
  {
    this.appLock();
    this.getDashboardLegend().setLegendXPosition(legendXPosition);
    this.getDashboardLegend().setLegendYPosition(legendYPosition);
    this.getDashboardLegend().setGroupedInLegend(groupedInLegend);
    this.apply();
  }

  public static String getSessionId(String viewName)
  {
    String[] split = viewName.split("_");

    if (split != null && split.length == 3)
    {
      String sessionId = split[1];

      return sessionId;
    }

    return null;
  }

  @Override
  public FeatureType getFeatureType()
  {
    // LayerType is required so it's safe to assume access to the object
    List<AllLayerType> types = this.getLayerType();

    if (types.size() > 0)
    {
      AllLayerType type = types.get(0);
      if (type == AllLayerType.BUBBLE || type == AllLayerType.BASICPOINT || type == AllLayerType.GRADIENTPOINT || type == AllLayerType.CATEGORYPOINT)
      {
        return FeatureType.POINT;
      }
      else if (type == AllLayerType.BASICPOLYGON || type == AllLayerType.CATEGORYPOLYGON || type == AllLayerType.GRADIENTPOLYGON || type == AllLayerType.CATEGORYPOLYGON || type == AllLayerType.GRADIENTPOLYGON)
      {
        return FeatureType.POLYGON;
      }
    }

    throw new ProgrammingErrorException("Feature type is a required value");
  }

  protected void populate(DashboardLayer source)
  {
    this.setBBoxIncluded(source.getBBoxIncluded());
    this.setActiveByDefault(source.getActiveByDefault());
    this.setDisplayInLegend(source.getDisplayInLegend());
    this.setLayerEnabled(source.getLayerEnabled());
    this.setVirtual(source.getVirtual());
    this.getDashboardLegend().populate(source.getDashboardLegend());
    this.setName(source.getName());

    List<AllLayerType> types = source.getLayerType();

    for (AllLayerType type : types)
    {
      this.addLayerType(type);
    }
  }

  public DashboardLayer clone(DashboardMap map)
  {
    List<? extends DashboardStyle> styles = this.getStyles();

    DashboardLayer clone = this.newInstance();
    clone.populate(this);

    for (DashboardStyle style : styles)
    {
      DashboardStyle cStyle = style.clone();

      clone.applyAll(cStyle, map.getId(), new DashboardCondition[] {});
    }

    return clone;
  }

}
