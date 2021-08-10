package com.company.repository;

import com.company.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StoreDao extends JpaRepository<Store,Integer> {

    @Override
    @Query("SELECT s FROM Store s WHERE s.id = ?1")
    Store getById(Integer integer);
}
