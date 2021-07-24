package com.company.repository.impl;

import com.company.models.Customer;
import com.company.repository.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.*;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private List<Customer> customerList = new ArrayList<>();

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
