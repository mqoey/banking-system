import java.io.Serializable;

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
