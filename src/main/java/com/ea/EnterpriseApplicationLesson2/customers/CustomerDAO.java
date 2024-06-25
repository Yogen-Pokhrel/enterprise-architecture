package com.ea.EnterpriseApplicationLesson2.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("one")
@Component
public class CustomerDAO implements ICustomerDAO{

	private ILogger logger;

	@Autowired
	public CustomerDAO(Logger logger) {
		this.logger = logger;
	}

	public void save(Customer customer) {
		// simple sleep
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("CustomerDAO: saving customer "+customer.getName());
		logger.log("Customer is saved in the DB: "+ customer.getName() );
	}

}
