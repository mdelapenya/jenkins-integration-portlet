<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<%@page import="com.liferay.ci.jenkins.action.ConfigurationValidator"%>
<%@ include file="/html/init.jsp" %>

<%
String baseApiURL = GetterUtil.getString(portletPreferences.getValue("baseapiurl", null));
String jobName = GetterUtil.getString(portletPreferences.getValue("jobname", null));
String jobURL = baseApiURL + "/job/" + jobName;
long timeout = GetterUtil.getLong(portletPreferences.getValue("timeout", String.valueOf(JenkinsIntegrationConstants.DEFAULT_TIMEOUT)));

int viewMode = GetterUtil.getInteger(portletPreferences.getValue("viewmode", String.valueOf(JenkinsIntegrationConstants.VIEW_MODE_SERIES)));

boolean configured = ConfigurationValidator.isConfigured(portletPreferences);

boolean hasConfigurationPermission = PortletPermissionUtil.contains(permissionChecker, layout, portletDisplay.getId(), ActionKeys.CONFIGURATION);
%>

<div>
	<c:choose>
		<c:when test="<%= !configured %>">
			<div class="alert alert-warn">
				<span class="displaying-help-message-holder">
					<c:choose>
						<c:when test="<%= hasConfigurationPermission %>">
							<a href="<%= portletDisplay.getURLConfiguration() %>" onClick="<%= portletDisplay.getURLConfigurationJS() %>">
								<liferay-ui:message key="please-configure-this-portlet-to-display-jenkins-build-information" />
							</a>
						</c:when>
						<c:otherwise>
							<liferay-ui:message key="please-configure-this-portlet-to-display-jenkins-build-information" />
						</c:otherwise>
					</c:choose>
				</span>
			</div>
		</c:when>
		<c:otherwise>
			<liferay-ui:error exception="<%= FileNotFoundException.class %>" message="the-job-could-not-be-retrieved-please-review-configuration" />

			<c:choose>
				<c:when test="<%= (viewMode == JenkinsIntegrationConstants.VIEW_MODE_SERIES) %>">
					<%@ include file="builds.jspf" %>
				</c:when>
				<c:when test="<%=(viewMode == JenkinsIntegrationConstants.VIEW_MODE_JOBS_STACK)%>">
					<%@ include file="stack.jspf" %>
				</c:when>
				<c:when test="<%=(viewMode == JenkinsIntegrationConstants.VIEW_MODE_TRAFFIC_LIGHTS)%>">
					<%@ include file="lights.jspf" %>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</div>

<c:if test="<%= configured %>">
	<aui:script use="aui-base">
		setTimeout(
			function(){
				Liferay.Portlet.refresh('#p_p_id_<%= portletDisplay.getId() %>_');
			},
			<%= timeout %>
		);
	</aui:script>
</c:if>