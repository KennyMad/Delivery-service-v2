package com.company.service;

import com.company.exception.WrongIdException;
import com.company.models.DTO.StoreDto;

import java.util.Collection;

public interface StoreService {

    StoreDto add(StoreDto storeDTO);

    void delete(int id) throws WrongIdException;

    StoreDto update(StoreDto storeDTO) throws WrongIdException;

    StoreDto getById(int id) throws WrongIdException;

    Collection<StoreDto> getStoreList();

}
