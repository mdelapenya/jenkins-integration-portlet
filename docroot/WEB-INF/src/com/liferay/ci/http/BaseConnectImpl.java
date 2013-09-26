package com.liferay.ci.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import javax.xml.bind.DatatypeConverter;

public abstract class BaseConnectImpl {

	public BaseConnectImpl() throws IOException {
		super();

		InputStream is = getClass().getResourceAsStream(
			"connection.properties");

		Properties properties = new Properties();
		properties.load(is);

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