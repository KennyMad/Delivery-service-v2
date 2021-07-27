package com.company.repository.impl;

import com.company.models.Store;
import com.company.repository.StoreDAO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class StoreDAOImpl implements StoreDAO {

    private List<Store> storeList = new ArrayList<>();

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

    @Override
    public void update(Store store){
        remove(store.getId());
        add(store);
    }
}
