package com.company.repository.impl;

import com.company.models.Store;
import com.company.repository.StoreDAO;

import java.util.Collection;
import java.util.List;

public class StoreDAOImpl implements StoreDAO {

    private List<Store> storeList;

    public StoreDAOImpl(List<Store> storeList){
        this.storeList = storeList;
    }

    @Override
    public Collection readAll() {
        return storeList;
    }

    @Override
    public Store getById(int id){
        return storeList.stream().filter(s -> s.getId() == id).limit(1).findFirst().orElse(null);
    }

    @Override
    public Store remove(int id) {
        Store store = getById(id);
        if (store != null)
            storeList.remove(store);
        return store;
    }

    @Override
    public void add(Store store) {
        storeList.add(store);
    }
}
