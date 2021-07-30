package com.company.config;

import com.company.models.Customer;
import com.company.models.Product;
import com.company.models.Store;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Properties;

@Configuration
@SpringBootApplication
@ComponentScan("com.company.service")
@ComponentScan("com.company.repository")
@ComponentScan("com.company.controller")
@PropertySource("resources/config.properties")
public class Config {

    @Autowired
    Environment environment;

    @Bean
    SessionFactory getSessionFactory(){
        Properties prop = new Properties();

        prop.setProperty("hibernate.connection.url", environment.getProperty("hibernate.connection.url"));

        prop.setProperty("dialect", environment.getProperty("dialect"));

        prop.setProperty("hibernate.connection.username", environment.getProperty("hibernate.connection.username"));
        prop.setProperty("hibernate.connection.password", environment.getProperty("hibernate.connection.password"));
        prop.setProperty("hibernate.connection.driver_class",environment.getProperty("hibernate.connection.driver_class"));
        prop.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));

        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.addProperties(prop);

        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(Store.class);
        return configuration.buildSessionFactory();
    }

}
