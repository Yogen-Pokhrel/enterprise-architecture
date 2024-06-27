package com.ea.lab4part1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EaLab4Part1Application implements CommandLineRunner {
	@Autowired
	ApplicationClient applicationClient;

	public static void main(String[] args) {
		SpringApplication.run(EaLab4Part1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		applicationClient.runDepartmentActions();
		applicationClient.runBookActions();
		applicationClient.runPassengerActions();
		applicationClient.runSchoolActions();
	}
}
