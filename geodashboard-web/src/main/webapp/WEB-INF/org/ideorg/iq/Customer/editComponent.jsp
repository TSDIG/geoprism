<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" scope="request" value="Edit an existing Customer"/>
<dl>
  <mjl:form id="org.ideorg.iq.Customer.form.id" name="org.ideorg.iq.Customer.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="org.ideorg.iq.Customer.form.update.button" value="Update" action="org.ideorg.iq.CustomerController.update.mojo" />
    <mjl:command name="org.ideorg.iq.Customer.form.delete.button" value="Delete" action="org.ideorg.iq.CustomerController.delete.mojo" />
    <mjl:command name="org.ideorg.iq.Customer.form.cancel.button" value="Cancel" action="org.ideorg.iq.CustomerController.cancel.mojo" />
  </mjl:form>
</dl>