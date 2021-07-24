package com.company.models.DTO;

import java.util.List;

public class ProductDto {

    private int id;

    private int storeId;

    private String name;

    private String description;

    private int amount;

    private Double price;

    List<String> categoryList;

    public ProductDto(){}

    public ProductDto(String name, String description, int amount, Double price, List<String> categoryList, int storeId) {
        this.storeId = storeId;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.price = price;
        this.categoryList = categoryList;
    }

    public ProductDto(int id, String name, String description, int amount, Double price, List<String> categoryList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.price = price;
        this.categoryList = categoryList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    public int getId() {
        return id;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public Double getPrice() {
        return price;
    }

    public List<String> getCategoryList() {
        return categoryList;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", storeId=" + storeId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", categoryList=" + categoryList +
                '}';
    }
}
