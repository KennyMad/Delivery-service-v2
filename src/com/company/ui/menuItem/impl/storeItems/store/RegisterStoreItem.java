package com.company.ui.menuItem.impl.storeItems.store;

import com.company.exception.SaveDataException;
import com.company.facade.Facade;
import com.company.models.DTO.StoreDto;
import com.company.ui.menuItem.MenuItem;

import java.util.Scanner;

public class RegisterStoreItem implements MenuItem {

    final Facade facade;

    public RegisterStoreItem(Facade facade){
        this.facade = facade;
    }

    @Override
    public void display() {
        System.out.println("Registration of a new store.");
    }

    @Override
    public void doAction() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.println("Description: ");
        String description = scanner.nextLine();

        try {
            facade.registerStore(new StoreDto(name, description));
        }
        catch (SaveDataException exception){
            System.out.println(exception.getMessage());
        }

        System.out.println("Registered");
    }
}
