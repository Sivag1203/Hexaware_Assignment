package org.example;

public class CurrentAccount extends BankAccount {
    private static final float OVERDRAFT_LIMIT = 5000;

    public CurrentAccount(String accountNumber, String customerName, float balance) {
        super(accountNumber, customerName, balance);
    }

    @Override
    public void deposit(float amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    @Override
    public void withdraw(float amount) {
        if (amount > 0 && (balance + OVERDRAFT_LIMIT) >= amount) {
            balance -= amount;
            System.out.println("Withdrew: " + amount);
        } else {
            System.out.println("Overdraft limit exceeded.");
        }
    }

    @Override
    public void calculateInterest() {
        System.out.println("No interest for current account.");
    }
}
