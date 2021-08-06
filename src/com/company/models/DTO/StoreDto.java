package com.company.models.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
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

}
