package org.example;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double balance;
        int option;

        System.out.print("Enter your current balance: ");
        balance = scanner.nextDouble();

        System.out.println("ATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.print("Choose an option (1-3): ");
        option = scanner.nextInt();

        if (option == 1) {
            System.out.println("Your balance is: " + balance +"₹");
        } else if (option == 2) {
            System.out.print("Enter amount to withdraw: ");
            double withdrawAmount = scanner.nextDouble();

            if (withdrawAmount <= balance) {
                if (withdrawAmount % 100 == 0 || withdrawAmount % 500 == 0) {
                    balance -= withdrawAmount;
                    System.out.println("Withdrawal successful. New balance: ₹" + balance);
                } else {
                    System.out.println("Amount must be in multiples of 100 or 500.");
                }
            } else {
                System.out.println("Insufficient balance.");
            }
        } else if (option == 3) {
            System.out.print("Enter amount to deposit: ");
            double depositAmount = scanner.nextDouble();
            balance += depositAmount;
            System.out.println("Deposit successful. New balance: ₹" + balance);
        } else {
            System.out.println("Invalid option.");
        }

        scanner.close();
    }
}
