package org.example.service;

import org.example.exception.InsufficientFundException;
import org.example.exception.InvalidAccountException;
import org.example.exception.OverDraftLimitExceededException;

public interface ICustomerServiceProvider {

    float getAccountBalance(long accNo) throws InvalidAccountException;

    float deposit(long accNo, float amount) throws InvalidAccountException;

    float withdraw(long accNo, float amount)
            throws InvalidAccountException, InsufficientFundException, OverDraftLimitExceededException;

    void transfer(long fromAcc, long toAcc, float amount)
            throws InvalidAccountException, InsufficientFundException, OverDraftLimitExceededException;

    void getAccountDetails(long accNo) throws InvalidAccountException;
}
