package com.company.service.impl;

import com.company.exception.WrongIdException;
import com.company.models.Customer;
import com.company.models.DTO.OrderDto;
import com.company.mapper.OrderMapper;
import com.company.models.Order;
import com.company.repository.CustomerDao;
import com.company.repository.OrderDao;
import com.company.service.OrderService;
import com.company.utils.impl.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerDao customerDAO;
    @Autowired
    private OrderDao orderDAO;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderDto add(OrderDto orderDto) throws WrongIdException{
        Order order = orderMapper.toEntity(orderDto);
        order.setId(SequenceGenerator.getFreeOrderId(orderDAO.readAll()));

        Customer customer = customerDAO.getById(order.getCustomerId());

        if (customer == null)
            throw new WrongIdException(order.getCustomerId());
        return orderMapper.toDto(orderDAO.add(order));
    }

    @Override
    public void delete(int id) throws WrongIdException {
        Order order = orderDAO.remove(id);
        if (order == null)
            throw new WrongIdException(id);
    }

    @Override
    public OrderDto update(OrderDto orderDto) throws WrongIdException {
        if (orderDAO.getById(orderDto.getId()) == null)
            throw new WrongIdException(orderDto.getId());

        if (customerDAO.getById(orderDto.getCustomerId()) == null)
            throw new WrongIdException(orderDto.getCustomerId());

        Order updatedOrder = orderMapper.toEntity(orderDto);
        return orderMapper.toDto(orderDAO.update(updatedOrder));
    }

    @Override
    public OrderDto getById(int id) throws WrongIdException{
        Order order = orderDAO.getById(id);

        if (order == null)
            throw new WrongIdException(id);

        return orderMapper.toDto(order);
    }

    @Override
    public Collection<OrderDto> getOrderList() {
        return orderDAO.readAll().stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }
}
