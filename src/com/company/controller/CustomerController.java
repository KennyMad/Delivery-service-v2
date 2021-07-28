package com.company.controller;

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
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CustomerDto customerDto){
        customerService.add(customerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Collection<CustomerDto>> read(@RequestParam(defaultValue = "-1", required = false) int id){
        Collection<CustomerDto> customers = customerService.getCustomerList();
        if (id != -1){
            customers = customers.stream().filter(c -> c.getId() == id).collect(Collectors.toList());
        }
        return customers != null && !customers.isEmpty()
                ? new ResponseEntity<>(customers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CustomerDto customerDto){
        try {
            customerService.update(customerDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (WrongIdException exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam int id){
        try {
            customerService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (WrongIdException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
