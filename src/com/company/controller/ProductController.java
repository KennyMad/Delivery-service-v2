package com.company.controller;

import com.company.annotations.ProductExceptionHandler;
import com.company.exception.WrongIdException;
import com.company.models.DTO.ProductDto;
import com.company.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@Tag(name = "Product controller", description = "Allows to manage products")
@RestController
@RequestMapping("products")
@ProductExceptionHandler
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Adds a new product")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.add(productDto),HttpStatus.CREATED);
    }

    @Operation(summary = "Returns a list of all existing products")
    @GetMapping
    public ResponseEntity<Collection<ProductDto>> read (){
        Collection<ProductDto> products = productService.getProductList();

        return products != null && !products.isEmpty()
                ? new ResponseEntity<>(products,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Returns product by id")
    @GetMapping(value = "{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        return new ResponseEntity<>(productService.getById(id),HttpStatus.OK);
    }

    @Operation(summary = "Updates information for a specific product")
    @PutMapping(value = "{id}")
    public ResponseEntity<?> update(@RequestBody ProductDto productDto, @PathVariable("id") int id){
        productDto.setId(id);
        return new ResponseEntity<>(productService.update(productDto),HttpStatus.OK);
    }

    @Operation(summary = "Deletes the product")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
