package com.company.ui.menuItem.impl.storeItems.store;

import com.company.facade.Facade;
import com.company.ui.menuItem.MenuItem;

public class DisplayStoreItem implements MenuItem {

    final Facade facade;

    public DisplayStoreItem(Facade facade){
        this.facade = facade;
    }

    @Override
    public void display() {
        System.out.println("Display all stores.");
    }

    @Override
    public void doAction() {
        facade.getStoreList().forEach(store -> System.out.println(store.toString()));
    }
}
