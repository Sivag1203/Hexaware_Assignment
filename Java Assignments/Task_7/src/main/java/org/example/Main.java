
package org.example;
import org.example.*;

public class Main {
    public static void main(String[] args) {

        Customer customer = new Customer("C123", "John", "Doe", "john.doe@example.com", "1234567890", "123 Elm Street");
        customer.printCustomerInfo();


        Account account = new Account("A12345", "Savings", 1000.0);
        account.printAccountInfo();

        account.deposit(500.0);
        account.withdraw(200.0);
        account.calculateInterest();

        account.printAccountInfo();
    }
}