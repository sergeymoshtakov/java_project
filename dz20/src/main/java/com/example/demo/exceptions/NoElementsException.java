package com.example.demo.exceptions;

public class NoElementsException extends RuntimeException {
    public NoElementsException(String message) {
        super("There is no " + message);
    }
}
