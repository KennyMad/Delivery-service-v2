package com.company.repository.impl;

import com.company.models.Order;
import com.company.repository.OrderDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
@Primary
public class OrderDaoImpl implements OrderDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Collection<Order> readAll() {
        return (List<Order>)sessionFactory.openSession().createQuery("FROM Order").list();
    }

    @Override
    public Order getById(int id) {
        return sessionFactory.openSession().get(Order.class,id);
    }

    @Override
    public Order remove(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete("id",id);
        transaction.commit();
        session.close();
        return null;
    }

    @Override
    public void add(Order order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(order.getOrderAddress());
        session.save(order);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Order order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(order.getOrderAddress());
        session.update(order);
        transaction.commit();
        session.close();
    }
}