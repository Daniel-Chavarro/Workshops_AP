package atmSoftware.view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Main frame that contains all screens and navigation.
 */
public class MainFrame extends JFrame {

    private JPanel currentPanelContainer;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton balanceButton;
    private JButton logoutButton;
    private CardLayout cardLayout;
    private WelcomeScreen welcomeScreen;
    private TransactionMenuScreen transactionMenuScreen;
    private DepositScreen depositScreen;
    private WithdrawScreen withdrawScreen;
    private BalanceScreen balanceScreen;

    /**
     * Constructor initializes the main frame with all screens and navigation
     */
    public MainFrame() {
        setTitle("ATM System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create main panel with border layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create navigation panel
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        logoutButton = new JButton("Logout");
        navPanel.add(logoutButton);

        mainPanel.add(navPanel, BorderLayout.NORTH);

        // Create card layout for screens
        cardLayout = new CardLayout();
        currentPanelContainer = new JPanel(cardLayout);

        // Initialize screens
        welcomeScreen = new WelcomeScreen("User"); // Will be updated with actual name
        transactionMenuScreen = new TransactionMenuScreen();
        depositScreen = new DepositScreen();
        withdrawScreen = new WithdrawScreen();
        balanceScreen = new BalanceScreen();

        // Add screens to card layout
        currentPanelContainer.add(welcomeScreen, "welcome");
        currentPanelContainer.add(transactionMenuScreen, "menu");
        currentPanelContainer.add(depositScreen, "deposit");
        currentPanelContainer.add(withdrawScreen, "withdraw");
        currentPanelContainer.add(balanceScreen, "balance");

        mainPanel.add(currentPanelContainer, BorderLayout.CENTER);

        add(mainPanel);
    }

    /**
     * Shows a specific panel by name
     *
     * @param panelName The name of the panel to show
     */
    public void showPanel(String panelName) {
        cardLayout.show(currentPanelContainer, panelName);
    }

    /**
     * Displays a welcome message with the client's name
     *
     * @param clientName The name of the client
     */
    public void displayWelcomeMessage(String clientName) {
        welcomeScreen = new WelcomeScreen(clientName);
        currentPanelContainer.remove(0); // Remove old welcome screen
        currentPanelContainer.add(welcomeScreen, "welcome", 0);
        showPanel("welcome");
    }

    /**
     * Closes the main frame
     */
    public void close() {
        dispose();
    }

    /**
     * @return The transaction menu screen
     */
    public TransactionMenuScreen getTransactionMenuScreen() {
        return transactionMenuScreen;
    }

    /**
     * @return The deposit screen
     */
    public DepositScreen getDepositScreen() {
        return depositScreen;
    }

    /**
     * @return The withdraw screen
     */
    public WithdrawScreen getWithdrawScreen() {
        return withdrawScreen;
    }

    /**
     * @return The balance screen
     */
    public BalanceScreen getBalanceScreen() {
        return balanceScreen;
    }

    /**
     * Adds an action listener to the logout button
     *
     * @param listener The action listener to add
     */
    public void addLogoutButtonListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

    /**
     * @return The logout button
     */
    public JButton getLogoutButton() {
        return logoutButton;
    }
}
