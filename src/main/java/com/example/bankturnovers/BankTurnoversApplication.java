package com.example.bankturnovers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "com.example.bankturnovers")
public class BankTurnoversApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BankTurnoversApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BankTurnoversApplication.class, args);
    }

}
