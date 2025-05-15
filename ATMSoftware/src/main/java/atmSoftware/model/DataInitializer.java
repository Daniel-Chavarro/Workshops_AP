package atmSoftware.model;

import atmSoftware.model.generic.Account;
import atmSoftware.model.generic.Card;
import atmSoftware.model.generic.Client;

import java.util.Random;

/**
 * Utility class to initialize test data for the ATM system.
 */
public class DataInitializer {
    
    private static final Random random = new Random();
    
    /**
     * Initializes the ATM with test data
     * 
     * @param atm The ATM to initialize
     */
    public static void initialize(AutomatedTellerMachine atm) {
        // Set ATM balance to 50 million pesos
        atm.setAmountMoney(50000000);
        
        // Create clients
        createClients(atm, 10);
    }
    
    /**
     * Creates a specified number of clients with accounts and cards
     * 
     * @param atm The ATM to add clients to
     * @param count The number of clients to create
     */
    private static void createClients(AutomatedTellerMachine atm, int count) {
        String[] firstNames = {"John", "Jane", "Michael", "Emily", "David", "Sarah", "Robert", "Maria", "James", "Linda"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};
        
        for (int i = 0; i < count; i++) {
            // Create client
            int clientId = i + 1;
            String firstName = firstNames[i % firstNames.length];
            String lastName = lastNames[i % lastNames.length];
            Client client = new Client(clientId, firstName, lastName);
            
            // Create card
            int cardNumber = 100000 + i;
            int pin = 1000 + random.nextInt(9000); // 4-digit PIN
            Card card = new Card(cardNumber, pin);
            
            // Create account with balance between 100,000 and 10 million pesos
            int accountNumber = 1000 + i;
            double balance = 100000 + random.nextDouble() * 9900000;
            Account account = new Account(accountNumber, balance, clientId, cardNumber);
            
            // Add to DAOs
            atm.getClientDAO().create(client);
            atm.getCardDAO().create(card);
            atm.getAccountDAO().create(account);
            
            System.out.println("Created client: " + client.getName() + " " + client.getLastName());
            System.out.println("Card Number: " + cardNumber + ", PIN: " + pin);
            System.out.println("Account Balance: $" + balance);
            System.out.println();
        }
    }
}
