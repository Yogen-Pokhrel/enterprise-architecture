package com.ea.EnterpriseApplicationLesson2;

import com.ea.EnterpriseApplicationLesson2.customers.CustomerService;
import com.ea.EnterpriseApplicationLesson2.customers.ICustomerService;
import com.ea.EnterpriseApplicationLesson2.customers.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnterpriseApplicationLesson2Application implements CommandLineRunner {

	@Autowired
	ICustomerService customerService;

	public static void main(String[] args) {
		SpringApplication.run(EnterpriseApplicationLesson2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerService.addCustomer("Frank Brown", "fbrown@acme.com",
				"mainstreet 5", "Chicago", "60613");
	}
}
