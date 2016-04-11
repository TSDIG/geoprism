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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/geoprism.tld" prefix="gdb"%>

<%@ page import="com.runwaysdk.constants.DeployProperties" %>
<%@ page import="net.geoprism.SystemLogoSingletonDTO" %>
<%@ page import="com.runwaysdk.ClientSession" %>
<%@ page import="com.runwaysdk.constants.CommonProperties" %>


<gdb:localize var="page_title" key="splash.pagetitle" />

       <%!
          String printBannerSrc(HttpServletRequest request)
          {
            ClientSession session = ClientSession.createAnonymousSession(new java.util.Locale[]{CommonProperties.getDefaultLocale()});
            com.runwaysdk.constants.ClientRequestIF clientRequest = session.getRequest();
          
            String bannerFile = SystemLogoSingletonDTO.getBannerFileFromCache(clientRequest, request);
            if (bannerFile != null)
            {
              return bannerFile;
            }
            else
            {
              String webappRoot = request.getContextPath() + "/";
              return webappRoot + "net/geoprism/images/splash_logo.png";
            }
          }
       %>

<gdb:localize var="logoalt" key="splash.logoalt" />
<img id="logo" style="max-width:250px;" src="<%= printBannerSrc(request) %>" alt="${logoalt}">

<br/>
<br/>

<header id="header">
  <h1><gdb:localize key="splash.header" /></h1>
</header>

<p><gdb:localize key="splash.powered" /></p>