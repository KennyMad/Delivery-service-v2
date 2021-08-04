package com.company.mapper;

import com.company.models.DTO.StoreDto;
import com.company.models.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ProductMapper.class})
public interface StoreMapper {

    @Mapping(source = "productList", target = "productDtoList")
    StoreDto toDto(Store store);

    @Mapping(source = "productDtoList", target = "productList")
    Store toEntity(StoreDto storeDTO);

}
