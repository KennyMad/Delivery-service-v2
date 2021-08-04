package com.company.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table (name = "orders")
public class Order {

    @Id
    private int id;

    @Column(name = "customer_id")
    private int customerId;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private OrderAddress orderAddress;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_count_order",
    joinColumns = {@JoinColumn(name = "order_id",referencedColumnName = "id")})
    @MapKeyColumn(name = "product_id")
    @Column(name = "count")
    private Map<Integer, Integer> productIdCountMap;

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

    public Map<Integer, Integer> getProductIdCountMap() {
        return productIdCountMap;
    }

    public void setProductIdCountMap(Map<Integer, Integer> productIdCountMap) {
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
