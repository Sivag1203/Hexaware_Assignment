package org.example.entity;

public class CurrentAccount extends Account {
    private final float overdraftLimit;

    public CurrentAccount(float balance, Customer customer, float overdraftLimit) {
        super("Current", balance, customer);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void calculateInterest() {}

    public float getOverdraftLimit() {
        return overdraftLimit;
    }
}
