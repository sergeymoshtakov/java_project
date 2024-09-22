package org.example.menu;

import org.example.dao.*;
import org.example.factories.*;
import org.example.models.*;
import org.example.util.RandomElements;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

public class CoffeeMenu {
    private Scanner scanner;
    private CoffeeMenuExecutor executor;

    public CoffeeMenu() {
        scanner = new Scanner(System.in);
        executor = new CoffeeMenuExecutor(scanner);
        initializeInfo();
    }

    private void initializeInfo() {
        if (CustomerDao.getAllCustomers().isEmpty()) {
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

            for (int i = 0; i < 5; i++) {
                Order randomOrder = OrderFactory.createRandomOrder();
                OrderDao.addNewOrder(randomOrder);
            }

            for (int i = 0; i < 5; i++) {
                StaffSchedule randomSchedule = StaffScheduleFactory.createRandomSchedule();
                StaffScheduleDao.addNewSchedule(randomSchedule);
            }
        }

        List<Staff> staffList = StaffDao.getAllStaff();
        for (int i = 0; i < 5; i++) {
            Staff staff = RandomElements.getRandomElement(staffList);

            Calendar calendar = Calendar.getInstance();

            Date workDay = new Date(calendar.getTimeInMillis());

            Time startTime = executor.getRandomTime();
            Time endTime = executor.getEndTimeAfter(startTime);

            StaffSchedule randomSchedule = new StaffSchedule(0, staff, workDay, startTime, endTime);
            StaffScheduleDao.addNewSchedule(randomSchedule);
        }
    }

    public void start() {
        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("Goodbye!");
                    break;
                case 1:
                    executor.addMenuItem();
                    break;
                case 2:
                    executor.addStaff("Barista");
                    break;
                case 3:
                    executor.addStaff("Confectioner");
                    break;
                case 4:
                    executor.addCustomer();
                    break;
                case 5:
                    executor.updateMenuItemPrice();
                    break;
                case 6:
                    executor.updateConfectionerAddress();
                    break;
                case 7:
                    executor.updateBaristaPhone();
                    break;
                case 8:
                    executor.updateCustomerDiscount();
                    break;
                case 9:
                    executor.deleteDessert();
                    break;
                case 10:
                    executor.deleteWaiter();
                    break;
                case 11:
                    executor.deleteBarista();
                    break;
                case 12:
                    executor.deleteCustomer();
                    break;
                case 13:
                    executor.showAllDrinks();
                    break;
                case 14:
                    executor.showAllDesserts();
                    break;
                case 15:
                    executor.showAllBaristas();
                    break;
                case 16:
                    executor.showAllWaiters();
                    break;
                case 17:
                    executor.showSmallestDiscount();
                    break;
                case 18:
                    executor.showBiggestDiscount();
                    break;
                case 19:
                    executor.showClientsWithSmallestDiscount();
                    break;
                case 20:
                    executor.showClientsWithBiggestDiscount();
                    break;
                case 21:
                    executor.showYoungestCustomer();
                    break;
                case 22:
                    executor.showOldestCustomer();
                    break;
                case 23:
                    executor.showCustomersWithBirthdayToday();
                    break;
                case 24:
                    executor.showCustomersWithoutEmail();
                    break;
                case 25:
                    executor.showOrdersByDate();
                    break;
                case 26:
                    executor.showOrdersByDateRange();
                    break;
                case 27:
                    executor.countDessertOrdersOnDate();
                    break;
                case 28:
                    executor.countDrinkOrdersOnDate();
                    break;
                case 29:
                    executor.showCustomersWhoOrderedDrinksToday();
                    break;
                case 30:
                    executor.showAverageOrderAmountForDate();
                    break;
                case 31:
                    executor.showMaxOrderAmountForDate();
                    break;
                case 32:
                    executor.showCustomerWithMaxOrderForDate();
                    break;
                case 33:
                    executor.showBaristaSchedule();
                    break;
                case 34:
                    executor.showAllBaristasSchedule();
                    break;
                case 35:
                    executor.showAllStaffSchedule();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 0);
    }

    private void showMenu(){
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
        System.out.println("17 - Show smallest discount for client");
        System.out.println("18 - Show biggest discount for client");
        System.out.println("19 - Show clients with smallest discount");
        System.out.println("20 - Show clients with biggest discount");
        System.out.println("21 - Show youngest customer");
        System.out.println("22 - Show oldest customer");
        System.out.println("23 - Show customers with birthday today");
        System.out.println("24 - Show customers without email");
        System.out.println("25 - Show orders at day");
        System.out.println("26 - Show orders between days");
        System.out.println("27 - Show orders of desserts at day");
        System.out.println("28 - Show orders of drinks at day");
        System.out.println("29 - Show customers who ordered drinks today");
        System.out.println("30 - Show average order amount for a specific date");
        System.out.println("31 - Show maximum order amount for a specific date");
        System.out.println("32 - Show customer with maximum order for a specific date");
        System.out.println("33 - Show barista's schedule");
        System.out.println("34 - Show schedule for all baristas for week");
        System.out.println("35 - Show schedule for all workers for week");
        System.out.println("0 - Exit");
        System.out.print("Enter your choice: ");
    }
}
