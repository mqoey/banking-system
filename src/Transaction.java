import java.util.Date;

public class Transaction {
    private final String transactionID;
    private final String accountNumber;
    private final String transactionType;
    private final double amount;
    private final Date date;

    public Transaction(String transactionID, String accountNumber, String transactionType, double amount, Date date) {
        this.transactionID = transactionID;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = date;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
