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
package com.runwaysdk.geodashboard.report;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.geodashboard.gis.persist.condition.ClassifierCondition;
import com.runwaysdk.geodashboard.gis.persist.condition.DashboardAttributeCondition;
import com.runwaysdk.geodashboard.gis.persist.condition.DashboardCondition;
import com.runwaysdk.geodashboard.gis.persist.condition.LocationCondition;
import com.runwaysdk.geodashboard.localization.LocalizationFacade;
import com.runwaysdk.geodashboard.parse.DateParseException;
import com.runwaysdk.query.GeneratedComponentQuery;

public class ReportProviderUtil implements Reloadable
{
  private static Log log = LogFactory.getLog(ReportProviderUtil.class);

  private static void parseCondition(String _criteria, ReportConditionHandlerIF _handler)
  {
    try
    {
      if (_criteria != null)
      {
        JSONArray conditions = new JSONArray(_criteria);

        int length = conditions.length();

        for (int i = 0; i < length; i++)
        {
          JSONObject condition = conditions.getJSONObject(i);
          String operation = condition.getString(DashboardCondition.OPERATION_KEY);
          String value = condition.getString(DashboardCondition.VALUE_KEY);
          String conditionType = condition.getString(DashboardCondition.TYPE_KEY);

          if (conditionType.equals(DashboardAttributeCondition.CONDITION_TYPE) || conditionType.equals(ClassifierCondition.CONDITION_TYPE))
          {
            String mdAttributeId = condition.getString(DashboardAttributeCondition.MD_ATTRIBUTE_KEY);
            MdAttributeDAOIF mdAttribute = MdAttributeDAO.get(mdAttributeId).getMdAttributeConcrete();

            _handler.handleAttributeCondition(mdAttribute, operation, value);
          }
          else if (conditionType.equals(LocationCondition.CONDITION_TYPE))
          {
            _handler.handleLocationCondition(value);
          }
        }
      }
    }
    catch (JSONException e)
    {
      log.error(e);
    }

  }

  public static void addConditions(QueryConfiguration _config, String _type, GeneratedComponentQuery _query)
  {
    String criteria = _config.getCriteria();

    if (criteria != null && criteria.length() > 0)
    {
      ReportProviderUtil.parseCondition(criteria, new ReportConditionHandler(_type, _config.getValueQuery(), _query));
    }
  }

  public static String getConditionInformation(String _criteria)
  {
    ConditionInformationHandler handler = new ConditionInformationHandler();

    ReportProviderUtil.parseCondition(_criteria, handler);

    return handler.getInformation();
  }

  public static Date parseDate(String source)
  {
    Locale locale = LocalizationFacade.getLocale();
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", locale);

    try
    {
      Date date = format.parse(source);

      return date;
    }
    catch (ParseException cause)
    {
      DateParseException e = new DateParseException(cause);
      e.setInput(source);
      e.setPattern(format.toLocalizedPattern());
      throw e;
    }
  }
}
