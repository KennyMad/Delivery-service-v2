package com.company.ui.menuItem.impl.storeItems.store;

import com.company.exception.SaveDataException;
import com.company.exception.WrongIdException;
import com.company.facade.Facade;
import com.company.ui.menuItem.MenuItem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DeleteStoreItem implements MenuItem {

    final Facade facade;

    public DeleteStoreItem(Facade facade){
        this.facade = facade;
    }

    @Override
    public void display() {
        System.out.println("Remove store.");
    }

    @Override
    public void doAction() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Store id: ");
            facade.deleteStore(scanner.nextInt());

            System.out.println("Deleted.");
        }
        catch (InputMismatchException mismatchException){
            System.out.println("Invalid input.");
        }
        catch (WrongIdException | SaveDataException exception){
            System.out.println(exception.getMessage());
        }
    }
}
