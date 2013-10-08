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

package com.liferay.ci.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Manuel de la Peña
 */
public class JSONReaderImpl {

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();

		int cp;

		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}

		return sb.toString();
	}

	public static JSONObject readJSONFromURL(InputStream is)
		throws IOException, JSONException {

		try {
			BufferedReader rd = new BufferedReader(
				new InputStreamReader(is, Charset.forName("UTF-8")));

			String jsonText = readAll(rd);

			JSONObject json = new JSONObject(jsonText);

			return json;
		}
		finally {
			is.close();
		}
	}

}