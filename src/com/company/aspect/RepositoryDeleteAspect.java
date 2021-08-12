package com.company.aspect;

import com.company.models.Customer;
import com.company.models.Order;
import com.company.models.Product;
import com.company.models.Store;
import com.company.utils.FileUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class RepositoryDeleteAspect {

    @Autowired
    FileUtil fileUtil;

    @Before(value = "execution(public * com.company.repository.CustomerDao.deleteById(..))")
    public void beforeCustomerDelete(JoinPoint joinPoint){
        int id = (int)joinPoint.getArgs()[0];
        fileUtil.delete(Customer.class, id);
    }

    @Before(value = "execution(public * com.company.repository.OrderDao.deleteById(..))")
    public void beforeOrderDelete(JoinPoint joinPoint){
        int id = (int)joinPoint.getArgs()[0];
        fileUtil.delete(Order.class, id);
    }

    @Before(value = "execution(public * com.company.repository.ProductDao.deleteById(..))")
    public void beforeProductDelete(JoinPoint joinPoint){
        int id = (int)joinPoint.getArgs()[0];
        fileUtil.delete(Product.class, id);
    }

    @Before(value = "execution(public * com.company.repository.StoreDao.deleteById(..))")
    public void beforeStoreDelete(JoinPoint joinPoint) throws Throwable {
        int id = (int)joinPoint.getArgs()[0];
        Arrays.stream(fileUtil.getFolders()).forEach(folder -> {
            if (folder.contains("Product")){
                try {
                    Product product = (Product)fileUtil.read(folder);
                    if (product.getStoreId() == id){
                        fileUtil.delete(Product.class, product.getId());
                    }
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
        fileUtil.delete(Store.class, id);
    }

}
