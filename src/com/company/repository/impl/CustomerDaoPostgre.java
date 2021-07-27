package com.company.repository.impl;

import com.company.models.Customer;
import com.company.repository.CustomerDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
@Primary
public class CustomerDaoPostgre implements CustomerDAO {

    @Value("${db.url}")
    String jdbcUrl;
    @Value("${db.username}")
    String username;
    @Value("${db.password}")
    String password;

    @Override
    public Collection<Customer> readAll() {
        List<Customer> customerList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcUrl,username,password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");

            while (resultSet.next()){
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setAdditionalInformation(resultSet.getString("additional_information"));
                customerList.add(customer);
            }
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        return customerList;
    }

    @Override
    public Customer getById(int id) {
        Customer customer = null;
        try (Connection connection = DriverManager.getConnection(jdbcUrl,username,password)){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customers WHERE id=" + id);

            if (resultSet.next()){
                customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setAdditionalInformation(resultSet.getString("additional_information"));
            }
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        return customer;
    }

    @Override
    public Customer remove(int id) {
        Customer customer = getById(id);
        if (customer == null) {
            return null;
        }

        try (Connection connection = DriverManager.getConnection(jdbcUrl,username,password)){
            String sql = "DELETE FROM customers WHERE id=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }

        return customer;
    }

    @Override
    public void add(Customer customer) {

        try (Connection connection = DriverManager.getConnection(jdbcUrl,username,password)){

            String sql = "INSERT INTO customers (id, name, additional_information)" +
                    "VALUES (?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,customer.getId());
            statement.setString(2,customer.getName());
            statement.setString(3,customer.getAdditionalInformation());

            statement.executeUpdate();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer){
        try (Connection connection = DriverManager.getConnection(jdbcUrl,username,password)){
            String sql = "UPDATE customers SET name=?, additional_information=? WHERE id=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,customer.getName());
            statement.setString(2,customer.getAdditionalInformation());
            statement.setInt(3,customer.getId());

            statement.executeUpdate();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
