package com.company.controller;

import com.company.annotations.StoreExceptionHandler;
import com.company.exception.WrongIdException;
import com.company.models.DTO.StoreDto;
import com.company.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.stream.Collectors;

@Tag(name = "Store controller", description = "Allows to manage stores")
@RestController
@RequestMapping("stores")
@StoreExceptionHandler
public class StoreController {

    @Autowired
    private StoreService storeService;

    @Operation(summary = "New store registration")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody StoreDto storeDto){
        return new ResponseEntity<>(storeService.add(storeDto),HttpStatus.CREATED);
    }

    @Operation(summary = "Returns a list of all existing stores")
    @GetMapping
    public ResponseEntity<Collection<StoreDto>> read(){
        Collection<StoreDto> stores = storeService.getStoreList();

        return new ResponseEntity<>(stores,HttpStatus.OK);
    }

    @Operation(summary = "Returns store by id")
    @GetMapping(value = "{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        return new ResponseEntity<>(storeService.getById(id),HttpStatus.OK);
    }

    @Operation(summary = "Updates information for a specific store")
    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody StoreDto storeDto, @PathVariable("id") int id){
        storeDto.setId(id);
        return new ResponseEntity<>(storeService.update(storeDto),HttpStatus.OK);
    }

    @Operation(summary = "Deletes the store and its products")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        storeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
