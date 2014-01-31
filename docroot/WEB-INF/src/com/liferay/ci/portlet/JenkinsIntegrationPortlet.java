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

package com.liferay.ci.portlet;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.json.JSONArray;
import org.json.JSONException;

import com.liferay.ci.http.AuthConnectionParams;
import com.liferay.ci.http.JenkinsConnectUtil;
import com.liferay.ci.jenkins.cache.LiferayJenkinsBuildCache;
import com.liferay.ci.jenkins.vo.JenkinsJob;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * 
 * @author Manuel de la Peña
 */
public class JenkinsIntegrationPortlet extends MVCPortlet {

	@Override
	public void init() throws PortletException {
		super.init();

		_cache = new LiferayJenkinsBuildCache();
	}

	public static LiferayJenkinsBuildCache getCache() {
		return _cache;
	}

	@Override
	public void render(RenderRequest request, RenderResponse response)
		throws PortletException, IOException {

		PortletPreferences portletPreferences = request.getPreferences();

		String jobName = portletPreferences.getValue(
			"jobname", StringPool.BLANK);

		if (!Validator.isNull(jobName)) {
			int viewMode = GetterUtil.getInteger(
				portletPreferences.getValue("viewmode", null),
				JenkinsIntegrationConstants.VIEW_MODE_SERIES);

			if (viewMode == JenkinsIntegrationConstants.VIEW_MODE_SERIES) {
				buildSeries(request);
			}
			else if (viewMode == JenkinsIntegrationConstants.VIEW_MODE_TRAFFIC_LIGHTS) {
				buildLights(request);
			}
			else if (viewMode == JenkinsIntegrationConstants.VIEW_MODE_PROJECTS_STACK) {
				buildProjectsStack(request);
			}
		}

		super.render(request, response);
	}

	protected void buildLights(RenderRequest request) {
		PortletPreferences portletPreferences = request.getPreferences();

		String jobName = portletPreferences.getValue(
			"jobname", StringPool.BLANK);

		_log.debug("Getting builds for " + jobName);

		AuthConnectionParams connectionParams = getConnectionParams(
			portletPreferences);

		try {
			String lastBuildStatus = JenkinsConnectUtil.getLastBuildStatus(
				connectionParams, jobName);

			request.setAttribute("LAST_BUILD_STATUS", lastBuildStatus);

			if (lastBuildStatus.equals(
					JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_UNSTABLE)
				) {

				// retrieve number of broken tests for last build

				JSONArray testResults = JenkinsConnectUtil.getBuilds(
					connectionParams, jobName, 1);

				request.setAttribute("TEST_RESULTS", testResults);
			}
		}
		catch (IOException ioe) {
			SessionErrors.add(request, ioe.getClass());

			_log.error("The job was not available", ioe);
		}
		catch (JSONException e) {
			_log.error("The job is not well-formed", e);
		}
	}

	protected void buildProjectsStack(RenderRequest request) {
		PortletPreferences portletPreferences = request.getPreferences();

		String projectNamesParam = portletPreferences.getValue(
			"projectnames", StringPool.BLANK);

		String[] projectNames = StringUtil.split(
			projectNamesParam, StringPool.NEW_LINE);

		AuthConnectionParams connectionParams = getConnectionParams(
			portletPreferences);

		try {
			JenkinsJob[] lastBuildStatuses =
				JenkinsConnectUtil.getLastBuildStatuses(
					connectionParams, projectNames);

			request.setAttribute("JENKINS_JOBS", lastBuildStatuses);
		}
		catch (IOException ioe) {
			SessionErrors.add(request, ioe.getClass());

			_log.error("The jobs were not available", ioe);
		}
		catch (JSONException e) {
			_log.error("The jobs are not well-formed", e);
		}
	}

	protected void buildSeries(RenderRequest request) {
		PortletPreferences portletPreferences = request.getPreferences();

		String portletId = (String)request.getAttribute(WebKeys.PORTLET_ID);

		String jobName = portletPreferences.getValue(
			"jobname", StringPool.BLANK);

		_log.debug("Getting builds for " + jobName);

		String buildsNumber = portletPreferences.getValue(
			"buildsnumber", StringPool.BLANK);

		try {
			int maxBuildNumber = 0;

			if (Validator.isNotNull(buildsNumber)) {
				maxBuildNumber = Integer.parseInt(buildsNumber);

				_log.debug(
					"Max BuildNumber for build: " + maxBuildNumber);
			}

			String jobCacheKey = jobName + StringPool.POUND +
				buildsNumber;

			if (!_cache.containsKey(portletId, jobCacheKey)) {
				JSONArray testResults = JenkinsConnectUtil.getBuilds(
					getConnectionParams(portletPreferences), jobName,
					maxBuildNumber);

				_cache.put(portletId, jobCacheKey, testResults);
			}

			request.setAttribute(
				"TEST_RESULTS", _cache.get(portletId, jobCacheKey));
		}
		catch (IOException ioe) {
			SessionErrors.add(request, ioe.getClass());

			_log.error("The job was not available", ioe);
		}
		catch (JSONException e) {
			_log.error("The job is not well-formed", e);
		}
	}

	protected AuthConnectionParams getConnectionParams(
		PortletPreferences portletPreferences) {

		String user = portletPreferences.getValue(
			"username", StringPool.BLANK);
		String password = portletPreferences.getValue(
			"password", StringPool.BLANK);
		String url = portletPreferences.getValue(
			"baseapiurl", StringPool.BLANK);

		return new AuthConnectionParams(password, url, user);
	}

	private static LiferayJenkinsBuildCache _cache;

	private static Log _log = LogFactoryUtil.getLog(
		JenkinsIntegrationPortlet.class);

}