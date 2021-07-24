package com.company.ui.menuItem.impl.storeItems.product;

import com.company.exception.SaveDataException;
import com.company.exception.WrongIdException;
import com.company.facade.Facade;
import com.company.models.DTO.ProductDto;
import com.company.models.ProductCategory;
import com.company.ui.menuItem.MenuItem;

import java.util.*;

public class AddProductItem implements MenuItem {

    final Facade facade;

    public AddProductItem(Facade facade){
        this.facade = facade;
    }

    @Override
    public void display() {
        System.out.println("Add product.");
    }

    @Override
    public void doAction() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Store id: ");
            int id = scanner.nextInt();

            scanner.skip("\\R");

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Description: ");
            String description = scanner.nextLine();

            System.out.print("Amount: ");
            int amount = scanner.nextInt();

            System.out.print("Price: ");
            double price = scanner.nextDouble();
            scanner.skip("\\R");

            facade.addProduct(new ProductDto(name,description,amount,price,getCategories(scanner),id));

            System.out.println("Added.");
        }
        catch (InputMismatchException mismatchException){
            System.out.println("Invalid input.");
        }
        catch (WrongIdException | SaveDataException exception){
            System.out.println(exception.getMessage());
        }
    }

    private List<String> getCategories(Scanner scanner){

        List<String> categories = new ArrayList<>();

        System.out.println("Categories:");
        for (ProductCategory category: ProductCategory.values()){
            System.out.println(category.toString().toLowerCase());
        }
        System.out.println("Enter categories (- if none):");

        String category;
        while (!(category = scanner.nextLine()).equals("-")){
            try {
                ProductCategory.valueOf(category.toUpperCase());
                categories.add(category.toUpperCase());
            }
            catch (IllegalArgumentException exception){
                System.out.println("Invalid category.");
            }
        }

        return categories;
    }
}
