package bank;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import bank.service.BankService;

@SpringBootApplication
public class Application implements CommandLineRunner{
	
	@Autowired
	BankService bankService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//separate try catch such that if one fails it doesnot affect the other
		try{
			bankService.createCustomerAndAccount(12, "Jack Bauer", "jbauer@yahoo.com","1223");
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}

		try{
			bankService.createCustomerAndAccount(14, "Frank Brown", "frankbrown@gmail.com","1248");
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}

		try{
			bankService.createCustomerAndAccount(16, "Yogen Pokhrel", "yogen@gmail.com","1249");
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}
}
