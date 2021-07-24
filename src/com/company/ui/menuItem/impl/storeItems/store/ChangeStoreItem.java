package com.company.ui.menuItem.impl.storeItems.store;

import com.company.exception.SaveDataException;
import com.company.exception.WrongIdException;
import com.company.facade.Facade;
import com.company.models.DTO.StoreDto;
import com.company.ui.menuItem.MenuItem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ChangeStoreItem implements MenuItem {

    final Facade facade;

    public ChangeStoreItem(Facade facade){
        this.facade = facade;
    }

    @Override
    public void display() {
        System.out.println("Change store details.");
    }

    @Override
    public void doAction() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Id: ");
            int id = scanner.nextInt();
            scanner.skip("\\R");

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.println("Description: ");
            String description = scanner.nextLine();

            facade.updateStore(new StoreDto(id,name,description));

            System.out.println("Changed.");
        }
        catch (InputMismatchException mismatchException){
            System.out.println("Invalid input.");
        }
        catch (WrongIdException | SaveDataException exception){
            System.out.println(exception.getMessage());
        }
    }
}
