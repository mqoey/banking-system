package ui;

import models.Customer;
import services.BankService;
import utils.PopUpManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerPanel extends JPanel {

    private final JTextField idField;
    private final JTextField nameField;
    private final JTextField addressField;

    public CustomerPanel() {
        setLayout(null);

        JLabel lblId = new JLabel("Customer ID:");
        lblId.setBounds(30, 20, 100, 25);
        add(lblId);

        idField = new JTextField();
        idField.setBounds(150, 20, 150, 25);
        add(idField);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(30, 60, 100, 25);
        add(lblName);

        nameField = new JTextField();
        nameField.setBounds(150, 60, 150, 25);
        add(nameField);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(30, 100, 100, 25);
        add(lblAddress);

        addressField = new JTextField();
        addressField.setBounds(150, 100, 150, 25);
        add(addressField);

        JButton createCustomerButton = getjButton();

        add(createCustomerButton);
    }

    private JButton getjButton() {
        JButton createCustomerButton = new JButton("Create Customer");
        createCustomerButton.setBounds(150, 150, 150, 30);
        createCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = idField.getText();
                    String name = nameField.getText();
                    String address = addressField.getText();

                    Customer customer = new Customer(id, name, address);
                    BankService.addCustomer(customer);

                    PopUpManager.showMessage("Customer created successfully!");
                } catch (Exception ex) {
                    PopUpManager.showError("Error creating customer: " + ex.getMessage());
                }
            }
        });
        return createCustomerButton;
    }
}
