package org.example;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of customers: ");
        int customers = scanner.nextInt();

        for (int i = 1; i <= customers; i++) {
            System.out.println("Customer " + i + ":");

            System.out.print("Enter initial balance: ");
            double initialBalance = scanner.nextDouble();

            System.out.print("Enter annual interest rate (%): ");
            double interestRate = scanner.nextDouble();

            System.out.print("Enter number of years: ");
            int years = scanner.nextInt();

            double futureBalance = initialBalance * Math.pow(1 + (interestRate / 100), years);

            System.out.println("Future balance after " + years + " years: $" + futureBalance);
            System.out.println();
        }
        scanner.close();
    }
}