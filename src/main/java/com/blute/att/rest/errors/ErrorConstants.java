package com.blute.att.rest.errors;

import java.net.URI;

public class ErrorConstants {

	
    public static final String PROBLEM_BASE_URL = "https://www.jhipster.tech/problem";
    public static final URI EMAIL_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/email-already-used");
    public static final URI LOGIN_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/login-already-used");
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");
    public static final URI DEVICE_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/device-already-used");
    public static final URI ATTENDANCE_ALREADY_SET_TYPE = URI.create(PROBLEM_BASE_URL + "/attendance-already-set");
    public static final URI DEVICE_NOT_REGISTERED = URI.create(PROBLEM_BASE_URL + "/device-not-registered");
    public static final URI DEVICE_REGISTRATION_ERROR = URI.create(PROBLEM_BASE_URL + "/device-registration-failed");
    
}
