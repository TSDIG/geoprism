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
<c:set var="page_title" scope="request" value="Create a new Description"/>
<dl>
  <mjl:form method="POST" name="net.geoprism.dashboard.DashboardDescription.form.name" id="net.geoprism.dashboard.DashboardDescription.form.id">
    <%@include file="form.jsp" %>
    <mjl:command name="net.geoprism.dashboard.DashboardDescription.form.create.button" action="net.geoprism.dashboard.DashboardDescriptionController.create.mojo" value="Create" />
  </mjl:form>
</dl>
