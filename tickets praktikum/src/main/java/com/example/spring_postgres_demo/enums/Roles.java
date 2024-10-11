package com.example.spring_postgres_demo.enums;

public enum Roles {
    ADMIN(1, "ADMIN"),
    USER(2, "USER");

    private long id;
    private String role;

    Roles(int i, String admin) {
        this.id = i;
        this.role = admin;
    }

    public long getId() {
        return id;
    }
    public String getRole() {
        return role;
    }
}
