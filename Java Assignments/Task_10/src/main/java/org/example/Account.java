package org.example;
public class Account {
    private long accountNumber;
    private String accountType;
    private float balance;
    private Customer customer;

    public Account() {}

    public Account(long accountNumber, String accountType, float balance, Customer customer) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.customer = customer;
    }

    public long getAccountNumber() { return accountNumber; }
    public String getAccountType() { return accountType; }
    public float getBalance() { return balance; }
    public Customer getCustomer() { return customer; }

    public void setAccountNumber(long accountNumber) { this.accountNumber = accountNumber; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    public void setBalance(float balance) { this.balance = balance; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public void deposit(float amount) {
        balance += amount;
        System.out.println("Amount deposited successfully.");
    }

    public void withdraw(float amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Amount withdrawn successfully.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void printAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: " + balance);
        System.out.println("--- Customer Details ---");
        customer.printCustomerInfo();
    }
}
