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
package net.geoprism.data.importer;

import java.util.Collection;

import net.geoprism.ontology.GeoEntityUtil;

import org.apache.poi.ss.usermodel.Cell;

import com.runwaysdk.ComponentIF;
import com.runwaysdk.business.ontology.Term;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.system.gis.geo.GeoEntity;
import com.runwaysdk.system.gis.geo.LocatedIn;
import com.runwaysdk.system.gis.geo.Universal;

public class GeoEntityColumn extends ExcelColumn
{

  private Universal universal;

  public GeoEntityColumn(String attributeName, String displayLabel, Universal universal)
  {
    super(attributeName, displayLabel);

    this.universal = universal;
  }

  public String getValue(ComponentIF component)
  {
    String attribute = this.attributeName.split(GeoEntityColumnListener.DELIMETER)[0];

    return component.getValue(attribute);
  }

  @Override
  public void setValue(Cell _cell, String _value)
  {
    String value = new String(_value);

    if (this.getTransform() != null)
    {
      value = (String) this.getTransform().transform(value);
    }

    if (value != null && value.length() > 0)
    {
      GeoEntity entity = GeoEntity.get(value);

      Collection<Term> ancestors = GeoEntityUtil.getOrderedAncestors(GeoEntity.getRoot(), entity, LocatedIn.CLASS);

      for (Term ancestor : ancestors)
      {
        GeoEntity parent = (GeoEntity) ancestor;

        if (parent.getUniversalOid().equals(universal.getOid()))
        {
          super.setValue(_cell, parent.getDisplayLabel().getValue());
        }
      }
    }
    else
    {
      super.setValue(_cell, value);
    }
  }
}
