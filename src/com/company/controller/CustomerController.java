package com.company.controller;

import com.company.annotations.CustomerExceptionHandler;
import com.company.exception.WrongIdException;
import com.company.models.DTO.CustomerDto;
import com.company.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("customer")
@CustomerExceptionHandler
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CustomerDto customerDto){
        return new ResponseEntity<>(customerService.add(customerDto),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Collection<CustomerDto>> read(){
        Collection<CustomerDto> customers = customerService.getCustomerList();

        return customers != null && !customers.isEmpty()
                ? new ResponseEntity<>(customers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        return new ResponseEntity<>(customerService.getById(id),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CustomerDto customerDto){
        return new ResponseEntity<>(customerService.update(customerDto),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam int id){
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
