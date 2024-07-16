package com.eaproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.logging.LoggersEndpoint;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DemoSchedule {


    Logger logger = LoggerFactory.getLogger(DemoSchedule.class);

    @Scheduled(fixedRate = 120000)
    public void demoScheduleWarn() {
        logger.warn("I have been scheduled guys but I am a warn");
    }

    @Scheduled(fixedRate = 45000)
    public void demoScheduleError() {
        logger.error("I have been scheduled guys but I am a error");
    }

    @Scheduled(fixedRate = 70000)
    public void demoScheduleOther() {
        logger.info("I have been scheduled guys but I am a info");
        logger.trace("I have been scheduled guys but I am a trace");
        logger.debug("I have been scheduled guys but I am a debug");
    }
}
