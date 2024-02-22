package Springboot.MealMinder.commands;
import java.util.*;
import Springboot.MealMinder.entity.MenuItem;
import Springboot.MealMinder.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;


@Component
public class MenuMinderCommands {
    private final MenuService menuService;

    @Autowired
    public MenuMinderCommands(MenuService menuService) {
        this.menuService = menuService;
    }

    public void start(Scanner scanner) {
        System.out.println("\nWelcome to MenuMinder!");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Get menu by mealtime");
            System.out.println("2. Get menu by category");
            System.out.println("3. Add menu item");
            System.out.println("4. Exit");

            System.out.println("Enter your choice:");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    getMenuByMealtime(scanner);
                    break;
                case 2:
                    getMenuByCategory(scanner);
                    break;
                case 3:
                    addMenuItem(scanner);
                    break;
                case 4:
                    System.out.println("Thank you for using MenuMinder.!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }
    private void getMenuByCategory() {
        Set<String> availableCategories = getAvailableCategories();
        System.out.println("Available categories:");
        for (String category : availableCategories) {
            System.out.println(category);
        }
    }

        private void getMenuByMealtime(Scanner scanner) {
        System.out.println("\nEnter mealtime (breakfast, lunch, dinner, snacks):");
        String mealtime = scanner.nextLine().trim().toLowerCase();

        if (!menuService.isValidMealtime(mealtime)) {
            System.out.println("Invalid mealtime. Please try again.");
            return;
        }

        printMenuForMealtime(mealtime);
    }

    private void getMenuByCategory(Scanner scanner) {
        System.out.println("\nEnter category:");
        String category = scanner.nextLine().trim().toLowerCase();

        if (!isValidCategory(category)) {
            System.out.println("Invalid category. Please try again.");
            return;
        }

        printMenuForCategory(category);
    }

    private void addMenuItem(Scanner scanner) {
        System.out.println("\nEnter mealtime [breakfast, lunch, dinner, snacks]:");
        String mealtime = scanner.nextLine().trim().toLowerCase();

        if (!menuService.isValidMealtime(mealtime)) {
            System.out.println("Invalid mealtime. Please try again.");
            return;
        }

        System.out.println("Enter item name:");
        String name = scanner.nextLine().trim();

        System.out.println("Enter item category:"+" "+getAvailableCategories());
        String category = scanner.nextLine().trim();

        if (!menuService.isValidCategory(category)) {
            System.out.println("Invalid category. Please try again.");
            return;
        }

        menuService.addMenuItem(mealtime, name, category);
        System.out.println("Item added successfully!");
    }

    private void printMenuForMealtime(String mealtime) {
        menuService.printMenu(mealtime);
    }

    private void printMenuForCategory(String category) {
        menuService.printCategory(category);
    }

    public boolean isValidCategory(String category) {
        Set<String> validCategories = getAvailableCategories();
        return validCategories.contains(category);
    }

    public Set<String> getAvailableMealTimes() {
        return menuService.getMealTimeCache().keySet();
    }

    public Set<String> getAvailableCategories() {
        return menuService.getcategoryCache().keySet();
    }
}
