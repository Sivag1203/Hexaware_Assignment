package org.example.entity;

public class SavingsAccount extends Account {
    private float interestRate;

    public SavingsAccount(float balance, Customer customer, float interestRate) {
        super("Savings", balance >= 500 ? balance : 500, customer);
        this.interestRate = interestRate;
    }

    @Override
    public void calculateInterest() {
        float interestRate = 0.05f; // 5% interest
        float interest = getAccountBalance() * interestRate;
        setAccountBalance(getAccountBalance() + interest);
    }
}
