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

import org.json.JSONArray;
import org.junit.Test;

/**
 * 
 * @author Manuel de la Peña
 */
public class JenkinsConnectionImplTest {

	@Test
	public void testGetBuildsTestReport() throws Exception {
		String jobName = "mdelapenya";

		JSONArray testReports = JenkinsConnectUtil.getBuilds(jobName, 0);

		assertThat(testReports).isNotNull();
		assertThat(testReports.length()).isEqualTo(12);
	}

	@Test
	public void testGetBuildsTestReportMaxNumber() throws Exception {
		String jobName = "mdelapenya";

		JSONArray testReports = JenkinsConnectUtil.getBuilds(jobName, 100);

		assertThat(testReports).isNotNull();
		assertThat(testReports.length()).isEqualTo(12);
	}

	@Test
	public void testGetBuildsTestReportMaxNumberNegative() throws Exception {
		String jobName = "mdelapenya";

		JSONArray testReports = JenkinsConnectUtil.getBuilds(jobName, -1);

		assertThat(testReports).isNotNull();
		assertThat(testReports.length()).isEqualTo(12);
	}

	@Test
	public void testGetBuildsTestReportMaxNumberLessThan() throws Exception {
		String jobName = "mdelapenya";

		JSONArray testReports = JenkinsConnectUtil.getBuilds(jobName, 7);

		assertThat(testReports).isNotNull();
		assertThat(testReports.length()).isEqualTo(7);
	}

}