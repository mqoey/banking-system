package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
    private final String customerID;
    private final List<Account> accounts;

    public Customer(String customerID, String name, String address) {
        this.customerID = customerID;
        this.accounts = new ArrayList<>();
    }

    public String getCustomerID() {
        return customerID;
    }

    public void openAccount(Account account) {
        accounts.add(account);
    }

    public Account getAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void printAccounts() {
        for (Account account : accounts) {
            System.out.println("Account Number: " + account.getAccountNumber());
        }
    }
}
