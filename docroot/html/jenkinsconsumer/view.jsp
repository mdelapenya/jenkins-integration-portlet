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

<%@ include file="/html/init.jsp" %>

<portlet:actionURL name="getBuilds" var="getBuildsURL" />

<aui:form action="<%= getBuildsURL.toString() %>" name="fm">
	<aui:row>
		<div class="alert alert-info">
			<span class="displaying-help-message-holder">
				This is the <b>Jenkins Consumer Portlet</b> portlet, from you can inspect some Jenkins statistics.
			</span>
		</div>
	</aui:row>
	<aui:row>
		<aui:col>
			<aui:select id="jobName" name="jobName">
				<aui:option label="mdelapenya" value="mdelapenya" />
				<aui:option label="liferay-portal-master-clone" value="liferay-portal-master-clone" />
			</aui:select>
		</aui:col>
		<aui:col>
			<input type="submit" value="Query" />
		</aui:col>
	</aui:row>
	<aui:row>
		<%@ include file="builds.jspf" %>
	</aui:row>
</aui:form>