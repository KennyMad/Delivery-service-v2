package com.company.ui.menuItem.impl.storeItems.product;

import com.company.facade.Facade;
import com.company.ui.menuItem.MenuItem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DisplayProductsItem implements MenuItem {

    final Facade facade;
    private List<MenuItem> displayProductsItems;

    public DisplayProductsItem(Facade facade){
        this.facade = facade;

        displayProductsItems = new ArrayList<>();
        displayProductsItems.add(new DisplayProductsByPriceItem(facade));
        displayProductsItems.add(new DisplayProductsByCategoryItem(facade));
    }

    @Override
    public void display() {
        System.out.println("Display products.");
    }

    @Override
    public void doAction() {
        System.out.println("1) Display all products.");
        int i = 2;
        for (MenuItem item: displayProductsItems){
            System.out.print(i++ + ") ");
            item.display();
        }

        Scanner scanner = new Scanner(System.in);

        try {
            int choice = scanner.nextInt();
            if (choice == 1)
                displayAll();
            else
                displayProductsItems.get(choice - 2).doAction();

        }
        catch (InputMismatchException | IndexOutOfBoundsException mismatchException){
            System.out.println("Invalid input.");
        }

    }

    private void displayAll(){
        facade.getProductList().forEach(product -> System.out.println(product.toString()));
    }
}
