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
import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.json.JSONArray;
import org.json.JSONException;

import com.liferay.ci.http.JenkinsConnectUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * 
 * @author Manuel de la Peña
 */
public class JenkinsConsumerPortlet extends MVCPortlet {

	@Override
	public void init() throws PortletException {
		super.init();

		_jobsCache = new HashMap<String, JSONArray>();
	}

	@Override
	public void render(RenderRequest request, RenderResponse response)
		throws PortletException, IOException {

		PortletPreferences portletPreferences = request.getPreferences();

		String jobName = portletPreferences.getValue(
			"jobname", StringPool.BLANK);

		if (!Validator.isNull(jobName)) {
			_log.debug("Getting builds for " + jobName);

			String buildsNumber = portletPreferences.getValue(
				"buildsnumber", StringPool.BLANK);

			try {
				int maxBuildNumber = 0;

				if (Validator.isNotNull(buildsNumber)) {
					maxBuildNumber = Integer.parseInt(buildsNumber);

					_log.debug("Max BuildNumber for build: " + maxBuildNumber);
				}

				String jobCacheKey = jobName + StringPool.POUND + buildsNumber;

				if (!_jobsCache.containsKey(jobCacheKey)) {
					JSONArray testResults = JenkinsConnectUtil.getBuilds(
						jobName, maxBuildNumber);

					_jobsCache.put(jobCacheKey, testResults);
				}

				request.setAttribute(
					"TEST_RESULTS", _jobsCache.get(jobCacheKey));
			}
			catch (IOException ioe) {
				SessionErrors.add(request, ioe.getClass());

				_log.error("The job was not available", ioe);
			}
			catch (JSONException e) {
				_log.error("The job is not well-formed", e);
			}
		}

		super.render(request, response);
	}

	private Map<String, JSONArray> _jobsCache;

	private static Log _log = LogFactoryUtil.getLog(
		JenkinsConsumerPortlet.class);

}