package services;

import models.Account;
import models.Customer;
import models.Transaction;
import utils.FileManager;
import utils.PopUpManager;

import java.util.HashMap;
import java.util.Map;

public class BankService {

    private static final Map<String, Customer> customers = new HashMap<>();
    private static final Map<String, Account> accounts = new HashMap<>();

    // Add a new customer to the bank
    public static void addCustomer(Customer customer) {
        if (customers.containsKey(customer.getCustomerID())) {
            throw new IllegalArgumentException("Customer with this ID already exists.");
        }
        customers.put(customer.getCustomerID(), customer);
        FileManager.saveCustomersToFile(customers);
    }

    // Open a new account
    public static void openAccount(Account account) {
        if (accounts.containsKey(account.getAccountNumber())) {
            throw new IllegalArgumentException("Account with this number already exists.");
        }
        accounts.put(account.getAccountNumber(), account);
        FileManager.saveAccountsToFile(accounts);
    }

    // Deposit money into an account
    public static void deposit(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }
        account.deposit(amount);
        logTransaction(accountNumber, "Deposit", amount);
        FileManager.saveAccountsToFile(accounts);
    }

    // Withdraw money from an account
    public static void withdraw(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }
        if (!account.withdraw(amount)) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        logTransaction(accountNumber, "Withdrawal", amount);
        FileManager.saveAccountsToFile(accounts);
    }

    // Transfer money between accounts
    public static void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = accounts.get(fromAccountNumber);
        Account toAccount = accounts.get(toAccountNumber);

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("One or both accounts not found.");
        }

        if (!fromAccount.withdraw(amount)) {
            throw new IllegalArgumentException("Insufficient funds in the source account.");
        }
        toAccount.deposit(amount);

        logTransaction(fromAccountNumber, "Transfer to " + toAccountNumber, amount);
        logTransaction(toAccountNumber, "Transfer from " + fromAccountNumber, amount);

        FileManager.saveAccountsToFile(accounts);
    }

    // Check balance of an account
    public static double checkBalance(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }
        return account.getBalance();
    }

    // View transaction history (you may load it from file or maintain it in memory)
    public static void viewTransactionHistory(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }
        account.printTransactionHistory();
    }

    // Log a transaction
    private static void logTransaction(String accountNumber, String type, double amount) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }

        // Create a new transaction
        Transaction transaction = new Transaction(type, accountNumber, amount);
        account.addTransaction(transaction);
        FileManager.saveTransactionsToFile(accountNumber, transaction);
    }

    // Calculate interest for a savings account
    public static void calculateInterest(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }

        if (account instanceof models.SavingsAccount) {
            ((models.SavingsAccount) account).applyInterest();
            logTransaction(accountNumber, "Interest Applied", account.getBalance());
            FileManager.saveAccountsToFile(accounts);
        } else {
            throw new IllegalArgumentException("Interest can only be applied to savings accounts.");
        }
    }

    // Find an account by account number
    public static Account findAccountByNumber(String accountNumber) {
        return accounts.get(accountNumber); // Return the account or null if not found
    }

    // Repay loan for loan accounts
    public static void repayLoan(String accountNumber, double amount) throws Exception {
        // Find the account by account number
        Account account = findAccountByNumber(accountNumber);
        if (account instanceof models.LoanAccount) {
            if (((models.LoanAccount) account).repayLoan(amount)) {
                logTransaction(accountNumber, "Loan Repayment", amount);
                FileManager.saveAccountsToFile(accounts);
                PopUpManager.showMessage("Loan repayment successful!");
            } else {
                throw new Exception("Repayment amount exceeds loan balance.");
            }
        } else {
            throw new Exception("Account not found or not a loan account.");
        }
    }
}
