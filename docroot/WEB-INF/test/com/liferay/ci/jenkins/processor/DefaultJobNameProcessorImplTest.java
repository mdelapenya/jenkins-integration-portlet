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

import static org.fest.assertions.Assertions.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class DefaultJobNameProcessorImplTest {

	@BeforeClass
	public static void setUpClass() throws Exception {
		_processor = new DefaultJobNameProcessorImpl();
	}

	@Test
	public void testProcess() {
		String actualJobName = _processor.process("jobnamewitnodashes");

		assertThat(actualJobName).isEqualTo("jobnamewitnodashes");
	}

	@Test
	public void testProcessWithDashes() {
		String actualJobName = _processor.process("job-name-with-dashes");

		assertThat(actualJobName).isEqualTo("job name with dashes");
	}

	private static DefaultJobNameProcessorImpl _processor;

}