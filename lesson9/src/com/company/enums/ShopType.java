package com.company.enums;

public enum ShopType {
    PRIVATE_SHOP(1),
    SUPERMARKET(5),
    CITY_MALL(10);
    private final int numberOfDepartments;
    ShopType(int numberOfDepartments) {
        this.numberOfDepartments = numberOfDepartments;
    }
    public int getNumberOfDepartments() {
        return numberOfDepartments;
    }
}
