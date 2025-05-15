package atmSoftware.model.generic;

import java.util.Date;

/**
 * Represents a transaction performed on an account.
 */
public class Transaction {
    private int transactionID;
    private TransactionType type;
    private double amount;
    private Date timestamp;
    private int accountID;

    /**
     * Default constructor
     */
    public Transaction() {
    }

    /**
     * Constructor with all fields
     * 
     * @param transactionID The transaction's unique identifier
     * @param type The type of transaction (DEPOSIT or WITHDRAWAL)
     * @param amount The amount of money involved in the transaction
     * @param timestamp The date and time when the transaction occurred
     * @param accountID The ID of the account involved in the transaction
     */
    public Transaction(int transactionID, TransactionType type, double amount, Date timestamp, int accountID) {
        this.transactionID = transactionID;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
        this.accountID = accountID;
    }

    /**
     * @return The transaction's unique identifier
     */
    public int getTransactionID() {
        return transactionID;
    }

    /**
     * @param transactionID The transaction ID to set
     */
    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    /**
     * @return The type of transaction
     */
    public TransactionType getType() {
        return type;
    }

    /**
     * @param type The transaction type to set
     */
    public void setType(TransactionType type) {
        this.type = type;
    }

    /**
     * @return The amount of money involved in the transaction
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount The transaction amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return The date and time when the transaction occurred
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp The transaction timestamp to set
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return The ID of the account involved in the transaction
     */
    public int getAccountID() {
        return accountID;
    }

    /**
     * @param accountID The account ID to set
     */
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", type=" + type +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", accountID=" + accountID +
                '}';
    }
}
