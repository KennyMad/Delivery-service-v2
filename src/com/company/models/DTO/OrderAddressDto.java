package com.company.models.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OrderAddressDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    private String house;
    private String street;
    private String city;

}
