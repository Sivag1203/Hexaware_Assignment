package org.example;

public abstract class BankAccount {
    protected String accountNumber;
    protected String customerName;
    protected float balance;

    public BankAccount() {}

    public BankAccount(String accountNumber, String customerName, float balance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void printAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Balance: " + balance);
    }

    public abstract void deposit(float amount);
    public abstract void withdraw(float amount);
    public abstract void calculateInterest();
}
