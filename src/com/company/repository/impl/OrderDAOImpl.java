package com.company.repository.impl;

import com.company.models.Order;
import com.company.repository.OrderDAO;

import java.util.Collection;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    List<Order> orderList;

    public OrderDAOImpl(List<Order> orderList){
        this.orderList = orderList;
    }

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
