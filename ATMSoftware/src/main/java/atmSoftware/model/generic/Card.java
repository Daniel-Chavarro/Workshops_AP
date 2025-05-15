package atmSoftware.model.generic;

/**
 * Represents an ATM card with security features.
 */
public class Card {
    private int cardNumber;
    private int pin;
    private boolean isBlocked;
    private int failedLoginAttempts;
    private static final int MAX_FAILED_ATTEMPTS = 3;

    /**
     * Default constructor
     */
    public Card() {
        this.isBlocked = false;
        this.failedLoginAttempts = 0;
    }

    /**
     * Constructor with card number and PIN
     * 
     * @param cardNumber The card's unique number
     * @param pin The card's PIN (Personal Identification Number)
     */
    public Card(int cardNumber, int pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.isBlocked = false;
        this.failedLoginAttempts = 0;
    }

    /**
     * Verifies if the provided PIN matches the card's PIN
     * 
     * @param pinAttempt The PIN to verify
     * @return true if the PIN is correct, false otherwise
     */
    public boolean verifyPIN(int pinAttempt) {
        if (isBlocked) {
            return false;
        }
        
        return pin == pinAttempt;
    }

    /**
     * Blocks the card, preventing further use
     */
    public void blockCard() {
        this.isBlocked = true;
    }

    /**
     * Increments the failed login attempts counter
     * If the counter reaches the maximum allowed attempts, the card is blocked
     */
    public void incrementFailedAttempts() {
        this.failedLoginAttempts++;
        if (this.failedLoginAttempts >= MAX_FAILED_ATTEMPTS) {
            blockCard();
        }
    }

    /**
     * Resets the failed login attempts counter to zero
     */
    public void resetFailedAttempts() {
        this.failedLoginAttempts = 0;
    }

    /**
     * @return The card's unique number
     */
    public int getCardNumber() {
        return cardNumber;
    }

    /**
     * @param cardNumber The card number to set
     */
    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * @return The card's PIN
     */
    public int getPin() {
        return pin;
    }

    /**
     * @param pin The PIN to set
     */
    public void setPin(int pin) {
        this.pin = pin;
    }

    /**
     * @return true if the card is blocked, false otherwise
     */
    public boolean isBlocked() {
        return isBlocked;
    }

    /**
     * @param blocked The blocked status to set
     */
    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    /**
     * @return The number of failed login attempts
     */
    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    /**
     * @param failedLoginAttempts The number of failed login attempts to set
     */
    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber=" + cardNumber +
                ", isBlocked=" + isBlocked +
                ", failedLoginAttempts=" + failedLoginAttempts +
                '}';
    }
}
