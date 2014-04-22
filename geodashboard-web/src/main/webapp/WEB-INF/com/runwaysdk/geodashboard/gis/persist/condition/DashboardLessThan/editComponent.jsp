<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" scope="request" value="Edit an existing Less Than"/>
<dl>
  <mjl:form id="com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThan.form.id" name="com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThan.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThan.form.update.button" value="Update" action="com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThanController.update.mojo" />
    <mjl:command name="com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThan.form.delete.button" value="Delete" action="com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThanController.delete.mojo" />
    <mjl:command name="com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThan.form.cancel.button" value="Cancel" action="com.runwaysdk.geodashboard.gis.persist.condition.DashboardLessThanController.cancel.mojo" />
  </mjl:form>
</dl>