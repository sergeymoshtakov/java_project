package org.example.menu;

import org.example.dao.*;
import org.example.factories.*;
import org.example.models.*;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class CoffeeMenu {
    private Scanner scanner;

    public CoffeeMenu() {
        scanner = new Scanner(System.in);
        initializeInfo();
    }

    private void initializeInfo() {
        for (String itemType : ItemTypeFactory.ITEM_TYPE_NAMES) {
            ItemType newItemType = new ItemType(0, itemType);
            ItemTypeDao.addNewItemType(newItemType);
        }
        System.out.println("Item types initialized.");

        for (String position : PositionFactory.POSITION_NAMES) {
            Position newPosition = new Position(0, position);
            PositionDao.addNewPosition(newPosition);
        }
        System.out.println("Staff positions initialized.");

        Staff randomStaff = StaffFactory.createRandomStaff();
        StaffDao.addNewStaff(randomStaff);

        MenuItem randomMenuItem = MenuItemFactory.createRandomMenuItem();
        MenuItemDao.addNewMenuItem(randomMenuItem);

        Customer randomCustomer = CustomerFactory.createRandomCustomer();
        CustomerDao.addNewCustomer(randomCustomer);

        System.out.println("Random staff, menu item, and customer initialized.");
    }


    public void start() {
        int choice;
        do {
            System.out.println("1 - Add new menu item");
            System.out.println("2 - Add new barista");
            System.out.println("3 - Add new confectioner");
            System.out.println("4 - Add new client");
            System.out.println("5 - Change price for coffee");
            System.out.println("6 - Change address for confectioner");
            System.out.println("7 - Change phone of barista");
            System.out.println("8 - Change discount for client");
            System.out.println("9 - Delete dessert");
            System.out.println("10 - Delete waiter");
            System.out.println("11 - Delete barista");
            System.out.println("12 - Delete client");
            System.out.println("13 - Show all drinks");
            System.out.println("14 - Show all desserts");
            System.out.println("15 - Show all baristas");
            System.out.println("16 - Show all waiters");
            System.out.println("0 - Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("Goodbye!");
                    break;
                case 1:
                    addMenuItem();
                    break;
                case 2:
                    addStaff("Barista");
                    break;
                case 3:
                    addStaff("Confectioner");
                    break;
                case 4:
                    addCustomer();
                    break;
                case 5:
                    updateMenuItemPrice();
                    break;
                case 6:
                    updateConfectionerAddress();
                    break;
                case 7:
                    updateBaristaPhone();
                    break;
                case 8:
                    updateCustomerDiscount();
                    break;
                case 9:
                    deleteDessert();
                    break;
                case 10:
                    deleteWaiter();
                    break;
                case 11:
                    deleteBarista();
                    break;
                case 12:
                    deleteCustomer();
                    break;
                case 13:
                    showAllDrinks();
                    break;
                case 14:
                    showAllDesserts();
                    break;
                case 15:
                    showAllBaristas();
                    break;
                case 16:
                    showAllWaiters();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 0);
    }

    private void addMenuItem() {
        System.out.println("Enter the name in English:");
        String nameEn = scanner.nextLine();
        System.out.println("Enter the name in German:");
        String nameDe = scanner.nextLine();
        System.out.println("Enter the price:");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter the item type:");
        String itemTypeName = scanner.nextLine();
        ItemType itemType = new ItemType(0, itemTypeName);

        MenuItem newItem = new MenuItem(0, nameEn, nameDe, price, itemType);
        MenuItemDao.addNewMenuItem(newItem);
    }

    private void addStaff(String role) {
        System.out.println("Enter " + role + " first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter " + role + " last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter " + role + " phone number:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter " + role + " email:");
        String email = scanner.nextLine();
        Position position = new Position(0, role);
        Staff newStaff = new Staff(0, firstName, lastName, phoneNumber, email, position);
        StaffDao.addNewStaff(newStaff);
    }

    private void addCustomer() {
        System.out.println("Enter customer first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter customer last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter customer birth date (YYYY-MM-DD):");
        String birthDate = scanner.nextLine();
        System.out.println("Enter customer phone number:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter customer email:");
        String email = scanner.nextLine();
        System.out.println("Enter customer discount:");
        double discount = scanner.nextDouble();
        scanner.nextLine();

        Customer newCustomer = new Customer(0, firstName, lastName, Date.valueOf(birthDate), phoneNumber, email, discount);
        CustomerDao.addNewCustomer(newCustomer);
    }

    private void updateMenuItemPrice() {
        System.out.println("Enter coffee name:");
        String coffeeName = scanner.nextLine();
        System.out.println("Enter new price:");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();
        MenuItemDao.updateMenuItemPrice(coffeeName, newPrice);
    }

    private void updateConfectionerAddress() {
        System.out.println("Enter confectioner last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter new email:");
        String newEmail = scanner.nextLine();
        StaffDao.updateConfectionerAddress(lastName, newEmail);
    }

    private void updateBaristaPhone() {
        System.out.println("Enter barista last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter new phone:");
        String newPhone = scanner.nextLine();
        StaffDao.updateBaristaPhone(lastName, newPhone);
    }

    private void updateCustomerDiscount() {
        System.out.println("Enter customer last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter new discount:");
        double newDiscount = scanner.nextDouble();
        scanner.nextLine();
        CustomerDao.updateCustomerDiscount(lastName, newDiscount);
    }

    private void deleteDessert() {
        System.out.println("Enter dessert name:");
        String dessertName = scanner.nextLine();
        MenuItem dessert = new MenuItem();
        dessert.setNameEn(dessertName);
        MenuItemDao.deleteMenuItem(dessert);
    }

    private void deleteWaiter() {
        deleteStaff("Waiter");
    }

    private void deleteBarista() {
        deleteStaff("Barista");
    }

    private void deleteCustomer() {
        System.out.println("Enter customer first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter customer last name:");
        String lastName = scanner.nextLine();
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        CustomerDao.deleteCustomer(customer);
    }

    private void showAllDrinks() {
        List<MenuItem> drinks = MenuItemDao.getAllMenuItems();
        System.out.println("All drinks:");
        for (MenuItem drink : drinks) {
            if (drink.getItemType().getName().equalsIgnoreCase("Drink")) {
                System.out.println(drink.getNameEn() + " - " + drink.getPrice() + " USD");
            }
        }
    }

    private void showAllDesserts() {
        List<MenuItem> desserts = MenuItemDao.getAllMenuItems();
        System.out.println("All desserts:");
        for (MenuItem dessert : desserts) {
            if (dessert.getItemType().getName().equalsIgnoreCase("Dessert")) {
                System.out.println(dessert.getNameEn() + " - " + dessert.getPrice() + " USD");
            }
        }
    }

    private void showAllBaristas() {
        showStaffByRole("Barista");
    }

    private void showAllWaiters() {
        showStaffByRole("Waiter");
    }

    private void deleteStaff(String role) {
        System.out.println("Enter " + role + " first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter " + role + " last name:");
        String lastName = scanner.nextLine();
        Staff staff = new Staff();
        staff.setFirstName(firstName);
        staff.setLastName(lastName);
        StaffDao.deleteStaff(staff);
    }

    private void showStaffByRole(String role) {
        List<Staff> staffList = StaffDao.getAllStaff();
        System.out.println("All " + role.toLowerCase() + "s:");
        for (Staff staff : staffList) {
            if (staff.getPosition().getName().equalsIgnoreCase(role)) {
                System.out.println(staff.getFirstName() + " " + staff.getLastName() + " - " + staff.getPhoneNumber());
            }
        }
    }
}
