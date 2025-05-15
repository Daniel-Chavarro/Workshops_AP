package atmSoftware.controller;

import atmSoftware.model.AutomatedTellerMachine;
import atmSoftware.model.DataInitializer;
import atmSoftware.model.generic.Account;
import atmSoftware.model.generic.Card;
import atmSoftware.model.generic.Client;
import atmSoftware.view.gui.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Controller class that manages interactions between the model and view.
 * Implements ActionListener to handle user actions.
 */
public class Controller implements ActionListener {

    private LoginFrame loginFrame;
    private MainFrame mainFrame;
    private AutomatedTellerMachine atm;
    private Account activeAccount;
    private int activeCardNumber;

    /**
     * Constructor initializes the controller with model and view components
     */
    public Controller() {
        // Initialize ATM
        atm = new AutomatedTellerMachine();

        // Initialize test data
        initializeTestData();

        // Initialize login frame
        loginFrame = new LoginFrame();
        loginFrame.addLoginButtonListener(this);
        loginFrame.addExitButtonListener(e -> System.exit(0));

        // Show login frame
        loginFrame.setVisible(true);
    }

    /**
     * Initializes test data for the ATM
     */
    private void initializeTestData() {
        // Initialize data using DataInitializer
        DataInitializer.initialize(atm);

        // Print login information for testing
        System.out.println("ATM initialized with test data.");
        System.out.println("Use the card numbers and PINs printed above to log in.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // Login frame actions
        if (loginFrame != null && source == loginFrame.loginButton) {
            handleLogin();
        }

        // Main frame actions
        if (mainFrame != null) {
            // Logout button
            if (source == mainFrame.getLogoutButton()) {
                handleLogout();
            }

            // Transaction menu buttons
            TransactionMenuScreen menuScreen = mainFrame.getTransactionMenuScreen();
            if (source == menuScreen.depositNavButton) {
                showDepositScreen();
            } else if (source == menuScreen.withdrawNavButton) {
                showWithdrawScreen();
            } else if (source == menuScreen.balanceNavButton) {
                showBalanceScreen();
            }

            // Deposit screen buttons
            DepositScreen depositScreen = mainFrame.getDepositScreen();
            if (source == depositScreen.confirmDepositButton) {
                handleConfirmDeposit();
            } else if (source == depositScreen.backButton) {
                handleBackToMenu();
            }

            // Withdraw screen buttons
            WithdrawScreen withdrawScreen = mainFrame.getWithdrawScreen();
            if (source == withdrawScreen.confirmWithdrawButton) {
                handleConfirmWithdraw();
            } else if (source == withdrawScreen.backButton) {
                handleBackToMenu();
            }

            // Balance screen buttons
            BalanceScreen balanceScreen = mainFrame.getBalanceScreen();
            if (source == balanceScreen.backButton) {
                handleBackToMenu();
            }
        }
    }

    /**
     * Handles the login button action
     */
    private void handleLogin() {
        try {
            int cardNumber = Integer.parseInt(loginFrame.getCardNumber());
            int pin = Integer.parseInt(loginFrame.getPIN());

            // Check if card is blocked
            if (atm.isCardBlocked(cardNumber)) {
                loginFrame.displayErrorMessage("This card is blocked. Please contact customer service.");
                return;
            }

            // Authenticate user
            if (atm.authenticateUser(cardNumber, pin)) {
                activeCardNumber = cardNumber;
                activeAccount = atm.getCurrentAccount();

                // Close login frame
                loginFrame.close();

                // Initialize and show main frame
                mainFrame = new MainFrame();

                // Add action listeners
                addMainFrameListeners();

                // Display welcome message
                Client client = atm.getCurrentClient();
                mainFrame.displayWelcomeMessage(client.getName() + " " + client.getLastName());

                // Show main frame
                mainFrame.setVisible(true);

                // Set timer to show transaction menu after 2 seconds
                Timer timer = new Timer(2000, e -> showTransactionMenu());
                timer.setRepeats(false);
                timer.start();
            } else {
                int attempts = atm.getFailedAttempts(cardNumber);
                if (attempts >= 3) {
                    loginFrame.displayErrorMessage("Your card has been blocked due to too many failed attempts.");
                } else {
                    loginFrame.displayErrorMessage("Invalid card number or PIN. Attempts remaining: " + (3 - attempts));
                }
            }
        } catch (NumberFormatException e) {
            loginFrame.displayErrorMessage("Please enter valid numeric values for card number and PIN.");
        }
    }

    /**
     * Adds action listeners to the main frame components
     */
    private void addMainFrameListeners() {
        // Main frame
        mainFrame.addLogoutButtonListener(this);

        // Transaction menu screen
        TransactionMenuScreen menuScreen = mainFrame.getTransactionMenuScreen();
        menuScreen.addDepositNavButtonListener(this);
        menuScreen.addWithdrawNavButtonListener(this);
        menuScreen.addBalanceNavButtonListener(this);

        // Deposit screen
        DepositScreen depositScreen = mainFrame.getDepositScreen();
        depositScreen.addConfirmDepositButtonListener(this);
        depositScreen.addBackButtonListener(this);

        // Withdraw screen
        WithdrawScreen withdrawScreen = mainFrame.getWithdrawScreen();
        withdrawScreen.addConfirmWithdrawButtonListener(this);
        withdrawScreen.addBackButtonListener(this);

        // Balance screen
        BalanceScreen balanceScreen = mainFrame.getBalanceScreen();
        balanceScreen.addBackButtonListener(this);

        // Window closing event
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                atm.logout();
            }
        });
    }

    /**
     * Handles the logout button action
     */
    private void handleLogout() {
        atm.logout();
        mainFrame.close();

        // Reset and show login frame
        loginFrame = new LoginFrame();
        loginFrame.addLoginButtonListener(this);
        loginFrame.addExitButtonListener(e -> System.exit(0));
        loginFrame.setVisible(true);
    }

    /**
     * Shows the welcome screen
     */
    private void showWelcomeScreen() {
        mainFrame.showPanel("welcome");
    }

    /**
     * Shows the transaction menu screen
     */
    private void showTransactionMenu() {
        mainFrame.showPanel("menu");
    }

    /**
     * Shows the deposit screen
     */
    private void showDepositScreen() {
        mainFrame.getDepositScreen().clearField();
        mainFrame.showPanel("deposit");
    }

    /**
     * Handles the confirm deposit button action
     */
    private void handleConfirmDeposit() {
        try {
            double amount = Double.parseDouble(mainFrame.getDepositScreen().getAmount());

            if (amount <= 0) {
                mainFrame.getDepositScreen().displayMessage(
                        "Please enter a positive amount.",
                        "Invalid Amount",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (atm.deposit(amount)) {
                mainFrame.getDepositScreen().displayMessage(
                        "Deposit successful. New balance: $" + atm.checkBalance(),
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
                mainFrame.getDepositScreen().clearField();
            } else {
                mainFrame.getDepositScreen().displayMessage(
                        "Deposit failed. Please try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (NumberFormatException e) {
            mainFrame.getDepositScreen().displayMessage(
                    "Please enter a valid numeric amount.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Shows the withdraw screen
     */
    private void showWithdrawScreen() {
        mainFrame.getWithdrawScreen().clearField();
        mainFrame.showPanel("withdraw");
    }

    /**
     * Handles the confirm withdraw button action
     */
    private void handleConfirmWithdraw() {
        try {
            double amount = Double.parseDouble(mainFrame.getWithdrawScreen().getAmount());

            if (amount <= 0) {
                mainFrame.getWithdrawScreen().displayMessage(
                        "Please enter a positive amount.",
                        "Invalid Amount",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (amount > atm.checkBalance()) {
                mainFrame.getWithdrawScreen().displayMessage(
                        "Insufficient funds. Your balance is $" + atm.checkBalance(),
                        "Insufficient Funds",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (amount > atm.getAmountMoney()) {
                mainFrame.getWithdrawScreen().displayMessage(
                        "The ATM does not have enough cash. Please try a smaller amount.",
                        "ATM Cash Limit",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (atm.withdraw(amount)) {
                mainFrame.getWithdrawScreen().displayMessage(
                        "Withdrawal successful. New balance: $" + atm.checkBalance(),
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
                mainFrame.getWithdrawScreen().clearField();
            } else {
                mainFrame.getWithdrawScreen().displayMessage(
                        "Withdrawal failed. Please try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (NumberFormatException e) {
            mainFrame.getWithdrawScreen().displayMessage(
                    "Please enter a valid numeric amount.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Shows the balance screen
     */
    private void showBalanceScreen() {
        mainFrame.getBalanceScreen().setBalance(atm.checkBalance());
        mainFrame.showPanel("balance");
    }

    /**
     * Handles the back to menu button action
     */
    private void handleBackToMenu() {
        showTransactionMenu();
    }

    /**
     * @return The login frame
     */
    public LoginFrame getLoginFrame() {
        return loginFrame;
    }

    /**
     * @return The main frame
     */
    public MainFrame getMainFrame() {
        return mainFrame;
    }

    /**
     * @return The ATM
     */
    public AutomatedTellerMachine getAtm() {
        return atm;
    }

    /**
     * @return The active account
     */
    public Account getActiveAccount() {
        return activeAccount;
    }

    /**
     * @return The active card number
     */
    public int getActiveCardNumber() {
        return activeCardNumber;
    }
}
