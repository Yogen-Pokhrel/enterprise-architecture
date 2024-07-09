package bank.jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.JMSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JMSSender implements IJMSSender{

	@Autowired
	JmsTemplate jmsTemplate;

	@Override
	public void sendJMSMessage(Transaction transaction) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String transactionAsString = objectMapper.writeValueAsString(transaction);

		System.out.println("Sending a JMS message:" + transactionAsString);
		jmsTemplate.convertAndSend("transaction",transactionAsString);
	}

	public void sendJMSMessage (String text){
		System.out.println("JMSSender: sending JMS message ="+text);
	}

}
