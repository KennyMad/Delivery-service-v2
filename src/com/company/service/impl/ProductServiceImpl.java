package com.company.service.impl;

import com.company.exception.InvalidAttributeException;
import com.company.exception.WrongIdException;
import com.company.models.DTO.ProductDto;
import com.company.mapper.ProductMapper;
import com.company.models.Product;
import com.company.models.ProductAttribute;
import com.company.models.ProductCategory;
import com.company.models.Store;
import com.company.repository.ProductDao;
import com.company.repository.StoreDao;
import com.company.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private StoreDao storeDao;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDto add(ProductDto productDto) throws WrongIdException{
        if (!storeDao.existsById(productDto.getStoreId())){
            throw new WrongIdException(productDto.getStoreId());
        }
        Product product = productMapper.toEntity(productDto);

        return productMapper.toDto(productDao.save(product));
    }


    @Override
    public void delete(int id) throws WrongIdException{
        if (!productDao.existsById(id)){
            throw new WrongIdException(id);
        }
        productDao.deleteById(id);
    }

    @Override
    public ProductDto update(ProductDto productDto) throws WrongIdException{
        if (!productDao.existsById(productDto.getId())){
            throw new WrongIdException(productDto.getId());
        }
        if (!storeDao.existsById(productDto.getStoreId())){
            throw new WrongIdException(productDto.getStoreId());
        }
        Product updatedProduct = productMapper.toEntity(productDto);

        return productMapper.toDto(productDao.save(updatedProduct));
    }

    @Override
    public ProductDto getById(int id) throws WrongIdException {
        if (!productDao.existsById(id)){
            throw new WrongIdException(id);
        }
        return productMapper.toDto(productDao.getById(id));
    }

    @Override
    public Collection<ProductDto> getProductList() {
        return productDao.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<ProductDto> getProductsByAttributes(Map<String,String> nameValueMap) throws InvalidAttributeException {
        Map<ProductAttribute, String> attributeValueMap = new HashMap<>();
        for (Map.Entry<String, String> entry : nameValueMap.entrySet()) {
            try {
                attributeValueMap.put(ProductAttribute.valueOf(entry.getKey().toUpperCase()), entry.getValue());
            }
            catch (IllegalArgumentException e){
                throw new InvalidAttributeException(entry.getValue());
            }
        }

        List<Product> productList = productDao.findAll().stream()
                .filter(product -> isProductHasAttribute(product,attributeValueMap))
                .collect(Collectors.toList());

        return productList.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    private boolean isProductHasAttribute(Product product, Map<ProductAttribute, String> attributeValueMap){
        for (Map.Entry<ProductAttribute, String> entry: attributeValueMap.entrySet()){
            try {
                switch (entry.getKey()) {
                    case PRICE:
                        if (!product.getPrice().toString().contains(entry.getValue().replace(",", ".")))
                            return false;
                        break;
                    case ID:
                        if (product.getId() != Integer.parseInt(entry.getValue()))
                            return false;
                        break;
                    case NAME:
                        if (!product.getName().toLowerCase().contains(entry.getValue().toLowerCase()))
                            return false;
                        break;
                    case AMOUNT:
                            if (product.getAmount() != Integer.parseInt(entry.getValue()))
                                return false;
                        break;
                    case DESCRIPTION:
                        if (!product.getDescription().toLowerCase().contains(entry.getValue().toLowerCase()))
                            return false;
                        break;
                    case STORE_ID:
                            if (product.getStoreId() != Integer.parseInt(entry.getValue()))
                                return false;
                        break;
                    case CATEGORY:
                            if (!product.getCategories().contains(ProductCategory.valueOf(entry.getValue().toUpperCase())))
                                return false;
                        break;
                    default:
                        return false;
                }
            }
            catch (Exception exception){
                return false;
            }
        }
        return true;
    }

    @Override
    public Collection<ProductDto> getProductsByPrice(boolean reversed) {

        if (reversed)
            return getProductList().stream().sorted(Comparator.comparing(ProductDto::getPrice).reversed()).collect(Collectors.toList());

        return getProductList().stream().sorted(Comparator.comparing(ProductDto::getPrice)).collect(Collectors.toList());
    }

    @Override
    public Collection<ProductDto> getProductsByCategory(ProductCategory category){
        return productDao.findAll().stream()
                .filter(p -> p.getCategories().contains(category))
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<ProductDto> getProductsByStore(int storeId) throws WrongIdException {
        if (!storeDao.existsById(storeId)){
            throw new WrongIdException(storeId);
        }
        Store store = storeDao.getById(storeId);

        List<Product> productList = new ArrayList<>();
        for (int id: store.getProductList().stream().map(Product::getId).collect(Collectors.toList()))
            productList.add(productDao.getById(id));
        return productList.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

}
