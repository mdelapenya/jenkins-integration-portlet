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

import javax.xml.bind.DatatypeConverter;

import com.liferay.ci.jenkins.util.PortletPropsValues;
import com.liferay.portal.kernel.util.Base64;

/**
 * 
 * @author Manuel de la Peña
 */
public abstract class BaseConnectImpl {

	public BaseConnectImpl() throws IOException {
		super();

		String user = PortletPropsValues.HTTP_BASIC_AUTH_USER;
		String password = PortletPropsValues.HTTP_BASIC_AUTH_PASSWORD;
		String baseApiURL = PortletPropsValues.JENKINS_BASE_API_URL;

		_connectionParams = new AuthConnectionParams(
			password, baseApiURL, user);
	}

	protected InputStream connect(
			AuthConnectionParams authParams, String urlSuffix,
			boolean appendUrlPrefix)
		throws IOException {

		if (authParams != null) {
			_connectionParams = authParams;
		}

		String user = _connectionParams.getUser();
		String password = _connectionParams.getPassword();

		String connectionURL = "";

		if (appendUrlPrefix) {
			connectionURL += authParams.getBaseApiUrl();
		}

		connectionURL += urlSuffix;

		URL url = new URL(connectionURL);

		URLConnection uc = url.openConnection();

		String userpass = user + " : " + new String(Base64.decode(password));

		String encodedUserPass = new String(Base64.encode(userpass.getBytes()));

		Authenticator.setDefault(new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				String decodedPassword = new String(
					Base64.decode(_connectionParams.getPassword()));

				return new PasswordAuthentication(
					_connectionParams.getUser(),
					decodedPassword.toCharArray());
			}
		});
	
		String basicAuth = "Basic " + DatatypeConverter.printBase64Binary(
			encodedUserPass.getBytes());

		uc.setRequestProperty ("Authorization", basicAuth);

		return uc.getInputStream();
	}

	protected AuthConnectionParams _connectionParams;

}