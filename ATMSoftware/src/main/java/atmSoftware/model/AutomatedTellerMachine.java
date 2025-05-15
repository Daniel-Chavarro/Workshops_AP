package atmSoftware.model;

import atmSoftware.model.generic.*;
import atmSoftware.model.persistence.DAO.*;

import java.util.Date;

/**
 * Core class that manages ATM operations.
 * Handles user authentication, transactions, and card security.
 */
public class AutomatedTellerMachine {
    
    private CardDAO cardDAO;
    private ClientDAO clientDAO;
    private AccountDAO accountDAO;
    private TransactionDAO transactionDAO;
    private double amountMoney;
    private Client currentClient;
    private Account currentAccount;
    private Card currentCard;
    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static int nextTransactionId = 1;
    
    /**
     * Constructor initializes the ATM with DAOs and sets initial amount of money
     */
    public AutomatedTellerMachine() {
        this.cardDAO = new CardDAO();
        this.clientDAO = new ClientDAO();
        this.accountDAO = new AccountDAO();
        this.transactionDAO = new TransactionDAO();
        this.amountMoney = 50000000; // 50 million pesos initial balance
    }
    
    /**
     * Authenticates a user by card number and PIN
     * 
     * @param cardNumber The card number
     * @param pin The PIN
     * @return true if authentication is successful, false otherwise
     */
    public boolean authenticateUser(int cardNumber, int pin) {
        Card card = cardDAO.getByID(String.valueOf(cardNumber));
        
        if (card == null || card.isBlocked()) {
            return false;
        }
        
        if (card.verifyPIN(pin)) {
            currentCard = card;
            currentAccount = accountDAO.findByCardID(cardNumber);
            
            if (currentAccount != null) {
                currentClient = clientDAO.getByID(String.valueOf(currentAccount.getClientID()));
                card.resetFailedAttempts();
                cardDAO.update(String.valueOf(cardNumber), card);
                return true;
            }
        } else {
            incrementFailedAttempts(cardNumber);
        }
        
        return false;
    }
    
    /**
     * Deposits money into the current account
     * 
     * @param amount The amount to deposit
     * @return true if deposit is successful, false otherwise
     */
    public boolean deposit(double amount) {
        if (currentAccount == null || amount <= 0) {
            return false;
        }
        
        currentAccount.deposit(amount);
        amountMoney += amount;
        
        // Create transaction record
        Transaction transaction = new Transaction(
                nextTransactionId++,
                TransactionType.DEPOSIT,
                amount,
                new Date(),
                currentAccount.getAccountNumber()
        );
        
        transactionDAO.create(transaction);
        accountDAO.update(String.valueOf(currentAccount.getAccountNumber()), currentAccount);
        
        return true;
    }
    
    /**
     * Withdraws money from the current account
     * 
     * @param amount The amount to withdraw
     * @return true if withdrawal is successful, false otherwise
     */
    public boolean withdraw(double amount) {
        if (currentAccount == null || amount <= 0 || amount > amountMoney) {
            return false;
        }
        
        if (currentAccount.withdraw(amount)) {
            amountMoney -= amount;
            
            // Create transaction record
            Transaction transaction = new Transaction(
                    nextTransactionId++,
                    TransactionType.WITHDRAWAL,
                    amount,
                    new Date(),
                    currentAccount.getAccountNumber()
            );
            
            transactionDAO.create(transaction);
            accountDAO.update(String.valueOf(currentAccount.getAccountNumber()), currentAccount);
            
            return true;
        }
        
        return false;
    }
    
    /**
     * Checks the balance of the current account
     * 
     * @return The current balance
     */
    public double checkBalance() {
        if (currentAccount == null) {
            return -1;
        }
        
        return currentAccount.getBalance();
    }
    
    /**
     * Blocks the current card
     */
    public void blockCard() {
        if (currentCard != null) {
            currentCard.blockCard();
            cardDAO.update(String.valueOf(currentCard.getCardNumber()), currentCard);
        }
    }
    
    /**
     * Logs out the current user
     */
    public void logout() {
        currentClient = null;
        currentAccount = null;
        currentCard = null;
    }
    
    /**
     * Checks if a card is blocked
     * 
     * @param cardNumber The card number to check
     * @return true if the card is blocked, false otherwise
     */
    public boolean isCardBlocked(int cardNumber) {
        Card card = cardDAO.getByID(String.valueOf(cardNumber));
        return card != null && card.isBlocked();
    }
    
    /**
     * Increments the failed attempts counter for a card
     * 
     * @param cardNumber The card number
     */
    public void incrementFailedAttempts(int cardNumber) {
        Card card = cardDAO.getByID(String.valueOf(cardNumber));
        
        if (card != null) {
            card.incrementFailedAttempts();
            cardDAO.update(String.valueOf(cardNumber), card);
        }
    }
    
    /**
     * Resets the failed attempts counter for a card
     * 
     * @param cardNumber The card number
     */
    public void resetFailedAttempts(int cardNumber) {
        Card card = cardDAO.getByID(String.valueOf(cardNumber));
        
        if (card != null) {
            card.resetFailedAttempts();
            cardDAO.update(String.valueOf(cardNumber), card);
        }
    }
    
    /**
     * Gets the number of failed attempts for a card
     * 
     * @param cardNumber The card number
     * @return The number of failed attempts
     */
    public int getFailedAttempts(int cardNumber) {
        Card card = cardDAO.getByID(String.valueOf(cardNumber));
        
        if (card != null) {
            return card.getFailedLoginAttempts();
        }
        
        return 0;
    }
    
    /**
     * @return The amount of money in the ATM
     */
    public double getAmountMoney() {
        return amountMoney;
    }
    
    /**
     * @param amountMoney The amount of money to set in the ATM
     */
    public void setAmountMoney(double amountMoney) {
        this.amountMoney = amountMoney;
    }
    
    /**
     * @return The current client
     */
    public Client getCurrentClient() {
        return currentClient;
    }
    
    /**
     * @param currentClient The current client to set
     */
    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }
    
    /**
     * @return The current account
     */
    public Account getCurrentAccount() {
        return currentAccount;
    }
    
    /**
     * @param currentAccount The current account to set
     */
    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }
    
    /**
     * @return The current card
     */
    public Card getCurrentCard() {
        return currentCard;
    }
    
    /**
     * @param currentCard The current card to set
     */
    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }
    
    /**
     * @return The card DAO
     */
    public CardDAO getCardDAO() {
        return cardDAO;
    }
    
    /**
     * @return The client DAO
     */
    public ClientDAO getClientDAO() {
        return clientDAO;
    }
    
    /**
     * @return The account DAO
     */
    public AccountDAO getAccountDAO() {
        return accountDAO;
    }
    
    /**
     * @return The transaction DAO
     */
    public TransactionDAO getTransactionDAO() {
        return transactionDAO;
    }
}
