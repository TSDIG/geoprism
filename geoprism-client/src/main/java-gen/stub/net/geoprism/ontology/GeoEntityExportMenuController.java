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

import java.util.ArrayList;
import java.util.List;

import com.runwaysdk.ontology.TermControllerUtil;
import com.runwaysdk.system.ontology.io.TermFileFormatDTO;
import com.runwaysdk.system.ontology.io.TermFileFormatMasterDTO;

public class GeoEntityExportMenuController extends GeoEntityExportMenuControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR = "/WEB-INF/net/geoprism/ontology/GeoEntityExportMenu/";
  public static final String LAYOUT = "WEB-INF/templates/layout.jsp";
  
  public GeoEntityExportMenuController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  @Override
  public void export(GeoEntityExportMenuDTO dto, String parentTerm, String downloadToken) throws java.io.IOException, javax.servlet.ServletException
  {
    TermControllerUtil.writeExport(req, resp, downloadToken, this.getClientRequest(), parentTerm, dto.getFileFormat().get(0));
  }
  
  @Override
  public void failExport(GeoEntityExportMenuDTO dto, String parentTerm, String downloadToken) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
  
  @Override
  public void viewExport() throws java.io.IOException, javax.servlet.ServletException
  {
    com.runwaysdk.constants.ClientRequestIF clientRequest = super.getClientRequest();
    GeoEntityExportMenuDTO dto = new GeoEntityExportMenuDTO(clientRequest);
    
//    List<TermFileFormatMasterDTO> formats = TermFileFormatDTO.allItems(super.getClientSession().getRequest());
    List<TermFileFormatMasterDTO> formats = new ArrayList<TermFileFormatMasterDTO>();
    formats.add(TermFileFormatDTO.XML.item(getClientRequest()));
    
    req.setAttribute("_fileFormat", formats);
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }
  
  @Override
  public void failViewExport() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
