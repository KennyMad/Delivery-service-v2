package com.company.ui.menuItem.impl.orderItems;

import com.company.exception.SaveDataException;
import com.company.exception.WrongIdException;
import com.company.facade.Facade;
import com.company.models.DTO.OrderAddressDto;
import com.company.models.DTO.OrderDto;
import com.company.models.DTO.ProductDto;
import com.company.ui.menuItem.MenuItem;

import java.util.*;

public class CreateOrderItem implements MenuItem {

    final Facade facade;

    public CreateOrderItem(Facade facade){
        this.facade = facade;
    }

    @Override
    public void display() {
        System.out.println("Take an order.");
    }

    @Override
    public void doAction() {

        Scanner scanner = new Scanner(System.in);

        try {
            int customerId;

            System.out.print("Customer id: ");
            customerId = scanner.nextInt();
            scanner.skip("\\R");

            HashMap<Integer, Integer> products = getProducts(scanner);

            System.out.print("House: ");
            String house = scanner.nextLine();

            System.out.print("Street: ");
            String street = scanner.nextLine();

            System.out.print("City: ");
            String city = scanner.nextLine();

            facade.createOrder(new OrderDto(customerId,new OrderAddressDto(house,street,city),products));

            System.out.println("Created.");
        }
        catch (InputMismatchException | IndexOutOfBoundsException | NumberFormatException mismatchException){
            System.out.println("Invalid input.");
        }
        catch (WrongIdException | SaveDataException exception){
            System.out.println(exception.getMessage());
        }
    }

    private HashMap<Integer,Integer> getProducts(Scanner scanner) throws WrongIdException, InputMismatchException, IndexOutOfBoundsException, NumberFormatException{
        int storeId;

        HashMap<Integer, Integer> productIdsAndCountMap = new HashMap();

        System.out.print("Enter store id (- if none): ");
        String input = scanner.nextLine();
        while (!input.equals("-")){
            storeId = Integer.parseInt(input);

            System.out.println("Products: ");
            facade.getProductsByStore(storeId).forEach(System.out::println);

            productIdsAndCountMap.putAll(getProductIdsAndCount(scanner,storeId));

            System.out.print("Enter store id (- if none): ");
            input = scanner.nextLine();
        }

        return productIdsAndCountMap;
    }

    private HashMap<Integer,Integer> getProductIdsAndCount(Scanner scanner, int storeId) throws WrongIdException{
        HashMap<Integer,Integer> productList = new HashMap<>();
        boolean added;
        int id;
        String input;

        System.out.print("Enter product id (- if none): ");
        input = scanner.nextLine();

        while (!input.equals("-")){
            id = Integer.parseInt(input);

            added = false;

            for (ProductDto product: facade.getProductsByStore(storeId)){
                if (product.getId() == id){
                    System.out.print("Amount: ");
                    productList.put(id,Integer.parseInt(scanner.nextLine()));
                    added = true;
                    break;
                }
            }
            if (!added)
                System.out.println("Wrong id.");

            input = scanner.nextLine();
        }

        return productList;
    }
}
