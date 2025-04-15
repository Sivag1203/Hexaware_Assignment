package org.example.exception;

public class OverDraftLimitExceededException extends Exception {
    public OverDraftLimitExceededException() {
        super("Overdraft limit exceeded.");
    }

    public OverDraftLimitExceededException(String message) {
        super(message);
    }
}
