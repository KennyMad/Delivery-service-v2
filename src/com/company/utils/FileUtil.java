package com.company.utils;

import com.company.exception.SaveDataException;
import com.company.repository.*;

import java.util.Collection;

public interface FileUtil{

    void save(Collection data, String fileName) throws SaveDataException;

    CustomerDAO loadCustomers();

    OrderDAO loadOrders();

    StoreDAO loadStores();

    ProductDAO loadProducts();
}
