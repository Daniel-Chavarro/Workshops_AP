package atmSoftware.view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Login frame for user authentication.
 * Provides fields for card number and PIN entry.
 */
public class LoginFrame extends JFrame {
    
    public JTextField cardNumberField;
    public JPasswordField pinField;
    public JButton loginButton;
    public JButton exitButton;
    
    /**
     * Constructor initializes the login frame with all components
     */
    public LoginFrame() {
        setTitle("ATM Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title label
        JLabel titleLabel = new JLabel("Welcome to ATM System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 2, 10, 10));
        
        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberField = new JTextField();
        JLabel pinLabel = new JLabel("PIN:");
        pinField = new JPasswordField();
        
        formPanel.add(cardNumberLabel);
        formPanel.add(cardNumberField);
        formPanel.add(pinLabel);
        formPanel.add(pinField);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 0));
        
        loginButton = new JButton("Login");
        exitButton = new JButton("Exit");
        
        buttonPanel.add(loginButton);
        buttonPanel.add(exitButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    /**
     * Gets the card number entered by the user
     * 
     * @return The card number as a string
     */
    public String getCardNumber() {
        return cardNumberField.getText();
    }
    
    /**
     * Gets the PIN entered by the user
     * 
     * @return The PIN as a string
     */
    public String getPIN() {
        return new String(pinField.getPassword());
    }
    
    /**
     * Displays an error message to the user
     * 
     * @param message The error message to display
     */
    public void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Closes the login frame
     */
    public void close() {
        dispose();
    }
    
    /**
     * Adds an action listener to the login button
     * 
     * @param listener The action listener to add
     */
    public void addLoginButtonListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }
    
    /**
     * Adds an action listener to the exit button
     * 
     * @param listener The action listener to add
     */
    public void addExitButtonListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }
}
