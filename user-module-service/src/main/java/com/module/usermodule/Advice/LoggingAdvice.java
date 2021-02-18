package com.module.usermodule.Advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * LoggingAdvice is used to print the log for controller method.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Aspect
@Component
public class LoggingAdvice {
	
	Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

	/**
	 * applicationLogger is used to generate log for every method execution.
	 * @param proceedingJoinPoint
	 * @return Object
	 * @throws Throwable
	 *
	 */
	@Around("@annotation(com.module.usermodule.Advice.TrackLogging)")
	public Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = proceedingJoinPoint.getSignature().getName();
		String className = proceedingJoinPoint.getTarget().getClass().toString();
		Object[] array = proceedingJoinPoint.getArgs();
		logger.info("method invoked " + className + " : " + methodName + "()" + "arguments : "
				+ mapper.writeValueAsString(array));
		Object object = proceedingJoinPoint.proceed();
		logger.info(className + " : " + methodName + "()" + "Response : " + mapper.writeValueAsString(object));
		return object;
	}

}
