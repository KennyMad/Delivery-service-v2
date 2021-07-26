package com.company.controller;

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
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController (StoreService storeService){
        this.storeService = storeService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody StoreDto storeDto){
        storeService.add(storeDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Collection<StoreDto>> read(@RequestParam(defaultValue = "-1", required = false) int id){
        Collection<StoreDto> stores = storeService.getStoreList();
        if (id != -1){
            stores = stores.stream().filter(s -> s.getId() == id).collect(Collectors.toList());
        }

        return stores != null && !stores.isEmpty()
                ? new ResponseEntity<>(stores,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody StoreDto storeDto){
        try {
            storeService.update(storeDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (WrongIdException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam int id){
        try {
            storeService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (WrongIdException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
