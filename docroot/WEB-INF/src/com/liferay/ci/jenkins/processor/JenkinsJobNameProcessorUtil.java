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

import com.liferay.ci.jenkins.util.PortletPropsValues;

/**
 * @author Manuel de la Peña
 */
public class JenkinsJobNameProcessorUtil {

	public static JenkinsJobNameProcessor getProcessor() throws Exception {
		if (_processor == null) {
			_initializeProcessor();
		}

		return _processor;
	}

	public static String process(String jobName) throws Exception {
		return getProcessor().process(jobName);
	}

	public static void setProcessor(JenkinsJobNameProcessor processor) {
		_processor = processor;
	}

	private JenkinsJobNameProcessorUtil() {
	}

	private static void _initializeProcessor() throws Exception {
		String processorClassName =
			PortletPropsValues.JOB_NAME_PROCESSOR_CLASSNAME;

		ClassLoader classLoader =
			JenkinsJobNameProcessorUtil.class.getClassLoader();

		Class<?> clazz = classLoader.loadClass(processorClassName);

		_processor = (AbstractJenkinsJobNameProcessor)clazz.newInstance();
	}

	private static JenkinsJobNameProcessor _processor;

}