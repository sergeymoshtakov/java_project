package com.company;

import com.company.models.Bank;

public class Main {
    public static void main(String[] args) {
        int atmCount = 4;
        int totalSum = 10000;
        Bank bank = new Bank(atmCount, totalSum);
        ATMMenu atmMenu = new ATMMenu(bank);
        atmMenu.displayMenu();
    }
}