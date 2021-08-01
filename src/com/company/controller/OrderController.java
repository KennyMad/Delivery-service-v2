package com.company.controller;

import com.company.annotations.OrderExceptionHandler;
import com.company.exception.WrongIdException;
import com.company.models.DTO.OrderDto;
import com.company.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("order")
@OrderExceptionHandler
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderDto orderDto){
        return new ResponseEntity<>(orderService.add(orderDto),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Collection<OrderDto>> read(@RequestParam(defaultValue = "-1",required = false) int id){
        Collection<OrderDto> orders = orderService.getOrderList();
        if (id != -1){
            orders = orders.stream().filter(o -> o.getId() == id).collect(Collectors.toList());
        }

        return orders != null && !orders.isEmpty()
                ? new ResponseEntity<>(orders,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody OrderDto orderDto){
        return new ResponseEntity<>(orderService.update(orderDto),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam int id){
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
