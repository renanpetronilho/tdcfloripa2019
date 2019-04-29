/*
* Copyright 2018 V-Platform
*************************************************************
*Nome     : LoggingAspect.java
*Autor    : Builders
*Data     : 05/09/2018
*Empresa  : Platform Builders
*************************************************************
*/
package io.platformbuilders.paymentapi.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
public class LoggingAspect {

    @Around("@annotation(loggable)")
    public Object logMethodCall(ProceedingJoinPoint joinPoint, Loggable loggable) throws Throwable {
        return handleJoinPoint(joinPoint);
    }

    @Around("execution(* io.platformbuilders.paymentapi..*Service*.*(..)) || execution(* io.platformbuilders.paymentapi..*Resource*.*(..)) || execution(* io.platformbuilders.paymentapi..*Client*.*(..)) || execution(* io.platformbuilders.paymentapi..*Repository*.*(..))")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        return handleJoinPoint(joinPoint);
    }

    private Object handleJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        final Logger log = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
        final String methodName = joinPoint.getSignature().getName();
        final Object[] args = joinPoint.getArgs();
        final String argsStr = Arrays.stream(args).map(String::valueOf).collect(Collectors.joining(",", "[", "]"));

        log.info("[m=" + methodName + "] - Begin of " + methodName + " with arguments " + argsStr);

        final Object result = joinPoint.proceed();

        log.info("[m=" + methodName + "] - End of " + methodName + " resulting in " + String.valueOf(result));

        return result;
    }

}