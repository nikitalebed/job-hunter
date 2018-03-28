package com.jh.lebid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StartUpworkJobHunterApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartUpworkJobHunterApplication.class, args);
    }
}
