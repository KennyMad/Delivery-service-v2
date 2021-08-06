package com.company.models.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashMap;

@Data
public class OrderDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    private int customerId;

    private OrderAddressDto orderAddressDto;

    private HashMap<Integer, Integer> productIdCountMap;

}
