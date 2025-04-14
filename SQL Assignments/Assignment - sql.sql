-- Tasks 1

create database HMBank;
use HMBank;

create table customers( 
	customer_id int primary key auto_increment, 
    first_name varchar(20), 
    last_name varchar(20), 
    DOB date, 
    email varchar(30) unique, 
    phone_number varchar(10),
    address varchar(20)
);
create table accounts( 
	account_id int primary key auto_increment, 
	customer_id int, 
	account_type varchar(20),  
	balance DECIMAL(10,2), 
	foreign key (customer_id) references customers(customer_id)
);
create table transaction (
	transaction_id int primary key auto_increment,
	account_id int, 
	transaction_type varchar(20), 
	amount decimal(10,2), 
	transaction_date date, 
	foreign key (account_id) references accounts(account_id)
);

-- Task 2 

-- 2.1 : Insert at least 10 sample records into each of the following tables
INSERT INTO customers 
(first_name, last_name, DOB, email, phone_number, address) 
VALUES
('Arun', 'Kumar', '1995-06-12', 'arun.kumar@example.com', '9876543210', 'Chennai'),
('Priya', 'Sundar', '1998-03-22', 'priya.sundar@example.com', '9898989898', 'Coimbatore'),
('Karthik', 'Mohan', '1992-07-15', 'karthik.mohan@example.com', '9789789789', 'Madurai'),
('Divya', 'Ravi', '2000-09-30', 'divya.ravi@example.com', '9654321098', 'Trichy'),
('Vikram', 'Suresh', '1994-01-19', 'vikram.suresh@example.com', '9561234789', 'Salem'),
('Meena', 'Rajesh', '1996-11-05', 'meena.rajesh@example.com', '9977553311', 'Tirunelveli'),
('Surya', 'Devan', '1999-05-18', 'surya.devan@example.com', '9887766554', 'Erode'),
('Anitha', 'Kishore', '1991-08-29', 'anitha.kishore@example.com', '9765432189', 'Thanjavur'),
('Ramesh', 'Velu', '1993-12-14', 'ramesh.velu@example.com', '9654783210', 'Vellore'),
('Lakshmi', 'Ganesh', '1997-04-07', 'lakshmi.ganesh@example.com', '9123456780', 'Puducherry');

INSERT INTO accounts 
(customer_id, account_type, balance) 
VALUES
(1, 'savings', 25000.00),
(2, 'current', 40000.50),
(3, 'savings', 15000.75),
(4, 'zero_balance', 0.00),
(5, 'savings', 32000.00),
(6, 'current', 50000.25),
(7, 'savings', 18000.90),
(8, 'current', 60000.10),
(9, 'zero_balance', 0.00),
(10, 'savings', 28000.80);

INSERT INTO transaction 
(account_id, transaction_type, amount, transaction_date) 
VALUES
(1, 'deposit', 5000.00, '2024-03-10'),
(2, 'withdrawal', 2000.50, '2024-03-11'),
(3, 'deposit', 7000.75, '2024-03-12'),
(4, 'deposit', 3000.00, '2024-03-13'),
(5, 'withdrawal', 10000.00, '2024-03-14'),
(6, 'deposit', 15000.25, '2024-03-15'),
(7, 'withdrawal', 2500.90, '2024-03-16'),
(8, 'deposit', 12000.10, '2024-03-17'),
(9, 'deposit', 5000.00, '2024-03-18'),
(10, 'withdrawal', 4000.80, '2024-03-19');

-- 2.2 Write SQL queries for the following tasks

-- 1. retrieve the name, account type, and email of all customers  
Select c.first_name,c.last_name,a.account_type,c.email 
from customers c 
join accounts a on a.customer_id = c.customer_id;

-- 2. Write a SQL query to list all transaction corresponding customer.
select c.first_name, c.last_name, t.transaction_type, t.amount, t.transaction_date from transaction t
join accounts a on a.account_id= t.account_id
join customers c on c.customer_id = a.customer_id;

