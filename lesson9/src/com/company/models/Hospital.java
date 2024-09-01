package com.company.models;

public class Hospital extends AbstractHouse{
    private int places;
    public Hospital(){
        super();
    }
    public Hospital(String name){
        super(name);
    }
    public Hospital(String name, int places){
        super(name);
        this.places = places;
    }
    @Override
    public void printInfo() {
        System.out.println("Hospital: ");
        super.printInfo();
        System.out.println("\tPlaces: " + places);
    }

    @Override
    public void updateFieldsFromString(String data) {
        String[] parts = data.split(",");
        for (String part : parts) {
            String[] keyValue = part.split(":");
            if (keyValue[0].toLowerCase().equals("places")) {
                this.places = Integer.parseInt(keyValue[1]);
            }
        }
    }
    public int getPlaces() {
        return places;
    }
    public void setPlaces(int places) {
        this.places = places;
    }
}
