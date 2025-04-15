package org.example.bean;

public class ZeroBalanceAccount extends Account {

    public ZeroBalanceAccount(Customer customer) {
        super("ZeroBalance", 0.0, customer);
    }

    @Override
    public void deposit(double amount) {
        setBalance(getBalance() + amount);
    }

    @Override
    public void withdraw(double amount) throws Exception {
        if (getBalance() < amount) {
            throw new Exception("Insufficient balance.");
        }
        setBalance(getBalance() - amount);
    }

    @Override
    public double calculateInterest() {
        return 0.0; // No interest on zero balance account
    }
}
