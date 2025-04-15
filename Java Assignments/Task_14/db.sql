CREATE DATABASE HM_Bank;
USE HM_Bank;

CREATE TABLE Customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    DOB DATE NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(15) NOT NULL,
    address VARCHAR(255) NOT NULL
);
CREATE TABLE Accounts (
    account_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    account_type ENUM('Savings', 'Current', 'ZeroBalance') NOT NULL,
    balance DECIMAL(15,2) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);
CREATE TABLE Transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    account_id INT NOT NULL,
    transaction_type ENUM('Deposit', 'Withdraw', 'Transfer') NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    transaction_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    description VARCHAR(255),
    FOREIGN KEY (account_id) REFERENCES Accounts(account_id)
);
INSERT INTO Customers (first_name, last_name, DOB, email, phone_number, address) VALUES
('John', 'Doe', '1990-05-15', 'john.doe@example.com', '9876543210', 'New York, NY'),
('Jane', 'Smith', '1985-03-22', 'jane.smith@example.com', '9123456780', 'Los Angeles, CA'),
('Alice', 'Johnson', '1979-07-09', 'alice.j@example.com', '9988776655', 'Houston, TX'),
('Bob', 'Brown', '1992-11-30', 'bob.brown@example.com', '9765432109', 'Chicago, IL'),
('Carol', 'White', '1988-01-25', 'carol.white@example.com', '9654321098', 'Phoenix, AZ');
INSERT INTO Accounts (customer_id, account_type, balance) VALUES
(1, 'Savings', 2500.00),
(2, 'Current', 15000.00),
(3, 'ZeroBalance', 0.00),
(4, 'Savings', 5000.00),
(5, 'Current', 20000.00);
INSERT INTO Transactions (account_id, transaction_type, amount, description) VALUES
(1, 'Deposit', 1000.00, 'Initial deposit'),
(1, 'Withdraw', 500.00, 'ATM withdrawal'),
(2, 'Deposit', 15000.00, 'Salary'),
(4, 'Deposit', 5000.00, 'Cash deposit'),
(5, 'Transfer', 2000.00, 'Transfer to account 1');


select * from customers;
select * from accounts;
select * from transactions;