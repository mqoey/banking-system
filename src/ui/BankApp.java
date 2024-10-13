package ui;

import javax.swing.*;
import java.awt.*;

public class BankApp extends JFrame {

    public BankApp() {
        setTitle("Banking System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        // Adding different panels for the operations
        tabbedPane.addTab("Customer", new CustomerPanel());
        tabbedPane.addTab("Account", new AccountPanel());
        tabbedPane.addTab("Transactions", new TransactionPanel());

        add(tabbedPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new BankApp();
    }
}
