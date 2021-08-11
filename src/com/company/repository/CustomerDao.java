package com.company.repository;

import com.company.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerDao extends JpaRepository<Customer,Integer> {

    @Override
    @Query("SELECT c FROM Customer c WHERE c.id = ?1")
    Customer getById(Integer integer);
}
