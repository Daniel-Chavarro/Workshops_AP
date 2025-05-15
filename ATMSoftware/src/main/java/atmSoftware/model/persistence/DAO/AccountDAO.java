package atmSoftware.model.persistence.DAO;

import atmSoftware.model.generic.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data Access Object for Account entities.
 * Implements in-memory storage using a Map.
 */
public class AccountDAO implements InterfaceDAO<Account> {
    
    private Map<String, Account> accounts;
    
    /**
     * Constructor initializes the accounts map
     */
    public AccountDAO() {
        this.accounts = new HashMap<>();
    }
    
    @Override
    public boolean create(Account account) {
        if (account == null || accounts.containsKey(String.valueOf(account.getAccountNumber()))) {
            return false;
        }
        
        accounts.put(String.valueOf(account.getAccountNumber()), account);
        return true;
    }
    
    @Override
    public boolean update(String id, Account account) {
        if (id == null || account == null || !accounts.containsKey(id)) {
            return false;
        }
        
        accounts.put(id, account);
        return true;
    }
    
    @Override
    public Account getByID(String id) {
        return accounts.get(id);
    }
    
    @Override
    public boolean delete(String id) {
        if (id == null || !accounts.containsKey(id)) {
            return false;
        }
        
        accounts.remove(id);
        return true;
    }
    
    @Override
    public List<Account> getAll() {
        return new ArrayList<>(accounts.values());
    }
    
    /**
     * Finds an account by its associated card ID
     * 
     * @param cardID The ID of the card
     * @return The account associated with the card, or null if not found
     */
    public Account findByCardID(int cardID) {
        for (Account account : accounts.values()) {
            if (account.getCardID() == cardID) {
                return account;
            }
        }
        return null;
    }
}
