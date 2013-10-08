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

package com.liferay.ci.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import javax.xml.bind.DatatypeConverter;

/**
 * 
 * @author Manuel de la Peña
 */
public abstract class BaseConnectImpl {

	public BaseConnectImpl() throws IOException {
		super();

		InputStream is = getClass().getResourceAsStream(
			"connection.properties");

		Properties properties = new Properties();
		properties.load(is);

		is = getClass().getResourceAsStream(
			"connection-ext.properties");

		if (is != null) {
			properties.load(is);
		}

		user = properties.getProperty("user");
		password = properties.getProperty("password");

		setBaseAPIURL(properties.getProperty("baseapiurl"));
	}

	protected InputStream connect(String apiURL) throws IOException {
		URL url = new URL(apiURL);
		URLConnection uc = url.openConnection();

		String userpass = user + " : " + password;

		Authenticator.setDefault(new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(
					user, password.toCharArray());
			}
		});
	
		String basicAuth = "Basic " + DatatypeConverter.printBase64Binary(
			userpass.getBytes());

		uc.setRequestProperty ("Authorization", basicAuth);

		return uc.getInputStream();
	}

	protected void setAuthConfiguration(String newUser, String newPassword) {
		user = newUser;
		password = newPassword;
	}

	protected abstract void setBaseAPIURL(String baseApiURL);

	private static String user;
	private static String password;

}