package bank.aop;

import bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class JMSAspect {
    @Autowired
    ILogger logger;

    @Pointcut("execution(* bank.jms.JMSSender.sendJMSMessage(..))")
    public void jmsPointcut() {}

    @After("jmsPointcut() && args(text)")
    public void afterSendingEmail(JoinPoint joinPoint, String text){
        logger.log("Message sent using JMS, method=" + joinPoint.getSignature().getName() + " text=" + text);
    }
}
