package com.module.vitalsignmodule.Advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ExecutionTimeTracker is used to track the execution time for controller method.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Aspect
@Component
public class ExecutionTimeTracker {

	Logger logger = LoggerFactory.getLogger(ExecutionTimeTracker.class);
	
	/**
	 * trackTime is used to calculate the execution time of the method.
	 * @param joinPoint
	 * @return Object
	 * @throws Throwable
	 *
	 */
	@Around("@annotation(com.module.vitalsignmodule.Advice.TrackExecutionTime)")
	public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object object = joinPoint.proceed();
		long endTime = System.currentTimeMillis();
		logger.info("Method name "+joinPoint.getSignature()+"time taken to execute : "+(endTime-startTime));
		return object;
	}
}
