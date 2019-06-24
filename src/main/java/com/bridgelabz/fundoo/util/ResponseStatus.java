package com.bridgelabz.fundoo.util;

import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.response.ResponseToken;

public class ResponseStatus {
public static Response statusinfo(String statusmessage,int statusCode) {
	Response response=new Response();
	response.setStatusCode(statusCode);
	response.setStatusmessage(statusmessage);
	return response;

}

public static ResponseToken tokenStatusInfo(String statusMessage,int statusCode,String token){
	ResponseToken tokenResponse = new ResponseToken();
	tokenResponse.setStatusCode(statusCode);
	tokenResponse.setStatusmessage(statusMessage);
	tokenResponse.setToken(token);

	return tokenResponse;
}
public static ResponseToken statusResponseInfo(String statusMessage, int statusCode) {
	ResponseToken response = new ResponseToken();
	response.setStatusCode(statusCode); 
	response.setStatusmessage(statusMessage);
	
	return response;
}

}
