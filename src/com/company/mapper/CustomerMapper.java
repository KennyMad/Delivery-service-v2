package com.company.mapper;

import com.company.models.Customer;
import com.company.models.DTO.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);

    CustomerDto toDTO(Customer customer);

    Customer toCustomer(CustomerDto customerDTO);
}
