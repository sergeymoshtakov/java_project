package com.company.models;

import com.company.exceptions.ExceedsMaximumBanknotesException;
import com.company.exceptions.NotWalidNominalException;

public class Bank {
    private int quantity; // количество банкоматов
    private ATM[] atms;
    public Bank(int quantity, ATM[] atms) {
        this.quantity = quantity;
        this.atms = atms;
    }
    public Bank(int quantity, int totalSum) {
        this.quantity = quantity;
        initializeATMs(quantity, totalSum);
    }

    public void initializeATMs(int quantity, int totalSum) {
        this.atms = new ATM[quantity];
        for (int i = 0; i < quantity; i++) {
            this.atms[i] = new ATM();
            try {
                this.atms[i].foolWithMoney(totalSum / quantity);
            } catch (NotWalidNominalException e) {
                System.out.println("Invalid nominal during ATM initialization at index " + i + ": " + e.getMessage());
                e.printStackTrace();
            } catch (ExceedsMaximumBanknotesException e) {
                System.out.println("Too many banknotes during ATM initialization at index " + i + ": " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Unexpected error during ATM initialization at index " + i + ": " + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public int getTotalMoneyInATMs() {
        int total = 0;
        for (int i = 0; i < quantity; i++) {
            total += atms[i].getTotalAmount();
        }
        return total;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public ATM[] getAtms() {
        return atms;
    }
    public void setAtms(ATM[] atms) {
        this.atms = atms;
    }
}
