package com.company.models;

import java.util.List;

public class Product implements Cloneable{

    private int id;

    private int storeId;

    private String name;

    private String description;

    private int amount;

    private Double price;

    List<ProductCategory> categories;

    public int getId() {
        return id;
    }

    public int getStoreId() {
        return storeId;
    }

    public int getAmount() {
        return amount;
    }

    public List<ProductCategory> getCategories() {
        return categories;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public void setAmount(int amount) {
        if (amount > 0)
            this.amount = amount;
        else
            this.amount = 0;
    }

    public void setCategories(List<ProductCategory> categories) {
        this.categories = categories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        if (price > 0)
            this.price = price;
        else
            this.price = 0.0;
    }

    public Product(){}

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", categories=" + categories +
                '}';
    }

    @Override
    public Product clone() throws CloneNotSupportedException {
        return (Product)super.clone();
    }
}
