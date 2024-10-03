# Bank System in Java

## Overview

This project is a simple banking system implemented in Java. It allows you to create customers, open accounts, perform transactions such as deposits and withdrawals, and calculate interest for savings accounts. The system also allows for transferring funds between accounts of the same customer.

## Features

- Add new customers to the bank
- Open savings accounts for customers
- Deposit and withdraw funds from accounts
- Apply monthly interest to savings accounts
- Transfer funds between accounts
- Save and load bank data from a file

## Classes

### 1. `Bank`
The `Bank` class is responsible for managing customers and accounts. It implements the following key functionalities:
- Add a customer
- Open an account for a customer
- Find an account by its account number
- Transfer funds between accounts
- Calculate interest for eligible accounts
- Save and load the bank data

### 2. `Customer`
The `Customer` class represents a customer in the bank. Each customer has a unique ID, name, address, and a list of accounts. 

### 3. `Account`
An abstract class that represents a bank account. It holds the account number, the balance, and the customer who owns the account. It provides methods for depositing, withdrawing, and logging transactions.

### 4. `SavingsAccount`
The `SavingsAccount` class extends the `Account` class and represents a savings account. It includes an interest rate and a method for applying interest to the account.

### 5. `Transaction`
The `Transaction` class is used to represent individual transactions on an account, such as deposits, withdrawals, or interest application.

## Getting Started

### Prerequisites
- Java 8 or higher
