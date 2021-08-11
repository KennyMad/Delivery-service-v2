package com.company.config;

import com.company.mapper.*;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@SpringBootApplication
@ComponentScan("com.company")
@EntityScan("com.company.models")
@EnableJpaRepositories("com.company.repository")
@EnableScheduling
public class Config {

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
