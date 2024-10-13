package ui;

import models.Account;
import models.SavingsAccount;
import services.BankService;
import utils.PopUpManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountPanel extends JPanel {

    private final JTextField accountNumberField;
    private final JTextField initialDepositField;
    private final JTextField interestRateField;

    public AccountPanel() {
        setLayout(null);

        JLabel lblAccountNumber = new JLabel("Account Number:");
        lblAccountNumber.setBounds(30, 20, 150, 25);
        add(lblAccountNumber);

        accountNumberField = new JTextField();
        accountNumberField.setBounds(180, 20, 150, 25);
        add(accountNumberField);

        JLabel lblInitialDeposit = new JLabel("Initial Deposit:");
        lblInitialDeposit.setBounds(30, 60, 150, 25);
        add(lblInitialDeposit);

        initialDepositField = new JTextField();
        initialDepositField.setBounds(180, 60, 150, 25);
        add(initialDepositField);

        JLabel lblInterestRate = new JLabel("Interest Rate:");
        lblInterestRate.setBounds(30, 100, 150, 25);
        add(lblInterestRate);

        interestRateField = new JTextField();
        interestRateField.setBounds(180, 100, 150, 25);
        add(interestRateField);

        JButton openAccountButton = getjButton();

        add(openAccountButton);
    }

    private JButton getjButton() {
        JButton openAccountButton = new JButton("Open Account");
        openAccountButton.setBounds(180, 150, 150, 30);
        openAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String accountNumber = accountNumberField.getText();
                    double initialDeposit = Double.parseDouble(initialDepositField.getText());
                    double interestRate = Double.parseDouble(interestRateField.getText());

                    Account account = new SavingsAccount(accountNumber, null, initialDeposit, interestRate);  // Replace 'null' with customer
                    BankService.openAccount(account);

                    PopUpManager.showMessage("Account opened successfully!");
                } catch (Exception ex) {
                    PopUpManager.showError("Error opening account: " + ex.getMessage());
                }
            }
        });
        return openAccountButton;
    }
}
