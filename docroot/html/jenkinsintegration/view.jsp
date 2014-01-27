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

<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@ include file="/html/init.jsp" %>

<%
String baseApiURL = GetterUtil.getString(portletPreferences.getValue("baseapiurl", null));
String jobName = GetterUtil.getString(portletPreferences.getValue("jobname", null));
String jobURL = baseApiURL + "/job/" + jobName;
long timeout = GetterUtil.getLong(portletPreferences.getValue("timeout", String.valueOf(JenkinsIntegrationConstants.DEFAULT_TIMEOUT)));

int viewMode = GetterUtil.getInteger(portletPreferences.getValue("viewmode", String.valueOf(JenkinsIntegrationConstants.VIEW_MODE_SERIES)));
%>

<div>
	<c:choose>
		<c:when test="<%= Validator.isNull(jobName) %>">
			<div class="portlet-msg-alert">
				<span class="displaying-help-message-holder">
					<liferay-ui:message key="please-configure-this-portlet-to-display-jenkins-build-information" />
				</span>
			</div>
		</c:when>
		<c:otherwise>
			<liferay-ui:error exception="<%= FileNotFoundException.class %>" message="the-job-could-not-be-retrieved-please-review-configuration" />

			<c:choose>
				<c:when test="<%= (viewMode == JenkinsIntegrationConstants.VIEW_MODE_SERIES) %>">
					<h3>
						<liferay-ui:icon target="_blank" url="<%= HtmlUtil.escape(jobURL) %>">
							<liferay-ui:message key="test-build-stats-for" /> <%= HtmlUtil.escape(jobName) %>
						</liferay-ui:icon>
					</h3>

					<%@ include file="builds.jspf" %>
				</c:when>
				<c:when test="<%= (viewMode == JenkinsIntegrationConstants.VIEW_MODE_LIGHTS) %>">
					<%@ include file="lights.jspf" %>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</div>

<aui:script use="aui-base">
	setTimeout(
		function(){
			Liferay.Portlet.refresh('#p_p_id_<%= portletDisplay.getId() %>_');
		},
		<%= timeout %>
	);
</aui:script>
