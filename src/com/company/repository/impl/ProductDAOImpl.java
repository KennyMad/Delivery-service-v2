package com.company.repository.impl;

import com.company.models.Product;
import com.company.repository.ProductDAO;

import java.util.Collection;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private List<Product> productList;

    public ProductDAOImpl(List<Product> productList){
        this.productList = productList;
    }

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
