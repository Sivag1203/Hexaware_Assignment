package org.example.bean;

import java.time.LocalDateTime;

public class Transaction {
    private int transactionId;
    private long accountId;
    private String transactionType;
    private double amount;
    private LocalDateTime transactionDate;
    private String description;

    public Transaction() {}

    public Transaction(int transactionId, long accountId, String transactionType, double amount, LocalDateTime transactionDate, String description) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.description = description;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public long getAccountId() {
        return accountId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void printTransactionDetails() {
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Account ID: " + accountId);
        System.out.println("Type: " + transactionType);
        System.out.println("Amount: " + amount);
        System.out.println("Date: " + transactionDate);
        System.out.println("Description: " + description);
    }
}
