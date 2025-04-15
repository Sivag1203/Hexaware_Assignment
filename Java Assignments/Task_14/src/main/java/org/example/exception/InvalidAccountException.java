package org.example.exception;

public class InvalidAccountException extends Exception {
    public InvalidAccountException() {
        super("Invalid account number. Account not found.");
    }

    public InvalidAccountException(String message) {
        super(message);
    }
}
