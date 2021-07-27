package com.company.repository.impl;

import com.company.mapper.ProductMapper;
import com.company.models.DTO.ProductDto;
import com.company.models.Product;
import com.company.models.ProductCategory;
import com.company.repository.ProductDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
@Primary
public class ProductDaoPostgre implements ProductDAO {

    @Value("${db.url}")
    String url;
    @Value("${db.username}")
    String username;
    @Value("${db.password}")
    String password;

    @Override
    public Collection<Product> readAll() {
        List<Product> productList = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url,username, password)){
            Statement statement = connection.createStatement();
            PreparedStatement categoryStatement = connection.prepareStatement("SELECT * FROM categories WHERE product_id=?");
            ResultSet products = statement.executeQuery("SELECT * FROM products");

            while (products.next()){
                ProductDto productDto = new ProductDto();
                productDto.setId(products.getInt("id"));
                productDto.setStoreId(products.getInt("store_id"));
                productDto.setName(products.getString("name"));
                productDto.setDescription(products.getString("description"));
                productDto.setAmount(products.getInt("amount"));
                productDto.setPrice(products.getDouble("price"));
                productDto.setCategoryList(new ArrayList<>());

                categoryStatement.setInt(1,productDto.getId());
                ResultSet categories = categoryStatement.executeQuery();
                while (categories.next()){
                    productDto.getCategoryList().add(categories.getString("category"));
                }
                productList.add(ProductMapper.PRODUCT_MAPPER.toProduct(productDto));
            }
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        return productList;
    }

    @Override
    public Product getById(int id) {
        Product product = null;
        try (Connection connection = DriverManager.getConnection(url,username,password)){
            String productSql = "SELECT * FROM products WHERE id=?";
            String categorySql = "SELECT * FROM categories WHERE product_id=?";
            PreparedStatement productStatement = connection.prepareStatement(productSql);
            PreparedStatement categoryStatement = connection.prepareStatement(categorySql);

            productStatement.setInt(1,id);
            categoryStatement.setInt(1,id);
            ResultSet productSet = productStatement.executeQuery();
            if (productSet.next()){
                ProductDto productDto = new ProductDto();
                productDto.setId(id);
                productDto.setStoreId(productSet.getInt("store_id"));
                productDto.setName(productSet.getString("name"));
                productDto.setDescription(productSet.getString("description"));
                productDto.setAmount(productSet.getInt("amount"));
                productDto.setPrice(productSet.getDouble("price"));
                productDto.setCategoryList(new ArrayList<>());
                ResultSet categories = categoryStatement.executeQuery();
                while (categories.next()){
                    productDto.getCategoryList().add(categories.getString("category"));
                }
                product = ProductMapper.PRODUCT_MAPPER.toProduct(productDto);
            }
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        return product;
    }

    @Override
    public Product remove(int id) {
        Product product = getById(id);
        if (product == null){
            return null;
        }
        try (Connection connection = DriverManager.getConnection(url,username,password)){
            String productSql = "DELETE FROM products WHERE id=?";
            String categorySql = "DELETE FROM categories WHERE product_id=?";
            PreparedStatement productsStatement = connection.prepareStatement(productSql);
            PreparedStatement categoryStatement = connection.prepareStatement(categorySql);

            productsStatement.setInt(1,id);
            categoryStatement.setInt(1,id);

            productsStatement.executeUpdate();
            categoryStatement.executeUpdate();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        return product;
    }

    @Override
    public void add(Product product) {
        try (Connection connection = DriverManager.getConnection(url, username, password)){
            connection.setAutoCommit(false);

            String productSql = "INSERT INTO products (id, store_id, name, description, amount, price) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            String categorySql = "INSERT INTO categories (product_id, category)" +
                    "VALUES (?, ?)";
            PreparedStatement productStatement = connection.prepareStatement(productSql);
            PreparedStatement categoryStatement = connection.prepareStatement(categorySql);

            productStatement.setInt(1,product.getId());
            productStatement.setInt(2,product.getStoreId());
            productStatement.setString(3, product.getName());
            productStatement.setString(4, product.getDescription());
            productStatement.setInt(5,product.getAmount());
            productStatement.setDouble(6,product.getPrice());
            productStatement.executeUpdate();

            product.getCategories().forEach(category -> {
                try {
                    categoryStatement.setInt(1,product.getId());
                    categoryStatement.setString(2,category.toString());
                    categoryStatement.executeUpdate();
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
    public void update(Product product) {
        String productSql = "UPDATE products SET " +
                "store_id=?, name=?, description=?, amount=?, price=? WHERE id=?";
        String categorySqlDelete = "DELETE FROM categories WHERE product_id=?";
        String categorySqlInsert = "INSERT INTO categories (product_id, category)" +
                "VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(url,username,password)){
            connection.setAutoCommit(false);
            PreparedStatement productStatement = connection.prepareStatement(productSql);
            PreparedStatement categoryStatement = connection.prepareStatement(categorySqlDelete);

            productStatement.setInt(1,product.getStoreId());
            productStatement.setString(2,product.getName());
            productStatement.setString(3, product.getDescription());
            productStatement.setInt(4,product.getAmount());
            productStatement.setDouble(5,product.getPrice());
            productStatement.setInt(6,product.getId());
            productStatement.executeUpdate();

            categoryStatement.setInt(1,product.getId());
            categoryStatement.executeUpdate();

            PreparedStatement categoryInsert = connection.prepareStatement(categorySqlInsert);
            product.getCategories().forEach(category -> {
                try {
                    categoryInsert.setInt(1,product.getId());
                    categoryInsert.setString(2,category.toString());
                    categoryInsert.executeUpdate();
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
}
