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
<c:set var="page_title" scope="request" value="Edit an existing Display Label"/>
<dl>
  <mjl:form id="net.geoprism.ontology.ClassifierDisplayLabel.form.id" name="net.geoprism.ontology.ClassifierDisplayLabel.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="net.geoprism.ontology.ClassifierDisplayLabel.form.update.button" value="Update" action="net.geoprism.ontology.ClassifierDisplayLabelController.update.mojo" />
    <mjl:command name="net.geoprism.ontology.ClassifierDisplayLabel.form.delete.button" value="Delete" action="net.geoprism.ontology.ClassifierDisplayLabelController.delete.mojo" />
    <mjl:command name="net.geoprism.ontology.ClassifierDisplayLabel.form.cancel.button" value="Cancel" action="net.geoprism.ontology.ClassifierDisplayLabelController.cancel.mojo" />
  </mjl:form>
</dl>
