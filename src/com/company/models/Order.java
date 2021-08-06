package com.company.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Map;

@Data
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

}
