package com.company.utils.impl;

import java.io.FileInputStream;
import java.io.IOException;

public class Properties {

    static java.util.Properties properties = new java.util.Properties();

    static {
        try (FileInputStream fileInputStream = new FileInputStream("src/resources/config.properties")){
            properties.load(fileInputStream);
        }
        catch (IOException ignored){}
    }

    public static String getCustomerFile(){
        return properties.getProperty("file.customers");
    }

    public static String getOrderFile(){
        return properties.getProperty("file.orders");
    }

    public static String getProductFile(){
        return properties.getProperty("file.products");
    }

    public static String getStoreFile(){
        return properties.getProperty("file.stores");
    }

}
