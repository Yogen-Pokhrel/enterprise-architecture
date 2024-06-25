package com.ea.EnterpriseApplicationLesson2.customers.aop;

import com.ea.EnterpriseApplicationLesson2.customers.EmailSender;
import com.ea.EnterpriseApplicationLesson2.customers.IEmailSender;
import com.ea.EnterpriseApplicationLesson2.customers.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class EmailAspect {

    @Autowired
    ILogger logger;

    @Pointcut("execution(* com.ea.EnterpriseApplicationLesson2.customers.EmailSender.sendEmail(..))")
    public void emailPointcut() {}

    @After("emailPointcut() && args(email, message)")
    public void afterSendingEmail(JoinPoint joinPoint, String email, String message){
        IEmailSender emailSender = (EmailSender) joinPoint.getTarget();
        logger.log("method=" + joinPoint.getSignature().getName() + " address=" + email + " message=" + message + " Outgoing mail server=" + emailSender.getOutgoingMailServer());
    }
}
