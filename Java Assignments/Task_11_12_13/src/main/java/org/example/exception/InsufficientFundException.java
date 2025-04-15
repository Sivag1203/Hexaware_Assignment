package org.example.exception;

public class InsufficientFundException extends Exception {
    private float accountBalance;
    private float withdrawalAmount;

    // Constructor to take account balance and withdrawal amount
    public InsufficientFundException(float accountBalance, float withdrawalAmount) {
        super("❌ Insufficient funds! Attempted withdrawal: ₹" + withdrawalAmount + " Current balance: ₹" + accountBalance);
        this.accountBalance = accountBalance;
        this.withdrawalAmount = withdrawalAmount;
    }

    public float getAccountBalance() {
        return accountBalance;
    }

    public float getWithdrawalAmount() {
        return withdrawalAmount;
    }
}
