package com.company.controller;

import com.company.models.DTO.OrderDto;
import com.company.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Tag(name = "Order controller", description = "Allows to manage orders")
@RestController
@RequestMapping("orders")
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

        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @Operation(summary = "Returns order by id")
    @GetMapping(value = "{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        return new ResponseEntity<>(orderService.getById(id),HttpStatus.OK);
    }

    @Operation(summary = "Updates information for a specific order")
    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody OrderDto orderDto, @PathVariable("id") int id){
        orderDto.setId(id);
        return new ResponseEntity<>(orderService.update(orderDto),HttpStatus.OK);
    }

    @Operation(summary = "Deletes the order")
    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
