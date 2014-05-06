/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.ci.jenkins.vo;

import java.net.URL;

/**
 * @author Cristina González
 */
public class JenkinsBuild {

	public JenkinsBuild(int number, URL url) {
		_number = number;
		_url = url;
	}

	public int getFailedTests() {
		return _failedTests;
	}

	public int getNumber() {
		return _number;
	}

	public String getStatus() {
		return _status;
	}

	public URL getUrl() {
		return _url;
	}

	public void setFailedTests(int failedTests) {
		_failedTests = failedTests;
	}

	public void setNumber(int number) {
		_number = number;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public void setUrl(URL url) {
		_url = url;
	}

	private int _number;
	private int _failedTests;
	private String _status;
	private URL _url;

}