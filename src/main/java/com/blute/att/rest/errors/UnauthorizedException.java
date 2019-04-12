package com.blute.att.rest.errors;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnauthorizedException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2219258977072305488L;
	
	protected static MessageSourceAccessor message = SpringSecurityMessageSource.getAccessor();
	
	public UnauthorizedException() {
		super(message.getMessage("AbstractAccessDecissionManager.accessDenied","Access Is Denied"));
	}
	
	public UnauthorizedException(String message) {
		super(message);
	}
}