-- 3. Write a SQL query to increase the balance of a specific account by a certain amount.
set sql_safe_updates = 0;
update accounts
set balance = balance + 500
where account_id = 4;

-- 4. Write a SQL query to Combine first and last names of customers as a full_name. 
select concat(first_name," ",last_name) as full_name from customers;

-- 5. Write a SQL query to remove accounts with a balance of zero where the account type is savings.
delete from accounts
where account_type = 'savings' and balance = 0;

-- 6. Write a SQL query to Find customers living in a specific city.
select * from customers
where address = 'chennai';

-- 7. Write a SQL query to Get the account balance for a specific account. 
select * from accounts
where account_id = 7;

-- 8. Write a SQL query to List all current accounts with a balance greater than $1,000.
select * from accounts
where account_type = 'current' and balance > 1000;

-- 9. Write a SQL query to Retrieve all transactions for a specific account.
select * from transaction
where account_id = 4;

-- 10. Write a SQL query to Calculate the interest accrued on savings accounts based on a given interest rate.
select account_id, balance,(balance*(12.5/100)) as interest_accured from accounts;

-- 11. Write a SQL query to Identify accounts where the balance is less than a specified overdraft limit. 
select * from accounts
where balance < 10000;

-- 12. Write a SQL query to Find customers not living in a specific city.
select * from customers
where address <> 'chennai' ;

-- Task 3 :

-- 1. Write a SQL query to Find the average account balance for all customers.  
select avg(balance) as average_balance from accounts;

-- 2.Write a SQL query to Retrieve the top 10 highest account balances.
select balance from accounts
order by balance desc
limit 10;

-- 3. Write a SQL query to Calculate Total Deposits for All Customers in specific date.
select sum(amount) as total_deposits from transaction 
where transaction_type = 'deposit' and transaction_date = '2024-03-10';

-- 4.Write a SQL query to Find the Oldest and Newest Customers. 
select(Select concat(c.first_name," ",c.last_name) from customers c
order by customer_id limit 1) as oldest,(Select concat(c.first_name," ",c.last_name) from customers c
order by customer_id desc limit 1) as newest;

-- 5. Write a SQL query to Retrieve transaction details along with the account type. 
select t.*,a.account_type from transaction t
join accounts a on t.account_id = a.account_id;

-- 6. Write a SQL query to Get a list of customers along with their account details.
select c.first_name as name , a.* from customers c
join accounts a on a.customer_id = c.customer_id;

-- 7.Write a SQL query to Retrieve transaction details along with customer information for a specific account.
select c.first_name, c.last_name, t.transaction_type, t.amount, t.transaction_date from transaction t
join accounts a on a.account_id = t.account_id
join customers c on c.customer_id = a.customer_id
where a.account_id = 5;

-- some random value for output
INSERT INTO customers (first_name, last_name, DOB, email, phone_number, address)  
VALUES ('Sanjay', 'Ramesh', '1990-08-15', 'sanjay.ramesh@example.com', '9845123456', 'Chennai');
INSERT INTO accounts (customer_id, account_type, balance)  
VALUES 
(11, 'savings', 35000.00),  
(11, 'current', 45000.50);

-- 8.Write a SQL query to Identify customers who have more than one account. 
select c.first_name, c.last_name , count(a.customer_id) as number_of_accounts from customers c
join accounts a on a.customer_id = c.customer_id
group by a.customer_id
having number_of_accounts > 1;

-- 9. Write a SQL query to Calculate the difference in transaction amounts between deposits and withdrawals.
select (select sum(amount) from transaction where transaction_type = 'deposit') - 
(select sum(amount) from transaction where transaction_type = 'withdrawal') 
as difference;

-- 10.Write a SQL query to Calculate the average daily balance for each account over a specified period. 
select a.account_id , avg(a.balance) as average_balance from accounts a
join transaction t on t.account_id = a.account_id
where t.transaction_date between '2024-03-10' and '2024-03-20' 
group by a.account_id;

