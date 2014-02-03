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

package com.liferay.ci.jenkins.action;

import javax.portlet.PortletPreferences;

import com.liferay.ci.portlet.JenkinsIntegrationConstants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Manuel de la Peña
 */
public class ConfigurationValidator {

	public static boolean isConfigured(PortletPreferences portletPreferences) {
		int viewMode = GetterUtil.getInteger(
			portletPreferences.getValue(
				"viewmode",
				String.valueOf(JenkinsIntegrationConstants.VIEW_MODE_SERIES)));

		if (viewMode != JenkinsIntegrationConstants.VIEW_MODE_JOBS_STACK) {
			String jobName = GetterUtil.getString(
				portletPreferences.getValue("jobname", null));

			if (Validator.isNull(jobName)) {
				return false;
			}
		}
		else {
			String jobNames = GetterUtil.getString(
				portletPreferences.getValue("jobnames", null));

			if (Validator.isNull(jobNames)) {
				return false;
			}
		}

		return true;
	}

}