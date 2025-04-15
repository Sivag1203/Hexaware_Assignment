package org.example.app;

import org.example.bean.*;
import org.example.service.BankServiceProviderImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BankApp {

    private static final Scanner sc = new Scanner(System.in);
    private static final BankServiceProviderImpl bank = new BankServiceProviderImpl();

    public static void main(String[] args) {
        System.out.println("Welcome to " + bank.getBranchName());
        System.out.println(bank.getBranchAddress());

        while (true) {
            System.out.println("\n==== HM Bank Menu ====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Get Account Balance");
            System.out.println("6. Get Account Details");
            System.out.println("7. List All Accounts");
            System.out.println("8. Get Transactions");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            try {
                switch (choice) {
                    case 1 -> createAccount();
                    case 2 -> deposit();
                    case 3 -> withdraw();
                    case 4 -> transfer();
                    case 5 -> getBalance();
                    case 6 -> getAccountDetails();
                    case 7 -> listAccounts();
                    case 8 -> getTransactions();
                    case 9 -> {
                        System.out.println("Thank you for banking with us.");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid choice! Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void createAccount() {
        sc.nextLine(); // flush
        System.out.println("Enter customer details:");
        System.out.print("First Name: ");
        String fName = sc.nextLine();
        System.out.print("Last Name: ");
        String lName = sc.nextLine();
        System.out.print("DOB (yyyy-MM-dd): ");
        String dob = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Address: ");
        String address = sc.nextLine();

        Customer customer = new Customer(0, fName, lName, dob, email, phone, address);

        System.out.println("Choose Account Type:");
        System.out.println("1. SavingsAccount");
        System.out.println("2. CurrentAccount");
        System.out.println("3. ZeroBalanceAccount");
        int typeChoice = sc.nextInt();

        float initialBalance = 0;
        if (typeChoice == 1 || typeChoice == 2) {
            System.out.print("Enter initial deposit: ");
            initialBalance = sc.nextFloat();
        }

        String accType = switch (typeChoice) {
            case 1 -> "Savings";
            case 2 -> "Current";
            case 3 -> "ZeroBalance";
            default -> throw new IllegalArgumentException("Invalid account type");
        };

        long generatedAccNo = System.currentTimeMillis(); // temporary unique accNo
        boolean created = bank.createAccount(customer, generatedAccNo, accType, initialBalance);
        if (created) {
            System.out.println(accType + " account created successfully!");
        } else {
            System.out.println("Failed to create account.");
        }
    }

    private static void deposit() {
        System.out.print("Enter account number: ");
        long accNo = sc.nextLong();
        System.out.print("Enter amount to deposit: ");
        float amount = sc.nextFloat();
        double newBalance = bank.deposit(accNo, amount);
        System.out.println("Deposit successful. Updated balance: ₹" + newBalance);
    }

    private static void withdraw() {
        System.out.print("Enter account number: ");
        long accNo = sc.nextLong();
        System.out.print("Enter amount to withdraw: ");
        float amount = sc.nextFloat();
        double newBalance = bank.withdraw(accNo, amount);
        System.out.println("Withdrawal successful. Updated balance: ₹" + newBalance);
    }

    private static void transfer() {
        System.out.print("Enter sender account number: ");
        long from = sc.nextLong();
        System.out.print("Enter receiver account number: ");
        long to = sc.nextLong();
        System.out.print("Enter amount to transfer: ");
        float amount = sc.nextFloat();

        boolean success = bank.transfer(from, to, amount);
        if (success) {
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Transfer failed.");
        }
    }

    private static void getBalance() {
        System.out.print("Enter account number: ");
        long accNo = sc.nextLong();
        double balance = bank.getAccountBalance(accNo);
        System.out.println("Current Balance: ₹" + balance);
    }

    private static void getAccountDetails() {
        System.out.print("Enter account number: ");
        long accNo = sc.nextLong();
        Account acc = bank.getAccountDetails(accNo);
        if (acc != null) {
            acc.printAccountDetails();
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void listAccounts() {
        List<Account> accounts = bank.listAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (Account acc : accounts) {
                acc.printAccountDetails();
                System.out.println("-----");
            }
        }
    }

    private static void getTransactions() throws Exception {
        sc.nextLine(); // flush
        System.out.print("Enter account number: ");
        long accNo = sc.nextLong();
        sc.nextLine(); // flush

        System.out.print("Enter FROM date (yyyy-MM-dd): ");
        String from = sc.nextLine();
        System.out.print("Enter TO date (yyyy-MM-dd): ");
        String to = sc.nextLine();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = sdf.parse(from);
        Date toDate = sdf.parse(to);

        List<Transaction> txns = bank.getTransactions(accNo, fromDate, toDate);
        if (txns.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction t : txns) {
                t.printTransactionDetails();
                System.out.println("----");
            }
        }
    }
}
