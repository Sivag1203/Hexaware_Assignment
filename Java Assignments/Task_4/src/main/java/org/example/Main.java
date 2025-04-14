package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] accountNumbers = {1001, 1002, 1003, 1004, 1005};
        double[] balances = {5000.0, 15000.0, 8000.5, 12000.75, 2000.0};

        boolean valid = false;

        while (!valid) {
            System.out.print("Enter your account number: ");
            int enteredAccNo = scanner.nextInt();

            boolean found = false;

            for (int i = 0; i < accountNumbers.length; i++) {
                if (accountNumbers[i] == enteredAccNo) {
                    System.out.println("Your balance is: $" + balances[i]);
                    found = true;
                    valid = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Invalid account number. Please try again.");
            }
        }

        scanner.close();
    }
}