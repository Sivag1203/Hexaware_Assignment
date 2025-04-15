package org.example.entity;

public abstract class Account {
    private static long lastAccNo = 1000;
    private long accountNumber;
    private String accountType;
    private float accountBalance;
    private Customer customer;

    public Account() {
        this.accountNumber = ++lastAccNo;
    }

    public Account(String accountType, float accountBalance, Customer customer) {
        this.accountNumber = ++lastAccNo;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
        this.customer = customer;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public float getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(float accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void calculateInterest() {
    }
}
