package ui;

import services.BankService;
import utils.PopUpManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionPanel extends JPanel {

    private final JTextField accountNumberField;
    private final JTextField amountField;

    public TransactionPanel() {
        setLayout(null);

        JLabel lblAccountNumber = new JLabel("Account Number:");
        lblAccountNumber.setBounds(30, 20, 150, 25);
        add(lblAccountNumber);

        accountNumberField = new JTextField();
        accountNumberField.setBounds(180, 20, 150, 25);
        add(accountNumberField);

        JLabel lblAmount = new JLabel("Amount:");
        lblAmount.setBounds(30, 60, 150, 25);
        add(lblAmount);

        amountField = new JTextField();
        amountField.setBounds(180, 60, 150, 25);
        add(amountField);

        // Deposit Button
        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(30, 100, 150, 30);
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String accountNumber = accountNumberField.getText();
                    double amount = Double.parseDouble(amountField.getText());

                    BankService.deposit(accountNumber, amount);
                    PopUpManager.showMessage("Deposit successful!");
                } catch (Exception ex) {
                    PopUpManager.showError("Error making deposit: " + ex.getMessage());
                }
            }
        });
        add(depositButton);

        // Withdraw Button
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(180, 100, 150, 30);
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String accountNumber = accountNumberField.getText();
                    double amount = Double.parseDouble(amountField.getText());

                    // Call BankService to handle withdrawal
                    BankService.withdraw(accountNumber, amount);
                    PopUpManager.showMessage("Withdrawal successful!");
                } catch (Exception ex) {
                    PopUpManager.showError("Error making withdrawal: " + ex.getMessage());
                }
            }
        });
        add(withdrawButton);

        // Repay Loan Button
        JButton repayLoanButton = new JButton("Repay Loan");
        repayLoanButton.setBounds(330, 100, 150, 30);
        repayLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String accountNumber = accountNumberField.getText();
                    double amount = Double.parseDouble(amountField.getText());

                    BankService.repayLoan(accountNumber, amount);
                    PopUpManager.showMessage("Loan repayment successful!");
                } catch (Exception ex) {
                    PopUpManager.showError("Error repaying loan: " + ex.getMessage());
                }
            }
        });
        add(repayLoanButton);
    }
}
