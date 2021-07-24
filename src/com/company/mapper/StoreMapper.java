package com.company.mapper;

import com.company.models.DTO.StoreDto;
import com.company.models.Store;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StoreMapper {

    StoreMapper STORE_MAPPER = Mappers.getMapper(StoreMapper.class);

    StoreDto toStoreDto(Store store);

    Store toStore(StoreDto storeDTO);

}
