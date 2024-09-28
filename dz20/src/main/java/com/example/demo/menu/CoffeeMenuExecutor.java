package com.example.demo.menu;

import com.example.demo.dao.customerDao.CustomerDao;
import com.example.demo.dao.menuItemDao.MenuItemDao;
import com.example.demo.dao.orderDao.OrderDao;
import com.example.demo.dao.staffDao.StaffDao;
import com.example.demo.dao.staffScheduleDao.StaffScheduleDao;
import com.example.demo.exceptions.ItemNotFoundException;
import com.example.demo.exceptions.NoElementsException;
import com.example.demo.model.*;
import com.example.demo.util.RandomElements;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

public class CoffeeMenuExecutor {
    private final CustomerDao customerDao;
    private final StaffDao staffDao;
    private final MenuItemDao menuItemDao;
    private final OrderDao orderDao;
    private final StaffScheduleDao staffScheduleDao;
    private final Scanner scanner;

    public CoffeeMenuExecutor(Scanner scanner, CustomerDao customerDao, StaffDao staffDao, MenuItemDao menuItemDao, OrderDao orderDao, StaffScheduleDao staffScheduleDao) {
        this.scanner = scanner;
        this.customerDao = customerDao;
        this.staffDao = staffDao;
        this.menuItemDao = menuItemDao;
        this.orderDao = orderDao;
        this.staffScheduleDao = staffScheduleDao;
    }

    public void addMenuItem() {
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
        menuItemDao.save(newItem);
    }

    public void addStaff(String role) {
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
        staffDao.save(newStaff);
    }

    public void addCustomer() {
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
        customerDao.save(newCustomer);
    }

