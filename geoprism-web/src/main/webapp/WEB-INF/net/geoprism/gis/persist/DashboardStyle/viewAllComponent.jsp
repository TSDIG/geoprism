<%--

    Copyright (c) 2015 TerraFrame, Inc. All rights reserved.

    This file is part of Runway SDK(tm).

    Runway SDK(tm) is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    Runway SDK(tm) is distributed in the hope that it will be useful, but
    WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" scope="request" value="View all Style"/>
<mjl:table var="item" query="${query}">
  <mjl:context action="net.geoprism.dashboard.DashboardStyleController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="enableLabel">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="enableValue">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="labelColor">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="labelFont">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="labelHalo">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="labelSize">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="labelWidth">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="name">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pointFill">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pointOpacity">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pointRotation">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pointSize">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pointStroke">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pointStrokeWidth">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="polygonFill">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="polygonStroke">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="polygonStrokeWidth">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="polygoneFillOpacity">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="polygoneStrokeOpacity">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="valueColor">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="valueFont">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="valueHalo">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="valueSize">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="valueWidth">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="wellKnownName">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="net.geoprism.dashboard.DashboardStyleController.view.mojo">
          View
          <mjl:property name="id" value="${item.id}" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
  <mjl:pagination>
    <mjl:page />
  </mjl:pagination>
</mjl:table>
<br />
<mjl:commandLink name="DashboardStyleController.newInstance" action="net.geoprism.dashboard.DashboardStyleController.newInstance.mojo">
  Create a new Style
</mjl:commandLink>
