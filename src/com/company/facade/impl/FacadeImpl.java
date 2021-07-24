package com.company.facade.impl;

import com.company.exception.SaveDataException;
import com.company.exception.InvalidAttributeException;
import com.company.exception.WrongIdException;
import com.company.facade.Facade;
import com.company.models.*;
import com.company.models.DTO.*;
import com.company.mapper.CustomerMapper;
import com.company.mapper.OrderMapper;
import com.company.mapper.ProductMapper;
import com.company.mapper.StoreMapper;
import com.company.utils.impl.Properties;
import com.company.service.CustomerService;
import com.company.service.OrderService;
import com.company.service.ProductService;
import com.company.service.StoreService;
import com.company.utils.FileUtil;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class FacadeImpl implements Facade {

    final CustomerService customerService;
    final OrderService orderService;
    final StoreService storeService;
    final ProductService productService;

    final FileUtil fileUtil;

    public FacadeImpl(CustomerService customerService, OrderService orderService, StoreService storeService, ProductService productService, FileUtil fileUtil){
        this.customerService = customerService;
        this.orderService = orderService;
        this.storeService = storeService;
        this.productService = productService;

        this.fileUtil = fileUtil;
    }

    @Override
    public void registerCustomer(CustomerDto customerDTO) throws SaveDataException {
        customerService.add(customerDTO);
    }

    @Override
    public void deleteCustomer(int id) throws WrongIdException, SaveDataException {
        customerService.delete(id);
    }

    @Override
    public void updateCustomer(CustomerDto customerDTO) throws WrongIdException, SaveDataException {
        customerService.update(customerDTO);
    }

    @Override
    public Collection<CustomerDto> getCustomerList() {
        return customerService.getCustomerList();
    }

    @Override
    public void registerStore(StoreDto storeDTO) throws SaveDataException {
        storeService.add(storeDTO);
    }

    @Override
    public void deleteStore(int id) throws WrongIdException, SaveDataException {
        storeService.delete(id);
    }

    @Override
    public void updateStore(StoreDto storeDTO) throws WrongIdException, SaveDataException {
        storeService.update(storeDTO);
    }

    @Override
    public Collection<StoreDto> getStoreList() {
        return storeService.getStoreList();
    }

    @Override
    public void addProduct(ProductDto productDTO) throws WrongIdException, SaveDataException {
        productService.add(productDTO);
    }

    @Override
    public void deleteProduct(int productId) throws WrongIdException, SaveDataException {
        productService.delete(productId);
    }

    @Override
    public void updateProduct(ProductDto productDTO) throws WrongIdException, SaveDataException {
        productService.update(productDTO);
    }

    @Override
    public Collection<ProductDto> getProductList() {
        return productService.getProductList();
    }

    @Override
    public Collection<ProductDto> getProductsByAttributes(Map<String, String> nameValueMap) throws InvalidAttributeException {
        return productService.getProductsByAttributes(nameValueMap);
    }

    @Override
    public Collection<ProductDto> getProductsByPrice(boolean reversed) {
        return productService.getProductsByPrice(reversed);
    }

    @Override
    public Collection<ProductDto> getProductsByCategory(ProductCategory category) {
        return productService.getProductsByCategory(category);
    }

    @Override
    public Collection<ProductDto> getProductsByStore(int storeId) throws WrongIdException {
        return productService.getProductsByStore(storeId);
    }

    @Override
    public void createOrder(OrderDto orderDTO) throws WrongIdException, SaveDataException {
        orderService.add(orderDTO);
    }

    @Override
    public Collection<OrderDto> getOrderList() {
        return orderService.getOrderList();
    }

    @Override
    public void saveData() throws SaveDataException {
        fileUtil.save(customerService.getCustomerList().stream()
                .map(CustomerMapper.CUSTOMER_MAPPER::toCustomer)
                .collect(Collectors.toList()), Properties.getCustomerFile());

        fileUtil.save(orderService.getOrderList().stream()
                .map(OrderMapper.ORDER_MAPPER::toOrder)
                .collect(Collectors.toList()), Properties.getOrderFile());

        fileUtil.save(storeService.getStoreList().stream()
                .map(StoreMapper.STORE_MAPPER::toStore)
                .collect(Collectors.toList()), Properties.getStoreFile());

        fileUtil.save(productService.getProductList().stream()
                .map(ProductMapper.PRODUCT_MAPPER::toProduct)
                .collect(Collectors.toList()), Properties.getProductFile());
    }
}
