package org.example.service;

import org.example.entity.*;

public class BankServiceProviderImpl extends CustomerServiceProviderImpl implements IBankServiceProvider {
    private String branchName = "Hexaware Bank";
    private String branchAddress = "Chennai";

    @Override
    public long create_account(Customer customer, String accType, float balance) {
        Account newAccount = null;
        switch (accType.toLowerCase()) {
            case "savings":
                if (balance < 500) {
                    System.out.println("❌ Minimum balance for Savings Account is ₹500.");
                    return -1;
                }
                newAccount = new SavingsAccount(balance, customer, 4.0f);
                break;
            case "current":
                newAccount = new CurrentAccount(balance, customer, 10000.0f);
                break;
            case "zerobalance":
                newAccount = new ZeroBalanceAccount(customer);
                break;
            default:
                System.out.println("❌ Invalid account type.");
                return -1;
        }

        accountList[accountCount++] = newAccount;
        return newAccount.getAccountNumber();  // Return the new account number
    }

    @Override
    public Account[] listAccounts() {
        Account[] list = new Account[accountCount];
        System.arraycopy(accountList, 0, list, 0, accountCount);
        return list;
    }

    @Override
    public void calculateInterest() {
        for (int i = 0; i < accountCount; i++) {
            Account acc = accountList[i];
            float oldBalance = acc.getAccountBalance();

            acc.calculateInterest(); // subclass handles logic

            float newBalance = acc.getAccountBalance();
            if (newBalance > oldBalance) {
                System.out.println("✅ Interest added to Account No: " + acc.getAccountNumber());
                System.out.printf("Old Balance: ₹%.2f, New Balance: ₹%.2f%n", oldBalance, newBalance);
            }
        }
        System.out.println("✅ Interest calculation completed for all applicable accounts.");
    }
}
