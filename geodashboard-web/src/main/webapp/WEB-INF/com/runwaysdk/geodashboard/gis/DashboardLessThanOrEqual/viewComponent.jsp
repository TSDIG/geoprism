<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" scope="request" value="View a Less Than Or Equal"/>
<dl>
  <mjl:form id="com.runwaysdk.geodashboard.gis.DashboardLessThanOrEqual.form.id" name="com.runwaysdk.geodashboard.gis.DashboardLessThanOrEqual.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="attributeValue">
        ${item.attributeValue}
      </mjl:dt>
      <mjl:dt attribute="styleReference">
        ${item.styleReference.keyName}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="com.runwaysdk.geodashboard.gis.DashboardLessThanOrEqual.form.edit.button" value="Edit" action="com.runwaysdk.geodashboard.gis.DashboardLessThanOrEqualController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="com.runwaysdk.geodashboard.gis.DashboardLessThanOrEqual.viewAll.link" action="com.runwaysdk.geodashboard.gis.DashboardLessThanOrEqualController.viewAll.mojo">
  View All
</mjl:commandLink>