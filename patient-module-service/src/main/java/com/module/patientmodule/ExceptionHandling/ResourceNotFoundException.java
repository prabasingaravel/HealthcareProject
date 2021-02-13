package com.module.patientmodule.ExceptionHandling;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ResourceNotFoundException is used for custom exception.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@ResponseStatus(reason = "PatientNotFound")
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -3043362665032549831L;

	public ResourceNotFoundException() {
	}

	public ResourceNotFoundException(final String message) {
		super(message);
	}
}