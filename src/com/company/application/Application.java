package com.company.application;

import com.company.config.Config;
import org.springframework.boot.SpringApplication;

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Config.class,args);
    }
}
