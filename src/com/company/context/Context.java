package com.company.context;

import com.company.facade.Facade;
import com.company.facade.impl.FacadeImpl;
import com.company.repository.CustomerDAO;
import com.company.repository.OrderDAO;
import com.company.repository.ProductDAO;
import com.company.repository.StoreDAO;
import com.company.repository.impl.CustomerDAOImpl;
import com.company.repository.impl.OrderDAOImpl;
import com.company.repository.impl.StoreDAOImpl;
import com.company.service.*;
import com.company.service.impl.*;
import com.company.ui.builder.MenuBuilder;
import com.company.ui.builder.impl.ConsoleMenuBuilder;
import com.company.ui.controller.MenuController;
import com.company.ui.controller.impl.MenuControllerImpl;
import com.company.utils.FileUtil;
import com.company.utils.impl.FileUtilImpl;

public class Context {

    private CustomerDAO customerDAO;
    private OrderDAO orderDAO;
    private StoreDAO storeDAO;
    private ProductDAO productDAO;

    private CustomerService customerService;
    private OrderService orderService;
    private StoreService storeService;
    private ProductService productService;

    private FileUtil fileUtil;

    private MenuController menuController;

    private Facade facade;

    public void initialize(){
        fileUtil = new FileUtilImpl();

        customerDAO = fileUtil.loadCustomers();
        orderDAO = fileUtil.loadOrders();
        storeDAO = fileUtil.loadStores();
        productDAO = fileUtil.loadProducts();

        customerService = new CustomerServiceImpl(customerDAO);
        orderService = new OrderServiceImpl(customerDAO, orderDAO);
        storeService = new StoreServiceImpl(storeDAO);
        productService = new ProductServiceImpl(productDAO,storeDAO);

        facade = new FacadeImpl(customerService,orderService, storeService,productService, fileUtil);

        MenuBuilder menuBuilder = new ConsoleMenuBuilder(facade);
        menuController = new MenuControllerImpl(menuBuilder.createMenu());
    }

    public Facade getFacade(){
        return facade;
    }

    public MenuController getMenuController(){
        return menuController;
    }
}
