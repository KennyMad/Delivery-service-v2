package com.company.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@ComponentScan("com.company.service")
@ComponentScan("com.company.repository")
@ComponentScan("com.company.controller")
public class Config {

}
