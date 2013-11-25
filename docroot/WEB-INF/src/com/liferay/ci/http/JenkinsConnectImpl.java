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

package com.liferay.ci.http;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.liferay.ci.json.JSONReaderImpl;

/**
 * 
 * @author Manuel de la Peña
 */
public class JenkinsConnectImpl extends BaseConnectImpl {

	public JenkinsConnectImpl() throws IOException {
		super();

		_apiURLSuffix = "api/json";
	}

	public String getAPIURLSuffix() {
		return _apiURLSuffix;
	}

	public String getBaseAPIURL() {
		return _baseAPIURL;
	}

	public JSONObject getBuildTestReport(JSONObject build)
		throws IOException, JSONException {

		String buildURL = (String)build.get("url");

		return _get(buildURL + "testReport/" + _apiURLSuffix);
	}

	public String getLastBuildStatus(JSONObject build)
		throws IOException, JSONException {

		String buildURL = (String)build.get("url");

		JSONObject buildResult = _get(buildURL + _apiURLSuffix);

		Object result = buildResult.get("result");

		return String.valueOf(result);
	}

	public JSONObject getJob(String jobName)
		throws IOException, JSONException {

		return _get(getJobAPIURL(jobName));
	}

	public String getJobAPIURL(String jobName) {
		return getBaseAPIURL() + "/job/" + jobName + "/" + getAPIURLSuffix();
	}

	public void setAPIURLSuffix(String apiURLSuffix) {
		_apiURLSuffix = apiURLSuffix;
	}

	protected void setBaseAPIURL(String baseApiURL) {
		_baseAPIURL = baseApiURL;
	}

	private JSONObject _get(String apiURL) throws IOException, JSONException {
		return JSONReaderImpl.readJSONFromURL(connect(apiURL));
	}

	private static String _apiURLSuffix;
	private static String _baseAPIURL;

}