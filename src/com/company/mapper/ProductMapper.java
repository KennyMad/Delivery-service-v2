package com.company.mapper;

import com.company.models.DTO.ProductDto;
import com.company.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductMapper {

    @Mapping(source = "categories", target = "categoryList")
    ProductDto toDto(Product product);

    @Mapping(source = "categoryList", target = "categories")
    Product toEntity(ProductDto productDTO);

}
