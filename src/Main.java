import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = null;

        // Load bank data from file if it exists, or create a new bank
        System.out.println("Enter the name of the bank: ");
        String bankName = sc.nextLine();
        String filePath = "data/" + bankName + "_data.ser";
        bank = Bank.loadFromFile(filePath);

        if (bank == null) {
            System.out.println("No saved data found for this bank. Creating a new bank.");
            bank = new Bank(bankName);
        }

        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- BANKING SYSTEM MENU ---");
            System.out.println("1. Add Customer");
            System.out.println("2. Open Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer Funds");
            System.out.println("6. Calculate Monthly Interest");
            System.out.println("7. View Account Balance");
            System.out.println("8. Exit and Save");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Add Customer
                    System.out.println("Enter customer ID: ");
                    String customerID = sc.nextLine();
                    System.out.println("Enter customer name: ");
                    String customerName = sc.nextLine();
                    System.out.println("Enter customer address: ");
                    String customerAddress = sc.nextLine();

                    Customer customer = new Customer(customerID, customerName, customerAddress);
                    bank.addCustomer(customer);
                    System.out.println("Customer added successfully!");
                    break;

                case 2:
                    // Open Account
                    System.out.println("Enter customer ID to open an account: ");
                    String custIDForAccount = sc.nextLine();
                    Customer existingCustomer = findCustomerById(bank, custIDForAccount);

                    if (existingCustomer != null) {
                        System.out.println("Enter account number: ");
                        String accountNumber = sc.nextLine();
                        System.out.println("Enter initial deposit: ");
                        double initialDeposit = sc.nextDouble();
                        sc.nextLine(); // Consume the newline character

                        System.out.println("Select account type: ");
                        System.out.println("1. Savings Account");
                        System.out.println("2. Current Account");
                        System.out.println("3. Loan Account");
                        int accountType = sc.nextInt();

                        Account account = null;

                        double interestRate = 0.05; // interest rate (5%)
                        switch (accountType) {
                            case 1:
                                account = new SavingsAccount(accountNumber, existingCustomer, initialDeposit, interestRate);

                                break;
                            case 2:
                                account = new CurrentAccount(accountNumber, existingCustomer, initialDeposit, interestRate);
                                break;
                            case 3:
                                account = new LoanAccount(accountNumber, existingCustomer, initialDeposit, interestRate);
                                break;
                            default:
                                System.out.println("Invalid account type.");
                        }

                        if (account != null) {
                            bank.openAccount(existingCustomer, account);
                            System.out.println("Account opened successfully!");
                        }
                    } else {
                        System.out.println("Customer not found!");
                    }
                    break;

                case 3:
                    // Deposit
                    System.out.println("Enter account number to deposit: ");
                    String accountNumberDeposit = sc.nextLine();
                    Account accountForDeposit = bank.findAccountByNumber(accountNumberDeposit);

                    if (accountForDeposit != null) {
                        System.out.println("Enter deposit amount: ");
                        double depositAmount = sc.nextDouble();
                        accountForDeposit.deposit(depositAmount);
                        System.out.println("Deposit successful!");
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 4:
                    // Withdraw
                    System.out.println("Enter account number to withdraw: ");
                    String accountNumberWithdraw = sc.nextLine();
                    Account accountForWithdraw = bank.findAccountByNumber(accountNumberWithdraw);

                    if (accountForWithdraw != null) {
                        System.out.println("Enter withdrawal amount: ");
                        double withdrawAmount = sc.nextDouble();
                        accountForWithdraw.withdraw(withdrawAmount);
                        System.out.println("Withdrawal successful!");
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 5:
                    // Transfer Funds
                    System.out.println("Enter source account number: ");
                    String fromAccount = sc.nextLine();
                    System.out.println("Enter destination account number: ");
                    String toAccount = sc.nextLine();
                    System.out.println("Enter transfer amount: ");
                    double transferAmount = sc.nextDouble();
                    bank.transferFunds(fromAccount, toAccount, transferAmount);
                    System.out.println("Transfer successful!");
                    break;

                case 6:
                    // Calculate Monthly Interest
                    bank.calculateMonthlyInterest();
                    System.out.println("Monthly interest calculated for all eligible accounts.");
                    break;

                case 7:
                    // View Account Balance
                    System.out.println("Enter account number to view balance: ");
                    String accNumber = sc.nextLine();
                    Account acc = bank.findAccountByNumber(accNumber);

                    if (acc != null) {
                        System.out.println("Account balance: " + acc.getBalance());
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 8:
                    // Exit and Save
                    bank.saveToFile(filePath);
                    System.out.println("Exiting and saving data. Goodbye!");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }

        sc.close();
    }

    // Helper method to find a customer by their ID
    private static Customer findCustomerById(Bank bank, String customerID) {
        for (Customer customer : bank.getCustomers()) {
            if (customer.getCustomerID().equals(customerID)) {
                return customer;
            }
        }
        return null;
    }
}