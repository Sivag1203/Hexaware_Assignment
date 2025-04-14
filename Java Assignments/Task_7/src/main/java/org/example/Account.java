package org.example;
class Account {
    private String accountNumber;
    private String accountType; // "Savings" or "Current"
    private double accountBalance;

    // Default constructor
    public Account() {
    }

    // Overloaded constructor with account attributes
    public Account(String accountNumber, String accountType, double accountBalance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
    }

    // Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    // Method to print all information
    public void printAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Type: " + accountType);
        System.out.println("Account Balance: " + accountBalance);
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            accountBalance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= accountBalance) {
            accountBalance -= amount;
            System.out.println("Withdrew: " + amount);
        } else if (amount > accountBalance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    // Method to calculate interest
    public void calculateInterest() {
        if (accountType.equals("Savings")) {
            double interest = accountBalance * 0.045; // 4.5% interest rate
            accountBalance += interest;
            System.out.println("Interest added: " + interest);
        } else {
            System.out.println("Interest not applicable for Current account.");
        }
    }
}
