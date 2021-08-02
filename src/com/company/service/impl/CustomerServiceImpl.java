package com.company.service.impl;

import com.company.exception.WrongIdException;
import com.company.models.Customer;
import com.company.models.DTO.CustomerDto;
import com.company.mapper.CustomerMapper;
import com.company.repository.CustomerDao;
import com.company.service.CustomerService;
import com.company.utils.impl.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public CustomerDto add(CustomerDto customerDto){
        Customer customer = CustomerMapper.CUSTOMER_MAPPER.toCustomer(customerDto);
        customer.setId(SequenceGenerator.getFreeCustomerId(customerDao.readAll()));
        return CustomerMapper.CUSTOMER_MAPPER.toDTO(customerDao.add(customer));
    }

    @Override
    public void delete(int id) throws WrongIdException{
        if(customerDao.remove(id) == null)
            throw new WrongIdException(id);
    }

    @Override
    public CustomerDto update(CustomerDto customerDto) throws WrongIdException{
        Customer updatedCustomer = CustomerMapper.CUSTOMER_MAPPER.toCustomer(customerDto);

        if (customerDao.getById(updatedCustomer.getId()) == null)
            throw new WrongIdException(updatedCustomer.getId());

        return CustomerMapper.CUSTOMER_MAPPER.toDTO(customerDao.update(updatedCustomer));
    }

    @Override
    public CustomerDto getById(int id) throws WrongIdException{
        Customer customer = customerDao.getById(id);

        if (customer == null){
            throw new WrongIdException(id);
        }

        return CustomerMapper.CUSTOMER_MAPPER.toDTO(customer);
    }

    @Override
    public Collection<CustomerDto> getCustomerList() {
        return customerDao.readAll().stream()
                .map(CustomerMapper.CUSTOMER_MAPPER::toDTO)
                .collect(Collectors.toList());
    }
}
