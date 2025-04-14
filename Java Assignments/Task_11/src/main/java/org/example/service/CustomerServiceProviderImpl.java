package org.example.service;

import org.example.entity.*;

public class CustomerServiceProviderImpl implements ICustomerServiceProvider {
    protected Account[] accountList = new Account[100];
    protected int accountCount = 0;

    @Override
    public float getAccountBalance(long accNo) {
        Account acc = findAccount(accNo);
        return acc != null ? acc.getAccountBalance() : -1;
    }

    @Override
    public float deposit(long accNo, float amount) {
        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.setAccountBalance(acc.getAccountBalance() + amount);
            return acc.getAccountBalance();
        }
        return -1;
    }

    @Override
    public float withdraw(long accNo, float amount) {
        Account acc = findAccount(accNo);
        if (acc == null) return -1;

        if (acc instanceof CurrentAccount) {
            float newBalance = acc.getAccountBalance() - amount;
            if (newBalance >= -((CurrentAccount) acc).getOverdraftLimit()) {
                acc.setAccountBalance(newBalance);
                return newBalance;
            }
        } else if (acc instanceof SavingsAccount || acc instanceof ZeroBalanceAccount) {
            if (acc.getAccountBalance() - amount >= 500 || acc instanceof ZeroBalanceAccount) {
                acc.setAccountBalance(acc.getAccountBalance() - amount);
                return acc.getAccountBalance();
            }
        }

        return -1;
    }

    @Override
    public void transfer(long fromAcc, long toAcc, float amount) {
        float withdrawn = withdraw(fromAcc, amount);
        if (withdrawn != -1) {
            deposit(toAcc, amount);
        }
    }

    @Override
    public void getAccountDetails(long accNo) {
        Account acc = findAccount(accNo);
        if (acc != null) {
            System.out.println("Account No: " + acc.getAccountNumber());
            System.out.println("Type: " + acc.getAccountType());
            System.out.println("Balance: " + acc.getAccountBalance());
            System.out.println("Customer Info: " + acc.getCustomer());
        } else {
            System.out.println("Account not found.");
        }
    }

    protected Account findAccount(long accNo) {
        for (int i = 0; i < accountCount; i++) {
            if (accountList[i].getAccountNumber() == accNo) {
                return accountList[i];
            }
        }
        return null;
    }
}
