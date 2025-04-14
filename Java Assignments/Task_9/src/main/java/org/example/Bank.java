package org.example;

import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = null;

        System.out.println("Choose Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter Account Number: ");
                String saNumber = scanner.next();
                System.out.print("Enter Customer Name: ");
                scanner.nextLine(); // consume newline
                String saName = scanner.nextLine();
                System.out.print("Enter Balance: ");
                float saBalance = scanner.nextFloat();
                System.out.print("Enter Interest Rate: ");
                float rate = scanner.nextFloat();
                account = new SavingsAccount(saNumber, saName, saBalance, rate);
                break;

            case 2:
                System.out.print("Enter Account Number: ");
                String caNumber = scanner.next();
                System.out.print("Enter Customer Name: ");
                scanner.nextLine(); // consume newline
                String caName = scanner.nextLine();
                System.out.print("Enter Balance: ");
                float caBalance = scanner.nextFloat();
                account = new CurrentAccount(caNumber, caName, caBalance);
                break;

            default:
                System.out.println("Invalid option.");
                return;
        }

        System.out.println("\nAccount Created Successfully!");
        account.printAccountInfo();

        // Deposit
        System.out.print("\nEnter amount to deposit: ");
        float depositAmount = scanner.nextFloat();
        account.deposit(depositAmount);

        // Withdraw
        System.out.print("Enter amount to withdraw: ");
        float withdrawAmount = scanner.nextFloat();
        account.withdraw(withdrawAmount);

        // Calculate interest
        account.calculateInterest();

        // Final Info
        System.out.println("\nFinal Account Info:");
        account.printAccountInfo();
    }
}
