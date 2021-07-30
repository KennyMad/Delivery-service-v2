package com.company.repository.impl;

import com.company.models.Customer;
import com.company.repository.CustomerDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Collection<Customer> readAll() {
        return (List<Customer>) sessionFactory.openSession().createQuery("From Customer").list();
    }

    @Override
    public Customer getById(int id) {
        return sessionFactory.openSession().get(Customer.class,id);
    }

    @Override
    public Customer remove(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete("id",id);
        transaction.commit();
        session.close();
        return null;
    }

    @Override
    public void add(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        session.close();
    }
}
