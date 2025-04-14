package org.example.service;

public interface ICustomerServiceProvider {
    float getAccountBalance(long accNo);
    float deposit(long accNo, float amount);
    float withdraw(long accNo, float amount);
    void transfer(long fromAcc, long toAcc, float amount);
    void getAccountDetails(long accNo);
}
