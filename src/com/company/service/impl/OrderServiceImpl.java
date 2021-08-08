package com.company.service.impl;

import com.company.exception.WrongIdException;
import com.company.models.Customer;
import com.company.models.DTO.OrderDto;
import com.company.mapper.OrderMapper;
import com.company.models.Order;
import com.company.repository.CustomerDao;
import com.company.repository.OrderDao;
import com.company.repository.ProductDao;
import com.company.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderDto add(OrderDto orderDto) throws WrongIdException{
        if (!customerDao.existsById(orderDto.getCustomerId())) {
            throw new WrongIdException(orderDto.getCustomerId());
        }
        orderDto.getProductIdCountMap().keySet().forEach(id -> {
            if (!productDao.existsById(id)){
                throw new WrongIdException(id);
            }
        });
        Order order = orderMapper.toEntity(orderDto);

        return orderMapper.toDto(orderDao.saveAndFlush(order));
    }

    @Override
    public void delete(int id) throws WrongIdException {
        if (!orderDao.existsById(id)) {
            throw new WrongIdException(id);
        }
        orderDao.deleteById(id);
    }

    @Override
    public OrderDto update(OrderDto orderDto) throws WrongIdException {
        if (!orderDao.existsById(orderDto.getId())){
            throw new WrongIdException(orderDto.getId());
        }
        if (!customerDao.existsById(orderDto.getCustomerId())){
            throw new WrongIdException(orderDto.getCustomerId());
        }
        orderDto.getProductIdCountMap().keySet().forEach(id -> {
            if (!productDao.existsById(id)){
                throw new WrongIdException(id);
            }
        });
        Order updatedOrder = orderMapper.toEntity(orderDto);

        return orderMapper.toDto(orderDao.saveAndFlush(updatedOrder));
    }

    @Override
    public OrderDto getById(int id) throws WrongIdException{
        if (!orderDao.existsById(id)){
            throw new WrongIdException(id);
        }
        return orderMapper.toDto(orderDao.getById(id));
    }

    @Override
    public Collection<OrderDto> getOrderList() {
        return orderDao.findAll().stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }
}
