package com.example.crmproject.aspect;

import java.util.logging.Logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class CRMAspect {

    @Pointcut("execution(* com.example.crmproject.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* com.example.crmproject.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("forControllerPackage() || forServicePackage()")
    private void forAppFlow() {
    }

    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint) {

        String theMethod = theJoinPoint.getSignature().toShortString();
        log.info("@Before: calling method: " + theMethod);

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {
            log.info("Argument: " + tempArg);
        }

    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult"
    )
    public void afterReturning(JoinPoint theJoinPoint, Object theResult) {

        String theMethod = theJoinPoint.getSignature().toShortString();
        log.info("@AfterReturning: from method: " + theMethod);

        log.info("Result: " + theResult);

    }
}