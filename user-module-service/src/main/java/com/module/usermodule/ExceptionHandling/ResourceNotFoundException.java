package com.module.usermodule.ExceptionHandling;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ResourceNotFoundException is used for custom exception.
 * @author Praba Singaravel
 *
 */
@ResponseStatus(reason = "UserNotFound")
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -3043362665032549831L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(final String message) {
		super(message);
	}
}