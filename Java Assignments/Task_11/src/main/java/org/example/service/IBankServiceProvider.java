package org.example.service;

import org.example.entity.Customer;
import org.example.entity.Account;

public interface IBankServiceProvider {
    long create_account(Customer customer, String accType, float balance);
    Account[] listAccounts();
    void calculateInterest();
}
