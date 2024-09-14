package org.example.enums;

public enum LengthUnit {
    MILLIMETER(1.0),
    CENTIMETER(10.0),
    DECIMETER(100.0),
    METER(1000.0),
    KILOMETER(1000000.0);

    private final double toMillimeterFactor;

    LengthUnit(double toMillimeterFactor) {
        this.toMillimeterFactor = toMillimeterFactor;
    }

    public double getToMillimeterFactor() {
        return toMillimeterFactor;
    }
}
