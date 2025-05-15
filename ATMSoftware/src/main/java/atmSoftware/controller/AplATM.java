package atmSoftware.controller;

import javax.swing.*;

/**
 * Main application class for the ATM system.
 */
public class AplATM {
    
    /**
     * Main method to start the application
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Start the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new Controller();
        });
    }
}
