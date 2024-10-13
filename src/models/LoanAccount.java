package models;

public class LoanAccount extends Account {
    private double loanBalance;

    public LoanAccount(String accountNumber, Customer accountHolder, double initialDeposit, double loanAmount) {
        super(accountNumber, accountHolder, initialDeposit);
        this.loanBalance = loanAmount;
    }

    public boolean repayLoan(double amount) {
        if (amount > loanBalance) {
            return false; // Repayment amount exceeds loan balance
        }
        loanBalance -= amount; // Reduce the loan balance
        logTransaction("Loan Repayment", amount); // Log the repayment transaction
        return true;
    }

    public double getLoanBalance() {
        return loanBalance;
    }
}
