package com.company.mapper;

import com.company.models.DTO.OrderDto;
import com.company.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "orderAddress", target = "orderAddressDto")
    OrderDto toOrderDto(Order order);

    @Mapping(source = "orderAddressDto", target = "orderAddress")
    Order toOrder(OrderDto orderDto);
}
