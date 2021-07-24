package com.company.ui.controller.impl;

import com.company.ui.controller.MenuController;
import com.company.ui.menu.Menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuControllerImpl implements MenuController {

    Menu menu;

    public MenuControllerImpl (Menu menu){
        this.menu = menu;
    }

    @Override
    public void run() {
        boolean run = true;
        int choice = 0;

        Scanner in = new Scanner(System.in);

        while (run){
            menu.display();

            try {
                choice = in.nextInt();
                menu.getItems().get(choice - 1).doAction();
            }
            catch (InputMismatchException mismatchException){
                System.out.println("Incorrect choice.");
            }
            catch (IndexOutOfBoundsException exception){
                if (choice == menu.getItems().size() + 1)
                    run = false;
                else
                    System.out.println("Incorrect choice.");
            }
        }
    }
}
