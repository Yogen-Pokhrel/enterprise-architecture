package jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CalculatorMessageListener {
    @Autowired
    Calculator calculator;

    @JmsListener(destination = "calculate")
    public void receiveCalculateRequest(final String calculatorData) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Calculator calculatorRequestData = objectMapper.readValue(calculatorData, Calculator.class);
            System.out.println("JMS receiver received message:" + calculator.getTotal() + " " + calculatorRequestData.getOperation() + " " + calculatorRequestData.getNumber()  + " = " + calculator.calculate(calculatorRequestData));

        } catch (IOException e) {
            System.out.println("JMS receiver: Cannot convert : " + calculatorData +" to a calculator object");
        }
    }

}
