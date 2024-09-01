package com.company.factories;

import com.company.enums.DepartmentType;
import com.company.enums.ShopType;
import com.company.interfaces.IHouseFactory;
import com.company.models.Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShopFactory implements IHouseFactory {
    public static Shop getHouse(String address) {
        Random random = new Random();
        int pick = random.nextInt(ShopType.values().length);
        ShopType shopType = ShopType.values()[pick];
        int numberOfDepartments = shopType.getNumberOfDepartments();
        List<DepartmentType> departments = new ArrayList<>();
        int counter = 0;
        while (counter < numberOfDepartments) {
            DepartmentType departmentType = DepartmentType.values()[random.nextInt(DepartmentType.values().length)];
            if (!departments.contains(departmentType)) {
                departments.add(departmentType);
                counter++;
            }
        }
        return new Shop(address, shopType, departments);
    }
}
