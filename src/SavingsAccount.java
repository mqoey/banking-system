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

        // Log transaction to a file or console for auditing
        System.out.println("Transaction logged: " + transaction.getTransactionType() + " of amount " + amount + " on " + transaction.getDate());
        // You can implement further logging mechanisms if necessary (e.g., saving to a file).
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
