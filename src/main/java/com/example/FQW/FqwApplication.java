package com.example.FQW;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FqwApplication {

    public static void main(String[] args) {
        SpringApplication.run(FqwApplication.class, args);
    }

}