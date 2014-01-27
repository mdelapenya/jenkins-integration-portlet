package com.liferay.ci.http;

public class AuthConnectionParams {

	public AuthConnectionParams(
		String password, String baseApiUrl, String user) {

		super();

		this.password = password;
		this.baseApiUrl = baseApiUrl;
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getBaseApiUrl() {
		return baseApiUrl;
	}

	public void setBaseApiUrl(String url) {
		this.baseApiUrl = url;
	}

	private String baseApiUrl;
	private String password;
	private String user;

}