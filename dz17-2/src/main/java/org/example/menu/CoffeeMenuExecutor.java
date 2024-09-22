package org.example.menu;

import org.example.dao.*;
import org.example.models.*;
import org.example.util.RandomElements;

import java.sql.Date;
import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

public class CoffeeMenuExecutor {
    private Scanner scanner;

    public CoffeeMenuExecutor(Scanner scanner) {
        this.scanner = scanner;
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
        MenuItemDao.addNewMenuItem(newItem);
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
        StaffDao.addNewStaff(newStaff);
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
        CustomerDao.addNewCustomer(newCustomer);
    }

    public void updateMenuItemPrice() {
        System.out.println("Enter coffee name:");
        String coffeeName = scanner.nextLine();
        System.out.println("Enter new price:");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();
        MenuItemDao.updateMenuItemPrice(coffeeName, newPrice);
    }

    public void updateConfectionerAddress() {
        System.out.println("Enter confectioner last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter new email:");
        String newEmail = scanner.nextLine();
        StaffDao.updateConfectionerAddress(lastName, newEmail);
    }

    public void updateBaristaPhone() {
        System.out.println("Enter barista last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter new phone:");
        String newPhone = scanner.nextLine();
        StaffDao.updateBaristaPhone(lastName, newPhone);
    }

    public void updateCustomerDiscount() {
        System.out.println("Enter customer last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter new discount:");
        double newDiscount = scanner.nextDouble();
        scanner.nextLine();
        CustomerDao.updateCustomerDiscount(lastName, newDiscount);
    }

    public void deleteDessert() {
        System.out.println("Enter dessert name:");
        String dessertName = scanner.nextLine();
        MenuItem dessert = new MenuItem();
        dessert.setNameEn(dessertName);
        MenuItemDao.deleteMenuItem(dessert);
    }

    public void deleteWaiter() {
        deleteStaff("Waiter");
    }

    public void deleteBarista() {
        deleteStaff("Barista");
    }

    public void deleteCustomer() {
        System.out.println("Enter customer first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter customer last name:");
        String lastName = scanner.nextLine();
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        CustomerDao.deleteCustomer(customer);
    }

    public void showAllDrinks() {
        List<MenuItem> drinks = MenuItemDao.getAllMenuItems();
        System.out.println("All drinks:");
        for (MenuItem drink : drinks) {
            if (drink.getItemType().getName().equalsIgnoreCase("Drink")) {
                System.out.println(drink.getNameEn() + " - " + drink.getPrice() + " USD");
            }
        }
    }

