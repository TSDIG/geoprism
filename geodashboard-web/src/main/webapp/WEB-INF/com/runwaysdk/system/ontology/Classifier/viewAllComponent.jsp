<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" scope="request" value="View all Classifier"/>
<mjl:table var="item" query="${query}">
  <mjl:context action="com.runwaysdk.system.ontology.ClassifierController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="classifierId">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="classifierPackage">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="displayLabel">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="com.runwaysdk.system.ontology.ClassifierController.view.mojo">
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
<mjl:commandLink name="ClassifierController.newInstance" action="com.runwaysdk.system.ontology.ClassifierController.newInstance.mojo">
  Create a new Classifier
</mjl:commandLink>
