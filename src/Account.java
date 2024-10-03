import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public abstract class Account implements IAccount, Serializable {
    private final String accountNumber;
    protected double balance;
    private final Customer accountHolder;

    public Account(String accountNumber, Customer accountHolder, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialDeposit;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    // Getter for the account holder
    public Customer getAccountHolder() {
        return accountHolder;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        logTransaction("Deposit", amount);
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            logTransaction("Withdraw", amount);
            return true;
        } else {
            System.out.println("Insufficient funds.");
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }

    // Log transactions to a file for all account types
    protected void logTransaction(String type, double amount) {
        String transactionID = UUID.randomUUID().toString(); // Generate a unique ID
        Date date = new Date(); // Get the current date
        Transaction transaction = new Transaction(transactionID, getAccountNumber(), type, amount, date);

        logTransactionToFile(transaction);
    }

    // Method to log transactions to a file
    private void logTransactionToFile(Transaction transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transaction_log.txt", true))) {
            writer.write("Transaction ID: " + transaction.getTransactionID() + "\n");
            writer.write("Transaction Type: " + transaction.getTransactionType() + "\n");
            writer.write("Account Number: " + transaction.getAccountNumber() + "\n");
            writer.write("Amount: " + transaction.getAmount() + "\n");
            writer.write("Date: " + transaction.getDate() + "\n");
            writer.write("------------------------------\n");
        } catch (IOException e) {
            System.out.println("Error logging transaction: " + e.getMessage());
        }
    }

    @Override
    public abstract void applyInterest();
}