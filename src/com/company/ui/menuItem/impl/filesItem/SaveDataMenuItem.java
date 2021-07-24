package com.company.ui.menuItem.impl.filesItem;

import com.company.exception.SaveDataException;
import com.company.facade.Facade;
import com.company.ui.menuItem.MenuItem;

public class SaveDataMenuItem implements MenuItem {

    private final Facade facade;

    public SaveDataMenuItem(Facade facade){
        this.facade = facade;
    }

    @Override
    public void display() {
        System.out.println("Save all data.");
    }

    @Override
    public void doAction() {
        try {
            facade.saveData();
        }
        catch (SaveDataException exception){
            System.out.println(exception.getMessage());
        }
    }
}
