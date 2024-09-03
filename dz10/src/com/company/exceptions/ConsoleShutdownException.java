package com.company.exceptions;

public class ConsoleShutdownException extends RuntimeException {
    public ConsoleShutdownException(String message) {
        super(message);
    }
}
