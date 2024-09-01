package com.company.exceptions;

public class StreetNotInitializedException extends StreetException {
    public StreetNotInitializedException() {
      super("Street is not initialized");
    }
}
