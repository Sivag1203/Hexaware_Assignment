package org.example.service;

import org.example.entity.*;
import org.example.exception.*;

import java.util.*;

public class BankServiceProviderImpl extends CustomerServiceProviderImpl implements IBankServiceProvider {

    private String branchName = "Hexaware Bank";
    private String branchAddress = "Chennai";

    /* ---------- PART 1: Using List<Account> ---------- */
    private List<Account> accountList = new ArrayList<>();

    /* ---------- PART 2: Using Set<Account> ---------- */
    // private Set<Account> accountList = new HashSet<>();

    /* ---------- PART 3: Using Map<Long, Account> ---------- */
    // private Map<Long, Account> accountList = new HashMap<>();

    @Override
    public long create_account(Customer customer, String accType, float balance) {
        Account newAccount = null;
        switch (accType.toLowerCase()) {
            case "savings":
                if (balance < 500) {
                    System.out.println("❌ Minimum balance for Savings Account is ₹500.");
                    return -1;
                }
                newAccount = new SavingsAccount(balance, customer, 5.0f);
                break;
            case "current":
                newAccount = new CurrentAccount(balance, customer, 10000.0f);
                break;
            case "zerobalance":
                newAccount = new ZeroBalanceAccount(customer);
                break;
            default:
                System.out.println("❌ Invalid account type.");
                return -1;
        }

        /* ---------- Add Account (Uncomment as per structure) ---------- */
        // Part 1: List
        accountList.add(newAccount);

        // Part 2: Set
        // accountList.add(newAccount); // duplicates avoided based on equals() and hashCode()

        // Part 3: Map
        // accountList.put(newAccount.getAccountNumber(), newAccount);

        return newAccount.getAccountNumber();
    }

    @Override
    public Account[] listAccounts() {
        /* ---------- Part 1: List ---------- */
        Account[] array = new Account[accountList.size()];
        accountList.toArray(array);

        /* ---------- Part 2: Set ---------- */
        // Account[] array = new Account[accountList.size()];
        // accountList.toArray(array);

        /* ---------- Part 3: Map ---------- */
        // Collection<Account> values = accountList.values();
        // Account[] array = new Account[values.size()];
        // values.toArray(array);

        // Optional: Sorting using Comparator by customer name
        Arrays.sort(array, Comparator.comparing(a -> a.getCustomer().getFirstName()));
        return array;
    }

    @Override
    public void calculateInterest() {
        Collection<Account> accounts;

        /* ---------- Part 1: List ---------- */
        accounts = accountList;

        /* ---------- Part 2: Set ---------- */
        // accounts = accountList;

        /* ---------- Part 3: Map ---------- */
        // accounts = accountList.values();

        for (Account acc : accounts) {
            float oldBalance = acc.getAccountBalance();
            acc.calculateInterest();
            float newBalance = acc.getAccountBalance();

            if (newBalance > oldBalance) {
                System.out.println("✅ Interest added to Account No: " + acc.getAccountNumber());
                System.out.printf("Old Balance: ₹%.2f, New Balance: ₹%.2f%n", oldBalance, newBalance);
            }
        }
        System.out.println("✅ Interest calculation completed for all applicable accounts.");
    }

    protected Account findAccount(long accNo) throws InvalidAccountException {
        /* ---------- Part 1 & 2 ---------- */
        for (Account acc : accountList) {
            if (acc.getAccountNumber() == accNo) return acc;
        }

        /* ---------- Part 3: Map ---------- */
        // if (accountList.containsKey(accNo)) return accountList.get(accNo);

        throw new InvalidAccountException("❌ Account number " + accNo + " not found.");
    }
}
