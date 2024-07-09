package com.lab12;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@ConfigurationProperties(prefix = "demo")
@Getter
@Setter
@Validated
public class ApplicationProperties {
    @NotBlank
    private String applicationName;

    @NotBlank
    private String version;

    @NotBlank
    private String serverUrl;
    private String serverName;
    private List<String> countries;
    private User user = new User();

    @Getter
    @Setter
    class User {
        private String firstName;
        private String LastName;

        @NotBlank
        @Size(min = 8, max = 15, message = "Username must be between 8 and 15 characters")
        private String userName;

        @NotBlank
        @Size(min = 8, max = 15, message = "Password must be between 8 and 15 characters")
        private String password;

        @Override
        public String toString() {
            return "User{" +
                    "\n\t\tfirstName='" + firstName + '\'' +
                    ", \n\t\tLastName='" + LastName + '\'' +
                    ", \n\t\tuserName='" + userName + '\'' +
                    ", \n\t\tpassword='" + password + '\'' +
                    "\n\t}";
        }
    }

    @Override
    public String toString() {
        return "ApplicationProperties{" +
                "\n\tapplicationName='" + applicationName + '\'' +
                ", \n\tversion='" + version + '\'' +
                ", \n\tserverUrl='" + serverUrl + '\'' +
                ", \n\tserverName='" + serverName + '\'' +
                ", \n\tcountries=" + countries +
                ", \n\tuser=" + user +
                "\n}";
    }
}
