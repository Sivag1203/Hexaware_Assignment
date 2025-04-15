package org.example.service;

import org.example.bean.Account;
import org.example.bean.Transaction;

import java.util.Date;
import java.util.List;

public interface ICustomerServiceProvider {
    double getAccountBalance(long accountNumber);
    double deposit(long accountNumber, float amount);
    double withdraw(long accountNumber, float amount);
    boolean transfer(long fromAccount, long toAccount, float amount);
    Account getAccountDetails(long accountNumber);
    List<Transaction> getTransactions(long accountNumber, Date fromDate, Date toDate);
}
