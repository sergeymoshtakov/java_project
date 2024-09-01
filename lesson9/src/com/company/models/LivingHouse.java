package com.company.models;

public class LivingHouse extends AbstractHouse {
    private int population;
    public LivingHouse(String address, int population) {
        super(address);
        this.population = population;
    }
    public LivingHouse(int population) {
        super();
        this.population = population;
    }
    @Override
    public void printInfo() {
        System.out.println("Living House: ");
        super.printInfo();
        System.out.println("\tPopulation: " + population);
    }

    @Override
    public void updateFieldsFromString(String data) {
        String[] parts = data.split(",");
        for (String part : parts) {
            String[] keyValue = part.split(":");
            if (keyValue[0].toLowerCase().equals("population")) {
                this.population = Integer.parseInt(keyValue[1]);
            }
        }
    }

    public int getPopulation() {
        return population;
    }
    public void setPopulation(int population) {
        this.population = population;
    }
}
