package com.ea.EnterpriseApplicationLesson2.customers;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class CustomerDAOMock implements ICustomerDAO {
    @Override
    public void save(Customer customer) {
        System.out.println("I am testing a new customer save method");
    }
}
