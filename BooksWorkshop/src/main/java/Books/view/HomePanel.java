package Books.view;

import Books.view.components.button.ButtonCreator;
import Books.view.components.button.FactoryButton;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    // Factory Button
    private FactoryButton factoryButton;

    // UI Components - Panels
    private JPanel buttonPanel;

    // UI Components - Labels
    private JLabel welcomeLabel;

    // UI Components - Buttons
    private JButton manageProductsBtn;

    /**
     * Constructor for HomePanel.
     * Initializes the panel with a BorderLayout and sets up all UI components.
     */
    public HomePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        factoryButton = new ButtonCreator();
        startComponents();
    }

    /**
     * Initializes and adds all UI components to the panel.
     */
    private void startComponents() {
        // Create and configure welcome label
        welcomeLabel = new JLabel("Welcome to the Books System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(60, 60, 60));

        add(welcomeLabel, BorderLayout.NORTH);

        // Create button panel
        buttonPanel = new JPanel(new GridLayout(1, 1, 20, 20));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(60, 100, 60, 100));

        // Create and configure buttons
        manageProductsBtn = factoryButton.createButton("Manage Products", new Color(0, 123, 255), "MANAGE_BOOK", new Dimension(150, 50));


        // Add buttons to panel
        buttonPanel.add(manageProductsBtn);

        add(buttonPanel, BorderLayout.CENTER);
    }

    public FactoryButton getFactoryButton() {
        return factoryButton;
    }

    public void setFactoryButton(FactoryButton factoryButton) {
        this.factoryButton = factoryButton;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public JLabel getWelcomeLabel() {
        return welcomeLabel;
    }

    public void setWelcomeLabel(JLabel welcomeLabel) {
        this.welcomeLabel = welcomeLabel;
    }

    public JButton getManageProductsBtn() {
        return manageProductsBtn;
    }

    public void setManageProductsBtn(JButton manageProductsBtn) {
        this.manageProductsBtn = manageProductsBtn;
    }

}
