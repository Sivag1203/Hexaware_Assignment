package org.example.dao;

import org.example.bean.Account;
import org.example.bean.Customer;
import org.example.bean.Transaction;

import java.util.Date;
import java.util.List;

public interface IBankRepository {
    boolean createAccount(Customer customer, long accNo, String accType, float balance);
    List<Account> listAccounts();
    double getAccountBalance(long accountNumber);
    double deposit(long accountNumber, float amount);
    double withdraw(long accountNumber, float amount);
    boolean transfer(long fromAccNo, long toAccNo, float amount);
    Account getAccountDetails(long accountNumber);
    List<Transaction> getTransactions(long accountNumber, Date fromDate, Date toDate);
    void calculateInterest(); // optional placeholder; you can expand this later
}
