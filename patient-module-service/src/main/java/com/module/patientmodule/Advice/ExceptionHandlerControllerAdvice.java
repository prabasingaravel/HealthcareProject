package com.module.patientmodule.Advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.module.patientmodule.ExceptionHandling.ResourceNotFoundException;

/**
 * ExceptionHandlerControllerAdvice is used for Handle the Controller Exception
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleResourceNotFound(final ResourceNotFoundException exception) {
		return exception.getMessage();
	}
}
