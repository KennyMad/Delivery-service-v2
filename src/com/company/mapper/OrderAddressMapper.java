package com.company.mapper;

import com.company.models.DTO.OrderAddressDto;
import com.company.models.OrderAddress;
import org.mapstruct.Mapper;

@Mapper
public interface OrderAddressMapper {

    OrderAddressDto toDto(OrderAddress orderAddress);

    OrderAddress toEntity(OrderAddressDto orderAddressDTO);

}
