package com.company.service;

import com.company.exception.InvalidAttributeException;
import com.company.exception.WrongIdException;
import com.company.models.DTO.ProductDto;
import com.company.models.ProductCategory;

import java.util.Collection;
import java.util.Map;

public interface ProductService {

    ProductDto add(ProductDto productDTO) throws WrongIdException;

    void delete(int id) throws WrongIdException;

    ProductDto update(ProductDto productDTO) throws WrongIdException;

    ProductDto getById(int id) throws WrongIdException;

    Collection<ProductDto> getProductList();

    Collection<ProductDto> getProductsByAttributes(Map<String, String> nameValueMap) throws InvalidAttributeException;

    Collection<ProductDto> getProductsByPrice(boolean reversed);

    Collection<ProductDto> getProductsByCategory(ProductCategory category);

    Collection<ProductDto> getProductsByStore(int storeId) throws WrongIdException;
}
