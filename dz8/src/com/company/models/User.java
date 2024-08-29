package com.company.models;

public class User {
    private String name;
    private int old;
    private String phone;
    public User(String name, int old, String phone) {
        this.name = name;
        this.old = old;
        this.phone = phone;
    }
    @Override
    public String toString() {
        return "Name: " + name + ", Old: " + old + ", Phone: " + phone;
    }
    @Override
    public int hashCode() {
        return name.hashCode() + old + phone.hashCode();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getOld() {
        return old;
    }
    public void setOld(int old) {
        this.old = old;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
