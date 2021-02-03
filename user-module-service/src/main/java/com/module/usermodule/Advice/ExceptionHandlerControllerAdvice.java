package com.module.usermodule.Advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.module.usermodule.ExceptionHandling.ResourceNotFoundException;

/**
 * ExceptionHandlerControllerAdvice is used for Handle the Controller Exception
 * @author Praba Singaravel
 *
 */
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody String handleResourceNotFound(final ResourceNotFoundException exception) {
		return exception.getMessage();
	}
}
