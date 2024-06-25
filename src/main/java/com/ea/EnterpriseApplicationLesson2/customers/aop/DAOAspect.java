package com.ea.EnterpriseApplicationLesson2.customers.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class DAOAspect {
    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void daoPointcut() {}

    @Around("daoPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        System.out.println("Executed method=" + joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName() + " Execution time=" + stopWatch.getTotalTimeMillis() +" ms");
        return result;
    }
}
