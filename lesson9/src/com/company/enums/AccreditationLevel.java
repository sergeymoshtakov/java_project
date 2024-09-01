package com.company.enums;

public enum AccreditationLevel {
    SCHOOL(50, 100),
    GYMNASIUM(100, 500),
    LYCEUM(500, 1000);
    public final int lowerQuantity;
    public final int higherQuantity;

    private AccreditationLevel(int lowerQuantity, int higherQuantity) {
        this.lowerQuantity = lowerQuantity;
        this.higherQuantity = higherQuantity;
    }

    public int getLowerQuantity() {
        return lowerQuantity;
    }
    public int getHigherQuantity() {
        return higherQuantity;
    }
}
