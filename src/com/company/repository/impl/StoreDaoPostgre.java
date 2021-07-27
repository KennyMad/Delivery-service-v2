package com.company.repository.impl;

import com.company.models.Store;
import com.company.repository.StoreDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
@Primary
public class StoreDaoPostgre implements StoreDAO {

    @Value("${db.url}")
    String url;
    @Value("${db.username}")
    String username;
    @Value("${db.password}")
    String password;

    @Override
    public Collection<Store> readAll() {
        List<Store> storeList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url,username,password)){
            String storeSql = "SELECT * FROM stores";
            String productSql = "SELECT id FROM products WHERE store_id=?";

            ResultSet storeSet = connection.prepareStatement(storeSql).executeQuery();
            while (storeSet.next()){
                Store store = new Store();
                store.setId(storeSet.getInt("id"));
                store.setName(storeSet.getString("name"));
                store.setDescription(storeSet.getString("description"));
                store.setProductListIds(new ArrayList<>());

                PreparedStatement productStatement = connection.prepareStatement(productSql);
                productStatement.setInt(1,store.getId());
                ResultSet productSet = productStatement.executeQuery();
                while (productSet.next()){
                    store.getProductListIds().add(productSet.getInt("id"));
                }
                storeList.add(store);
            }
        }
        catch (Exception exception){
            exception.printStackTrace();
        }

        return storeList;
    }

    @Override
    public Store getById(int id) {
        Store store = null;

        try (Connection connection = DriverManager.getConnection(url,username,password)){
            String storeSql = "SELECT * FROM stores WHERE id=?";
            String productSql = "SELECT id FROM products WHERE store_id=?";

            PreparedStatement storeStatement = connection.prepareStatement(storeSql);
            PreparedStatement productStatement = connection.prepareStatement(productSql);

            storeStatement.setInt(1,id);
            productStatement.setInt(1,id);

            ResultSet storeSet = storeStatement.executeQuery();
            if (storeSet.next()){
                store = new Store();
                store.setId(storeSet.getInt("id"));
                store.setName(storeSet.getString("name"));
                store.setDescription(storeSet.getString("description"));
                store.setProductListIds(new ArrayList<>());
                ResultSet productSet = productStatement.executeQuery();
                while (productSet.next()){
                    store.getProductListIds().add(productSet.getInt("id"));
                }
            }
        }
        catch (Exception exception){
            exception.printStackTrace();
        }

        return store;
    }

    @Override
    public Store remove(int id) {
        Store store = getById(id);
        if (store == null)
            return null;

        try(Connection connection = DriverManager.getConnection(url,username,password)){
            String storeSql = "DELETE FROM stores WHERE id=?";
            PreparedStatement storeStatement = connection.prepareStatement(storeSql);

            storeStatement.setInt(1,id);
            storeStatement.executeUpdate();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }

        return store;
    }

    @Override
    public void add(Store store) {
        try (Connection connection = DriverManager.getConnection(url,username,password)){
            String storeSql = "INSERT INTO stores (id, name, description) " +
                    "VALUES (?, ?, ?)";

            PreparedStatement storeStatement = connection.prepareStatement(storeSql);
            storeStatement.setInt(1,store.getId());
            storeStatement.setString(2,store.getName());
            storeStatement.setString(3,store.getDescription());

            storeStatement.executeUpdate();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Store store) {
        try (Connection connection = DriverManager.getConnection(url,username,password)){
            String storeSql = "UPDATE stores SET name=?, description=? WHERE id=?";

            PreparedStatement storeStatement = connection.prepareStatement(storeSql);
            storeStatement.setString(1,store.getName());
            storeStatement.setString(2,store.getDescription());
            storeStatement.setInt(3,store.getId());

            storeStatement.executeUpdate();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
