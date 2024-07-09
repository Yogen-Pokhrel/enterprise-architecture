package bank.service;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix="spring.mail")
public class MailProperties {
    String host;
    String username;
    String password;
}