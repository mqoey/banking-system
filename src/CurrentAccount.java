public class CurrentAccount extends Account {
    private final double overdraftLimit;

    public CurrentAccount(String accountNumber, Customer accountHolder, double initialDeposit, double overdraftLimit) {
        super(accountNumber, accountHolder, initialDeposit);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance + overdraftLimit >= amount) {
            balance -= amount;
            logTransaction("Withdraw", amount);
        } else {
            System.out.println("Overdraft limit exceeded.");
        }
        return false;
    }

    @Override
    public void applyInterest() {
        // No interest for current accounts
    }
}
