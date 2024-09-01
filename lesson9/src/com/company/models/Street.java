package com.company.models;

import com.company.enums.DepartmentType;
import com.company.exceptions.AddressAlreadyExistsException;
import com.company.exceptions.NoSuchHouseException;
import com.company.interfaces.IHouse;
import com.company.interfaces.IPrintable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Street implements IPrintable {
    public static Set<String> addresses = new TreeSet<>();
    private List<IHouse> houses;
    private String name;
    public Street() {
        houses = new ArrayList<>();
    }
    public Street(String name) {
        this.name = name;
        houses = new ArrayList<>();
    }
    public Street(String name, List<IHouse> houses) {
        this.name = name;
        this.houses = houses;
    }
    public void addHouse(IHouse house) throws AddressAlreadyExistsException {
        if (!addresses.contains(house.getAddress())) {
            houses.add(house);
            addresses.add(house.getAddress());
        } else {
            throw new AddressAlreadyExistsException(house.getAddress());
        }
    }
    public void removeHouse(String address) throws NoSuchHouseException {
        if (addresses.contains(address)) {
            IHouse searchedHouse = null;
            for (IHouse house : houses) {
                if (house.getAddress().equals(address)) {
                    searchedHouse = house;
                }
            }
            houses.remove(searchedHouse);
            if (searchedHouse != null) {
                addresses.remove(searchedHouse.getAddress());
            }
        } else {
            throw new NoSuchHouseException(address);
        }
    }
    public Shop findShopNearHouse(int number, int distance, DepartmentType departmentType) throws NoSuchHouseException{
        if (addresses.contains(this.getName() + " " + number)) {
            for(IHouse h : houses) {
                int numberOfHouse = Integer.parseInt(h.getAddress().replaceAll("[^0-9]", ""));
                if (numberOfHouse >= number - distance && numberOfHouse <= number + distance) {
                    if(h instanceof Shop shop){
                        if(shop.getDepartments().contains(departmentType)) {
                            return shop;
                        }
                    }
                }
            }
            return null;
        } else {
            throw new NoSuchHouseException(this.getName() + " " + number);
        }
    }
    @Override
    public void printInfo() {
        System.out.println("Name: " + name);
        for (IHouse house : houses) {
            System.out.print("\t");
            house.printInfo();
        }
    }
    public void updateHouse(String address, String data) {
        for (IHouse house : houses) {
            if (house.getAddress().equals(address)) {
                if (house instanceof AbstractHouse) {
                    ((AbstractHouse) house).updateFieldsFromString(data);
                }
                return;
            }
        }
        throw new NoSuchHouseException(address);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<IHouse> getHouses() {
        return houses;
    }
    public void setHouses(List<IHouse> houses) {
        this.houses = houses;
    }
}
