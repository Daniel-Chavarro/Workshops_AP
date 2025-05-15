package atmSoftware.view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Screen that displays the main transaction menu options.
 */
public class TransactionMenuScreen extends JPanel {
    
    public JButton depositNavButton;
    public JButton withdrawNavButton;
    public JButton balanceNavButton;
    
    /**
     * Constructor initializes the transaction menu screen with buttons for each operation
     */
    public TransactionMenuScreen() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Transaction Menu");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 0, 10));
        
        depositNavButton = new JButton("Deposit");
        withdrawNavButton = new JButton("Withdraw");
        balanceNavButton = new JButton("Check Balance");
        
        depositNavButton.setFont(new Font("Arial", Font.PLAIN, 16));
        withdrawNavButton.setFont(new Font("Arial", Font.PLAIN, 16));
        balanceNavButton.setFont(new Font("Arial", Font.PLAIN, 16));
        
        buttonPanel.add(depositNavButton);
        buttonPanel.add(withdrawNavButton);
        buttonPanel.add(balanceNavButton);
        
        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }
    
    /**
     * Adds an action listener to the deposit navigation button
     * 
     * @param listener The action listener to add
     */
    public void addDepositNavButtonListener(ActionListener listener) {
        depositNavButton.addActionListener(listener);
    }
    
    /**
     * Adds an action listener to the withdraw navigation button
     * 
     * @param listener The action listener to add
     */
    public void addWithdrawNavButtonListener(ActionListener listener) {
        withdrawNavButton.addActionListener(listener);
    }
    
    /**
     * Adds an action listener to the balance navigation button
     * 
     * @param listener The action listener to add
     */
    public void addBalanceNavButtonListener(ActionListener listener) {
        balanceNavButton.addActionListener(listener);
    }
}
