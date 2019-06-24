package com.bridgelabz.fundoo.exception;

public class UserException  extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	int code;
	public UserException(String message, int code) {
		super(message);
	this.code=code;
	}
	
	public UserException(String message) {
		super();
		
		
	}
}
