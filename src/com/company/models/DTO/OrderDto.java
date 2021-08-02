package com.company.models.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;

public class OrderDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    private int customerId;

    private OrderAddressDto orderAddressDto;

    private HashMap<Integer, Integer> productIdCountMap;

    public OrderDto(){}

    public OrderDto(int customerId, OrderAddressDto orderAddressDto, HashMap<Integer, Integer> productIdCountMap) {
        this.customerId = customerId;
        this.orderAddressDto = orderAddressDto;
        this.productIdCountMap = productIdCountMap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public OrderAddressDto getOrderAddressDto() {
        return orderAddressDto;
    }

    public void setOrderAddressDto(OrderAddressDto orderAddressDto) {
        this.orderAddressDto = orderAddressDto;
    }

    public HashMap<Integer, Integer> getProductIdCountMap() {
        return productIdCountMap;
    }

    public void setProductIdCountMap(HashMap<Integer, Integer> productIdCountMap) {
        this.productIdCountMap = productIdCountMap;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", orderAddressDTO=" + orderAddressDto +
                ", productIdCountMap=" + productIdCountMap +
                '}';
    }
}
