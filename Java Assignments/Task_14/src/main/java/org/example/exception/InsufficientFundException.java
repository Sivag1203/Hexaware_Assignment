package org.example.exception;

public class InsufficientFundException extends Exception {
    public InsufficientFundException() {
        super("Insufficient funds in the account.");
    }

    public InsufficientFundException(String message) {
        super(message);
    }
}
