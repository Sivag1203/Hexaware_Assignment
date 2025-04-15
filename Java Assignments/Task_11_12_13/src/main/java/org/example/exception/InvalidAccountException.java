package org.example.exception;

public class InvalidAccountException extends Exception {
    public InvalidAccountException(String message) {
        super(message);
    }

    public InvalidAccountException(long accNo) {
        super("❌ Account number " + accNo + " is invalid.");
    }
}
