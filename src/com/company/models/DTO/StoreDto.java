package com.company.models.DTO;

import java.util.ArrayList;
import java.util.List;

public class StoreDto {

    private int id;

    private List<Integer> productListIds;

    private String name;

    private String description;

    public StoreDto(){
        productListIds = new ArrayList<>();
    }

    public StoreDto(String name, String description) {
        this.name = name;
        this.description = description;
        this.productListIds = new ArrayList<>();
    }

    public StoreDto(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.productListIds = new ArrayList<>();
    }

    public StoreDto(int id, List<Integer> productListIds, String name, String description) {
        this.id = id;
        this.productListIds = productListIds;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getProductListIds() {
        return productListIds;
    }

    public void setProductListIds(List<Integer> productListIds) {
        this.productListIds = productListIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "StoreDTO{" +
                "id=" + id +
                ", productListIds=" + productListIds +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
