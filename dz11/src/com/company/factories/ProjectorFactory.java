package com.company.factories;

import com.company.models.Projector;
import com.company.util.RandomElements;

public class ProjectorFactory {
    public static final String[] names = {
            "Epson Home Cinema 2150",
            "BenQ HT2050A",
            "Optoma HD146X",
            "LG PF50KA",
            "ViewSonic PX701HD",
            "Sony VPL-VW295ES",
            "XGIMI Horizon Pro",
            "Anker Nebula Capsule",
            "AAXA P300 Pico",
            "JVC DLA-NX5"
    };

    public static final int[] yearOfProduction = {
            2018, 2019, 2020, 2021, 2022, 2023, 2024
    };

    public static final double[] prices = {
            699.99, 799.99, 549.99, 649.99, 899.99,
            4999.99, 1699.99, 299.99, 399.99, 5999.99
    };

    public static final String[] producers = {
            "Epson", "BenQ", "Optoma", "LG", "ViewSonic",
            "Sony", "XGIMI", "Anker", "AAXA", "JVC"
    };

    public static Projector getProjector(){
        String name = RandomElements.getRandomElement(names);
        int year = RandomElements.getRandomElement(yearOfProduction);
        double price = RandomElements.getRandomElement(prices);
        String producer = RandomElements.getRandomElement(producers);
        return new Projector(name, year, price, producer);
    }
}
