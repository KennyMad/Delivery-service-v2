package com.company.repository;

import com.company.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product,Integer> {

    @Override
    @Query("SELECT p FROM Product p WHERE p.id = ?1")
    Product getById(Integer integer);
}
