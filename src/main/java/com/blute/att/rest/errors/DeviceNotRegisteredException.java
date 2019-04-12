package com.blute.att.rest.errors;

public class DeviceNotRegisteredException extends BadRequestAlertException{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DeviceNotRegisteredException() {
		super(ErrorConstants.DEVICE_NOT_REGISTERED, "Device is not registered!", "deviceManagement", "devicenotexists");
	}

}
