package com.ea.EnterpriseApplicationLesson2.customers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailSender implements IEmailSender {
	@Value("${app.outgoing.mail-server}")
	String outgoingMailServer;

	@Autowired
	private ILogger logger;

	public String getOutgoingMailServer() {
		return outgoingMailServer;
	}

	public void sendEmail (String email, String message){
		System.out.println("EmailSender: sending '"+message+"' to "+email );
		logger.log("Email is sent: message= "+message +" , emailaddress ="+ email +" , outgoingMailServer= "+ outgoingMailServer  );
	}
}
