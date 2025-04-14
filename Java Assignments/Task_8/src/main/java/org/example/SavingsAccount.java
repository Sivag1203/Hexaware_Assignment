package org.example;

public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountNumber, String accountType, double accountBalance, double interestRate) {
        super(accountNumber, accountType, accountBalance);
        this.interestRate = interestRate;
    }

    @Override
    public void calculateInterest() {
        double interest = getAccountBalance() * interestRate;
        double newBalance = getAccountBalance() + interest;
        setAccountBalance(newBalance);
        System.out.println("Interest added: " + interest);
        System.out.println("New balance after interest: " + newBalance);
    }

    public void printAccountInfo() {
        super.printAccountInfo();
        System.out.println("Interest Rate: " + interestRate);
    }
}
