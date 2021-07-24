package com.company.ui.menuItem.impl.orderItems;

import com.company.facade.Facade;
import com.company.ui.menuItem.MenuItem;

public class DisplayOrderItem implements MenuItem {

    final Facade facade;

    public DisplayOrderItem(Facade facade){
        this.facade = facade;
    }

    @Override
    public void display() {
        System.out.println("Display all orders.");
    }

    @Override
    public void doAction() {
        facade.getOrderList().forEach(System.out::println);
    }
}
