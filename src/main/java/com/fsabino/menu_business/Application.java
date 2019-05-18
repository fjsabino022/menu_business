package com.fsabino.menu_business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.fsabino.menu_business.config")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
