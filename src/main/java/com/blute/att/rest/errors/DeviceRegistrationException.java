package com.blute.att.rest.errors;

public class DeviceRegistrationException extends BadRequestAlertException {

	public DeviceRegistrationException() {
		super(ErrorConstants.DEVICE_REGISTRATION_ERROR, "Unable to register device", "deviceManagement", "deviceerror");
		// TODO Auto-generated constructor stub
	}

	
	
}
	
