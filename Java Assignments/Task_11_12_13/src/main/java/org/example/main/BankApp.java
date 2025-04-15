package org.example.main;

import org.example.entity.Account;
import org.example.entity.Customer;
import org.example.service.BankServiceProviderImpl;
import org.example.exception.InvalidAccountException;
import org.example.exception.InsufficientFundException;
import org.example.exception.OverDraftLimitExceededException;

import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankServiceProviderImpl bank = new BankServiceProviderImpl();
        System.out.println("🏦 Welcome to Sg Bank System");

        while (true) {
            System.out.println("\n================= MENU =================");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Get Balance");
            System.out.println("5. Transfer");
            System.out.println("6. Get Account Details");
            System.out.println("7. List All Accounts");
            System.out.println("8. Calculate Interest");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number from 1 to 9.");
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.println("Enter Customer ID:");
                        long id = Long.parseLong(sc.nextLine());
                        System.out.println("Enter First Name:");
                        String fn = sc.nextLine();
                        System.out.println("Enter Last Name:");
                        String ln = sc.nextLine();
                        System.out.println("Enter Email:");
                        String email = sc.nextLine();
                        System.out.println("Enter Phone Number:");
                        String ph = sc.nextLine();
                        System.out.println("Enter Address:");
                        String addr = sc.nextLine();

                        Customer cust = new Customer(id, fn, ln, email, ph, addr);

                        System.out.println("Choose Account Type:\n1. Savings\n2. Current\n3. ZeroBalance");
                        int typeChoice = Integer.parseInt(sc.nextLine());
                        String type = switch (typeChoice) {
                            case 1 -> "Savings";
                            case 2 -> "Current";
                            case 3 -> "ZeroBalance";
                            default -> {
                                System.out.println("❌ Invalid account type. Account not created.");
                                yield null;
                            }
                        };
                        if (type == null) break;

                        System.out.println("Enter Initial Balance:");
                        float bal = Float.parseFloat(sc.nextLine());

                        long accNo = bank.create_account(cust, type, bal);
                        if (accNo != -1)
                            System.out.println("✅ Account created successfully! Your Account Number is: " + accNo);
                        else
                            System.out.println("❌ Account creation failed.");

                    } catch (NumberFormatException e) {
                        System.out.println("❌ Invalid input. Please enter valid numbers.");
                    } catch (Exception e) {
                        System.out.println("❌ Account creation failed. Please try again.");
                    }
                    break;

                case 2:
                    try {
                        System.out.println("Enter Account Number:");
                        long accNo = Long.parseLong(sc.nextLine());
                        System.out.println("Enter Amount to Deposit:");
                        float amount = Float.parseFloat(sc.nextLine());
                        float updatedBalance = bank.deposit(accNo, amount);
                        if (updatedBalance != -1)
                            System.out.println("✅ Deposit successful. Updated Balance: ₹" + updatedBalance);
                        else
                            System.out.println("❌ Account not found.");
                    } catch (Exception e) {
                        System.out.println("❌ Deposit failed. Please check the account number or amount.");
                    }
                    break;

                case 3:
                    try {
                        System.out.println("Enter Account Number:");
                        long accNo = Long.parseLong(sc.nextLine());
                        System.out.println("Enter Amount to Withdraw:");
                        float amount = Float.parseFloat(sc.nextLine());
                        float updatedBalance = bank.withdraw(accNo, amount);
                        if (updatedBalance != -1)
                            System.out.println("✅ Withdraw successful. Updated Balance: ₹" + updatedBalance);
                        else
                            System.out.println("❌ Withdraw failed. Check balance or account number.");
                    } catch (InsufficientFundException e) {
                        System.out.println("❌ Insufficient funds for withdrawal.");
                    } catch (OverDraftLimitExceededException e) {
                        System.out.println("❌ Overdraft limit exceeded.");
                    } catch (Exception e) {
                        System.out.println("❌ Withdraw failed. Please try again.");
                    }
                    break;

                case 4:
                    try {
                        System.out.println("Enter Account Number:");
                        long accNo = Long.parseLong(sc.nextLine());
                        float balance = bank.getAccountBalance(accNo);
                        if (balance != -1)
                            System.out.println("✅ Account Balance: ₹" + balance);
                        else
                            System.out.println("❌ Account not found.");
                    } catch (Exception e) {
                        System.out.println("❌ Invalid input. Please try again.");
                    }
                    break;

                case 5:
                    try {
                        System.out.println("Enter Sender Account Number:");
                        long from = Long.parseLong(sc.nextLine());
                        System.out.println("Enter Receiver Account Number:");
                        long to = Long.parseLong(sc.nextLine());
                        System.out.println("Enter Amount to Transfer:");
                        float amount = Float.parseFloat(sc.nextLine());
                        bank.transfer(from, to, amount);
                        System.out.println("✅ Transfer successful.");
                    } catch (InvalidAccountException e) {
                        System.out.println("❌ Invalid account details.");
                    } catch (Exception e) {
                        System.out.println("❌ Transfer failed. Please check the details.");
                    }
                    break;

                case 6:
                    try {
                        System.out.println("Enter Account Number:");
                        long accNo = Long.parseLong(sc.nextLine());
                        bank.getAccountDetails(accNo);
                    } catch (Exception e) {
                        System.out.println("❌ Account details could not be retrieved.");
                    }
                    break;

                case 7:
                    Account[] accounts = bank.listAccounts();
                    if (accounts.length == 0) {
                        System.out.println("⚠️ No accounts found.");
                    } else {
                        System.out.println("📋 List of Accounts:");
                        for (Account a : accounts) {
                            System.out.println("Account No: " + a.getAccountNumber() +
                                    ", Type: " + a.getAccountType() +
                                    ", Balance: ₹" + a.getAccountBalance() +
                                    ", Owner: " + a.getCustomer().getFirstName() + " " + a.getCustomer().getLastName());
                        }
                    }
                    break;

                case 8:
                    bank.calculateInterest();
                    break;

                case 9:
                    System.out.println("👋 Thank you for using Sg Bank System. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice. Please select between 1 and 9.");
            }
        }
    }
}
