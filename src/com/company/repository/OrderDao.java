package com.company.repository;

import com.company.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDao extends JpaRepository<Order,Integer> {

    @Override
    @Query("SELECT o FROM Order o WHERE o.id = ?1")
    Order getById(Integer integer);
}
