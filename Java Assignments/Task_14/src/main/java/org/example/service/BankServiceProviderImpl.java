package org.example.service;

import org.example.bean.Account;
import org.example.bean.Customer;
import org.example.dao.BankRepositoryImpl;
import org.example.dao.IBankRepository;

import java.util.List;

public class BankServiceProviderImpl extends CustomerServiceProviderImpl implements IBankServiceProvider {
    private final String branchName = "Hexaware City Branch";
    private final String branchAddress = "Hexaware Building, Hexa City";

    public BankServiceProviderImpl() {
        super();
    }

    @Override
    public boolean createAccount(Customer customer, long accNo, String accType, float balance) {
        return bankRepo.createAccount(customer, accNo, accType, balance);
    }

    @Override
    public List<Account> listAccounts() {
        return bankRepo.listAccounts();
    }

    @Override
    public void calculateInterest() {
        bankRepo.calculateInterest();
    }

    @Override
    public Account getAccountDetails(long accountNumber) {
        return bankRepo.getAccountDetails(accountNumber);
    }

    public String getBranchName() {
        return branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }
}
