package org.example.dao;

import org.example.bean.Account;
import org.example.bean.Customer;
import org.example.bean.Transaction;
import org.example.util.DBConnUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankRepositoryImpl implements IBankRepository {

    private final String DB_PROPS = "db.properties";

    @Override
    public boolean createAccount(Customer customer, long accNo, String accType, float balance) {
        String insertCustomer = "INSERT INTO Customers (first_name, last_name, DOB, email, phone_number, address) VALUES (?, ?, ?, ?, ?, ?)";
        String insertAccount = "INSERT INTO Accounts (customer_id, account_type, balance) VALUES (?, ?, ?)";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS)) {
            conn.setAutoCommit(false);

            // Insert Customer
            PreparedStatement pst1 = conn.prepareStatement(insertCustomer, Statement.RETURN_GENERATED_KEYS);
            pst1.setString(1, customer.getFirstName());
            pst1.setString(2, customer.getLastName());
            String dob = customer.getDob();  // Assuming dob is a String in "yyyy-MM-dd" format
            java.sql.Date sqlDate = java.sql.Date.valueOf(dob);  // Convert String to java.sql.Date
            pst1.setDate(3, sqlDate);
//            pst1.setDate(3, Date.valueOf(customer.getDob()));
            pst1.setString(4, customer.getEmail());
            pst1.setString(5, customer.getPhoneNumber());
            pst1.setString(6, customer.getAddress());

            int rows = pst1.executeUpdate();
            if (rows == 0) throw new SQLException("Failed to insert customer");

            ResultSet generatedKeys = pst1.getGeneratedKeys();
            if (generatedKeys.next()) {
                int customerId = generatedKeys.getInt(1);

                // Insert Account
                PreparedStatement pst2 = conn.prepareStatement(insertAccount);
                pst2.setInt(1, customerId);
                pst2.setString(2, accType);
                pst2.setFloat(3, balance);

                pst2.executeUpdate();
                conn.commit();
                return true;
            }

            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Account> listAccounts() {
        List<Account> list = new ArrayList<>();
        String query = "SELECT * FROM Accounts";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Account acc = new Account() {
                    @Override
                    public void deposit(double amount) {}
                    @Override
                    public void withdraw(double amount) {}
                    @Override
                    public double calculateInterest() { return 0; }
                };
                list.add(acc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public double getAccountBalance(long accountNumber) {
        String query = "SELECT balance FROM Accounts WHERE account_id = ?";
        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setLong(1, accountNumber);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public double deposit(long accountNumber, float amount) {
        String update = "UPDATE Accounts SET balance = balance + ? WHERE account_id = ?";
        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement pst = conn.prepareStatement(update)) {
            pst.setFloat(1, amount);
            pst.setLong(2, accountNumber);
            int rows = pst.executeUpdate();
            if (rows > 0) {
                return getAccountBalance(accountNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public double withdraw(long accountNumber, float amount) {
        double currentBalance = getAccountBalance(accountNumber);
        if (currentBalance >= amount) {
            String update = "UPDATE Accounts SET balance = balance - ? WHERE account_id = ?";
            try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
                 PreparedStatement pst = conn.prepareStatement(update)) {
                pst.setFloat(1, amount);
                pst.setLong(2, accountNumber);
                int rows = pst.executeUpdate();
                if (rows > 0) {
                    return getAccountBalance(accountNumber);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return currentBalance;
    }

    @Override
    public boolean transfer(long fromAccNo, long toAccNo, float amount) {
        try (Connection conn = DBConnUtil.getConnection(DB_PROPS)) {
            conn.setAutoCommit(false);

            if (withdraw(fromAccNo, amount) >= amount) {
                deposit(toAccNo, amount);
                conn.commit();
                return true;
            }

            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Account getAccountDetails(long accountNumber) {
        String query = "SELECT a.*, c.* FROM Accounts a JOIN Customers c ON a.customer_id = c.customer_id WHERE a.account_id = ?";
        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setLong(1, accountNumber);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Customer c = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("DOB"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("address")
                );
                String type = rs.getString("account_type");
                double bal = rs.getDouble("balance");

                return switch (type) {
                    case "Savings" -> new org.example.bean.SavingsAccount(bal, c);
                    case "Current" -> new org.example.bean.CurrentAccount(bal, c);
                    case "ZeroBalance" -> new org.example.bean.ZeroBalanceAccount(c);
                    default -> null;
                };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Transaction> getTransactions(long accountNumber, Date fromDate, Date toDate) {
        List<Transaction> list = new ArrayList<>();
        String query = "SELECT * FROM Transactions WHERE account_id = ? AND transaction_date BETWEEN ? AND ?";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setLong(1, accountNumber);
            pst.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(fromDate));
            pst.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(toDate));

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Transaction txn = new Transaction(
                        rs.getInt("transaction_id"),
                        rs.getLong("account_id"),
                        rs.getString("transaction_type"),
                        rs.getDouble("amount"),
                        rs.getTimestamp("transaction_date").toLocalDateTime(),
                        rs.getString("description")
                );
                list.add(txn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void calculateInterest() {
        // Optional: implement logic to update balances based on account type & interest
        System.out.println("Interest calculation logic to be implemented if needed.");
    }
}