    public void updateMenuItemPrice() {
        System.out.print("Enter item ID to update price: ");
        int itemId = scanner.nextInt();
        scanner.nextLine();

        MenuItem menuItem = menuItemDao.findById(itemId);
        if (menuItem == null) {
            System.out.println("Menu item not found.");
            return;
        }

        System.out.print("Enter new price: ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();

        menuItem.setPrice(newPrice);
        menuItemDao.update(menuItem);
    }

    public void updateConfectionerAddress() {
        List<MenuItem> menuItems = menuItemDao.findAll();
        if (menuItems.isEmpty()) {
            System.out.println("No menu items found.");
            throw new NoElementsException("");
        }
        System.out.print("Enter the name of the menu item to update: ");
        String itemName = scanner.nextLine();
        MenuItem menuItem = menuItems.stream()
                .filter(item -> item.getNameEn().equalsIgnoreCase(itemName))
                .findFirst()
                .orElse(null);

        if (menuItem == null) {
            System.out.println("Menu item not found.");
            throw new ItemNotFoundException();
        }
        System.out.print("Enter new price: ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();
        menuItem.setPrice(newPrice);
        menuItemDao.update(menuItem);
    }

    public void updateBaristaPhone() {
        System.out.println("Enter barista last name:");
        String lastName = scanner.nextLine();
        List<Staff> baristas = staffDao.findAll();

        Staff barista = baristas.stream()
                .filter(b -> b.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);

        if (barista == null) {
            System.out.println("Barista not found.");
            throw new ItemNotFoundException();
        }

        System.out.println("Enter new phone:");
        String newPhone = scanner.nextLine();

        barista.setPhoneNumber(newPhone);
        staffDao.update(barista);
    }

    public void updateCustomerDiscount() {
        System.out.println("Enter customer last name:");
        String lastName = scanner.nextLine();

        List<Customer> customers = customerDao.findAll();
        Customer customer = customers.stream()
                .filter(c -> c.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        System.out.println("Enter new discount percentage:");
        double newDiscount;
        try {
            newDiscount = scanner.nextDouble();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid percentage.");
            scanner.nextLine();
            return;
        }

        customer.setDiscount(newDiscount);
        customerDao.update(customer);
    }

    public void deleteDessert() {
        System.out.println("Enter the name of the dessert to delete:");
        String dessertName = scanner.nextLine();

        List<MenuItem> menuItems = menuItemDao.findAll();

        MenuItem dessertToDelete = menuItems.stream()
                .filter(menuItem -> menuItem.getNameEn().equalsIgnoreCase(dessertName) || menuItem.getNameDe().equalsIgnoreCase(dessertName))
                .findFirst()
                .orElse(null);

        if (dessertToDelete == null) {
            System.out.println("Dessert not found.");
            throw new ItemNotFoundException();
        }

        menuItemDao.delete(dessertToDelete.getId());
        System.out.println("Dessert \"" + dessertName + "\" has been deleted.");
    }

    public void deleteWaiter() {
        System.out.print("Enter the last name of the waiter to delete: ");
        String lastName = scanner.nextLine();

        List<Staff> staffList = staffDao.findAll();

        Staff waiterToDelete = staffList.stream()
                .filter(staff -> staff.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);

        if (waiterToDelete == null) {
            System.out.println("Waiter not found.");
            return;
        }

        staffDao.delete(waiterToDelete.getId());
        System.out.println("Waiter \"" + lastName + "\" has been deleted.");
    }

    public void deleteBarista() {
        System.out.print("Enter the last name of the barista to delete: ");
        String lastName = scanner.nextLine();

        List<Staff> staffList = staffDao.findAll();

        Staff baristaToDelete = staffList.stream()
                .filter(staff -> staff.getLastName().equalsIgnoreCase(lastName) && staff.getPosition().getName().equalsIgnoreCase("Barista"))
                .findFirst()
                .orElse(null);

        if (baristaToDelete == null) {
            System.out.println("Barista not found.");
            return;
        }

        staffDao.delete(baristaToDelete.getId());
        System.out.println("Barista \"" + lastName + "\" has been deleted.");
    }

    public void deleteCustomer() {
        System.out.print("Enter the last name of the customer to delete: ");
        String lastName = scanner.nextLine();

        List<Customer> customerList = customerDao.findAll();

        Customer customerToDelete = customerList.stream()
                .filter(customer -> customer.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);

        if (customerToDelete == null) {
            System.out.println("Customer not found.");
            return;
        }

        customerDao.delete(customerToDelete.getId());
        System.out.println("Customer \"" + lastName + "\" has been deleted.");
    }

    public void showAllDrinks() {
        List<MenuItem> drinks = menuItemDao.findAll();
        System.out.println("All drinks:");
        for (MenuItem drink : drinks) {
            if (drink.getItemType().getName().equalsIgnoreCase("Drink")) {
                System.out.println(drink.getNameEn() + " - " + drink.getPrice() + " USD");
            }
        }
    }

    public void showAllDesserts() {
        List<MenuItem> desserts = menuItemDao.findAll();
        System.out.println("All desserts:");
        for (MenuItem dessert : desserts) {
            if (dessert.getItemType().getName().equalsIgnoreCase("Dessert")) {
                System.out.println(dessert.getNameEn() + " - " + dessert.getPrice() + " USD");
            }
        }
    }

    public void showAllBaristas() {
        showStaffByRole("Barista");
    }

    public void showAllWaiters() {
        showStaffByRole("Waiter");
    }

    public void showStaffByRole(String role) {
        List<Staff> staffList = staffDao.findAll();
        System.out.println("All " + role.toLowerCase() + "s:");
        for (Staff staff : staffList) {
            if (staff.getPosition().getName().equalsIgnoreCase(role)) {
                System.out.println(staff.getFirstName() + " " + staff.getLastName() + " - " + staff.getPhoneNumber());
            }
        }
    }

    public void showSmallestDiscount() {
        List<Customer> customers = customerDao.findAll();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            throw new NoElementsException("No customers available.");
        }

        Customer smallestDiscountCustomer = customers.get(0);
        for (Customer customer : customers) {
            if (customer.getDiscount() < smallestDiscountCustomer.getDiscount()) {
                smallestDiscountCustomer = customer;
            }
        }
        System.out.printf("Smallest discount: %.2f%%\n",
                smallestDiscountCustomer.getDiscount());
    }

    public void showBiggestDiscount() {
        List<Customer> customers = customerDao.findAll();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            throw new NoElementsException("No customers available.");
        }

        Customer biggestDiscountCustomer = customers.get(0);
        for (Customer customer : customers) {
            if (customer.getDiscount() > biggestDiscountCustomer.getDiscount()) {
                biggestDiscountCustomer = customer;
            }
        }
        System.out.printf("Biggest discount: - %.2f%%\n",
                biggestDiscountCustomer.getDiscount());
    }

    public void showClientsWithSmallestDiscount() {
        List<Customer> customers = customerDao.findAll();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            throw new NoElementsException("No customers available.");
        }

        double smallestDiscount = customers.stream().mapToDouble(Customer::getDiscount).min().orElse(0);
        System.out.println("Customers with the smallest discount:");
        for (Customer customer : customers) {
            if (customer.getDiscount() == smallestDiscount) {
                System.out.printf("%s %s - %.2f%%\n",
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getDiscount());
            }
        }
    }

