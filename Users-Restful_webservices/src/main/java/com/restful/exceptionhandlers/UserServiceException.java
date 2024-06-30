package com.restful.exceptionhandlers;

public class UserServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1537853624420279919L;

	public UserServiceException(String message) {
		super(message);
	}

}
