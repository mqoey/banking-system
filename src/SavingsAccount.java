import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class SavingsAccount extends Account implements Serializable {
    private double interestRate;

    public SavingsAccount(String accountNumber, Customer accountHolder, double initialDeposit, double interestRate) {
        super(accountNumber, accountHolder, initialDeposit);

        // Ensure that the interest rate is a valid percentage
        if (interestRate < 0 || interestRate > 1) {
            throw new IllegalArgumentException("Interest rate must be between 0 and 1.");
        }
        this.interestRate = interestRate;
    }

    @Override
    public void applyInterest() {
        // Calculate interest only if there's a balance
        if (balance > 0) {
            double interest = balance * interestRate;
            balance += interest;
            logTransaction("Interest Applied", interest);
        } else {
            System.out.println("No balance to apply interest.");
        }
    }

    @Override
    protected void logTransaction(String type, double amount) {
        // Create a new Transaction with a generated transaction ID
        String transactionID = UUID.randomUUID().toString(); // Generate a unique ID
        Date date = new Date(); // Get the current date
        Transaction transaction = new Transaction(transactionID, getAccountNumber(), type, amount, date);

        // Log the transaction
        logTransactionToFile(transaction);
    }

    // Method to log transactions to a file
    private void logTransactionToFile(Transaction transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transaction_log.txt", true))) {
            writer.write("Transaction Type: " + transaction.getTransactionType() + "\n");
            writer.write("Account Number: " + transaction.getAccountNumber() + "\n");
            writer.write("Amount: " + transaction.getAmount() + "\n");
            writer.write("Date: " + new Date() + "\n");
            writer.write("------------------------------\n");
        } catch (IOException e) {
            System.out.println("Error logging transaction: " + e.getMessage());
        }
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        if (interestRate < 0 || interestRate > 1) {
            throw new IllegalArgumentException("Interest rate must be between 0 and 1.");
        }
        this.interestRate = interestRate;
    }
}
