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
package net.geoprism.ontology;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.wdtinc.mapbox_vector_tile.VectorTile.Tile.Feature.Builder;
import com.wdtinc.mapbox_vector_tile.adapt.jts.IUserDataConverter;
import com.wdtinc.mapbox_vector_tile.build.MvtLayerProps;

public class UserDataConverter implements IUserDataConverter
{
  @Override
  @SuppressWarnings("unchecked")
  public void addTags(Object userData, MvtLayerProps layerProps, Builder featureBuilder)
  {
    if (userData != null)
    {
      Map<String, String> data = (Map<String, String>) userData;
      Set<Entry<String, String>> entries = data.entrySet();

      for (Entry<String, String> entry : entries)
      {
        int kIndex = layerProps.addKey(entry.getKey());
        int vIndex = layerProps.addValue(entry.getValue());

        featureBuilder.addTags(kIndex);
        featureBuilder.addTags(vIndex);
      }
    }
  }
}