package com.company.exceptions;

public class ExceedsMaximumBanknotesException extends ATMException{
    public ExceedsMaximumBanknotesException() {
        super("Exceeds the maximum number of banknotes that can be dispensed.");
    }
}
