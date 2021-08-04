package com.company.mapper;

import com.company.models.DTO.OrderDto;
import com.company.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {OrderAddressMapper.class})
public interface OrderMapper {

    @Mapping(source = "orderAddress", target = "orderAddressDto")
    OrderDto toDto(Order order);

    @Mapping(source = "orderAddressDto", target = "orderAddress")
    Order toEntity(OrderDto orderDto);
}
