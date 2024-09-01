package com.company.exceptions;

public class AddressAlreadyExistsException extends StreetException {
    public AddressAlreadyExistsException(String address) {
        super("This address already exists: " + address);
    }
}
