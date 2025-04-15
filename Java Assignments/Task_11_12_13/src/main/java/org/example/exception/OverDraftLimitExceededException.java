package org.example.exception;

public class OverDraftLimitExceededException extends Exception {
    private float overdraftLimit;
    private float withdrawalAmount;
    private float accountBalance;

    // Constructor to take overdraft limit, withdrawal amount, and account balance
    public OverDraftLimitExceededException(float overdraftLimit, float withdrawalAmount, float accountBalance) {
        super("❌ Overdraft limit exceeded! Attempted withdrawal: ₹" + withdrawalAmount + " Current balance: ₹" + accountBalance + " Overdraft limit: ₹" + overdraftLimit);
        this.overdraftLimit = overdraftLimit;
        this.withdrawalAmount = withdrawalAmount;
        this.accountBalance = accountBalance;
    }

    public float getOverdraftLimit() {
        return overdraftLimit;
    }

    public float getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public float getAccountBalance() {
        return accountBalance;
    }
}
