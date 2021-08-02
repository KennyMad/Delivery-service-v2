package com.company.models.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

public class StoreDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private List<ProductDto> productDtoList;

    private String name;

    private String description;

    public StoreDto(){
        productDtoList = new ArrayList<>();
    }

    public StoreDto(String name, String description) {
        this.name = name;
        this.description = description;
        this.productDtoList = new ArrayList<>();
    }

    public StoreDto(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.productDtoList = new ArrayList<>();
    }

    public StoreDto(int id, List<ProductDto> productDtoList, String name, String description) {
        this.id = id;
        this.productDtoList = productDtoList;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }

    public void setProductDtoList(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
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
        return "StoreDto{" +
                "id=" + id +
                ", productDtoList=" + productDtoList +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
