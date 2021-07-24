package com.company.service.impl;

import com.company.exception.WrongIdException;
import com.company.models.Customer;
import com.company.models.DTO.CustomerDto;
import com.company.mapper.CustomerMapper;
import com.company.repository.CustomerDAO;
import com.company.service.CustomerService;
import com.company.utils.impl.SequenceGenerator;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDao;

    public CustomerServiceImpl(CustomerDAO customerDao){
        this.customerDao = customerDao;
    }

    @Override
    public void add(CustomerDto customerDTO){
        Customer customer = CustomerMapper.CUSTOMER_MAPPER.toCustomer(customerDTO);
        customer.setId(SequenceGenerator.getFreeCustomerId(customerDao.readAll()));
        customerDao.add(customer);
    }

    @Override
    public void delete(int id) throws WrongIdException{
        if(customerDao.remove(id) == null)
            throw new WrongIdException(id);
    }

    @Override
    public void update(CustomerDto customerDTO) throws WrongIdException{
        Customer updatedCustomer = CustomerMapper.CUSTOMER_MAPPER.toCustomer(customerDTO);
        Customer customer = customerDao.getById(updatedCustomer.getId());

        if (customer == null)
            throw new WrongIdException(updatedCustomer.getId());

        customer.setName(updatedCustomer.getName());
        customer.setAdditionalInformation(updatedCustomer.getAdditionalInformation());
    }

    @Override
    public Collection<CustomerDto> getCustomerList() {
        return customerDao.readAll().stream()
                .map(CustomerMapper.CUSTOMER_MAPPER::toDTO)
                .collect(Collectors.toList());
    }
}
