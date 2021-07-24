package com.company.ui.menuItem.impl.storeItems.product;

import com.company.exception.WrongIdException;
import com.company.facade.Facade;
import com.company.models.ProductCategory;
import com.company.ui.menuItem.MenuItem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DisplayProductsByCategoryItem implements MenuItem {

    final Facade facade;

    public DisplayProductsByCategoryItem(Facade facade){
        this.facade = facade;
    }

    @Override
    public void display() {
        System.out.println("Display products of a certain category.");
    }

    @Override
    public void doAction() {
        System.out.println("Categories: ");
        for (ProductCategory category: ProductCategory.values()){
            System.out.println(category.toString().toLowerCase());
        }

        try {
            Scanner scanner = new Scanner(System.in);

            facade.getProductsByCategory(ProductCategory.valueOf(scanner.nextLine().toUpperCase())).forEach(product -> System.out.println(product.toString()));
        }
        catch (InputMismatchException | IllegalArgumentException mismatchException){
            System.out.println("Invalid input.");
        }
    }
}
