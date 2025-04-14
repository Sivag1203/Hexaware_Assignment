package org.example;

import java.util.*;

public class Bank {
    private Map<Long, Account> accounts = new HashMap<>();
    private static long nextAccountNumber = 1001;

    public long createAccount(Customer customer, String accType, float balance) {
        long accNo = nextAccountNumber++;
        Account account = new Account(accNo, accType, balance, customer);
        accounts.put(accNo, account);
        System.out.println("Account created successfully! Account Number: " + accNo);
        return accNo;
    }

    public float getAccountBalance(long accNo) {
        Account account = accounts.get(accNo);
        if (account != null) {
            return account.getBalance();
        }
        System.out.println("Account not found.");
        return -1;
    }

    public float deposit(long accNo, float amount) {
        Account account = accounts.get(accNo);
        if (account != null) {
            account.deposit(amount);
            return account.getBalance();
        }
        System.out.println("Account not found.");
        return -1;
    }

    public float withdraw(long accNo, float amount) {
        Account account = accounts.get(accNo);
        if (account != null) {
            account.withdraw(amount);
            return account.getBalance();
        }
        System.out.println("Account not found.");
        return -1;
    }

    public void transfer(long fromAccNo, long toAccNo, float amount) {
        Account fromAccount = accounts.get(fromAccNo);
        Account toAccount = accounts.get(toAccNo);
        if (fromAccount != null && toAccount != null) {
            if (fromAccount.getBalance() >= amount) {
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
                System.out.println("Transfer successful.");
            } else {
                System.out.println("Insufficient balance to transfer.");
            }
        } else {
            System.out.println("Invalid account number(s).");
        }
    }

    public void getAccountDetails(long accNo) {
        Account account = accounts.get(accNo);
        if (account != null) {
            account.printAccountInfo();
        } else {
            System.out.println("Account not found.");
        }
    }
}
