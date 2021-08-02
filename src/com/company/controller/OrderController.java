package com.company.controller;

import com.company.annotations.OrderExceptionHandler;
import com.company.exception.WrongIdException;
import com.company.models.DTO.OrderDto;
import com.company.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@Tag(name = "Order controller", description = "Allows to manage orders")
@RestController
@RequestMapping("orders")
@OrderExceptionHandler
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "Creates a new order")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderDto orderDto){
        return new ResponseEntity<>(orderService.add(orderDto),HttpStatus.CREATED);
    }

    @Operation(summary = "Returns a list of all existing orders")
    @GetMapping
    public ResponseEntity<Collection<OrderDto>> read(){
        Collection<OrderDto> orders = orderService.getOrderList();

        return orders != null && !orders.isEmpty()
                ? new ResponseEntity<>(orders,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Returns order by id")
    @GetMapping(value = "{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        return new ResponseEntity<>(orderService.getById(id),HttpStatus.OK);
    }

    @Operation(summary = "Updates information for a specific order")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody OrderDto orderDto){
        return new ResponseEntity<>(orderService.update(orderDto),HttpStatus.OK);
    }

    @Operation(summary = "Deletes the order")
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam int id){
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
