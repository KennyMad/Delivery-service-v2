package com.company.service.impl;

import com.company.exception.WrongIdException;
import com.company.models.Customer;
import com.company.models.DTO.OrderDto;
import com.company.mapper.OrderMapper;
import com.company.models.Order;
import com.company.repository.CustomerDAO;
import com.company.repository.OrderDAO;
import com.company.service.OrderService;
import com.company.utils.impl.SequenceGenerator;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private OrderDAO orderDAO;

    @Override
    public void add(OrderDto orderDto) throws WrongIdException{
        Order order = OrderMapper.ORDER_MAPPER.toOrder(orderDto);
        order.setId(SequenceGenerator.getFreeOrderId(orderDAO.readAll()));

        Customer customer = customerDAO.getById(order.getCustomerId());

        if (customer == null)
            throw new WrongIdException(order.getCustomerId());
        orderDAO.add(order);
    }

    @Override
    public void delete(int id) throws WrongIdException {
        Order order = orderDAO.remove(id);
        if (order == null)
            throw new WrongIdException(id);
    }

    @Override
    public void update(OrderDto orderDto) throws WrongIdException {
        if (orderDAO.getById(orderDto.getId()) == null)
            throw new WrongIdException(orderDto.getId());

        if (customerDAO.getById(orderDto.getCustomerId()) == null)
            throw new WrongIdException(orderDto.getCustomerId());

        Order updatedOrder = OrderMapper.ORDER_MAPPER.toOrder(orderDto);
        orderDAO.update(updatedOrder);
    }

    @Override
    public Collection<OrderDto> getOrderList() {
        return orderDAO.readAll().stream()
                .map(OrderMapper.ORDER_MAPPER::toOrderDto)
                .collect(Collectors.toList());
    }
}
