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

package com.liferay.ci.jenkins.processor;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Manuel de la Peña
 */
public class DefaultJobNameProcessorImpl extends
	AbstractJenkinsJobNameProcessor {

	@Override
	public String process(String jobName) {
		if (Validator.isNull(jobName)) {
			return StringPool.BLANK;
		}

		String processedJobName = jobName.replace("liferay", StringPool.BLANK);

		processedJobName = processedJobName.replace(
			StringPool.DASH, StringPool.SPACE);

		return processedJobName.trim();
	}

}