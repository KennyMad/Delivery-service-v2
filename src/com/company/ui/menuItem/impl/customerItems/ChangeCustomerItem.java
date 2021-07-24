package com.company.ui.menuItem.impl.customerItems;

import com.company.exception.SaveDataException;
import com.company.exception.WrongIdException;
import com.company.facade.Facade;
import com.company.models.DTO.CustomerDto;
import com.company.ui.menuItem.MenuItem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ChangeCustomerItem implements MenuItem {

    final Facade facade;

    public ChangeCustomerItem(Facade facade){
        this.facade = facade;
    }

    @Override
    public void display() {
        System.out.println("Change customer details.");
    }

    @Override
    public void doAction() {
        int id;
        String name, additionalInformation;

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("ID: ");
            id = scanner.nextInt();
            scanner.skip("\\R");

            System.out.print("Name: ");
            name = scanner.nextLine();

            System.out.print("Additional information: ");
            additionalInformation = scanner.nextLine();

            facade.updateCustomer(new CustomerDto(name,additionalInformation,id));

            System.out.println("Updated.");
        }
        catch (WrongIdException | SaveDataException exception){
            System.out.println(exception.getMessage());
        }
        catch (InputMismatchException mismatchException){
            System.out.println("Invalid input.");
        }
    }
}
