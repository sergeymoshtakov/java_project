package com.company.exceptions;

public class InsufficientFundsException extends ATMException {
    public InsufficientFundsException() {
        super("Insufficient funds in the ATM.");
    }
    public InsufficientFundsException(String message) {
        super(message);
    }
}
