package com.company.application;

import com.company.config.Config;
import com.company.models.DTO.CustomerDto;
import com.company.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        context.getBean(CustomerService.class).add(new CustomerDto("Vasya","Have bike"));
        context.getBean(CustomerService.class).getCustomerList().forEach(System.out::println);
    }
}
