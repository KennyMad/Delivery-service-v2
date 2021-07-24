package com.company.ui.menu.impl;

import com.company.ui.menu.Menu;
import com.company.ui.menuItem.MenuItem;

import java.util.Collection;
import java.util.List;

public class ConsoleMenu implements Menu {

    private final List<MenuItem> menuItems;

    public ConsoleMenu(List<MenuItem> menuItems){
        this.menuItems = menuItems;
    }

    @Override
    public void display() {
        int i = 1;
        for (MenuItem menuItem: menuItems){
            System.out.print(i++ + ") ");
            menuItem.display();
        }
        System.out.println(i + ") Exit.");
    }

    @Override
    public List<MenuItem> getItems() {
        return menuItems;
    }
}
