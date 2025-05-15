package atmSoftware.view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Screen for withdrawal operations.
 */
public class WithdrawScreen extends JPanel {
    
    public JTextField amountField;
    public JButton confirmWithdrawButton;
    public JButton backButton;
    
    /**
     * Constructor initializes the withdraw screen with input field and buttons
     */
    public WithdrawScreen() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Withdraw Money");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 1, 0, 10));
        
        JLabel amountLabel = new JLabel("Enter amount to withdraw:");
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        
        amountField = new JTextField();
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        
        formPanel.add(amountLabel);
        formPanel.add(amountField);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 0));
        
        confirmWithdrawButton = new JButton("Confirm Withdrawal");
        backButton = new JButton("Back to Menu");
        
        buttonPanel.add(confirmWithdrawButton);
        buttonPanel.add(backButton);
        
        add(titleLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Gets the amount entered by the user
     * 
     * @return The amount as a string
     */
    public String getAmount() {
        return amountField.getText();
    }
    
    /**
     * Clears the amount field
     */
    public void clearField() {
        amountField.setText("");
    }
    
    /**
     * Displays a message to the user
     * 
     * @param message The message to display
     * @param title The title of the message dialog
     * @param messageType The type of message (e.g., JOptionPane.INFORMATION_MESSAGE)
     */
    public void displayMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
    
    /**
     * Adds an action listener to the confirm withdraw button
     * 
     * @param listener The action listener to add
     */
    public void addConfirmWithdrawButtonListener(ActionListener listener) {
        confirmWithdrawButton.addActionListener(listener);
    }
    
    /**
     * Adds an action listener to the back button
     * 
     * @param listener The action listener to add
     */
    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }
}
