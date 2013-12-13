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

<%@ include file="/html/init.jsp" %>

<%
String userName = GetterUtil.getString(portletPreferences.getValue("username", null));
String password = GetterUtil.getString(portletPreferences.getValue("password", null));
String buildsNumber = GetterUtil.getString(portletPreferences.getValue("buildsnumber", null));
String jobName = GetterUtil.getString(portletPreferences.getValue("jobname", null));
String baseApiURL = GetterUtil.getString(portletPreferences.getValue("baseapiurl", null));

int viewMode = GetterUtil.getInteger(portletPreferences.getValue("viewmode", String.valueOf(JenkinsIntegrationConstants.VIEW_MODE_SERIES)));

long timeout = GetterUtil.getLong(portletPreferences.getValue("timeout", String.valueOf(JenkinsIntegrationConstants.DEFAULT_TIMEOUT)));

boolean displayStacked = GetterUtil.getBoolean(portletPreferences.getValue("stacked", null));

String charType = GetterUtil.getString(portletPreferences.getValue("charttype", "default"));

boolean displayPassCount = GetterUtil.getBoolean(portletPreferences.getValue("passcount", null));
boolean displaySkipCount = GetterUtil.getBoolean(portletPreferences.getValue("skipcount", null));
boolean displayFailCount = GetterUtil.getBoolean(portletPreferences.getValue("failcount", null));
boolean displayTotalCount = GetterUtil.getBoolean(portletPreferences.getValue("totalcount", null));

String passedCountColor = GetterUtil.getString(portletPreferences.getValue("passedcountcolor", JenkinsIntegrationConstants.DEFAULT_PASSED_COLOR));
String skippedCountColor = GetterUtil.getString(portletPreferences.getValue("skippedcountcolor", JenkinsIntegrationConstants.DEFAULT_SKIPPED_COLOR));
String failedCountColor = GetterUtil.getString(portletPreferences.getValue("failedcountcolor", JenkinsIntegrationConstants.DEFAULT_FAILED_COLOR));
String totalCountColor = GetterUtil.getString(portletPreferences.getValue("totalcountcolor", JenkinsIntegrationConstants.DEFAULT_TOTAL_COLOR));
%>

<liferay-portlet:actionURL portletConfiguration="true" var="actionURL" />

<aui:form action="<%= actionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<aui:fieldset label="basic-authentication-configuration">
		<div class="alert alert-info">
			<span class="displaying-help-message-holder">
				<liferay-ui:message key="please-set-up-here-basic-authentication-for-your-ci-server" />
			</span>
		</div>

		<aui:input cssClass="lfr-input-text-container" label="username" name="preferences--username--" type="text" value="<%= userName %>" />

		<aui:input label="password" name="preferences--password--" type="password" value="" />
	</aui:fieldset>

	<aui:fieldset label="builds-view-configuration">
		<liferay-ui:error key="baseApiURLError" message="please-enter-a-valid-base-api-url" />

		<aui:input cssClass="lfr-input-text-container" label="base-api-url" name="preferences--baseapiurl--" type="text" value="<%= baseApiURL %>" />

		<aui:input cssClass="lfr-input-text-container" label="job-name" name="preferences--jobname--" type="text" value="<%= jobName %>" />

		<aui:input cssClass="lfr-input-text-container" label="reload-timeout" name="preferences--timeout--" type="text" value="<%= timeout %>" />

		<aui:select id="preferences--viewmode--" label="view-mode" name="preferences--viewmode--">
			<aui:option selected='<%= (viewMode == JenkinsIntegrationConstants.VIEW_MODE_SERIES) %>' label="series-view-mode" value="<%= JenkinsIntegrationConstants.VIEW_MODE_SERIES %>" />
			<aui:option selected='<%= (viewMode == JenkinsIntegrationConstants.VIEW_MODE_LIGHTS) %>' label="lights-view-mode" value="<%= JenkinsIntegrationConstants.VIEW_MODE_LIGHTS %>" />
		</aui:select>

		<aui:panel label="series-view-mode">
			<aui:select id="preferences--buildsnumber--" label="number-of-builds-to-view" name="preferences--buildsnumber--">
				<aui:option selected='<%= buildsNumber.equals("5") %>' label="5" value="5" />
				<aui:option selected='<%= buildsNumber.equals("10") %>' label="10" value="10" />
				<aui:option selected='<%= buildsNumber.equals("25") %>' label="25" value="25" />
				<aui:option selected='<%= buildsNumber.equals("50") %>' label="50" value="50" />
			</aui:select>

			<aui:input checked="<%= displayStacked %>" label="display-stacked" name="preferences--stacked--" type="checkbox" />

			<aui:select id="preferences--charttype--" label="chart-type" name="preferences--charttype--">
				<aui:option selected='<%= charType.equals("default") %>' label="default" value="default" />
				<aui:option selected='<%= charType.equals("column") %>' label="column" value="column" />
			</aui:select>

			<aui:input checked="<%= displayPassCount %>" label="display-passed-test-count" name="preferences--passcount--" type="checkbox" />
			<aui:input cssClass="color-picker-trigger" inlineField="true" inlineLabel="true" label="color" name="preferences--passedcountcolor--" type="text" value="<%= passedCountColor %>" />

			<aui:input checked="<%= displaySkipCount %>" label="display-skipped-test-count" name="preferences--skipcount--" type="checkbox" />
			<aui:input cssClass="color-picker-trigger" inlineField="true" inlineLabel="true" label="color" name="preferences--skippedcountcolor--" type="text" value="<%= skippedCountColor %>" />

			<aui:input checked="<%= displayFailCount %>" label="display-failed-test-count" name="preferences--failcount--" type="checkbox" />
			<aui:input cssClass="color-picker-trigger" inlineField="true" inlineLabel="true" label="color" name="preferences--failedcountcolor--" type="text" value="<%= failedCountColor %>" />

			<aui:input checked="<%= displayTotalCount %>" label="display-total-test-count" name="preferences--totalcount--" type="checkbox" />
			<aui:input cssClass="color-picker-trigger" inlineField="true" inlineLabel="true" label="color" name="preferences--totalcountcolor--" type="text" value="<%= totalCountColor %>" />
		</aui:panel>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-color-picker-popover">
	var colorPicker = new A.ColorPickerPopover({
		trigger: '.color-picker-trigger',
		zIndex: 2
	}).render();

	colorPicker.on('select', function(event) {
		event.trigger.val(event.color);
	});
</aui:script>