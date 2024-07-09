package bank.aspect;

import bank.domain.AccountTrace;
import bank.repository.AccountTraceRepository;
import jakarta.transaction.Transactional;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AccountOperationAspect {

    @Autowired
    private AccountTraceRepository accountTraceRepository;

    @AfterReturning(pointcut = "execution(* bank.service.AccountService.deposit(..))")
    @Transactional
    public void logDeposit(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        long accountNumber = (long) args[0];
        double amount = (double) args[1];

        AccountTrace trace = new AccountTrace(LocalDateTime.now(), accountNumber, "Deposit", amount);
        accountTraceRepository.save(trace);
    }

    @AfterReturning(pointcut = "execution(* bank.service.AccountService.withdraw(..))")
    @Transactional
    public void logWithdraw(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        long accountNumber = (long) args[0];
        double amount = (double) args[1];

        AccountTrace trace = new AccountTrace(LocalDateTime.now(), accountNumber, "Withdraw", amount);
        accountTraceRepository.save(trace);
    }

    @AfterReturning(pointcut = "execution(* bank.service.AccountService.transferFunds(..))")
    @Transactional
    public void logTransferFunds(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        long fromAccountNumber = (long) args[0];
        long toAccountNumber = (long) args[1];
        double amount = (double) args[2];

        AccountTrace traceFrom = new AccountTrace(LocalDateTime.now(), fromAccountNumber, "Transfer Out", amount);
        accountTraceRepository.save(traceFrom);

        AccountTrace traceTo = new AccountTrace(LocalDateTime.now(), toAccountNumber, "Transfer In", amount);
        accountTraceRepository.save(traceTo);
    }
}
