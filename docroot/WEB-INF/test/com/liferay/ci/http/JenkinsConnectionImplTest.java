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

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;

import java.io.IOException;

import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Manuel de la Peña
 */
public class JenkinsConnectionImplTest {

	@Before
	public void setUp() throws IOException {
		JenkinsConnectImpl connectionImpl = new JenkinsConnectImpl();

		connectionParams = connectionImpl._connectionParams;
	}

	@Test
	public void testGetBuildsTestReport() throws Exception {
		String jobName = "mdelapenya";

		JSONArray testReports = JenkinsConnectUtil.getBuilds(
			connectionParams, jobName, 0);

		assertThat(testReports).isNotNull();
		assertThat(testReports.length()).isGreaterThan(0);
	}

	@Test(expected = IOException.class)
	public void testGetBuildsTestReportIOException() throws Exception {
		connectionParams.setBaseApiUrl("http://fooblablablxxh");

		String jobName = "mdelapenya";

		JenkinsConnectUtil.getBuilds(connectionParams, jobName, 0);

		fail();
	}

	@Test
	public void testGetBuildsTestReportMaxNumber() throws Exception {
		String jobName = "mdelapenya";

		JSONArray testReports = JenkinsConnectUtil.getBuilds(
			connectionParams, jobName, 100);

		assertThat(testReports).isNotNull();
		assertThat(testReports.length()).isGreaterThan(0);
	}

	@Test
	public void testGetBuildsTestReportMaxNumberNegative() throws Exception {
		String jobName = "mdelapenya";

		JSONArray testReports = JenkinsConnectUtil.getBuilds(
			connectionParams, jobName, -1);

		assertThat(testReports).isNotNull();
		assertThat(testReports.length()).isGreaterThan(0);
	}

	@Test
	public void testGetBuildsTestReportMaxNumberLessThan() throws Exception {
		String jobName = "mdelapenya";

		JSONArray testReports = JenkinsConnectUtil.getBuilds(
			connectionParams, jobName, 3);

		assertThat(testReports).isNotNull();
		assertThat(testReports.length()).isGreaterThan(0);
	}

	private AuthConnectionParams connectionParams;

}