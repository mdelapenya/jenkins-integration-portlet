<%--
/**
* Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
*
* This library is free software; you can redistribute it and/or modify it under
* the terms of the GNU Lesser General Public License as published by the Free
* Software Foundation; either version 2.1 of the License, or (at your option)
* any later version.
*
* This library is distributed in the hope that it will be useful, but WITHOUT
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
* FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
* details.
*/
--%>

<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@ include file="/html/init.jsp" %>

<%
String jobName = GetterUtil.getString(portletPreferences.getValue("jobname", null));
%>
<portlet:actionURL name="getBuilds" var="getBuildsURL" />

<aui:form action="<%= getBuildsURL.toString() %>" name="fm">
	<aui:row>
		<c:choose>
			<c:when test="<%= Validator.isNull(jobName) %>">
				<div class="alert alert-info">
					<span class="displaying-help-message-holder">
						<liferay-ui:message key="this-is-the-jenkins-consumer-portlet-portlet-from-you-can-inspect-some-jenkins-statistics" />
					</span>
				</div>

				<div class="alert alert-warn">
					<span class="displaying-help-message-holder">
						<liferay-ui:message key="please-configure-this-portlet-to-display-jenkins-build-information" />
					</span>
				</div>
			</c:when>
			<c:otherwise>
				<h2><liferay-ui:message key="test-build-stats-for" /> <%= jobName %></h2>
				<%@ include file="builds.jspf" %>
			</c:otherwise>
		</c:choose>
	</aui:row>
</aui:form>