package com.company.repository;

import com.company.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreDao extends JpaRepository<Store,Integer> {
}
