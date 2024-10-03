import java.io.*;
import java.util.ArrayList;

public class Bank implements IBank, Serializable {
    @Serial
    private static final long serialVersionUID = 1L; // For version control of serialized objects

    private final String bankName;
    private final ArrayList<Customer> customers;
    private final ArrayList<Account> accounts;

    public Bank(String bankName) {
        this.bankName = bankName;
        this.customers = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }

    @Override
    public void addCustomer(Customer c) {
        customers.add(c);
    }

    @Override
    public void openAccount(Customer c, Account a) {
        c.openAccount(a);
        accounts.add(a);
    }

    @Override
    public Account findAccountByNumber(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public void calculateMonthlyInterest() {
        for (Account account : accounts) {
            if (account instanceof SavingsAccount || account instanceof LoanAccount) {
                account.applyInterest();
            }
        }
    }

    @Override
    public void repayLoan(String accountNumber, double amount) {
        Account account = findAccountByNumber(accountNumber);
        if (account instanceof LoanAccount) {
            ((LoanAccount) account).repayLoan(amount);
        } else {
            System.out.println("Account is not a loan account.");
        }
    }

    // Save the current state of the bank to a file
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this); // 'this' refers to the current Bank instance
            System.out.println("Bank data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving bank data: " + e.getMessage());
        }
    }

    // Load the bank state from a file
    public static Bank loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return null; // File doesn't exist, so return null
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Bank) ois.readObject(); // Cast to Bank
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading bank data: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = findAccountByNumber(fromAccountNumber);
        Account toAccount = findAccountByNumber(toAccountNumber);

        // Check if both accounts exist
        if (fromAccount == null || toAccount == null) {
            System.out.println("One or both account numbers are invalid.");
            return;
        }

        // Check if both accounts belong to the same customer
        if (!fromAccount.getAccountHolder().equals(toAccount.getAccountHolder())) {
            System.out.println("Transfer can only be made between accounts of the same customer.");
            return;
        }

        // Perform withdrawal from 'fromAccount'
        if (fromAccount.withdraw(amount)) {
            // Deposit the amount into 'toAccount'
            toAccount.deposit(amount);
            System.out.println("Transfer successful! Transferred " + amount + " from "
                    + fromAccountNumber + " to " + toAccountNumber);
        } else {
            System.out.println("Insufficient funds in the source account for transfer.");
        }
    }


    public String getBankName() {
        return bankName;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
