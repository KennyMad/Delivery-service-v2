package com.company.repository.impl;

import com.company.models.Product;
import com.company.repository.ProductDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Collection<Product> readAll() {
        return (List<Product>)sessionFactory.openSession().createQuery("From Product").list();
    }

    @Override
    public Product getById(int id) {
        return sessionFactory.openSession().get(Product.class,id);
    }

    @Override
    public Product remove(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete("id",id);
        transaction.commit();
        session.close();
        return null;
    }

    @Override
    public void add(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(product);
        transaction.commit();
        session.close();
    }
}
