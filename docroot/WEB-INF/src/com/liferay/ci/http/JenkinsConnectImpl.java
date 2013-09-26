package com.liferay.ci.http;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.liferay.ci.json.JSONReaderImpl;

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