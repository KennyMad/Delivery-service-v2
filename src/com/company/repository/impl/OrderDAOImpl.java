package com.company.repository.impl;

import com.company.models.Order;
import com.company.repository.OrderDAO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    List<Order> orderList = new ArrayList<>();

    @Override
    public Collection<Order> readAll() {
        return orderList;
    }

    @Override
    public Order getById(int id){
        return orderList.stream().filter(o -> o.getId() == id).limit(1).findFirst().orElse(null);
    }

    @Override
    public Order remove(int id) {
        Order order = getById(id);
        if (order != null)
            orderList.remove(order);
        return order;
    }

    @Override
    public void add(Order order) {
        orderList.add(order);
    }
}
