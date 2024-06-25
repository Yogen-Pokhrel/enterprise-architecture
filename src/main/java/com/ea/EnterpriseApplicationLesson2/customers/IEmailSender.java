package com.ea.EnterpriseApplicationLesson2.customers;

public interface IEmailSender {
	void sendEmail(String email, String message);
	String getOutgoingMailServer();
}