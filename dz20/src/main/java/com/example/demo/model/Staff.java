package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Staff {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Position position;
}