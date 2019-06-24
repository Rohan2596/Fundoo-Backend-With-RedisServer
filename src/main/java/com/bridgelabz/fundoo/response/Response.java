package com.bridgelabz.fundoo.response;

public class Response {
	private int statusCode;
	private String statusmessage;

	public Response() {
		super();
	}

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

	@Override
	public String toString() {
		return "Response [statusCode=" + statusCode + ", statusmessage=" + statusmessage + "]";
	}
}
