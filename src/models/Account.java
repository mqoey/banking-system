package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
    private final String accountNumber;
    protected double balance;
    private final Customer accountHolder;
    private final List<Transaction> transactions;

    public Account(String accountNumber, Customer accountHolder, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialDeposit;
        this.transactions = new ArrayList<>();
        // Log the initial deposit
        addTransaction(new Transaction("Deposit", accountNumber, initialDeposit));
    }

    public void applyInterest() {
        // This will be implemented in subclasses
    }

    protected void logTransaction(String type, double amount) {
        // Create and add a new Transaction to the list
        Transaction transaction = new Transaction(type, accountNumber, amount);
        addTransaction(transaction);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Customer getAccountHolder() {
        return accountHolder;
    }

    public void deposit(double amount) {
        balance += amount;
        logTransaction("Deposit", amount);
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            logTransaction("Withdraw", amount);
        } else {
            System.out.println("Insufficient funds.");
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void printTransactionHistory() {
        System.out.println("Transaction history for account: " + accountNumber);
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}
