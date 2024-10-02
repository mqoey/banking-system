import java.util.ArrayList;

public class Customer {
    private final String customerID;
    private final String name;
    private final String address;
    private final ArrayList<Account> accounts;

    public Customer(String customerID, String name, String address) {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.accounts = new ArrayList<>();
    }

    // Getter for customerID
    public String getCustomerID() {
        return customerID;
    }

    public void openAccount(Account a) {
        accounts.add(a);
    }

    public Account getAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    // Additional getter for name and address if needed
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
