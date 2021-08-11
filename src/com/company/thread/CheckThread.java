package com.company.thread;

import com.company.models.Customer;
import com.company.models.Order;
import com.company.models.Product;
import com.company.models.Store;
import com.company.repository.CustomerDao;
import com.company.repository.OrderDao;
import com.company.repository.ProductDao;
import com.company.repository.StoreDao;
import com.company.utils.FileUtil;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CheckThread extends Thread{

    @Setter
    private String folderPath;
    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private StoreDao storeDao;

    @SneakyThrows
    public void run(){
        Object object = fileUtil.read(folderPath);
        if (object instanceof Customer){
            checkCustomer((Customer) object);
        }
        else if (object instanceof Order){
            checkOrder((Order) object);
        }
        else if (object instanceof Product){
            checkProduct((Product) object);
        }
        else if (object instanceof Store){
            checkStore((Store) object);
        }
    }

    private void checkCustomer(Customer customer){
        if (!customerDao.existsById(customer.getId()) || !customer.equals(customerDao.getById(customer.getId()))){
            customerDao.saveAndFlush(customer);
        }
    }

    private void checkOrder(Order order){
        if (!orderDao.existsById(order.getId()) || !order.equals(orderDao.getById(order.getId()))){
            orderDao.saveAndFlush(order);
        }
    }

    private void checkProduct(Product product){
        if (!productDao.existsById(product.getId()) || !product.equals(productDao.getById(product.getId()))){
            productDao.saveAndFlush(product);
        }
    }

    private void checkStore(Store store){
        if (!storeDao.existsById(store.getId())){
            storeDao.saveAndFlush(store);
            return;
        }
        Store dbStore = storeDao.getById(store.getId());
        store.setProductList(dbStore.getProductList());
        if (!store.equals(dbStore)){
            storeDao.saveAndFlush(store);
        }
    }
}
