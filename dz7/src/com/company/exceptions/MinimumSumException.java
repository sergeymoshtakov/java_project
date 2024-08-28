package com.company.exceptions;

public class MinimumSumException extends ATMException {
    public MinimumSumException() {
        super("Amount is less than the minimum allowed sum.");
    }
    public MinimumSumException(int sum) {
        super(sum + "is less than the minimum allowed sum.");
    }
}
