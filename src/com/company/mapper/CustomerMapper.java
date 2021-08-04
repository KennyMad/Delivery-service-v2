package com.company.mapper;

import com.company.models.Customer;
import com.company.models.DTO.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDto toDto(Customer customer);

    Customer toEntity(CustomerDto customerDTO);
}
