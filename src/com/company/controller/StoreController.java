package com.company.controller;

import com.company.annotations.StoreExceptionHandler;
import com.company.exception.WrongIdException;
import com.company.models.DTO.StoreDto;
import com.company.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("store")
@StoreExceptionHandler
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody StoreDto storeDto){
        return new ResponseEntity<>(storeService.add(storeDto),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Collection<StoreDto>> read(){
        Collection<StoreDto> stores = storeService.getStoreList();

        return stores != null && !stores.isEmpty()
                ? new ResponseEntity<>(stores,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        return new ResponseEntity<>(storeService.getById(id),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody StoreDto storeDto){
        return new ResponseEntity<>(storeService.update(storeDto),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam int id){
        storeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
