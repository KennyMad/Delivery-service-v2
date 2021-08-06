package com.company.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product implements Cloneable{

    @Id
    private int id;

    @Column(name = "store_id")
    private int storeId;

    private String name;

    private String description;

    private int amount;

    private Double price;

    @Column
    @Enumerated
    @ElementCollection(targetClass = ProductCategory.class, fetch = FetchType.EAGER)
    List<ProductCategory> categories;

    @Override
    public Product clone() throws CloneNotSupportedException {
        return (Product)super.clone();
    }
}
