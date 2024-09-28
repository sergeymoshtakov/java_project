package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MenuItem {
    private int id;
    private String nameEn;
    private String nameDe;
    private double price;
    private ItemType itemType;
}