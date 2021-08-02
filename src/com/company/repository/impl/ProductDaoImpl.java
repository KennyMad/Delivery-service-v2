package com.company.repository.impl;

import com.company.models.Product;
import com.company.repository.ProductDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Collection<Product> readAll() {
        return (List<Product>)sessionFactory.openSession().createQuery("From Product").list();
    }

    @Override
    public Product getById(int id) {
        Session session = sessionFactory.openSession();
        Product product = session.get(Product.class,id);
        session.close();
        return product;
    }

    @Override
    public Product remove(int id) {
        Product removedProduct = getById(id);
        if (removedProduct == null)
            return null;
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(removedProduct);
        transaction.commit();
        return removedProduct;
    }

    @Override
    public Product add(Product product) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();
        return getById(product.getId());
    }

    @Override
    public Product update(Product product) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.update(product);
        transaction.commit();
        return getById(product.getId());
    }
}
