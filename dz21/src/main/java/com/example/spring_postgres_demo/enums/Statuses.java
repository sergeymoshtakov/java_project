package com.example.spring_postgres_demo.enums;

public enum Statuses {

    ACTIVE(1, "Active"),
    PENDING(2, "Pending"),
    COMPLETED(3, "Completed"),
    AVAILABLE(4, "Available"),
    UNAVAILABLE(5, "Unavailable");

    int id;
    String name;
    Statuses(int i, String name) {
        this.id = i;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
