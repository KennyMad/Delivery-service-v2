package com.company.repository.impl;

import com.company.models.Order;
import com.company.models.OrderAddress;
import com.company.repository.OrderDAO;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
@Primary
public class OrderDaoPostgre implements OrderDAO {

    @Value("${db.url}")
    String url;
    @Value("${db.username}")
    String username;
    @Value("${db.password}")
    String password;

    @Override
    public Collection<Order> readAll() {
        List<Order> orderList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url,username,password)){
            String orderSql = "SELECT * FROM orders";
            String productsSql = "SELECT * FROM order_products WHERE order_id=?";

            ResultSet orderSet = connection.createStatement().executeQuery(orderSql);
            while (orderSet.next()){
                Order order = new Order();
                order.setId(orderSet.getInt("id"));
                order.setCustomerId(orderSet.getInt("customer_id"));
                OrderAddress orderAddress = new OrderAddress();
                orderAddress.setStreet(orderSet.getString("street"));
                orderAddress.setHouse(orderSet.getString("house"));
                orderAddress.setCity(orderSet.getString("city"));
                order.setOrderAddress(orderAddress);
                order.setProductIdCountMap(new HashMap<>());

                PreparedStatement productStatement = connection.prepareStatement(productsSql);
                productStatement.setInt(1,order.getId());
                ResultSet productSet = productStatement.executeQuery();
                while (productSet.next()){
                    order.getProductIdCountMap().put(productSet.getInt("product_id"),productSet.getInt("amount"));
                }

                orderList.add(order);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

        return orderList;
    }

    @Override
    public Order getById(int id) {
        Order order = null;
        try (Connection connection = DriverManager.getConnection(url,username,password)){
            String orderSql = "SELECT * FROM orders WHERE id=?";
            String productsSql = "SELECT * FROM order_products WHERE order_id=?";

            PreparedStatement orderStatement = connection.prepareStatement(orderSql);
            orderStatement.setInt(1,id);
            ResultSet orderSet = orderStatement.executeQuery();
            if (orderSet.next()){
                order = new Order();
                order.setId(orderSet.getInt("id"));
                order.setCustomerId(orderSet.getInt("customer_id"));
                OrderAddress orderAddress = new OrderAddress();
                orderAddress.setStreet(orderSet.getString("street"));
                orderAddress.setHouse(orderSet.getString("house"));
                orderAddress.setCity(orderSet.getString("city"));
                order.setOrderAddress(orderAddress);
                order.setProductIdCountMap(new HashMap<>());

                PreparedStatement productStatement = connection.prepareStatement(productsSql);
                productStatement.setInt(1,order.getId());
                ResultSet productSet = productStatement.executeQuery();
                while (productSet.next()){
                    order.getProductIdCountMap().put(productSet.getInt("product_id"),productSet.getInt("amount"));
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

        return order;
    }

    @Override
    public Order remove(int id) {
        Order order = getById(id);
        if (order == null)
            return null;

        try (Connection connection = DriverManager.getConnection(url,username,password)){
            String orderSql = "DELETE FROM orders WHERE id=?";
            String productSql = "DELETE FROM order_products WHERE order_id=?";

            PreparedStatement orderStatement = connection.prepareStatement(orderSql);
            PreparedStatement productStatement = connection.prepareStatement(productSql);

            orderStatement.setInt(1,id);
            productStatement.setInt(1,id);

            orderStatement.executeUpdate();
            productStatement.executeUpdate();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }

        return order;
    }

    @Override
    public void add(Order order) {
        try (Connection connection = DriverManager.getConnection(url,username,password)){
            String orderSql = "INSERT INTO orders (id, customer_id, house, street, city) " +
                    "VALUES (?, ?, ?, ?, ?)";
            String productSql = "INSERT INTO order_products (order_id, product_id, amount) " +
                    "VALUES (?, ?, ?)";

            connection.setAutoCommit(false);

            PreparedStatement orderStatement = connection.prepareStatement(orderSql);
            PreparedStatement productStatement = connection.prepareStatement(productSql);

            orderStatement.setInt(1,order.getId());
            orderStatement.setInt(2,order.getCustomerId());
            orderStatement.setString(3,order.getOrderAddress().getHouse());
            orderStatement.setString(4,order.getOrderAddress().getStreet());
            orderStatement.setString(5,order.getOrderAddress().getCity());
            orderStatement.executeUpdate();

            order.getProductIdCountMap().forEach((key, value) -> {
                try {
                    productStatement.setInt(1, order.getId());
                    productStatement.setInt(2, key);
                    productStatement.setInt(3, value);
                    productStatement.executeUpdate();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            });

            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Order order) {
        try (Connection connection = DriverManager.getConnection(url,username,password)){
            String orderSql = "UPDATE orders SET customer_id=?, street=?, house=?, city=? WHERE id=?";
            String productInsertSql = "INSERT INTO order_products (order_id, product_id, amount) " +
                    "VALUES (?, ?, ?)";
            String productDeleteSql = "DELETE FROM order_products WHERE order_id=?";

            connection.setAutoCommit(false);

            PreparedStatement orderStatement = connection.prepareStatement(orderSql);
            orderStatement.setInt(1,order.getCustomerId());
            orderStatement.setString(2,order.getOrderAddress().getStreet());
            orderStatement.setString(3,order.getOrderAddress().getHouse());
            orderStatement.setString(4,order.getOrderAddress().getCity());
            orderStatement.setInt(5,order.getId());
            orderStatement.executeUpdate();

            PreparedStatement deleteStatement = connection.prepareStatement(productDeleteSql);
            deleteStatement.setInt(1,order.getId());
            deleteStatement.executeUpdate();

            PreparedStatement insertStatement = connection.prepareStatement(productInsertSql);
            order.getProductIdCountMap().forEach((key,value) -> {
                try {
                    insertStatement.setInt(1,order.getId());
                    insertStatement.setInt(2,key);
                    insertStatement.setInt(3,value);
                    insertStatement.executeUpdate();
                }
                catch (SQLException exception){
                    exception.printStackTrace();
                }
            });

            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
