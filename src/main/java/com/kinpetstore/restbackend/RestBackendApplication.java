package com.kinpetstore.restbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class RestBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestBackendApplication.class, args);
    }

}
