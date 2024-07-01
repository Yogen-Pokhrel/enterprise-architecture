package bank.integration;

import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    public void sendEmail(String emailAddress, String message){
        System.out.println("Sending email to "+emailAddress+" , message = "+ message);
    }
}