    public void showClientsWithBiggestDiscount() {
        List<Customer> customers = customerDao.findAll();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            throw new NoElementsException("No customers available.");
        }

        double biggestDiscount = customers.stream().mapToDouble(Customer::getDiscount).max().orElse(0);
        System.out.println("Customers with the biggest discount:");
        for (Customer customer : customers) {
            if (customer.getDiscount() == biggestDiscount) {
                System.out.printf("%s %s - %.2f%%\n",
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getDiscount());
            }
        }
    }

    public void showYoungestCustomer() {
        List<Customer> customers = customerDao.findAll();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            throw new NoElementsException("No customers available.");
        }

        Customer youngestCustomer = customers.get(0);
        for (Customer customer : customers) {
            if (customer.getBirthDate().after(youngestCustomer.getBirthDate())) {
                youngestCustomer = customer;
            }
        }
        System.out.printf("Youngest customer: %s %s - Born on %s\n",
                youngestCustomer.getFirstName(),
                youngestCustomer.getLastName(),
                youngestCustomer.getBirthDate());
    }

    public void showOldestCustomer() {
        List<Customer> customers = customerDao.findAll();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            throw new NoElementsException("No customers available.");
        }

        Customer oldestCustomer = customers.get(0);
        for (Customer customer : customers) {
            if (customer.getBirthDate().before(oldestCustomer.getBirthDate())) {
                oldestCustomer = customer;
            }
        }
        System.out.printf("Oldest customer: %s %s - Born on %s\n",
                oldestCustomer.getFirstName(),
                oldestCustomer.getLastName(),
                oldestCustomer.getBirthDate());
    }

    public void showCustomersWithBirthdayToday() {
        List<Customer> customers = customerDao.findAll();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            throw new NoElementsException("No customers available.");
        }

        java.time.LocalDate today = java.time.LocalDate.now();
        java.sql.Date sqlToday = java.sql.Date.valueOf(today);
        System.out.println("Customers with birthday today:");

        for (Customer customer : customers) {
            java.sql.Date birthDate = (Date) customer.getBirthDate();

            if (birthDate.getMonth() == sqlToday.getMonth() && birthDate.getDate() == sqlToday.getDate()) {
                System.out.printf("%s %s - Born on %s\n",
                        customer.getFirstName(),
                        customer.getLastName(),
                        birthDate);
            }
        }
    }

    public void showCustomersWithoutEmail() {
        List<Customer> customers = customerDao.findAll();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            throw new NoElementsException("No customers available.");
        }

        System.out.println("Customers without email:");
        for (Customer customer : customers) {
            if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
                System.out.printf("%s %s\n",
                        customer.getFirstName(),
                        customer.getLastName());
            }
        }
    }

    public void showOrdersByDate() {
        System.out.println("Enter date (YYYY-MM-DD):");
        String dateInput = scanner.nextLine();
        Date date = Date.valueOf(dateInput);
        List<Order> orders = orderDao.findAll();
        for (Order order : orders) {
            Date orderDate = Date.valueOf(order.getOrderDate().toLocalDateTime().toLocalDate());
            if(orderDate.equals(date)) {
                System.out.println("Id: " + order.getId());
                System.out.println("Customer: " + order.getCustomerFirstName() + " " + order.getCustomerLastName());
                System.out.println("Total amount: " + order.getTotalAmount());
                System.out.println("Order date: " + order.getOrderDate());
                for(OrderItem orderItem : order.getOrderItems()){
                    System.out.println(orderItem.getMenuItem().getNameEn() + " : " + orderItem.getMenuItem().getPrice());
                }
                System.out.println();
            }
        }
    }

    public void showOrdersByDateRange() {
        System.out.println("Enter start date (YYYY-MM-DD):");
        String startDateInput = scanner.nextLine();
        Date startDate = Date.valueOf(startDateInput);

        System.out.println("Enter end date (YYYY-MM-DD):");
        String endDateInput = scanner.nextLine();
        Date endDate = Date.valueOf(endDateInput);

        List<Order> orders = orderDao.findAll();
        System.out.println("Orders between " + startDate + " and " + endDate + ":");

        for (Order order : orders) {
            Date orderDate = Date.valueOf(order.getOrderDate().toLocalDateTime().toLocalDate());
            if (!orderDate.before(startDate) && !orderDate.after(endDate)) {
                System.out.println("Id: " + order.getId());
                System.out.println("Customer: " + order.getCustomerFirstName() + " " + order.getCustomerLastName());
                System.out.println("Total amount: " + order.getTotalAmount());
                System.out.println("Order date: " + order.getOrderDate());
                for (OrderItem orderItem : order.getOrderItems()) {
                    System.out.println(orderItem.getMenuItem().getNameEn() + " : " + orderItem.getMenuItem().getPrice());
                }
                System.out.println();
            }
        }
    }

    public void countDessertOrdersOnDate() {
        System.out.println("Enter date (YYYY-MM-DD):");
        String dateInput = scanner.nextLine();
        Date date = Date.valueOf(dateInput);
        List<Order> orders = orderDao.findAll();
        int dessertOrderCount = 0;

        for (Order order : orders) {
            Date orderDate = Date.valueOf(order.getOrderDate().toLocalDateTime().toLocalDate());

            if (orderDate.equals(date)) {
                for (OrderItem orderItem : order.getOrderItems()) {
                    if ("Dessert".equalsIgnoreCase(orderItem.getMenuItem().getItemType().getName())) {
                        dessertOrderCount++;
                        break;
                    }
                }
            }
        }

        System.out.println("Total dessert orders on " + date + ": " + dessertOrderCount);
    }

    public void countDrinkOrdersOnDate() {
        System.out.println("Enter date (YYYY-MM-DD):");
        String dateInput = scanner.nextLine();
        Date date = Date.valueOf(dateInput);
        List<Order> orders = orderDao.findAll();
        int drinkOrderCount = 0;

        for (Order order : orders) {
            Date orderDate = Date.valueOf(order.getOrderDate().toLocalDateTime().toLocalDate());

            if (orderDate.equals(date)) {
                for (OrderItem orderItem : order.getOrderItems()) {
                    if ("Drink".equalsIgnoreCase(orderItem.getMenuItem().getItemType().getName())) {
                        drinkOrderCount++;
                        break;
                    }
                }
            }
        }

        System.out.println("Total drink orders on " + date + ": " + drinkOrderCount);
    }

    public void showCustomersWhoOrderedDrinksToday() {
        java.time.LocalDate today = java.time.LocalDate.now();
        Date sqlToday = Date.valueOf(today);

        List<Order> orders = orderDao.findAll();

        List<Customer> customers = customerDao.findAll();

        List<Staff> baristas = staffDao.findAll().stream()
                .filter(s -> s.getPosition().getName().equalsIgnoreCase("Barista"))
                .toList();

        System.out.println("Customers who ordered drinks today:");

        for (Order order : orders) {
            if (Date.valueOf(order.getOrderDate().toLocalDateTime().toLocalDate()).equals(sqlToday)) {
                if (order.getOrderItems() != null && order.getOrderItems().stream().anyMatch(orderItem ->
                        orderItem.getMenuItem() != null && orderItem.getMenuItem().getItemType().getName().equalsIgnoreCase("Drink"))) {

                    Customer customer = customers.stream()
                            .filter(c -> c.getLastName().equals(order.getCustomerLastName()))
                            .findFirst().orElse(null);

                    Staff barista = baristas.isEmpty() ? null : baristas.get(new Random().nextInt(baristas.size()));

                    if (customer != null && barista != null) {
                        System.out.printf("Customer: %s %s, Barista: %s %s, Order Total: %.2f\n",
                                customer.getFirstName(),
                                customer.getLastName(),
                                barista.getFirstName(),
                                barista.getLastName(),
                                order.getTotalAmount());
                    }
                }
            }
        }
    }

    public void showAverageOrderAmountForDate() {
        System.out.println("Enter date (YYYY-MM-DD):");
        String dateInput = scanner.nextLine();
        Date date = Date.valueOf(dateInput);

        List<Order> orders = orderDao.findAll();

        double totalAmount = 0;
        int orderCount = 0;

        for (Order order : orders) {
            if (Date.valueOf(order.getOrderDate().toLocalDateTime().toLocalDate()).equals(date)) {
                totalAmount += order.getTotalAmount();
                orderCount++;
            }
        }

        double averageAmount = orderCount > 0 ? totalAmount / orderCount : 0;

        if (orderCount > 0) {
            System.out.printf("Average order amount for date %s: %.2f\n", date, averageAmount);
        } else {
            System.out.printf("No orders found for date %s.\n", date);
        }
    }


    public void showMaxOrderAmountForDate() {
        System.out.println("Enter date (YYYY-MM-DD):");
        String dateInput = scanner.nextLine();
        Date date = Date.valueOf(dateInput);

        List<Order> orders = orderDao.findAll();

        double maxAmount = 0;
        boolean orderFound = false;

        for (Order order : orders) {
            if (Date.valueOf(order.getOrderDate().toLocalDateTime().toLocalDate()).equals(date)) {
                orderFound = true;
                if (order.getTotalAmount() > maxAmount) {
                    maxAmount = order.getTotalAmount();
                }
            }
        }

        if (orderFound) {
            System.out.printf("Maximum order amount for date %s: %.2f\n", date, maxAmount);
        } else {
            System.out.printf("No orders found for date %s.\n", date);
        }
    }

    public void showCustomerWithMaxOrderForDate() {
        System.out.println("Enter date (YYYY-MM-DD):");
        String dateInput = scanner.nextLine();
        Date date = Date.valueOf(dateInput);

        List<Order> orders = orderDao.findAll();

        double maxAmount = 0;
        Customer maxCustomer = null;
        boolean orderFound = false;

        for (Order order : orders) {
            if (Date.valueOf(order.getOrderDate().toLocalDateTime().toLocalDate()).equals(date)) {
                orderFound = true;
                if (order.getTotalAmount() > maxAmount) {
                    maxAmount = order.getTotalAmount();
                    maxCustomer = customerDao.findAll().stream()
                            .filter(c -> c.getLastName().equals(order.getCustomerLastName()))
                            .findFirst().orElse(null);
                }
            }
        }

        if (orderFound && maxCustomer != null) {
            System.out.printf("Customer with maximum order amount for date %s: %s %s, Amount: %.2f\n",
                    date, maxCustomer.getFirstName(), maxCustomer.getLastName(), maxAmount);
        } else {
            System.out.printf("No orders found for date %s.\n", date);
        }
    }

    public void showBaristaSchedule() {
        System.out.print("Enter the barista's name: ");
        String baristaName = scanner.nextLine();

        Date[] weekRange = getCurrentWeekRange();

        List<StaffSchedule> schedules = staffScheduleDao.findAll();

        List<StaffSchedule> filteredSchedules = schedules.stream()
                .filter(schedule -> (schedule.getStaff().getFirstName() + " " + schedule.getStaff().getLastName())
                        .equalsIgnoreCase(baristaName) &&
                        !schedule.getWorkDay().before(weekRange[0]) &&
                        !schedule.getWorkDay().after(weekRange[1]))
                .toList();

        if (filteredSchedules.isEmpty()) {
            System.out.println("No schedule found for barista: " + baristaName);
        } else {
            System.out.println("Schedule for barista " + baristaName + " (from " + weekRange[0] + " to " + weekRange[1] + "):");
            for (StaffSchedule schedule : filteredSchedules) {
                System.out.println("Date: " + schedule.getWorkDay() +
                        ", Start: " + schedule.getStartTime() + ", End: " + schedule.getEndTime());
            }
        }
    }

    public void showAllBaristasSchedule() {
        Date[] weekRange = getCurrentWeekRange();
        Date startOfWeek = weekRange[0];
        Date endOfWeek = weekRange[1];

        List<StaffSchedule> schedules = staffScheduleDao.findAll();

        List<StaffSchedule> filteredSchedules = schedules.stream()
                .filter(schedule -> !schedule.getWorkDay().before(startOfWeek) && !schedule.getWorkDay().after(endOfWeek))
                .toList();

        if (filteredSchedules.isEmpty()) {
            System.out.println("No barista schedules found for the week from: " + startOfWeek + " to " + endOfWeek);
        } else {
            System.out.println("Schedule for all baristas (from " + startOfWeek + " to " + endOfWeek + "):");
            for (StaffSchedule schedule : filteredSchedules) {
                if (schedule.getStaff().getPosition().getName().equalsIgnoreCase("Barista")) {
                    System.out.println("Barista: " + schedule.getStaff().getFirstName() + " " + schedule.getStaff().getLastName() +
                            ", Date: " + schedule.getWorkDay() +
                            ", Start: " + schedule.getStartTime() + ", End: " + schedule.getEndTime());
                }
            }
        }
    }

    public void showAllStaffSchedule() {
        Date[] weekRange = getCurrentWeekRange();
        Date startOfWeek = weekRange[0];
        Date endOfWeek = weekRange[1];

        List<StaffSchedule> schedules = staffScheduleDao.findAll();

        List<StaffSchedule> filteredSchedules = schedules.stream()
                .filter(schedule -> !schedule.getWorkDay().before(startOfWeek) && !schedule.getWorkDay().after(endOfWeek))
                .toList();

        if (filteredSchedules.isEmpty()) {
            System.out.println("No staff schedules found for the week from: " + startOfWeek + " to " + endOfWeek);
        } else {
            System.out.println("Schedule for all staff (from " + startOfWeek + " to " + endOfWeek + "):");
            for (StaffSchedule schedule : filteredSchedules) {
                System.out.println("Staff: " + schedule.getStaff().getFirstName() + " " + schedule.getStaff().getLastName() +
                        ", Position: " + schedule.getStaff().getPosition().getName() +
                        ", Date: " + schedule.getWorkDay() +
                        ", Start: " + schedule.getStartTime() + ", End: " + schedule.getEndTime());
            }
        }
    }

    public Date[] getCurrentWeekRange() {
        java.time.LocalDate today = java.time.LocalDate.now();

        java.time.LocalDate monday = today.with(java.time.DayOfWeek.MONDAY);

        java.time.LocalDate sunday = today.with(java.time.DayOfWeek.SUNDAY);

        return new Date[] { Date.valueOf(monday), Date.valueOf(sunday) };
    }
}
