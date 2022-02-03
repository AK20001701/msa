package com.msa.module2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(com.msa.module2.GreetingController)")
    public void logParamsPointcut() {
    }

    @AfterReturning(pointcut = "logParamsPointcut()", returning = "result")
    public void logMethodCall(JoinPoint jp, Object result) {
        logger.info("Args:");
        for (Object o : jp.getArgs()) {
            logger.info(o.toString());
        }
        logger.info("Return: " + result.toString());
    }
}
