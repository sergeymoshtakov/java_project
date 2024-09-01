package com.company.models;

import com.company.interfaces.IHouse;
import com.company.interfaces.IPrintable;

public abstract class AbstractHouse implements IHouse{
    private String address;
    public AbstractHouse() {}
    public AbstractHouse(String address) {
        this.address = address;
    }
    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public void printInfo() {
        System.out.println("\tAddress: " + this.address);
    }
    public abstract void updateFieldsFromString(String data);
}
