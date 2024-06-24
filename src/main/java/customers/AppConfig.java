package customers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CustomerDAO customerDAO(ILogger logger) {
        return new CustomerDAO(logger);
    }

    @Bean Logger logger(){
        return new Logger();
    }

    @Bean EmailSender emailSender(ILogger logger){
        return new EmailSender(logger);
    }

    @Bean
    public CustomerService customerService(ICustomerDAO customerDAO, IEmailSender emailSender){
        return new CustomerService(customerDAO, emailSender);
    }
}
