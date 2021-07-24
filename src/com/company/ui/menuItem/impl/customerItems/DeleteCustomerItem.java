package com.company.ui.menuItem.impl.customerItems;

import com.company.exception.SaveDataException;
import com.company.exception.WrongIdException;
import com.company.facade.Facade;
import com.company.ui.menuItem.MenuItem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DeleteCustomerItem implements MenuItem {

    final Facade facade;

    public DeleteCustomerItem(Facade facade){
        this.facade = facade;
    }

    @Override
    public void display() {
        System.out.println("Remove client.");
    }

    @Override
    public void doAction() {
        int id;
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("ID: ");
            id = scanner.nextInt();

            facade.deleteCustomer(id);

            System.out.println("Deleted.");
        }
        catch (WrongIdException | SaveDataException exception){
            System.out.println(exception.getMessage());
        }
        catch (InputMismatchException mismatchException){
            System.out.println("Invalid input.");
        }
    }
}
