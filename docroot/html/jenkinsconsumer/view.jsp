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
String baseApiURL = GetterUtil.getString(portletPreferences.getValue("baseapiurl", null));
String jobName = GetterUtil.getString(portletPreferences.getValue("jobname", null));
String jobURL = baseApiURL + "/job/" + jobName;
%>

<div>
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
			<h2>
				<liferay-ui:icon target="_blank" url="<%= jobURL %>">
					<liferay-ui:message key="test-build-stats-for" /> <%= jobName %>
				</liferay-ui:icon>
			</h2>

			<liferay-ui:error exception="<%= FileNotFoundException.class %>" message="the-job-could-not-be-retrieved-please-review-configuration" />

			<%@ include file="builds.jspf" %>
		</c:otherwise>
	</c:choose>
</div>