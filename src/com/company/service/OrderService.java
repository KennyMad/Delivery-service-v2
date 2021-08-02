package com.company.service;

import com.company.exception.WrongIdException;
import com.company.models.DTO.OrderDto;

import java.util.Collection;

public interface OrderService {

    OrderDto add(OrderDto orderDTO) throws WrongIdException;

    void delete(int id) throws WrongIdException;

    OrderDto update(OrderDto orderDto) throws WrongIdException;

    OrderDto getById(int id) throws WrongIdException;

    Collection<OrderDto> getOrderList();

}
