package com.company.mapper;

import com.company.models.DTO.ProductDto;
import com.company.models.DTO.StoreDto;
import com.company.models.Product;
import com.company.models.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StoreMapper {

    StoreMapper STORE_MAPPER = Mappers.getMapper(StoreMapper.class);

    @Mapping(source = "productList", target = "productDtoList")
    StoreDto toStoreDto(Store store);

    @Mapping(source = "productDtoList", target = "productList")
    Store toStore(StoreDto storeDTO);

    @Mapping(source = "categories", target = "categoryList")
    ProductDto toProductDto(Product product);

    @Mapping(source = "categoryList", target = "categories")
    Product toProduct(ProductDto productDTO);

}
