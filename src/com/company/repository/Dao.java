package com.company.repository;

import com.company.exception.SaveDataException;

import java.util.Collection;

public interface Dao<T> {

    Collection<T> readAll();

    T getById(int id);

    T remove(int id);

    T add (T t);

    T update(T t);
}
