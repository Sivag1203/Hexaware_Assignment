package org.example.service;

import org.example.bean.Account;
import org.example.bean.Transaction;
import org.example.dao.BankRepositoryImpl;
import org.example.dao.IBankRepository;

import java.util.Date;
import java.util.List;

public class CustomerServiceProviderImpl implements ICustomerServiceProvider {
    protected IBankRepository bankRepo;

    public CustomerServiceProviderImpl() {
        bankRepo = new BankRepositoryImpl();
    }

    @Override
    public double getAccountBalance(long accountNumber) {
        return bankRepo.getAccountBalance(accountNumber);
    }

    @Override
    public double deposit(long accountNumber, float amount) {
        return bankRepo.deposit(accountNumber, amount);
    }

    @Override
    public double withdraw(long accountNumber, float amount) {
        return bankRepo.withdraw(accountNumber, amount);
    }

    @Override
    public boolean transfer(long fromAccount, long toAccount, float amount) {
        return bankRepo.transfer(fromAccount, toAccount, amount);
    }

    @Override
    public Account getAccountDetails(long accountNumber) {
        return bankRepo.getAccountDetails(accountNumber);
    }

    @Override
    public List<Transaction> getTransactions(long accountNumber, Date fromDate, Date toDate) {
        return bankRepo.getTransactions(accountNumber, fromDate, toDate);
    }
}
