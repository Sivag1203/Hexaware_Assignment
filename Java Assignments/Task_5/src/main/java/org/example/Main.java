package org.example;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Create your password: ");
        String password = scanner.nextLine();

        boolean hasUpper = false;
        boolean hasDigit = false;

        if (password.length() >= 8) {
            for (int i = 0; i < password.length(); i++) {
                char ch = password.charAt(i);
                if (Character.isUpperCase(ch)) {
                    hasUpper = true;
                }
                if (Character.isDigit(ch)) {
                    hasDigit = true;
                }
            }

            if (hasUpper && hasDigit) {
                System.out.println("Password is valid.");
            } else {
                System.out.println("Password must contain at least one uppercase letter and one digit.");
            }
        } else {
            System.out.println("Password must be at least 8 characters long.");
        }

        scanner.close();
    }
}