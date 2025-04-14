package org.example;

import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the SG Bank!");
        System.out.println("Choose Account Type: ");
        System.out.println("1. SavingsAccount");
        System.out.println("2. CurrentAccount");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();

        Account account = null;

        switch (choice) {
            case 1:
                System.out.print("Enter Account Number: ");
                String savingsAccountNumber = scanner.next();
                System.out.print("Enter Account Balance: ");
                double savingsBalance = scanner.nextDouble();
                System.out.print("Enter Interest Rate: ");
                double interestRate = scanner.nextDouble();
                account = new SavingsAccount(savingsAccountNumber, "Savings", savingsBalance, interestRate);
                break;
            case 2:
                System.out.print("Enter Account Number: ");
                String currentAccountNumber = scanner.next();
                System.out.print("Enter Account Balance: ");
                double currentBalance = scanner.nextDouble();
                account = new CurrentAccount(currentAccountNumber, "Current", currentBalance);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("\nAccount Created Successfully!");
        account.printAccountInfo();

        System.out.println("\nDeposit and Withdraw Operations:");
        System.out.print("Enter amount to deposit: ");
        double depositAmount = scanner.nextDouble();
        account.deposit(depositAmount);

        System.out.print("Enter amount to withdraw: ");
        double withdrawAmount = scanner.nextDouble();
        account.withdraw(withdrawAmount);

        account.calculateInterest();
        account.printAccountInfo();
    }
}
