package com.company.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order {

    private int id;

    private int customerId;

    private OrderAddress orderAddress;

    private HashMap<Integer, Integer> productIdCountMap;

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setOrderAddress(OrderAddress orderAddress) {
        this.orderAddress = orderAddress;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public OrderAddress getOrderAddress() {
        return orderAddress;
    }

    public HashMap<Integer, Integer> getProductIdCountMap() {
        return productIdCountMap;
    }

    public void setProductIdCountMap(HashMap<Integer, Integer> productIdCountMap) {
        this.productIdCountMap = productIdCountMap;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", orderAddress=" + orderAddress +
                ", productIdCountMap=" + productIdCountMap +
                '}';
    }
}
