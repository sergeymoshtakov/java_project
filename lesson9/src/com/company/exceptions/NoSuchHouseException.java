package com.company.exceptions;

public class NoSuchHouseException extends StreetException {
    public NoSuchHouseException(String address) {
        super("House with this address " + address + " does not exist");
    }
}
