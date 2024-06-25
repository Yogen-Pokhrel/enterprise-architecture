package bank.aop;

import bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect
@Component
public class DAOAspect {

    @Autowired
    ILogger logger;

    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void daoPointcut() {}

    @Before("daoPointcut()")
    public void beforeDaoMethods(JoinPoint joinPoint) {
        String arguments = Arrays.toString(joinPoint.getArgs());
        logger.log("Executing DAO method=" + joinPoint.getTarget().getClass().getSimpleName() + "." +joinPoint.getSignature().getName() + " with arguments=" + arguments);
    }
}
