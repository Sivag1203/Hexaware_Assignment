package org.example.service;

import org.example.entity.*;
import org.example.exception.InvalidAccountException;
import org.example.exception.InsufficientFundException;
import org.example.exception.OverDraftLimitExceededException;

public class CustomerServiceProviderImpl implements ICustomerServiceProvider {
    protected Account[] accountList = new Account[100];
    protected int accountCount = 0;

    @Override
    public float getAccountBalance(long accNo) throws InvalidAccountException {
        Account acc = findAccount(accNo);
        return acc.getAccountBalance();
    }

    @Override
    public float deposit(long accNo, float amount) throws InvalidAccountException {
        Account acc = findAccount(accNo);
        acc.setAccountBalance(acc.getAccountBalance() + amount);
        return acc.getAccountBalance();
    }

    @Override
    public float withdraw(long accNo, float amount) throws InvalidAccountException, InsufficientFundException, OverDraftLimitExceededException {
        Account acc = findAccount(accNo);

        if (acc == null) {
            throw new InvalidAccountException("Account with number " + accNo + " not found.");
        }

        if (acc instanceof CurrentAccount) {
            float newBalance = acc.getAccountBalance() - amount;
            if (newBalance >= -((CurrentAccount) acc).getOverdraftLimit()) {
                acc.setAccountBalance(newBalance);
                return newBalance;
            } else {
                // Overdraft limit exceeded
                throw new OverDraftLimitExceededException(((CurrentAccount) acc).getOverdraftLimit(), amount, acc.getAccountBalance());
            }
        } else if (acc instanceof SavingsAccount || acc instanceof ZeroBalanceAccount) {
            if (acc.getAccountBalance() - amount >= 500 || acc instanceof ZeroBalanceAccount) {
                acc.setAccountBalance(acc.getAccountBalance() - amount);
                return acc.getAccountBalance();
            } else {
                // Insufficient funds for withdrawal
                throw new InsufficientFundException(acc.getAccountBalance(), amount);
            }
        }

        return -1;
    }

    @Override
    public void transfer(long fromAcc, long toAcc, float amount)
            throws InvalidAccountException, InsufficientFundException, OverDraftLimitExceededException {
        Account fromAccount = findAccount(fromAcc);
        Account toAccount = findAccount(toAcc);

        // If both accounts exist, proceed with transaction
        if (fromAccount != null && toAccount != null) {
            float withdrawn = withdraw(fromAcc, amount);
            deposit(toAcc, amount);
        }
    }

    @Override
    public void getAccountDetails(long accNo) throws InvalidAccountException {
        Account acc = findAccount(accNo);
        System.out.println("Account No: " + acc.getAccountNumber());
        System.out.println("Type: " + acc.getAccountType());
        System.out.println("Balance: ₹" + acc.getAccountBalance());
        System.out.println("Customer Info: " + acc.getCustomer());
    }

    protected Account findAccount(long accNo) throws InvalidAccountException {
        for (int i = 0; i < accountCount; i++) {
            if (accountList[i].getAccountNumber() == accNo) {
                return accountList[i];
            }
        }
        throw new InvalidAccountException("❌ Invalid account number: " + accNo);
    }
}
