package com.eaproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.logging.LoggersEndpoint;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DemoSchedule {


    Logger logger = LoggerFactory.getLogger(DemoSchedule.class);

    @Scheduled(fixedRate = 5000)
    public void demoSchedule() {
        logger.info("I have been scheduled guys but I am a info");
        logger.trace("I have been scheduled guys but I am a trace");
        logger.debug("I have been scheduled guys but I am a debug");
        logger.error("I have been scheduled guys but I am a error");
        logger.warn("I have been scheduled guys but I am a warn \n\n");
    }
}
