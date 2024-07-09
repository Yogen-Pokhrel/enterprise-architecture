package bank.jms;

import jakarta.jms.JMSException;

public interface IJMSSender {
	public void sendJMSMessage (Transaction transaction) throws Exception;
	public void sendJMSMessage (String text);
}