-- 11.calculate the total balance for each account type 
select sum(balance) as tot_balance ,account_type from accounts
group by account_type;

-- 12. identify accounts with the highest number of transactions ordered by descending order
select a.account_id , count(t.transaction_id) as transaction_count from accounts a
join transaction t on t.account_id = a.account_id
group by a.account_id
order by transaction_count desc;

-- 13. list customers with high aggregate account balances, along with their account types 
select concat(c.first_name,c.last_name) as name , sum(a.balance) as balance, a.account_type from customers c
join accounts a on a.customer_id = c.customer_id
group by c.customer_id , a.account_type
having balance > 35000;

-- random data
insert into transaction (account_id, transaction_type, amount, transaction_date) values
(1, 'deposit', 5000.00, '2024-03-10'),  -- Duplicate of an existing transaction
(1, 'deposit', 5000.00, '2024-03-10'),  -- Duplicate of an existing transaction
(3, 'withdrawal', 2500.90, '2024-03-16'), -- New transaction
(3, 'withdrawal', 2500.90, '2024-03-16'); -- Duplicate transaction

-- 14. identify and list duplicate transactions based on transaction amount, date, and account
select account_id, amount, transaction_date, count(*) as duplicate_count  
from transaction  
group by account_id, amount, transaction_date  
having duplicate_count > 1; 


-- Task 4 : 

-- 1.Retrieve the customer(s) with the highest account balance.
select concat(c.first_name," ",c.last_name) as name , a.account_id, a.account_type , a.balance
from customers c
join accounts a on a.customer_id = c.customer_id
where a.balance = (select max(balance) from accounts);

-- 2.Calculate the average account balance for customers who have more than one account. 
select concat(c.first_name, " ", c.last_name) as name, avg(a.balance) as average , count(a.customer_id) as tot_accounts 
from accounts a
join customers c on a.customer_id = c.customer_id
where c.customer_id in (  
    select customer_id  
    from accounts  
    group by customer_id  
    having count(account_id) > 1  
)
group by c.customer_id;

-- 3. Retrieve accounts with transactions whose amounts exceed the average transaction amount.
select account_id from transaction
where amount > (select avg(amount) from transaction);

-- 4.Identify customers who have no recorded transactions. 
select distinct c.* from customers c
join accounts a on c.customer_id = a.customer_id
where a.account_id not in (select account_id from transaction);

-- 5.Calculate the total balance of accounts with no recorded transactions.
select c.*, sum(a.balance) as total_balance from accounts a
join customers c on c.customer_id = a.customer_id
where a.account_id not in (select account_id from transaction)
group by a.account_id;

-- 6. Retrieve transactions for accounts with the lowest balance.
select * from transaction 
where account_id in
(select account_id from accounts where balance = (select min(balance) from accounts));

-- random data 
insert into accounts (customer_id, account_type, balance)  
values 
(11, 'savings', 45000.00),  
(11, 'current', 30000.50),  
(11, 'zero_balance', 0.00);

-- 7.Identify customers who have accounts of multiple types. 
select c.* , count(distinct a.account_type) as tot_ac from customers c
join accounts a on a.customer_id = c.customer_id
group by a.customer_id
having tot_ac > 1;

-- 8. Calculate the percentage of each account type out of the total number of accounts.
select 
	account_type,
	count(*) as tot_ac, 
	(count(*) * 100.0 / (select count(*) from accounts)) as percentage
from accounts
group by account_type;

-- 9. Retrieve all transactions for a customer with a given customer_id.
select t.* from transaction t
where t.account_id in (select account_id from accounts where customer_id = 6);


-- 10.Calculate the total balance for each account type, including a subquery within the SELECT clause.
select account_type,  
       (select sum(balance) from accounts where account_type = a1.account_type) as total_balance  
from accounts a1  
group by account_type;