package com.company.service;

import com.company.exception.WrongIdException;
import com.company.models.DTO.CustomerDto;

import java.util.Collection;

public interface CustomerService {

    void add(CustomerDto customerDTO);

    void delete(int id) throws WrongIdException;

    void update(CustomerDto customerDTO) throws WrongIdException;

    Collection<CustomerDto> getCustomerList();

}
