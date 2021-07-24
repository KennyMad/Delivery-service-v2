package com.company.repository.impl;

import com.company.models.Customer;
import com.company.repository.CustomerDAO;


import java.util.*;

public class CustomerDAOImpl implements CustomerDAO {

    private List<Customer> customerList;

    public CustomerDAOImpl(List<Customer> customerList){
        this.customerList = customerList;
    }

    @Override
    public Collection readAll() {
        return customerList;
    }

    @Override
    public Customer getById(int id){
        return customerList.stream().filter(c -> c.getId() == id).limit(1).findFirst().orElse(null);
    }

    @Override
    public Customer remove(int id) {
        Customer customer = getById(id);
        if (customer != null)
            customerList.remove(customer);
        return customer;
    }

    @Override
    public void add(Customer customer) {
        customerList.add(customer);
    }
}
