package org.example.entity;

public class ZeroBalanceAccount extends Account {
    public ZeroBalanceAccount(Customer customer) {
        super("ZeroBalance", 0, customer);
    }

    @Override
    public void calculateInterest() {}
}
