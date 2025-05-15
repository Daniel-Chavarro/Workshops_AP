package atmSoftware.view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Screen for displaying account balance.
 */
public class BalanceScreen extends JPanel {
    
    public JLabel balanceLabel;
    public JButton backButton;
    
    /**
     * Constructor initializes the balance screen with balance display and back button
     */
    public BalanceScreen() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Account Balance");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        
        JPanel balancePanel = new JPanel();
        balancePanel.setLayout(new GridLayout(2, 1, 0, 10));
        
        JLabel balanceTextLabel = new JLabel("Your current balance is:");
        balanceTextLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        balanceTextLabel.setHorizontalAlignment(JLabel.CENTER);
        
        balanceLabel = new JLabel("$0.00");
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 24));
        balanceLabel.setHorizontalAlignment(JLabel.CENTER);
        
        balancePanel.add(balanceTextLabel);
        balancePanel.add(balanceLabel);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        backButton = new JButton("Back to Menu");
        
        buttonPanel.add(backButton);
        
        add(titleLabel, BorderLayout.NORTH);
        add(balancePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Sets the balance to display
     * 
     * @param balance The balance amount
     */
    public void setBalance(double balance) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        balanceLabel.setText(currencyFormatter.format(balance));
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
