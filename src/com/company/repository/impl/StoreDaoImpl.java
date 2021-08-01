package com.company.repository.impl;

import com.company.models.Store;
import com.company.repository.StoreDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class StoreDaoImpl implements StoreDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Collection<Store> readAll() {
        return (List<Store>)sessionFactory.openSession().createQuery("From Store").list();
    }

    @Override
    public Store getById(int id) {
        return sessionFactory.openSession().get(Store.class, id);
    }

    @Override
    public Store remove(int id) {
        Store removedStore = getById(id);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(removedStore);
        transaction.commit();
        session.close();
        return removedStore;
    }

    @Override
    public Store add(Store store) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(store);
        transaction.commit();
        session.close();
        return getById(store.getId());
    }

    @Override
    public Store update(Store store) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(store);
        transaction.commit();
        session.close();
        return getById(store.getId());
    }
}
