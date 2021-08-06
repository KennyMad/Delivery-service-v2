package com.company.models.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    private int storeId;

    private String name;

    private String description;

    private int amount;

    private Double price;

    List<String> categoryList;

}
