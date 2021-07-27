package com.company.service.impl;

import com.company.exception.WrongIdException;
import com.company.models.DTO.StoreDto;
import com.company.mapper.StoreMapper;
import com.company.models.Store;
import com.company.repository.StoreDAO;
import com.company.service.StoreService;
import com.company.utils.impl.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreDAO storeDao;

    @Override
    public void add(StoreDto storeDto){
        Store store = StoreMapper.STORE_MAPPER.toStore(storeDto);
        store.setId(SequenceGenerator.getFreeStoreId(storeDao.readAll()));
        storeDao.add(store);
    }

    @Override
    public void delete(int id) throws WrongIdException{
        if(storeDao.remove(id) == null)
            throw new WrongIdException(id);
    }

    @Override
    public void update(StoreDto storeDto) throws WrongIdException{
        Store updatedStore = StoreMapper.STORE_MAPPER.toStore(storeDto);
        if (storeDao.getById(updatedStore.getId()) == null)
            throw new WrongIdException(updatedStore.getId());

        storeDao.update(updatedStore);
    }

    @Override
    public Collection<StoreDto> getStoreList() {
        return storeDao.readAll().stream()
                .map(StoreMapper.STORE_MAPPER::toStoreDto)
                .collect(Collectors.toList());
    }


}
