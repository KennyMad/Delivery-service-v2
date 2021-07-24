package com.company.ui.menuItem.impl.customerItems;

import com.company.facade.Facade;
import com.company.ui.menuItem.MenuItem;

public class DisplayCustomerItem implements MenuItem {
    final Facade facade;

    public DisplayCustomerItem(Facade facade){
        this.facade = facade;
    }

    @Override
    public void display() {
        System.out.println("Display all customers.");
    }

    @Override
    public void doAction() {
        facade.getCustomerList().forEach(customer -> System.out.println(customer.toString()));
    }
}
