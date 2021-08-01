package com.company.controller;

import com.company.annotations.ProductExceptionHandler;
import com.company.exception.WrongIdException;
import com.company.models.DTO.ProductDto;
import com.company.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("product")
@ProductExceptionHandler
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.add(productDto),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Collection<ProductDto>> read (@RequestParam(defaultValue = "-1", required = false) int id){
        Collection<ProductDto> products = productService.getProductList();
        if (id != -1){
            products = products.stream().filter(p -> p.getId() == id).collect(Collectors.toList());
        }
        return products != null && !products.isEmpty()
                ? new ResponseEntity<>(products,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.update(productDto),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam int id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
