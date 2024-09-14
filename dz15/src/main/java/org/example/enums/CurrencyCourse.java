package org.example.enums;

public enum CurrencyCourse {
    USD(1.0),
    EUR(0.9),
    GBP(0.76),
    JPY(110.0);

    private final double rate;

    CurrencyCourse(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
