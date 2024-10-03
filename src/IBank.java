public interface IBank {
    void addCustomer(Customer c);
    void openAccount(Customer c, Account a);
    Account findAccountByNumber(String accountNumber);
    void calculateMonthlyInterest();

    void repayLoan(String accountNumber, double amount);

    // Transfer funds method
    void transferFunds(String fromAccountNumber, String toAccountNumber, double amount);
}
