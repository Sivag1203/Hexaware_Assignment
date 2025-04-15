package org.example.service;

import org.example.bean.Account;
import org.example.bean.Customer;

import java.util.List;

public interface IBankServiceProvider {
    boolean createAccount(Customer customer, long accNo, String accType, float balance);
    List<Account> listAccounts();
    Account getAccountDetails(long accountNumber);
    void calculateInterest();
}
