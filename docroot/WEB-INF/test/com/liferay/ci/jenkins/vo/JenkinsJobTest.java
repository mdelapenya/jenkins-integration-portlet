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
		JenkinsJob pendingJob = new JenkinsJob(
			"pendingJob",
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_NULL);

		JenkinsJob failedJob = new JenkinsJob("failedJob", 
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_FAILURE);

		JenkinsJob[] sortedJobs = sort(
			new JenkinsJob[] {pendingJob, failedJob});

		assertThat(
			sortedJobs[0].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_FAILURE);
		assertThat(
			sortedJobs[1].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_NULL);
	}

	@Test
	public void testCompare2() {
		JenkinsJob pendingJob = new JenkinsJob(
			"pendingJob",
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_NULL);

		JenkinsJob failedJob = new JenkinsJob("failedJob", 
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_FAILURE);

		JenkinsJob[] sortedJobs = sort(
			new JenkinsJob[] {failedJob, pendingJob});

		assertThat(
			sortedJobs[0].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_FAILURE);
		assertThat(
			sortedJobs[1].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_NULL);
	}

	@Test
	public void testCompare3() {
		JenkinsJob successJob = new JenkinsJob(
			"successJob",
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_SUCCESS);

		JenkinsJob failedJob = new JenkinsJob("failedJob", 
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
			"successJob",
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_SUCCESS);

		JenkinsJob pendingJob = new JenkinsJob("pendingJob", 
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_NULL);

		JenkinsJob failedJob = new JenkinsJob("failedJob", 
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_FAILURE);

		JenkinsJob[] sortedJobs = sort(failedJob, successJob, pendingJob);

		assertThat(
			sortedJobs[0].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_FAILURE);
		assertThat(
			sortedJobs[1].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_NULL);
		assertThat(
			sortedJobs[2].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_SUCCESS);
	}

	@Test
	public void testCompare5() {
		JenkinsJob successJob = new JenkinsJob(
			"successJob",
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_SUCCESS);

		JenkinsJob pendingJob = new JenkinsJob("pendingJob", 
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_NULL);

		JenkinsJob unstableJob = new JenkinsJob("unstableJob", 
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_UNSTABLE);

		JenkinsJob failedJob = new JenkinsJob("failedJob", 
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_FAILURE);

		JenkinsJob[] sortedJobs = sort(
			successJob, pendingJob, unstableJob, failedJob);

		assertThat(
			sortedJobs[0].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_FAILURE);
		assertThat(
			sortedJobs[1].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_UNSTABLE);
		assertThat(
			sortedJobs[2].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_NULL);
		assertThat(
			sortedJobs[3].getLastBuildStatus()).isEqualTo(
				JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_SUCCESS);
	}

	protected JenkinsJob[] sort(JenkinsJob... jobs) {
		Arrays.sort(jobs);

		return jobs;
	}

}