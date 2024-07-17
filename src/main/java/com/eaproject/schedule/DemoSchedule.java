package com.eaproject.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DemoSchedule {

    Logger logger = LoggerFactory.getLogger(DemoSchedule.class);

    @Scheduled(fixedRate = 45000)
    public void demoScheduleError() {
        logger.error("Scheduled error");
    }
}
