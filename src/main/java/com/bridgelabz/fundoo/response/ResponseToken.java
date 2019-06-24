package com.bridgelabz.fundoo.response;

public class ResponseToken {
	private int statusCode;
	private String statusmessage;
	private String Token;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusmessage() {
		return statusmessage;
	}
	public void setStatusmessage(String statusmessage) {
		this.statusmessage = statusmessage;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	@Override
	public String toString() {
		return "ResponseToken [statusCode=" + statusCode + ", statusmessage=" + statusmessage + ", Token=" + Token
				+ "]";
	}
	public ResponseToken() {
		super();
		
	}
	
}
