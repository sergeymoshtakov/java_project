package com.company.models;

public abstract class Publication implements IPublication, IPrintable{
    private String name;

    public Publication(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void printInfo() {
        System.out.println("Name: " + getName());
    }
}
