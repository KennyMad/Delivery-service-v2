package com.company.facade;

import com.company.exception.SaveDataException;
import com.company.exception.InvalidAttributeException;
import com.company.exception.WrongIdException;
import com.company.models.*;
import com.company.models.DTO.CustomerDto;
import com.company.models.DTO.OrderDto;
import com.company.models.DTO.ProductDto;
import com.company.models.DTO.StoreDto;

import java.util.Collection;
import java.util.Map;

public interface Facade {

    void registerCustomer(CustomerDto customerDTO) throws SaveDataException;

    void deleteCustomer(int id) throws WrongIdException, SaveDataException;

    void updateCustomer(CustomerDto customerDTO) throws WrongIdException, SaveDataException;

    Collection<CustomerDto> getCustomerList();

    void registerStore(StoreDto storeDTO) throws SaveDataException;

    void deleteStore(int id) throws WrongIdException, SaveDataException;

    void updateStore(StoreDto storeDTO) throws WrongIdException, SaveDataException;

    Collection<StoreDto> getStoreList();

    void addProduct(ProductDto productDTO) throws WrongIdException, SaveDataException;

    void deleteProduct(int productId) throws WrongIdException, SaveDataException;

    void updateProduct(ProductDto productDTO) throws WrongIdException, SaveDataException;

    Collection<ProductDto> getProductList();

    Collection<ProductDto> getProductsByAttributes(Map<String, String> nameValueMap) throws InvalidAttributeException;

    Collection<ProductDto> getProductsByPrice(boolean reversed);

    Collection<ProductDto> getProductsByCategory(ProductCategory category);

    Collection<ProductDto> getProductsByStore(int storeId) throws WrongIdException;

    void createOrder(OrderDto orderDTO) throws WrongIdException, SaveDataException;

    Collection<OrderDto> getOrderList();

    void saveData() throws SaveDataException;

}
