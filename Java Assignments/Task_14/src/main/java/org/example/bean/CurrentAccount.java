package org.example.bean;

public class CurrentAccount extends Account {
    private static final double OVERDRAFT_LIMIT = 10000.0;

    public CurrentAccount(double balance, Customer customer) {
        super("Current", balance, customer);
    }

    @Override
    public void deposit(double amount) {
        setBalance(getBalance() + amount);
    }

    @Override
    public void withdraw(double amount) throws Exception {
        if (getBalance() - amount < -OVERDRAFT_LIMIT) {
            throw new Exception("Overdraft limit exceeded (₹10,000).");
        }
        setBalance(getBalance() - amount);
    }

    @Override
    public double calculateInterest() {
        return 0.0; // Current accounts don't earn interest
    }
}
