package utils;

import models.Account;
import models.Customer;
import models.Transaction;

import java.io.*;
import java.util.Map;

public class FileManager {

    private static final String CUSTOMER_FILE = "data/customers.ser";
    private static final String ACCOUNT_FILE = "data/accounts.ser";
    private static final String TRANSACTION_FILE_PREFIX = "data/transactions_";

    // Save customers to file
    public static void saveCustomersToFile(Map<String, Customer> customers) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CUSTOMER_FILE))) {
            oos.writeObject(customers);
        } catch (IOException e) {
            System.out.println("Error saving customer data: " + e.getMessage());
        }
    }

    // Save accounts to file
    public static void saveAccountsToFile(Map<String, Account> accounts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ACCOUNT_FILE))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Error saving account data: " + e.getMessage());
        }
    }

    // Save transactions to file
    public static void saveTransactionsToFile(String accountNumber, Transaction transaction) {
        String transactionFile = TRANSACTION_FILE_PREFIX + accountNumber + ".ser";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(transactionFile, true))) {
            oos.writeObject(transaction);
        } catch (IOException e) {
            System.out.println("Error saving transaction data: " + e.getMessage());
        }
    }

    // Load customers from file
    @SuppressWarnings("unchecked")
    public static Map<String, Customer> loadCustomersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CUSTOMER_FILE))) {
            return (Map<String, Customer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading customer data: " + e.getMessage());
            return null;
        }
    }

    // Load accounts from file
    @SuppressWarnings("unchecked")
    public static Map<String, Account> loadAccountsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ACCOUNT_FILE))) {
            return (Map<String, Account>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading account data: " + e.getMessage());
            return null;
        }
    }
}
