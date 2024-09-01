package com.company.models;

import com.company.enums.DepartmentType;
import com.company.enums.ShopType;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AbstractHouse{
    private ShopType shopType;
    private int numberOfDepartments;
    private List<DepartmentType> departments;
    public Shop() {
        super();
        departments = new ArrayList<>();
    }
    public Shop(ShopType shopType, List<DepartmentType> departments) {
        super();
        this.shopType = shopType;
        this.numberOfDepartments = shopType.getNumberOfDepartments();
        this.departments = departments;
    }
    public Shop(String address, ShopType shopType, List<DepartmentType> departments) {
        super(address);
        this.shopType = shopType;
        this.numberOfDepartments = shopType.getNumberOfDepartments();
        this.departments = departments;
    }
    @Override
    public void printInfo() {
        System.out.println("Shop: ");
        super.printInfo();
        System.out.println("\tShop Type: " + shopType.toString().toLowerCase());
        System.out.println("\tNumber of departments: " + numberOfDepartments);
        for (DepartmentType departmentType : departments) {
            System.out.println("\tDepartment: " + departmentType.toString().toLowerCase());
        }
    }

    @Override
    public void updateFieldsFromString(String data) {
        String[] parts = data.split(",");
        for (String part : parts) {
            String[] keyValue = part.split(":");
            switch (keyValue[0].toLowerCase()) {
                case "shoptype":
                    this.shopType = ShopType.valueOf(keyValue[1].toUpperCase());
                    this.numberOfDepartments = shopType.getNumberOfDepartments();
                    break;
                case "departments":
                    String[] departmentNames = keyValue[1].split(",");
                    List<DepartmentType> updatedDepartments = new ArrayList<>();
                    for (String name : departmentNames) {
                        updatedDepartments.add(DepartmentType.valueOf(name.toUpperCase()));
                    }
                    this.departments = updatedDepartments;
                    break;
            }
        }
    }

    public ShopType getShopType() {
        return shopType;
    }
    public void setShopType(ShopType shopType) {
        this.shopType = shopType;
    }
    public int getNumberOfDepartments() {
        return numberOfDepartments;
    }
    public void setNumberOfDepartments(int numberOfDepartments) {
        this.numberOfDepartments = numberOfDepartments;
    }
    public List<DepartmentType> getDepartments() {
        return departments;
    }
    public void setDepartments(List<DepartmentType> departments) {
        this.departments = departments;
    }
}
