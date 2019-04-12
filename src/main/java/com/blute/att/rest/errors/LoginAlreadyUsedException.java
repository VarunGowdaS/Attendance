package com.blute.att.rest.errors;


public class LoginAlreadyUsedException extends BadRequestAlertException {

	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginAlreadyUsedException() {
	        super(ErrorConstants.DEVICE_ALREADY_USED_TYPE, "Device already used!", "userManagement", "deviceexists");
	    }
	
}
