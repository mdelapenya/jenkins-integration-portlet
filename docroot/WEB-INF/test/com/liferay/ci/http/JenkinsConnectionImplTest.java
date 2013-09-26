package com.liferay.ci.http;

import static org.fest.assertions.Assertions.assertThat;

import org.json.JSONArray;
import org.junit.Test;

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