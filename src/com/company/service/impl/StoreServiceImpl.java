package com.company.service.impl;

import com.company.exception.WrongIdException;
import com.company.models.DTO.StoreDto;
import com.company.mapper.StoreMapper;
import com.company.models.Store;
import com.company.repository.StoreDao;
import com.company.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public StoreDto add(StoreDto storeDto){
        Store store = storeMapper.toEntity(storeDto);
        return storeMapper.toDto(storeDao.save(store));
    }

    @Override
    public void delete(int id) throws WrongIdException{
        if (!storeDao.existsById(id)){
            throw new WrongIdException(id);
        }
        storeDao.deleteById(id);
    }

    @Override
    public StoreDto update(StoreDto storeDto) throws WrongIdException{
        if (!storeDao.existsById(storeDto.getId())){
            throw new WrongIdException(storeDto.getId());
        }
        Store updatedStore = storeMapper.toEntity(storeDto);

        Store store = storeDao.getById(updatedStore.getId());
        updatedStore.setProductList(store.getProductList());

        return storeMapper.toDto(storeDao.save(updatedStore));
    }

    @Override
    public StoreDto getById(int id) throws WrongIdException {
        if (!storeDao.existsById(id)){
            throw new WrongIdException(id);
        }
        return storeMapper.toDto(storeDao.getById(id));
    }

    @Override
    public Collection<StoreDto> getStoreList() {
        return storeDao.findAll().stream()
                .map(storeMapper::toDto)
                .collect(Collectors.toList());
    }


}
