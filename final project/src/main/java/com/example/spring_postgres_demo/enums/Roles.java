package com.example.spring_postgres_demo.enums;

public enum Roles {
    ADMIN(1, "ADMIN"),
    USER(2, "USER");

    private int id;
    private String role;

    Roles(int i, String admin) {
        this.id = i;
        this.role = admin;
    }

    public int getId() {
        return id;
    }
    public String getRole() {
        return role;
    }
}
