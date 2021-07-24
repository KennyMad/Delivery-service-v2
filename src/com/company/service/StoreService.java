package com.company.service;

import com.company.exception.WrongIdException;
import com.company.models.DTO.StoreDto;

import java.util.Collection;

public interface StoreService {

    void add(StoreDto storeDTO);

    void delete(int id) throws WrongIdException;

    void update(StoreDto storeDTO) throws WrongIdException;

    Collection<StoreDto> getStoreList();

}
