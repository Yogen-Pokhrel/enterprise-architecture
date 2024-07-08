package jms;

import jakarta.jms.ConnectionFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.Optional;


@SpringBootApplication
@EnableJms
public class SpringJmsPersonSenderApplication implements CommandLineRunner {
	@Autowired
	JmsTemplate jmsTemplate;


	public static void main(String[] args)  {
		ConfigurableApplicationContext context = SpringApplication.run(SpringJmsPersonSenderApplication.class, args);
		context.close();
	}

	@Override
	public void run(String... args) throws Exception {
		Person person = new Person("Frank", "Brown");
		//convert person to JSON string
		ObjectMapper objectMapper = new ObjectMapper();
		String personAsString = objectMapper.writeValueAsString(person);

		System.out.println("Sending a JMS message:" + personAsString);
		jmsTemplate.convertAndSend("testQueue",personAsString);

		sendCalculatorRequest(new Calculator(12, CalculatorOperation.ADD));
		sendCalculatorRequest(new Calculator(2, CalculatorOperation.MULTIPLY));
		sendCalculatorRequest(new Calculator(3, CalculatorOperation.DIVIDE));
		sendCalculatorRequest(new Calculator(8, CalculatorOperation.ADD));
		sendCalculatorRequest(new Calculator(14, CalculatorOperation.SUBTRACT));
	}

	void sendCalculatorRequest(Calculator calculator) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String calculateRequest = objectMapper.writeValueAsString(calculator);

		System.out.println("Sending a JMS message:" + calculateRequest);
		jmsTemplate.convertAndSend("calculate",calculateRequest);
	}

}
