package com.lab11;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab11.dto.request.CreateAccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public void send(String topic, Object message) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        String requestAsString = objectMapper.writeValueAsString(message);
        kafkaTemplate.send(topic, requestAsString);
    }
}
