package models;

import java.io.Serializable;

public class SavingsAccount extends Account implements Serializable {
    private double interestRate;

    public SavingsAccount(String accountNumber, Customer accountHolder, double initialDeposit, double interestRate) {
        super(accountNumber, accountHolder, initialDeposit);
        if (interestRate < 0 || interestRate > 1) {
            throw new IllegalArgumentException("Interest rate must be between 0 and 1.");
        }
        this.interestRate = interestRate;
    }

    public void applyInterest() {
        if (balance > 0) {
            double interest = balance * interestRate;
            balance += interest;
            logTransaction(interest);
        } else {
            System.out.println("No balance to apply interest.");
        }
    }

    protected void logTransaction(double amount) {
        // Transaction logic
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