    public void showAllDesserts() {
        List<MenuItem> desserts = MenuItemDao.getAllMenuItems();
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

    public void deleteStaff(String role) {
        System.out.println("Enter " + role + " first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter " + role + " last name:");
        String lastName = scanner.nextLine();
        Staff staff = new Staff();
        staff.setFirstName(firstName);
        staff.setLastName(lastName);
        StaffDao.deleteStaff(staff);
    }

    public void showStaffByRole(String role) {
        List<Staff> staffList = StaffDao.getAllStaff();
        System.out.println("All " + role.toLowerCase() + "s:");
        for (Staff staff : staffList) {
            if (staff.getPosition().getName().equalsIgnoreCase(role)) {
                System.out.println(staff.getFirstName() + " " + staff.getLastName() + " - " + staff.getPhoneNumber());
            }
        }
    }

    public void showSmallestDiscount() {
        List<Customer> customers = CustomerDao.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            return;
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
        List<Customer> customers = CustomerDao.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            return;
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
        List<Customer> customers = CustomerDao.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            return;
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
        List<Customer> customers = CustomerDao.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            return;
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
        List<Customer> customers = CustomerDao.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            return;
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
        List<Customer> customers = CustomerDao.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            return;
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
        List<Customer> customers = CustomerDao.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            return;
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
        List<Customer> customers = CustomerDao.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            return;
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
        List<Order> orders = OrderDao.getOrdersByDate(date);
        System.out.println("Orders on " + date + ":");
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public void showOrdersByDateRange() {
        System.out.println("Enter start date (YYYY-MM-DD):");
        String startDateInput = scanner.nextLine();
        Date startDate = Date.valueOf(startDateInput);

        System.out.println("Enter end date (YYYY-MM-DD):");
        String endDateInput = scanner.nextLine();
        Date endDate = Date.valueOf(endDateInput);

        List<Order> orders = OrderDao.getOrdersByDateRange(startDate, endDate);
        System.out.println("Orders between " + startDate + " and " + endDate + ":");
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public void countDessertOrdersOnDate() {
        System.out.println("Enter date (YYYY-MM-DD):");
        String dateInput = scanner.nextLine();
        Date date = Date.valueOf(dateInput);
        int count = OrderDao.countDessertOrders(date);
        System.out.println("Number of dessert orders on " + date + ": " + count);
    }

    public void countDrinkOrdersOnDate() {
        System.out.println("Enter date (YYYY-MM-DD):");
        String dateInput = scanner.nextLine();
        Date date = Date.valueOf(dateInput);
        int count = OrderDao.countDrinkOrders(date);
        System.out.println("Number of drink orders on " + date + ": " + count);
    }

    public void showCustomersWhoOrderedDrinksToday() {
        java.time.LocalDate today = java.time.LocalDate.now();
        Date sqlToday = Date.valueOf(today);

        List<Order> orders = OrderDao.getOrdersByDate(sqlToday);
        List<Customer> customers = CustomerDao.getAllCustomers();
        List<Staff> baristas = StaffDao.getAllStaff().stream()
                .filter(s -> s.getPosition().getName().equalsIgnoreCase("Barista")) // Фильтруем только бариста
                .collect(Collectors.toList());

        System.out.println("Customers who ordered drinks today:");
        for (Order order : orders) {
            if (order.getOrderItems() != null && order.getOrderItems().stream().anyMatch(orderItem ->
                    orderItem.getMenuItem() != null && orderItem.getMenuItem().getItemType().getName().equalsIgnoreCase("Drink"))) {
                Customer customer = customers.stream()
                        .filter(c -> c.getLastName().equals(order.getCustomerLastName()))
                        .findFirst().orElse(null);

                Staff barista = baristas.isEmpty() ? null : baristas.get(new Random().nextInt(baristas.size()));

                if (customer != null && barista != null) {
                    System.out.printf("Customer: %s %s, Barista: %s %s, Order: %.2f\n",
                            customer.getFirstName(),
                            customer.getLastName(),
                            barista.getFirstName(),
                            barista.getLastName(),
                            order.getTotalAmount());
                }
            }
        }
    }

    public void showAverageOrderAmountForDate() {
        System.out.println("Enter date (YYYY-MM-DD):");
        String dateInput = scanner.nextLine();
        Date date = Date.valueOf(dateInput);

        List<Order> orders = OrderDao.getOrdersByDate(date);

        double averageAmount = orders.stream()
                .mapToDouble(Order::getTotalAmount)
                .average().orElse(0);

        System.out.printf("Average order amount on %s: %.2f\n", date, averageAmount);
    }

    public void showMaxOrderAmountForDate() {
        System.out.println("Enter date (YYYY-MM-DD):");
        String dateInput = scanner.nextLine();
        Date date = Date.valueOf(dateInput);

        List<Order> orders = OrderDao.getOrdersByDate(date);

        double maxAmount = orders.stream()
                .mapToDouble(Order::getTotalAmount)
                .max().orElse(0);

        System.out.printf("Maximum order amount on %s: %.2f\n", date, maxAmount);
    }

    public void showCustomerWithMaxOrderForDate() {
        System.out.println("Enter date (YYYY-MM-DD):");
        String dateInput = scanner.nextLine();
        Date date = Date.valueOf(dateInput);

        List<Order> orders = OrderDao.getOrdersByDate(date);

        Order maxOrder = orders.stream()
                .max(Comparator.comparingDouble(Order::getTotalAmount))
                .orElse(null);

        if (maxOrder != null) {
            String customerLastName = maxOrder.getCustomerLastName();

            Customer customer = CustomerDao.getCustomerByLastName(customerLastName);

            if (customer != null) {
                System.out.printf("Customer with maximum order on %s: %s %s, Amount: %.2f\n",
                        date, customer.getFirstName(), customer.getLastName(), maxOrder.getTotalAmount());
            } else {
                System.out.println("Customer not found for this order.");
            }
        } else {
            System.out.println("No orders found on this date.");
        }
    }

    public Date[] getCurrentWeekRange() {
        java.time.LocalDate today = java.time.LocalDate.now();

        java.time.LocalDate monday = today.with(java.time.DayOfWeek.MONDAY);

        java.time.LocalDate sunday = today.with(java.time.DayOfWeek.SUNDAY);

        return new Date[] { Date.valueOf(monday), Date.valueOf(sunday) };
    }


    public void showBaristaSchedule() {
        System.out.print("Enter the barista's name: ");
        String baristaName = scanner.nextLine();

        Date[] weekRange = getCurrentWeekRange();

        List<StaffSchedule> schedules = StaffScheduleDao.getBaristaScheduleForWeek(weekRange[0]);

        List<StaffSchedule> filteredSchedules = schedules.stream()
                .filter(schedule -> (schedule.getStaff().getFirstName() + " " + schedule.getStaff().getLastName())
                        .equalsIgnoreCase(baristaName))
                .collect(Collectors.toList());

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

        List<StaffSchedule> schedules = StaffScheduleDao.getBaristaScheduleForWeek(weekRange[0]);

        if (schedules.isEmpty()) {
            System.out.println("No barista schedules found for the week from: " + weekRange[0] + " to " + weekRange[1]);
        } else {
            System.out.println("Schedule for all baristas (from " + weekRange[0] + " to " + weekRange[1] + "):");
            for (StaffSchedule schedule : schedules) {
                System.out.println("Barista: " + schedule.getStaff().getFirstName() + " " + schedule.getStaff().getLastName() +
                        ", Date: " + schedule.getWorkDay() +
                        ", Start: " + schedule.getStartTime() + ", End: " + schedule.getEndTime());
            }
        }
    }

    public void showAllStaffSchedule() {
        Date[] weekRange = getCurrentWeekRange();

        List<StaffSchedule> schedules = StaffScheduleDao.getAllStaffScheduleForWeek(weekRange[0]);

        if (schedules.isEmpty()) {
            System.out.println("No staff schedules found for the week from: " + weekRange[0] + " to " + weekRange[1]);
        } else {
            System.out.println("Schedule for all staff (from " + weekRange[0] + " to " + weekRange[1] + "):");
            for (StaffSchedule schedule : schedules) {
                System.out.println("Staff: " + schedule.getStaff().getFirstName() + " " + schedule.getStaff().getLastName() +
                        ", Date: " + schedule.getWorkDay() +
                        ", Start: " + schedule.getStartTime() + ", End: " + schedule.getEndTime());
            }
        }
    }

    public Time getRandomTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, RandomElements.getRandomElement(new int[]{8, 9, 10, 11, 12, 13, 14, 15, 16})); // Work hours
        calendar.set(Calendar.MINUTE, RandomElements.getRandomElement(new int[]{0, 15, 30, 45})); // Minutes
        return new Time(calendar.getTimeInMillis());
    }

    public Time getEndTimeAfter(Time startTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.add(Calendar.HOUR, RandomElements.getRandomElement(new int[]{1, 2})); // Add 1 or 2 hours
        return new Time(calendar.getTimeInMillis());
    }
}
