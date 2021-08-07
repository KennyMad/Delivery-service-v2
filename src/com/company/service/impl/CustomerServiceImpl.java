package com.company.service.impl;

import com.company.exception.WrongIdException;
import com.company.models.Customer;
import com.company.models.DTO.CustomerDto;
import com.company.mapper.CustomerMapper;
import com.company.repository.CustomerDao;
import com.company.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public CustomerDto add(CustomerDto customerDto){
        Customer customer = customerMapper.toEntity(customerDto);
        return customerMapper.toDto(customerDao.save(customer));
    }

    @Override
    public void delete(int id) throws WrongIdException{
        if (!customerDao.existsById(id)) {
            throw new WrongIdException(id);
        }
        customerDao.deleteById(id);
    }

    @Override
    public CustomerDto update(CustomerDto customerDto) throws WrongIdException{
        if (!customerDao.existsById(customerDto.getId())) {
            throw new WrongIdException(customerDto.getId());
        }
        return customerMapper.toDto(customerDao.save(customerMapper.toEntity(customerDto)));
    }

    @Override
    public CustomerDto getById(int id) throws WrongIdException{
        if (!customerDao.existsById(id)) {
            throw new WrongIdException(id);
        }
        return customerMapper.toDto(customerDao.getById(id));
    }

    @Override
    public Collection<CustomerDto> getCustomerList() {
        return customerDao.findAll().stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }
}
