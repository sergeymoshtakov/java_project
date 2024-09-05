package com.company.models;


import com.company.enums.Color;
import com.company.enums.DeviceType;

public class Device {
    private String name;
    private int yearOfProduction;
    private double price;
    private Color color;
    private DeviceType type;

    public Device(String name, int yearOfProduction, double price, Color color, DeviceType type) {
        this.name = name;
        this.yearOfProduction = yearOfProduction;
        this.price = price;
        this.color = color;
        this.type = type;
    }

    @Override
    public String toString() {
        return name + ", " + yearOfProduction + ", " + price + ", " + color.toString() + ", " + type;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getYearOfProduction() {
        return yearOfProduction;
    }
    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public DeviceType getType() {
        return type;
    }
    public void setType(DeviceType type) {
        this.type = type;
    }
}
