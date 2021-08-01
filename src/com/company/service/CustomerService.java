package com.company.service;

import com.company.exception.WrongIdException;
import com.company.models.DTO.CustomerDto;

import java.util.Collection;

public interface CustomerService {

    CustomerDto add(CustomerDto customerDTO);

    void delete(int id) throws WrongIdException;

    CustomerDto update(CustomerDto customerDTO) throws WrongIdException;

    Collection<CustomerDto> getCustomerList();

}
