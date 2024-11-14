package com.microLearnByDoing.department_service.custon_annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Around("@annotation(FlowLogger)")
	public Object logException(ProceedingJoinPoint joinPoint) throws Throwable{
		String methodName = joinPoint.getSignature().toShortString();
		
		logger.info("Entering Method "+methodName, joinPoint.getClass() );
		
		Object result;
		
		try {
			result = joinPoint.proceed();
		}
		finally {
			logger.info("Exiting Method "+methodName, joinPoint.getClass() );
		}
		return result;
	}
	
}
