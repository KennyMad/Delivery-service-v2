package com.company.service.impl;

import com.company.exception.WrongIdException;
import com.company.models.DTO.StoreDto;
import com.company.mapper.StoreMapper;
import com.company.models.Store;
import com.company.repository.StoreDAO;
import com.company.service.StoreService;
import com.company.utils.impl.SequenceGenerator;

import java.util.*;
import java.util.stream.Collectors;

public class StoreServiceImpl implements StoreService {

    private final StoreDAO storeDao;

    public StoreServiceImpl(StoreDAO storeDao){
        this.storeDao = storeDao;
    }


    @Override
    public void add(StoreDto storeDTO){
        Store store = StoreMapper.STORE_MAPPER.toStore(storeDTO);
        store.setId(SequenceGenerator.getFreeStoreId(storeDao.readAll()));
        storeDao.add(store);
    }

    @Override
    public void delete(int id) throws WrongIdException{
        if(storeDao.remove(id) == null)
            throw new WrongIdException(id);
    }

    @Override
    public void update(StoreDto storeDTO) throws WrongIdException{
        Store updatedStore = StoreMapper.STORE_MAPPER.toStore(storeDTO);
        Store store = storeDao.getById(updatedStore.getId());
        if (store == null)
            throw new WrongIdException(updatedStore.getId());
        store.setName(updatedStore.getName());
        store.setDescription(updatedStore.getDescription());
    }

    @Override
    public Collection<StoreDto> getStoreList() {
        return storeDao.readAll().stream()
                .map(StoreMapper.STORE_MAPPER::toStoreDto)
                .collect(Collectors.toList());
    }


}
