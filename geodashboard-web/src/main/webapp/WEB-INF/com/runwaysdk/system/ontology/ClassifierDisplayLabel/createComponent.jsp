<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" scope="request" value="Create a new Display Label"/>
<dl>
  <mjl:form id="com.runwaysdk.system.ontology.ClassifierDisplayLabel.form.id" name="com.runwaysdk.system.ontology.ClassifierDisplayLabel.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="com.runwaysdk.system.ontology.ClassifierDisplayLabel.form.create.button" value="Create" action="com.runwaysdk.system.ontology.ClassifierDisplayLabelController.create.mojo" />
  </mjl:form>
</dl>
