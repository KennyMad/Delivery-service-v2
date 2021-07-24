package com.company.ui.menuItem.impl.storeItems.product;

import com.company.facade.Facade;
import com.company.ui.menuItem.MenuItem;

import java.util.Locale;
import java.util.Scanner;

public class DisplayProductsByPriceItem implements MenuItem {

    final Facade facade;

    public DisplayProductsByPriceItem(Facade facade){
        this.facade = facade;
    }

    @Override
    public void display() {
        System.out.println("Display products sorted by price.");
    }

    @Override
    public void doAction() {
        System.out.println("Is reversed ? (yes/no)");
        Scanner scanner = new Scanner(System.in);

        if ("yes".equals(scanner.nextLine().toLowerCase(Locale.ROOT))) {
            facade.getProductsByPrice(true).forEach(product -> System.out.println(product.toString()));
        } else {
            facade.getProductsByPrice(false).forEach(product -> System.out.println(product.toString()));
        }
    }
}
