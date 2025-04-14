package org.example;

public class CurrentAccount extends Account {
    private static final double OVERDRAFT_LIMIT = 5000.0;

    public CurrentAccount(String accountNumber, String accountType, double accountBalance) {
        super(accountNumber, accountType, accountBalance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (getAccountBalance() + OVERDRAFT_LIMIT) >= amount) {
            setAccountBalance(getAccountBalance() - amount);
            System.out.println("Withdrew: " + amount);
        } else if (amount > (getAccountBalance() + OVERDRAFT_LIMIT)) {
            System.out.println("Insufficient funds and overdraft limit exceeded.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    public void printAccountInfo() {
        super.printAccountInfo();
        System.out.println("Overdraft Limit: " + OVERDRAFT_LIMIT);
    }
}
