package com.ea.EnterpriseApplicationLesson2.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService{

    @Autowired
    IProductDAO productDAO;

    @Autowired
    IEmailSender emailSender;

    @Override
    public void addProduct(String name, double price) {
        Product product = new Product(name, price);
        productDAO.save(product);
        emailSender.sendEmail(Constants.ADMIN_EMAIL, "New product has been added");
    }
}
