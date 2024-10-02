public interface IAccount {
    void deposit(double amount);
    boolean withdraw(double amount);
    void applyInterest();
}
