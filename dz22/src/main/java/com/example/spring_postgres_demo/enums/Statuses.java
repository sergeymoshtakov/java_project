package com.example.spring_postgres_demo.enums;

public enum Statuses {
    FREE(1L, "Free"),
    SOLD(2L, "Sold");

    private long id;
    private String name;

    Statuses(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public long getId() {
        return id;
    }
}
