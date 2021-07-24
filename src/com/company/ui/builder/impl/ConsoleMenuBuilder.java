package com.company.ui.builder.impl;

import com.company.facade.Facade;
import com.company.ui.builder.MenuBuilder;
import com.company.ui.menu.Menu;
import com.company.ui.menu.impl.ConsoleMenu;
import com.company.ui.menuItem.MenuItem;
import com.company.ui.menuItem.impl.customerItems.ChangeCustomerItem;
import com.company.ui.menuItem.impl.customerItems.DeleteCustomerItem;
import com.company.ui.menuItem.impl.customerItems.DisplayCustomerItem;
import com.company.ui.menuItem.impl.customerItems.RegisterCustomerItem;
import com.company.ui.menuItem.impl.filesItem.SaveDataMenuItem;
import com.company.ui.menuItem.impl.orderItems.CreateOrderItem;
import com.company.ui.menuItem.impl.orderItems.DisplayOrderItem;
import com.company.ui.menuItem.impl.storeItems.product.*;
import com.company.ui.menuItem.impl.storeItems.store.ChangeStoreItem;
import com.company.ui.menuItem.impl.storeItems.store.DeleteStoreItem;
import com.company.ui.menuItem.impl.storeItems.store.DisplayStoreItem;
import com.company.ui.menuItem.impl.storeItems.store.RegisterStoreItem;

import java.util.ArrayList;
import java.util.List;

public class ConsoleMenuBuilder implements MenuBuilder {

    final Facade facade;

    public ConsoleMenuBuilder(Facade facade){
        this.facade = facade;
    }

    @Override
    public Menu createMenu() {

        List<MenuItem> menuItems = new ArrayList<>();

        menuItems.add(new RegisterCustomerItem(facade));
        menuItems.add(new DeleteCustomerItem(facade));
        menuItems.add(new ChangeCustomerItem(facade));
        menuItems.add(new DisplayCustomerItem(facade));

        menuItems.add(new CreateOrderItem(facade));
        menuItems.add(new DisplayOrderItem(facade));

        menuItems.add(new AddProductItem(facade));
        menuItems.add(new DeleteProductItem(facade));
        menuItems.add(new ChangeProductItem(facade));
        menuItems.add(new FindProductsByAttributesItem(facade));
        menuItems.add(new DisplayProductsItem(facade));
        menuItems.add(new RegisterStoreItem(facade));
        menuItems.add(new DeleteStoreItem(facade));
        menuItems.add(new ChangeStoreItem(facade));
        menuItems.add(new DisplayStoreItem(facade));

        menuItems.add(new SaveDataMenuItem(facade));

        return new ConsoleMenu(menuItems);
    }
}
