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

public class JenkinsUnstableJob extends JenkinsJob {

	public JenkinsUnstableJob(
		String jobName, String alias, String lastBuildStatus, String buildUrl) {

		this(jobName, alias, lastBuildStatus, buildUrl, 0);
	}

	public JenkinsUnstableJob(
		String jobName, String alias, String lastBuildStatus, String buildUrl,
		int failedTestCount) {

		super(jobName, alias, lastBuildStatus, buildUrl);

		this.failedTestCount = failedTestCount;
	}

	public int getFailedTestCount() {
		return failedTestCount;
	}

	private int failedTestCount;

}