package com.company.mapper.decorator;

import com.company.exception.InvalidAttributeException;
import com.company.mapper.ProductMapper;
import com.company.models.DTO.ProductDto;
import com.company.models.Product;
import com.company.models.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.stream.Collectors;

public abstract class ProductMapperDecorator implements ProductMapper {

    @Autowired
    @Qualifier("delegate")
    private ProductMapper delegate;

    @Override
    public Product toEntity(ProductDto productDTO) {
        productDTO.setCategoryList(productDTO.getCategoryList()
                .stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList()));

        productDTO.getCategoryList().forEach(category -> {
            try {
                ProductCategory.valueOf(category);
            }
            catch (IllegalArgumentException exception){
                throw new InvalidAttributeException(category);
            }
        });

        return delegate.toEntity(productDTO);
    }
}
