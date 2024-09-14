package org.example.enums;

public enum WeightUnit {
    MILLIGRAM(1.0),
    GRAM(1000.0),
    KILOGRAM(1000000.0),
    QUINTAL(100000000.0),
    TON(1000000000.0);

    private final double toMilligramFactor;

    WeightUnit(double toMilligramFactor) {
        this.toMilligramFactor = toMilligramFactor;
    }

    public double getToMilligramFactor() {
        return toMilligramFactor;
    }
}
