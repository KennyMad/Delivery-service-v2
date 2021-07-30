package com.company.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stores")
public class Store implements Cloneable{

    @Id
    private int id;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "store_id")
    private List<Product> productList;

    private String name;

    private String description;

    public int getId() {
        return id;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Store clone() throws CloneNotSupportedException{
        return (Store) super.clone();
    }

    public Store(){}

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", productList=" + productList +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
