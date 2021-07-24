package com.company.mapper;

import com.company.models.DTO.OrderAddressDto;
import com.company.models.OrderAddress;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderAddressMapper {

    OrderAddressMapper ORDER_ADDRESS_MAPPER = Mappers.getMapper(OrderAddressMapper.class);

    OrderAddressDto toOrderAddressDto(OrderAddress orderAddress);

    OrderAddress toOrderAddress(OrderAddressDto orderAddressDTO);

}
