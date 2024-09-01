package com.company.models;

import com.company.enums.AccreditationLevel;

import java.util.Random;

public class School extends AbstractHouse{
    private int numberOfStudents;
    private AccreditationLevel accreditationLevel;
    public School(){
        super();
    }
    public School(AccreditationLevel accreditationLevel) {
        super();
        this.accreditationLevel = accreditationLevel;
        Random random = new Random();
        this.numberOfStudents = random.nextInt(accreditationLevel.getHigherQuantity() - accreditationLevel.getLowerQuantity()) + accreditationLevel.getLowerQuantity();

    }
    public School(String address, AccreditationLevel accreditationLevel) {
        super(address);
        this.accreditationLevel = accreditationLevel;
        Random random = new Random();
        this.numberOfStudents = random.nextInt(accreditationLevel.getHigherQuantity() - accreditationLevel.getLowerQuantity()) + accreditationLevel.getLowerQuantity();

    }
    @Override
    public void printInfo() {
        System.out.println("School: ");
        super.printInfo();
        System.out.println("\tNumber of students: " + numberOfStudents);
        System.out.println("\tAccreditation level: " + accreditationLevel.toString().toLowerCase());
    }

    @Override
    public void updateFieldsFromString(String data) {
        String[] parts = data.split(",");
        for (String part : parts) {
            String[] keyValue = part.split(":");
            switch (keyValue[0].toLowerCase()) {
                case "accreditationlevel":
                    this.accreditationLevel = AccreditationLevel.valueOf(keyValue[1].toUpperCase());
                    Random random = new Random();
                    this.numberOfStudents = random.nextInt(accreditationLevel.getHigherQuantity() - accreditationLevel.getLowerQuantity()) + accreditationLevel.getLowerQuantity();
                    break;
                case "numberofstudents":
                    this.numberOfStudents = Integer.parseInt(keyValue[1]);
                    break;
            }
        }
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }
    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }
    public AccreditationLevel getAccreditationLevel() {
        return accreditationLevel;
    }
    public void setAccreditationLevel(AccreditationLevel accreditationLevel) {
        this.accreditationLevel = accreditationLevel;
    }
}
