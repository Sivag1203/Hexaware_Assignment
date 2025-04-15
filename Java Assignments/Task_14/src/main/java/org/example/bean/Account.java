package org.example.bean;

public abstract class Account {
    private static long lastAccNo = 1000;
    private long accountNumber;
    private String accountType;
    private double balance;
    private Customer customer;

    public Account() {
        this.accountNumber = ++lastAccNo;
    }

    public Account(String accountType, double balance, Customer customer) {
        this.accountNumber = ++lastAccNo;
        this.accountType = accountType;
        this.balance = balance;
        this.customer = customer;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount) throws Exception;

    public abstract double calculateInterest();

    public void printAccountDetails() {
        System.out.println("Account No: " + accountNumber);
        System.out.println("Type: " + accountType);
        System.out.println("Balance: " + balance);
        System.out.println("Owned by:");
        customer.printCustomerDetails();
    }
}
