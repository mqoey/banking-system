public abstract class Account implements IAccount {
    private final String accountNumber;
    protected double balance;
    private final Customer accountHolder;

    public Account(String accountNumber, Customer accountHolder, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialDeposit;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    // Getter for the account holder
    public Customer getAccountHolder() {
        return accountHolder;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        logTransaction("Deposit", amount);
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            logTransaction("Withdraw", amount);
            return true;
        } else {
            System.out.println("Insufficient funds.");
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }

    protected abstract void logTransaction(String type, double amount);

    @Override
    public abstract void applyInterest();
}