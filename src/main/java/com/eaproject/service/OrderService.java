package com.eaproject.service;

import com.eaproject.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public String createOrder() {
        String salesData = String.format("Sales data - product: %s, category: %s, quantity: %d, price: %d",
                Utils.getRandomProductTitle(),
                Utils.getRandomCategory(),
                Utils.getRandomNumber(1, 20),
                Utils.getRandomNumber(20, 4500));
        logger.info(salesData);
        return salesData;
    }

}
