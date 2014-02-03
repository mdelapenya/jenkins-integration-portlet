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

import com.liferay.ci.portlet.JenkinsIntegrationConstants;

/**
 * @author Manuel de la Peña
 */
public class JenkinsJob implements Comparable<JenkinsJob>{

	public JenkinsJob(String jobName, String lastBuildStatus) {
		this.jobName = jobName;
		this.lastBuildStatus = lastBuildStatus;

		if (lastBuildStatus.equals(
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_SUCCESS)) {

			internalLastBuildStatus = 0;
		}
		else if (lastBuildStatus.equals(
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_NULL)) {

			internalLastBuildStatus = -1;
		}
		else if (lastBuildStatus.equals(
			JenkinsIntegrationConstants.JENKINS_BUILD_STATUS_UNSTABLE)) {

			internalLastBuildStatus = -2;
		}
		else {
			internalLastBuildStatus = -3;
		}
	}

	@Override
	public int compareTo(JenkinsJob that) {
		if (this.internalLastBuildStatus > that.internalLastBuildStatus) {
			return 1;
		}
		else if (this.internalLastBuildStatus == that.internalLastBuildStatus) {
			return 0;
		}
		else {
			return -1;
		}
	}

	public String getJobName() {
		return jobName;
	}

	public String getLastBuildStatus() {
		return lastBuildStatus;
	}

	private int internalLastBuildStatus;
	private String jobName;
	private String lastBuildStatus;

}