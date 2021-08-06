package com.company.models.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CustomerDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    private String name;

    private String additionalInformation;

}
