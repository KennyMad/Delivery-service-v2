package com.company.controller;

import com.company.models.DTO.CustomerDto;
import com.company.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Tag(name = "Customer controller", description = "Allows to manage customers")
@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "New customer registration")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CustomerDto customerDto){
        return new ResponseEntity<>(customerService.add(customerDto),HttpStatus.CREATED);
    }

    @Operation(summary = "Returns a list of all existing customers")
    @GetMapping
    public ResponseEntity<Collection<CustomerDto>> read(){
        Collection<CustomerDto> customers = customerService.getCustomerList();

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @Operation(summary = "Returns customer by id")
    @GetMapping(value = "{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        return new ResponseEntity<>(customerService.getById(id),HttpStatus.OK);
    }

    @Operation(summary = "Updates information for a specific customer")
    @PutMapping(value = "{id}")
    public ResponseEntity<?> update(@RequestBody CustomerDto customerDto, @PathVariable("id") int id){
        customerDto.setId(id);
        return new ResponseEntity<>(customerService.update(customerDto),HttpStatus.OK);
    }

    @Operation(summary = "Deletes the customer")
    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
