package com.lab12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class Lab12Application implements CommandLineRunner {
	@Autowired
	ApplicationProperties applicationProperties;

	public static void main(String[] args) {
		SpringApplication.run(Lab12Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(applicationProperties);
	}
}
