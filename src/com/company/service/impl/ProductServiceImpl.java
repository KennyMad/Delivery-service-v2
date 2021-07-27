package com.company.service.impl;

import com.company.exception.InvalidAttributeException;
import com.company.exception.WrongIdException;
import com.company.models.DTO.ProductDto;
import com.company.mapper.ProductMapper;
import com.company.models.Product;
import com.company.models.ProductAttribute;
import com.company.models.ProductCategory;
import com.company.models.Store;
import com.company.repository.ProductDAO;
import com.company.repository.StoreDAO;
import com.company.service.ProductService;
import com.company.utils.impl.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private StoreDAO storeDAO;

    @Override
    public void add(ProductDto productDto) throws WrongIdException{
        Product product = ProductMapper.PRODUCT_MAPPER.toProduct(productDto);
        product.setId(SequenceGenerator.getFreeProductId(productDAO.readAll()));

        Store store = storeDAO.getById(product.getStoreId());
        if (store == null)
            throw new WrongIdException(product.getStoreId());
        store.getProductListIds().add(product.getId());

        productDAO.add(product);
    }


    @Override
    public void delete(int productId) throws WrongIdException{
        Product product = productDAO.remove(productId);
        if (product == null)
            throw new WrongIdException(productId);

        Store store = storeDAO.getById(product.getStoreId());
        if (store == null)
            throw new WrongIdException(product.getStoreId());
        store.getProductListIds().remove(productId);
    }

    @Override
    public void update(ProductDto productDto) throws WrongIdException{
        Product updatedProduct = ProductMapper.PRODUCT_MAPPER.toProduct(productDto);
        if (productDAO.getById(updatedProduct.getId()) == null)
            throw new WrongIdException(updatedProduct.getId());

        productDAO.update(updatedProduct);
    }

    @Override
    public Collection<ProductDto> getProductList() {
        return productDAO.readAll().stream()
                .map(ProductMapper.PRODUCT_MAPPER::toProductDto)
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

        List<Product> productList = productDAO.readAll().stream()
                .filter(product -> isProductHasAttribute(product,attributeValueMap))
                .collect(Collectors.toList());

        return productList.stream()
                .map(ProductMapper.PRODUCT_MAPPER::toProductDto)
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
        return productDAO.readAll().stream()
                .filter(p -> p.getCategories().contains(category))
                .map(ProductMapper.PRODUCT_MAPPER::toProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<ProductDto> getProductsByStore(int storeId) throws WrongIdException {
        Store store = storeDAO.getById(storeId);
        if (store == null)
            throw new WrongIdException(storeId);

        List<Product> productList = new ArrayList<>();
        for (int id: store.getProductListIds())
            productList.add(productDAO.getById(id));
        return productList.stream()
                .map(ProductMapper.PRODUCT_MAPPER::toProductDto)
                .collect(Collectors.toList());
    }

}
