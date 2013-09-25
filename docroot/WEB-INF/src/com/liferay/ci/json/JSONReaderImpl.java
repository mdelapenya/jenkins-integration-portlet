package com.liferay.ci.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

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