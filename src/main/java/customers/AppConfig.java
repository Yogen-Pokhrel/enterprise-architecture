package customers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CustomerDAO customerDAO(Logger logger) {
        return new CustomerDAO(logger);
    }

    @Bean Logger logger(){
        return new Logger();
    }

    @Bean EmailSender emailSender(Logger logger){
        return new EmailSender(logger);
    }

    @Bean
    public CustomerService customerService(CustomerDAO customerDAO, EmailSender emailSender){
        return new CustomerService(customerDAO, emailSender);
    }
}
