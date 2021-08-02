package com.company.repository.impl;

import com.company.models.Customer;
import com.company.repository.CustomerDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Collection<Customer> readAll() {
        return (List<Customer>) sessionFactory.openSession().createQuery("From Customer").list();
    }

    @Override
    public Customer getById(int id) {
        Session session = sessionFactory.openSession();
        Customer customer = session.get(Customer.class, id);
        session.close();
        return customer;
    }

    @Override
    public Customer remove(int id) {
        Customer removedCustomer = getById(id);
        if (removedCustomer == null)
            return null;
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(removedCustomer);
        transaction.commit();
        return removedCustomer;
    }

    @Override
    public Customer add(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        return getById(customer.getId());
    }

    @Override
    public Customer update(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        return getById(customer.getId());
    }
}
