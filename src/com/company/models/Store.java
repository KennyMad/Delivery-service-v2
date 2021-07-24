package com.company.models;

import java.util.List;

public class Store implements Cloneable{

    private int id;

    private List<Integer> productListIds;

    private String name;

    private String description;

    public int getId() {
        return id;
    }

    public List<Integer> getProductListIds() {
        return productListIds;
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

    public void setProductListIds(List<Integer> productListIds) {
        this.productListIds = productListIds;
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

        StringBuilder productList = new StringBuilder();

        this.productListIds.forEach(id -> productList.append(id).append(" "));

        return "Store{" +
                "id=" + id +
                ", productList=[" + productList +
                "], name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
