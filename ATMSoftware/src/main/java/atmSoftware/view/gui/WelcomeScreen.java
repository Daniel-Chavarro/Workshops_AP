package atmSoftware.view.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Welcome screen displayed after successful login.
 */
public class WelcomeScreen extends JPanel {
    
    public JLabel welcomeLabel;
    
    /**
     * Constructor initializes the welcome screen with a personalized message
     * 
     * @param clientName The name of the client to welcome
     */
    public WelcomeScreen(String clientName) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        welcomeLabel = new JLabel("Welcome, " + clientName + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel instructionLabel = new JLabel("Please select an operation from the menu");
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        instructionLabel.setHorizontalAlignment(JLabel.CENTER);
        
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(2, 1, 0, 10));
        labelPanel.add(welcomeLabel);
        labelPanel.add(instructionLabel);
        
        add(labelPanel, BorderLayout.CENTER);
    }
}
