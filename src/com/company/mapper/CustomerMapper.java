package com.company.mapper;

import com.company.models.Customer;
import com.company.models.DTO.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CustomerMapper {

    @Mapping(source = "customerName", target = "name")
    CustomerDto toDto(Customer customer);

    @Mapping(source = "name", target = "customerName")
    Customer toEntity(CustomerDto customerDTO);
}
