package com.company.ui.menuItem.impl.storeItems.product;

import com.company.exception.InvalidAttributeException;
import com.company.facade.Facade;
import com.company.ui.menuItem.MenuItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FindProductsByAttributesItem implements MenuItem {

    final Facade facade;

    public FindProductsByAttributesItem(Facade facade){
        this.facade = facade;
    }

    @Override
    public void display() {
        System.out.println("Find products by attributes value.");
    }

    @Override
    public void doAction() {
        System.out.println("Enter attributes and values (type attribute:value (- if none)):");
        String input;

        Scanner scanner = new Scanner(System.in);
        Map<String, String> nameValueMap = new HashMap<>();
        while (!(input = scanner.nextLine()).equals("-")){
            String[] values = input.split(":");
            if (values.length != 2){
                System.out.println("Wrong input.");
            }
            else {
                nameValueMap.put(values[0],values[1]);
            }
        }

        try {
            facade.getProductsByAttributes(nameValueMap).forEach(System.out::println);
        }
        catch (InvalidAttributeException exception){
            System.out.println(exception.getMessage());
        }
    }
}
