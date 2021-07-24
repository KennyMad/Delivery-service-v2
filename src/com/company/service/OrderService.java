package com.company.service;

import com.company.exception.WrongIdException;
import com.company.models.DTO.OrderDto;

import java.util.Collection;

public interface OrderService {

    void add(OrderDto orderDTO) throws WrongIdException;

    Collection<OrderDto> getOrderList();

}
