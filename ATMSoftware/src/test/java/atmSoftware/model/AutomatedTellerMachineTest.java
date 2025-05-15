package atmSoftware.model;

import atmSoftware.model.generic.Account;
import atmSoftware.model.generic.Card;
import atmSoftware.model.generic.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the AutomatedTellerMachine class.
 */
public class AutomatedTellerMachineTest {
    
    private AutomatedTellerMachine atm;
    private static final int TEST_CARD_NUMBER = 123456;
    private static final int TEST_PIN = 1234;
    private static final int TEST_ACCOUNT_NUMBER = 1001;
    private static final int TEST_CLIENT_ID = 1;
    private static final double INITIAL_BALANCE = 5000000;
    
    @BeforeEach
    void setUp() {
        atm = new AutomatedTellerMachine();
        
        // Create test data
        Client client = new Client(TEST_CLIENT_ID, "Test", "User");
        Card card = new Card(TEST_CARD_NUMBER, TEST_PIN);
        Account account = new Account(TEST_ACCOUNT_NUMBER, INITIAL_BALANCE, TEST_CLIENT_ID, TEST_CARD_NUMBER);
        
        // Add to DAOs
        atm.getClientDAO().create(client);
        atm.getCardDAO().create(card);
        atm.getAccountDAO().create(account);
    }
    
    @Test
    void testAuthenticateUser_Success() {
        // When
        boolean result = atm.authenticateUser(TEST_CARD_NUMBER, TEST_PIN);
        
        // Then
        assertTrue(result);
        assertNotNull(atm.getCurrentClient());
        assertNotNull(atm.getCurrentAccount());
        assertNotNull(atm.getCurrentCard());
        assertEquals(TEST_CLIENT_ID, atm.getCurrentClient().getId());
        assertEquals(TEST_ACCOUNT_NUMBER, atm.getCurrentAccount().getAccountNumber());
        assertEquals(TEST_CARD_NUMBER, atm.getCurrentCard().getCardNumber());
    }
    
    @Test
    void testAuthenticateUser_WrongPin() {
        // When
        boolean result = atm.authenticateUser(TEST_CARD_NUMBER, 9999);
        
        // Then
        assertFalse(result);
        assertNull(atm.getCurrentClient());
        assertNull(atm.getCurrentAccount());
        assertNull(atm.getCurrentCard());
        assertEquals(1, atm.getFailedAttempts(TEST_CARD_NUMBER));
    }
    
    @Test
    void testAuthenticateUser_CardBlocked() {
        // Given
        atm.incrementFailedAttempts(TEST_CARD_NUMBER);
        atm.incrementFailedAttempts(TEST_CARD_NUMBER);
        atm.incrementFailedAttempts(TEST_CARD_NUMBER);
        
        // When
        boolean result = atm.authenticateUser(TEST_CARD_NUMBER, TEST_PIN);
        
        // Then
        assertFalse(result);
        assertTrue(atm.isCardBlocked(TEST_CARD_NUMBER));
    }
    
    @Test
    void testDeposit_Success() {
        // Given
        atm.authenticateUser(TEST_CARD_NUMBER, TEST_PIN);
        double initialBalance = atm.checkBalance();
        double depositAmount = 100000;
        
        // When
        boolean result = atm.deposit(depositAmount);
        
        // Then
        assertTrue(result);
        assertEquals(initialBalance + depositAmount, atm.checkBalance());
    }
    
    @Test
    void testWithdraw_Success() {
        // Given
        atm.authenticateUser(TEST_CARD_NUMBER, TEST_PIN);
        double initialBalance = atm.checkBalance();
        double withdrawAmount = 100000;
        
        // When
        boolean result = atm.withdraw(withdrawAmount);
        
        // Then
        assertTrue(result);
        assertEquals(initialBalance - withdrawAmount, atm.checkBalance());
    }
    
    @Test
    void testWithdraw_InsufficientFunds() {
        // Given
        atm.authenticateUser(TEST_CARD_NUMBER, TEST_PIN);
        double withdrawAmount = INITIAL_BALANCE + 1;
        
        // When
        boolean result = atm.withdraw(withdrawAmount);
        
        // Then
        assertFalse(result);
        assertEquals(INITIAL_BALANCE, atm.checkBalance());
    }
    
    @Test
    void testLogout() {
        // Given
        atm.authenticateUser(TEST_CARD_NUMBER, TEST_PIN);
        
        // When
        atm.logout();
        
        // Then
        assertNull(atm.getCurrentClient());
        assertNull(atm.getCurrentAccount());
        assertNull(atm.getCurrentCard());
    }
}
