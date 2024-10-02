public class LoanAccount extends Account {
    private double loanAmount;
    private final double interestRate;

    public LoanAccount(String accountNumber, Customer accountHolder, double loanAmount, double interestRate) {
        super(accountNumber, accountHolder, 0);
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
    }

    @Override
    public void applyInterest() {
        double interest = loanAmount * interestRate;
        loanAmount += interest;
        logTransaction("Interest", interest);
    }

    public void repayLoan(double amount) {
        if (amount <= loanAmount) {
            loanAmount -= amount;
            logTransaction("Loan Repayment", amount);
        } else {
            System.out.println("Amount exceeds loan balance.");
        }
    }

    @Override
    protected void logTransaction(String type, double amount) {
        System.out.println("Transaction: " + type + ", Amount: " + amount);
    }
}
