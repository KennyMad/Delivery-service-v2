package com.company.utils;

import com.company.models.Customer;
import com.company.models.Order;
import com.company.models.Product;
import com.company.models.Store;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileUtil {

    @Getter
    @Value("${folder.root}")
    private String rootFolder;

    @Autowired
    private FileCreator creator;

    @Autowired
    private ObjectGenerator objectGenerator;

    public void save (Object object) throws Throwable {
        creator.createFolder(rootFolder);
        if (object instanceof Store){
            Store store = (Store) object;
            creator.createFolder(rootFolder + File.separator + "Store id_" + store.getId());
            creator.createJson(store,rootFolder + File.separator + "Store id_" + store.getId());
        }
        else if (object instanceof Product){
            Product product = (Product) object;
            creator.createFolder(rootFolder + File.separator + "Product id_" + product.getId());
            creator.createJson(product,rootFolder + File.separator + "Product id_" + product.getId());
        }
        else if (object instanceof Customer){
            Customer customer = (Customer) object;
            creator.createFolder(rootFolder + File.separator + "Customer id_" + customer.getId());
            creator.createJson(customer,rootFolder + File.separator + "Customer id_" + customer.getId());
        }
        else if (object instanceof Order){
            Order order = (Order) object;
            creator.createFolder(rootFolder + File.separator + "Order id_" + order.getId());
            creator.createJson(order,rootFolder + File.separator + "Order id_" + order.getId());
        }
    }

    public Object read(String folderName) throws Throwable{
        String path = rootFolder + File.separator + folderName;
        switch (folderName.split(" ")[0]){
            case "Store":
                return objectGenerator.generate(path, Store.class);
            case "Product":
                return objectGenerator.generate(path, Product.class);
            case "Order":
                return objectGenerator.generate(path, Order.class);
            case "Customer":
                return objectGenerator.generate(path, Customer.class);
        }
        return null;
    }

    public void delete(Class<?> objectClass, int id){
       File file = new File(rootFolder + File.separator + objectClass.getSimpleName() + " id_" + id);
       if (file.exists()){
           new File(file.getPath() + File.separator + "object.json").delete();
           file.delete();
       }
    }

    public String[] getFolders(){
        creator.createFolder(rootFolder);
        File file = new File(rootFolder);
        return file.list();
    }
}
