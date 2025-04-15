package org.example.bean;

public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 4.5;
    private static final double MIN_BALANCE = 500;

    public SavingsAccount(double balance, Customer customer) {
        super("Savings", Math.max(balance, MIN_BALANCE), customer);
    }

    @Override
    public void deposit(double amount) {
        setBalance(getBalance() + amount);
    }

    @Override
    public void withdraw(double amount) throws Exception {
        if (getBalance() - amount < MIN_BALANCE) {
            throw new Exception("Insufficient balance after maintaining minimum balance of ₹500.");
        }
        setBalance(getBalance() - amount);
    }

    @Override
    public double calculateInterest() {
        return getBalance() * INTEREST_RATE / 100;
    }
}
