package com.company.mapper;

import com.company.models.DTO.ProductDto;
import com.company.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "categories", target = "categoryList")
    ProductDto toProductDto(Product product);

    @Mapping(source = "categoryList", target = "categories")
    Product toProduct(ProductDto productDTO);

}
