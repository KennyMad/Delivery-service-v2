package com.company.config;

import com.company.mapper.*;
import com.company.models.*;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.hibernate.SessionFactory;
import org.mapstruct.factory.Mappers;
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
@ComponentScan("com.company.exception.handler")
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
        prop.setProperty("hibernate.current_session_context_class",environment.getProperty("hibernate.current_session_context_class"));

        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.addProperties(prop);

        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(Store.class);
        configuration.addAnnotatedClass(Order.class);
        configuration.addAnnotatedClass(OrderAddress.class);
        return configuration.buildSessionFactory();
    }


    @Bean
    public OpenAPI getOpenApi(){
        return new OpenAPI()
                .info(
                        new Info()
                        .title("Delivery service api")
                        .version("1.0.0")
                );
    }

    @Bean
    public CustomerMapper getCustomerMapper(){
        return Mappers.getMapper(CustomerMapper.class);
    }

    @Bean
    public OrderAddressMapper getOrderAddressMapper(){
        return Mappers.getMapper(OrderAddressMapper.class);
    }

    @Bean
    public OrderMapper getOrderMapper(){
        return Mappers.getMapper(OrderMapper.class);
    }

    @Bean
    public ProductMapper getProductMapper(){
        return Mappers.getMapper(ProductMapper.class);
    }

    @Bean
    public StoreMapper getStoreMapper(){
        return Mappers.getMapper(StoreMapper.class);
    }
}
