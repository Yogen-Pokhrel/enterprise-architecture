package bank.service;

import bank.domain.TraceRecord;
import bank.repositories.TraceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TraceService {
    @Autowired
    TraceRecordRepository traceRecordRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void traceRecord(String customerName, String accountNumber, boolean success){
        System.out.println("TRACE RECORD" + customerName + " " + accountNumber + " " + success);
        try {
            String message = success
                    ? "Customer " + customerName + " created with account " + accountNumber
                    : "Could not create customer " + customerName + " with account " + accountNumber;
            traceRecordRepository.save(new TraceRecord(message));
        } catch (Exception e) {
            System.err.println("Failed to save trace record: " + e.getMessage());
        }
    }
}
