<%--

    Copyright (c) 2013 TerraFrame, Inc. All rights reserved.

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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/geodashboard.tld" prefix="gdb"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

<head>

<gdb:localize var="page_title" key="ontologies.title"/>

<!-- Ontologies CSS -->
<jwr:style src="/com/runwaysdk/geodashboard/ontology/TermTree.css" useRandomParam="false"/>  

<!-- Ontologies Javascript -->
<jwr:script src="/bundles/runway-controller.js" useRandomParam="false"/>
<jwr:script src="/com/runwaysdk/geodashboard/ontology/OntologyTree.js" useRandomParam="false"/>

<%@page import="com.runwaysdk.geodashboard.ontology.ClassifierDTO" %>
<%@page import="com.runwaysdk.geodashboard.gis.ClassifierExportMenuDTO" %>
<%@page import="com.runwaysdk.geodashboard.ontology.ClassifierController" %>
<%@page import="com.runwaysdk.geodashboard.ontology.ClassifierDisplayLabelDTO" %>
<%@page import="com.runwaysdk.geodashboard.ontology.ClassifierIsARelationshipDTO" %>
<%@page import="com.runwaysdk.system.ontology.TermUtilDTO" %>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>
<%@page import="com.runwaysdk.generation.loader.LoaderDecorator"%>

<script type="text/javascript">
<%
  // use a try catch before printing out the definitions, otherwise, if an
  // error occurs here, javascript spills onto the actual page (ugly!)
  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    
  try
  {
    String js = JSONController.importTypes(clientRequest.getSessionId(), new String[] {
      ClassifierDTO.CLASS, ClassifierIsARelationshipDTO.CLASS, ClassifierDisplayLabelDTO.CLASS, ClassifierController.CLASS, TermUtilDTO.CLASS, ClassifierExportMenuDTO.CLASS
      }, true);
    out.print(js);
  }
  catch(Exception e)
  {
    // perform cleanup
    throw e;
  }
%>
</script>

</head>

<div id="ontologies"></div>

<script type="text/javascript">
// var $tree = document.getElementById('main-content-frame').contentWindow.document.universaltree.getImpl();

  com.runwaysdk.ui.DOMFacade.execOnPageLoad(function(){
    com.runwaysdk.ui.Manager.setFactory("JQuery");
    
    document.universaltree = new com.runwaysdk.geodashboard.ontology.OntologyTree({
      termType : <% out.print("\"" + ClassifierDTO.CLASS + "\""); %>,
      relationshipTypes : [ <% out.print("\"" + ClassifierIsARelationshipDTO.CLASS + "\""); %> ],
      rootTerms : [ {termId: "${requestScope.rootId}"} ],
      editable : true,
      /* checkable: true, */
      crud: {
        create: { // This configuration gets merged into the jquery create dialog.
          height: 290
        },
        update: {
          height: 290
        }
      }
    });
    document.universaltree.render("#ontologies");
  });
</script>
