package com.company.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "stores")
public class Store implements Cloneable{

    @Id
    private int id;

    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id")
    private List<Product> productList;

    private String name;

    private String description;

}
