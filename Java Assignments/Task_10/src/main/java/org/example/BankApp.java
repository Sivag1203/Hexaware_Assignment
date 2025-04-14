package org.example;
import java.util.*;

public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("\n--- BANK MENU ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Get Balance");
            System.out.println("5. Transfer");
            System.out.println("6. Get Account Details");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Customer ID: ");
                    int custId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter First Name: ");
                    String fName = sc.nextLine();
                    System.out.print("Enter Last Name: ");
                    String lName = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter Address: ");
                    String addr = sc.nextLine();

                    System.out.print("Enter Account Type (Savings/Current): ");
                    String accType = sc.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    float balance = sc.nextFloat();

                    try {
                        Customer cust = new Customer(custId, fName, lName, email, phone, addr);
                        bank.createAccount(cust, accType, balance);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    long depAcc = sc.nextLong();
                    System.out.print("Enter Deposit Amount: ");
                    float depAmt = sc.nextFloat();
                    System.out.println("Updated Balance: " + bank.deposit(depAcc, depAmt));
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    long witAcc = sc.nextLong();
                    System.out.print("Enter Withdrawal Amount: ");
                    float witAmt = sc.nextFloat();
                    System.out.println("Updated Balance: " + bank.withdraw(witAcc, witAmt));
                    break;

                case 4:
                    System.out.print("Enter Account Number: ");
                    long balAcc = sc.nextLong();
                    System.out.println("Current Balance: " + bank.getAccountBalance(balAcc));
                    break;

                case 5:
                    System.out.print("Enter Sender Account Number: ");
                    long fromAcc = sc.nextLong();
                    System.out.print("Enter Receiver Account Number: ");
                    long toAcc = sc.nextLong();
                    System.out.print("Enter Amount to Transfer: ");
                    float amt = sc.nextFloat();
                    bank.transfer(fromAcc, toAcc, amt);
                    break;

                case 6:
                    System.out.print("Enter Account Number: ");
                    long detailsAcc = sc.nextLong();
                    bank.getAccountDetails(detailsAcc);
                    break;

                case 7:
                    System.out.println("Exiting. Thank you!");
                    sc.close();
                    System.exit(0);
            }
        }
    }
}
