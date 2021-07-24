package com.company.repository.impl;

import com.company.models.Product;
import com.company.repository.ProductDAO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    private List<Product> productList = new ArrayList<>();

    @Override
    public Collection<Product> readAll() {
        return productList;
    }

    @Override
    public Product getById(int id) {
        return productList.stream().filter(p -> p.getId() == id).limit(1).findFirst().orElse(null);
    }

    @Override
    public Product remove(int id) {
        Product product = getById(id);
        if (product != null)
            productList.remove(product);
        return product;
    }

    @Override
    public void add(Product product) {
        productList.add(product);
    }
}
