package com.lab12;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "demo")
@Getter
@Setter
public class ApplicationProperties {
    private String applicationName;
    private String version;
    private String serverUrl;
    private String serverName;
    private List<String> countries;
    private User user = new User();

    @Getter
    @Setter
    class User {
        private String firstName;
        private String LastName;
        private String userName;
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
