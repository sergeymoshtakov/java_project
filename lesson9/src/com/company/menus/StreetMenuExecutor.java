package com.company.menus;

import com.company.enums.AccreditationLevel;
import com.company.enums.DepartmentType;
import com.company.enums.ShopType;
import com.company.exceptions.AddressAlreadyExistsException;
import com.company.exceptions.NoSuchHouseException;
import com.company.interfaces.IHouse;
import com.company.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StreetMenuExecutor {
    private Street street;

    public void initializeStreet(Scanner scanner) {
        System.out.print("Enter street name: ");
        scanner.nextLine();
        String streetName = scanner.nextLine();
        street = new Street(streetName);
    }

    public void findShopDepartment(Scanner scanner) {
        int departmentNumber;
        do {
            for(DepartmentType departmentType : DepartmentType.values()) {
                System.out.print(departmentType.name() + " - " + departmentType.ordinal() + 1);
            }
            System.out.print("Enter department number: ");
            departmentNumber = scanner.nextInt();

        } while (departmentNumber < 1 || departmentNumber > DepartmentType.values().length);
        DepartmentType departmentType = DepartmentType.values()[departmentNumber - 1];
        int distance;
        do {
            System.out.print("Enter distance: ");
            distance = scanner.nextInt();
        } while (distance < 0);
        int numberOfHouse;
        do {
            System.out.print("Enter number of house: ");
            numberOfHouse = scanner.nextInt();
        } while (numberOfHouse < 0);
        try {
            Shop shop = street.findShopNearHouse(numberOfHouse, distance, departmentType);
            shop.printInfo();
        } catch (NoSuchHouseException e) {
            System.out.println("There is no such address. " + e.getMessage());
        }
    }

    public void printInfo(){
        street.printInfo();
    }

    public void addHouse(Scanner scanner, int houseType) {
        int houseNumber;
        do {
            System.out.print("Enter house number: ");
            houseNumber = scanner.nextInt();
            if(Street.addresses.contains(street.getName() + " " + houseNumber)) {
                System.out.println("Street already exists. Please choose another one.");
                houseNumber = -1;
            }
        } while(houseNumber < 0);
        IHouse house;
        switch(houseType) {
            case 1:
                house = getHospital(scanner, houseNumber);
                break;
            case 2:
                house = getLivingHouse(scanner, houseNumber);
                break;
            case 3:
                house = getSchool(scanner, houseNumber);
                break;
            default:
                house = getShop(scanner, houseNumber);
                break;
        }
        try {
            street.addHouse(house);
        } catch(AddressAlreadyExistsException e) {
            System.out.println("This address already exists. Please choose another one.\n" + e.getMessage());
        }
    }

    public void removeHouse(Scanner scanner) {
        int houseNumber;
        do {
            System.out.print("Enter house number: ");
            houseNumber = scanner.nextInt();
        } while(houseNumber < 0);
        try {
            street.removeHouse(street.getName() + " " + houseNumber);
        } catch (NoSuchHouseException e) {
            System.out.println("This house does not exist. Please choose another one.\n" + e.getMessage());
        }

    }

    public Hospital getHospital(Scanner scanner, int houseNumber) {
        int hospitalPlaces;
        do {
            System.out.println("Enter number of hospital places: ");
            hospitalPlaces = scanner.nextInt();
        } while(hospitalPlaces < 0);
        return new Hospital(street.getName() + " " + houseNumber, hospitalPlaces);
    }

    public LivingHouse getLivingHouse(Scanner scanner, int houseNumber) {
        int livingHousePopulation;
        do {
            System.out.println("Enter population of living house: ");
            livingHousePopulation = scanner.nextInt();
        } while(livingHousePopulation < 0);
        return new LivingHouse(street.getName()  + " " + houseNumber, livingHousePopulation);
    }

    public School getSchool(Scanner scanner, int houseNumber) {
        int choice;
        do {
            for (AccreditationLevel al : AccreditationLevel.values()) {
                System.out.println(al.name() + " " + al.ordinal() + 1);
            }
            System.out.println("Enter accreditation level: ");
            choice = scanner.nextInt();
        } while (choice < 1 || choice > AccreditationLevel.values().length);
        AccreditationLevel accreditationLevel = AccreditationLevel.values()[choice - 1];
        return new School(street.getName()  + " " + houseNumber, accreditationLevel);
    }

    public Shop getShop(Scanner scanner, int houseNumber) {
        int choice;
        do {
            for (ShopType type : ShopType.values()) {
                System.out.println(type.name() + " " + type.ordinal() + 1);
            }
            System.out.println("Enter shop type: ");
            choice = scanner.nextInt();
        } while(choice < 1 || choice > ShopType.values().length);
        ShopType shopType = ShopType.values()[choice - 1];
        List<DepartmentType> departments = new ArrayList<>();
        for (int i = 0; i < shopType.getNumberOfDepartments(); i++){
            choice = -1;
            do {
                for (DepartmentType dt : DepartmentType.values()) {
                    System.out.println(dt.name() + " " + dt.ordinal() + 1);
                }
                System.out.println("Enter department type: ");
                choice = scanner.nextInt();
            } while (choice < 1 || choice > DepartmentType.values().length);
            departments.add(DepartmentType.values()[choice - 1]);
        }
        return new Shop(street.getName()  + " " + houseNumber, shopType, departments);
    }
}
