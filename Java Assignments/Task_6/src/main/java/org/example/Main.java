package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] transactions = new String[100];
        int index = 0;
        double balance = 0;
        boolean running = true;

        while (running) {
            System.out.println("Choose transaction type:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdrawal");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter deposit amount: ");
                double amount = scanner.nextDouble();
                balance += amount;
                transactions[index] = "Deposited: $" + amount;
                index++;
            } else if (choice == 2) {
                System.out.print("Enter withdrawal amount: ");
                double amount = scanner.nextDouble();
                balance -= amount;
                transactions[index] = "Withdrawn: $" + amount;
                index++;
            } else if (choice == 3) {
                running = false;
            } else {
                System.out.println("Invalid option.");
            }
        }

        System.out.println("Transaction History:");
        for (int i = 0; i < index; i++) {
            System.out.println(transactions[i]);
        }

        System.out.println("Total Balance: $" + balance);

        scanner.close();
    }
}
