package models;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
    private final String transactionType;
    private final double amount;
    private final Date date;

    public Transaction(String transactionType, String accountNumber, double amount) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return transactionType + ": " + amount + " on " + date;
    }
}
