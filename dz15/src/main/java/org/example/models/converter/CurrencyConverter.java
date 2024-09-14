package org.example.models.converter;

import org.example.enums.CurrencyCourse;

public class CurrencyConverter {
    public double convert(CurrencyCourse fromCurrency, CurrencyCourse toCurrency, double amount) {
        double amountInDollars = amount / fromCurrency.getRate();
        return amountInDollars * toCurrency.getRate();
    }
}
