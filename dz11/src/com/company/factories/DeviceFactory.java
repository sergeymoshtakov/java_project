package com.company.factories;

import com.company.enums.Color;
import com.company.enums.DeviceType;
import com.company.models.Device;
import com.company.util.RandomElements;

public class DeviceFactory {
    public static final String[] names = {
            "Smartphone", "Laptop", "Refrigerator", "Washing Machine", "Microwave",
            "Television", "Smartwatch", "Vacuum Cleaner", "Air Conditioner", "Tablet"
    };

    public static final int[] yearsOfProduction = {
            2020, 2021, 2019, 2018, 2022, 2021, 2022, 2020, 2019, 2021
    };

    public static final double[] prices = {
            699.99, 1299.99, 899.99, 499.99, 199.99,
            599.99, 249.99, 149.99, 999.99, 499.99
    };

    public static Device getDevice(){
        String name = RandomElements.getRandomElement(names);
        int yearOfProduction = RandomElements.getRandomElement(yearsOfProduction);
        double price = RandomElements.getRandomElement(prices);
        Color color = RandomElements.getRandomElement(Color.values());
        DeviceType deviceType = RandomElements.getRandomElement(DeviceType.values());
        return new Device(name, yearOfProduction, price, color, deviceType);
    }
}
