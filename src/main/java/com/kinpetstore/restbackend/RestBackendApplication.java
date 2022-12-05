package com.kinpetstore.restbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class RestBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestBackendApplication.class, args);
    }

}
