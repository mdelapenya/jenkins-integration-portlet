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

package com.liferay.ci.jenkins.vo;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;

import com.liferay.ci.portlet.JenkinsIntegrationConstants;

/**
 * @author Manuel de la Peña
 */
public class JenkinsJobTest {

	@Test
	public void testCompare1() {
		JenkinsJob unstableJob = new JenkinsJob(
			"unstableJobName", "unstableJob",
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_UNSTABLE);

		JenkinsJob failedJob = new JenkinsJob(
			"failedJobName", "failedJob",
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_FAILURE);

		JenkinsJob[] sortedJobs = sort(
			new JenkinsJob[] {unstableJob, failedJob});

		assertThat(
			sortedJobs[0].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_FAILURE);
		assertThat(
			sortedJobs[1].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_UNSTABLE);
	}

	@Test
	public void testCompare2() {
		JenkinsJob unstableJob = new JenkinsJob(
			"unstableJobName", "unstableJob",
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_UNSTABLE);

		JenkinsJob failedJob = new JenkinsJob(
			"failedJobName", "failedJob",
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_FAILURE);

		JenkinsJob[] sortedJobs = sort(
			new JenkinsJob[] {failedJob, unstableJob});

		assertThat(
			sortedJobs[0].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_FAILURE);
		assertThat(
			sortedJobs[1].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_UNSTABLE);
	}

	@Test
	public void testCompare3() {
		JenkinsJob successJob = new JenkinsJob(
			"successJobName", "successJob",
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_SUCCESS);

		JenkinsJob failedJob = new JenkinsJob(
			"failedJobName", "failedJob",
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_FAILURE);

		JenkinsJob[] sortedJobs = sort(failedJob, successJob);

		assertThat(
			sortedJobs[0].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_FAILURE);
		assertThat(
			sortedJobs[1].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_SUCCESS);
	}

	@Test
	public void testCompare4() {
		JenkinsJob successJob = new JenkinsJob(
			"successJobName", "successJob",
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_SUCCESS);

		JenkinsJob unstableJob = new JenkinsJob(
			"unstableJobName", "unstableJob",
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_UNSTABLE);

		JenkinsJob failedJob = new JenkinsJob(
			"failedJobName", "failedJob",
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_FAILURE);

		JenkinsJob[] sortedJobs = sort(failedJob, successJob, unstableJob);

		assertThat(
			sortedJobs[0].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_FAILURE);
		assertThat(
			sortedJobs[1].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_UNSTABLE);
		assertThat(
			sortedJobs[2].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_SUCCESS);
	}

	@Test
	public void testCompare5() {
		JenkinsJob successJob = new JenkinsJob(
			"successJobName", "successJob",
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_SUCCESS);

		JenkinsJob abortedJob = new JenkinsJob(
			"abortedJobName", "abortedJob",
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_ABORTED);

		JenkinsJob unstableJob = new JenkinsJob(
			"unstableJobName", "unstableJob",
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_UNSTABLE);

		JenkinsJob failedJob = new JenkinsJob(
			"failedJobName", "failedJob",
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_FAILURE);

		JenkinsJob[] sortedJobs = sort(
			successJob, abortedJob, unstableJob, failedJob);

		assertThat(
			sortedJobs[0].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_FAILURE);
		assertThat(
			sortedJobs[1].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_ABORTED);
		assertThat(
			sortedJobs[2].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_UNSTABLE);
		assertThat(
			sortedJobs[3].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_SUCCESS);
	}

	@Test
	public void testCompare6() {
		JenkinsJob successJob1 = new JenkinsJob(
			"successJobName1", "successJob1",
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_SUCCESS);

		JenkinsJob successJob2 = new JenkinsJob(
			"successJobName2", "successJob2",
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_SUCCESS);

		JenkinsJob[] sortedJobs = sort(successJob1, successJob2);

		assertThat(sortedJobs[0].getJobName()).isEqualTo("successJob1");
		assertThat(sortedJobs[1].getJobName()).isEqualTo("successJob2");
	}

	@Test
	public void testCompare7() {
		JenkinsJob successJob1 = new JenkinsJob(
			"successJobName1", "successJob1",
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_SUCCESS);

		JenkinsJob successJob2 = new JenkinsJob(
			"successJobName2", "successJob2",
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_SUCCESS);

		JenkinsJob[] sortedJobs = sort(successJob2, successJob1);

		assertThat(sortedJobs[0].getJobName()).isEqualTo("successJob1");
		assertThat(sortedJobs[1].getJobName()).isEqualTo("successJob2");
	}

	protected JenkinsJob[] sort(JenkinsJob... jobs) {
		Arrays.sort(jobs);

		return jobs;
	}

}