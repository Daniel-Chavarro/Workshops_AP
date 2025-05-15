package atmSoftware.model.generic;

/**
 * Represents a bank account with balance and operations.
 */
public class Account {
    private int accountNumber;
    private double balance;
    private int clientID;
    private int cardID;

    /**
     * Default constructor
     */
    public Account() {
    }

    /**
     * Constructor with all fields
     * 
     * @param accountNumber The account's unique number
     * @param initialBalance The initial balance of the account
     * @param clientID The ID of the client who owns this account
     * @param cardID The ID of the card associated with this account
     */
    public Account(int accountNumber, double initialBalance, int clientID, int cardID) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.clientID = clientID;
        this.cardID = cardID;
    }

    /**
     * Deposits money into the account
     * 
     * @param amount The amount to deposit
     */
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    /**
     * Withdraws money from the account if sufficient funds are available
     * 
     * @param amount The amount to withdraw
     * @return true if the withdrawal was successful, false otherwise
     */
    public boolean withdraw(double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    /**
     * @return The account's unique number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber The account number to set
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return The current balance of the account
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance The balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @return The ID of the client who owns this account
     */
    public int getClientID() {
        return clientID;
    }

    /**
     * @param clientID The client ID to set
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    /**
     * @return The ID of the card associated with this account
     */
    public int getCardID() {
        return cardID;
    }

    /**
     * @param cardID The card ID to set
     */
    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", clientID=" + clientID +
                ", cardID=" + cardID +
                '}';
    }
}
