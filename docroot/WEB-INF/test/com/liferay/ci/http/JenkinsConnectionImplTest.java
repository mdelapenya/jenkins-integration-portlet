package com.liferay.ci.http;

import static org.fest.assertions.Assertions.assertThat;

import org.json.JSONArray;
import org.junit.Test;

public class JenkinsConnectionImplTest {

	@Test
	public void testGetBuildsTestReport() throws Exception {
		String jobName = "mdelapenya";

		JSONArray testReports = JenkinsConnectUtil.getBuilds(jobName);

		assertThat(testReports).isNotNull();
	}

}