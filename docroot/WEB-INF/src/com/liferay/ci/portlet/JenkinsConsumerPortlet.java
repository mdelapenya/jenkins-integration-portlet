package com.liferay.ci.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;

import org.json.JSONArray;
import org.json.JSONException;

import com.liferay.ci.http.JenkinsConnectUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class JenkinsConsumerPortlet extends MVCPortlet {

	@Override
	public void init() throws PortletException {
		super.init();
	}

	public void getBuilds(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String jobName = ParamUtil.get(
			actionRequest, "jobName", StringPool.BLANK);

		_log.debug("Getting builds for " + jobName);

		PortletPreferences portletPreferences = actionRequest.getPreferences();

		String buildsNumber = portletPreferences.getValue(
			"buildsnumber", StringPool.BLANK);

		try {
			int maxBuildNumber = 0;

			if (Validator.isNotNull(buildsNumber)) {
				maxBuildNumber = Integer.parseInt(buildsNumber);

				_log.debug("Max BuildNumber for build: " + maxBuildNumber);
			}

			JSONArray testResults = JenkinsConnectUtil.getBuilds(
				jobName, maxBuildNumber);

			actionRequest.setAttribute("TEST_RESULTS", testResults);
		}
		catch (IOException ioe) {
			_log.error("The job was not available", ioe);
		}
		catch (JSONException e) {
			_log.error("The job is not well-formed", e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		JenkinsConsumerPortlet.class);

}