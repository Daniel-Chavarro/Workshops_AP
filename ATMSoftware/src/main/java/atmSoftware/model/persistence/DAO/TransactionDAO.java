package atmSoftware.model.persistence.DAO;

import atmSoftware.model.generic.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Data Access Object for Transaction entities.
 * Implements in-memory storage using a Map.
 */
public class TransactionDAO implements InterfaceDAO<Transaction> {
    
    private Map<String, Transaction> transactions;
    
    /**
     * Constructor initializes the transactions map
     */
    public TransactionDAO() {
        this.transactions = new HashMap<>();
    }
    
    @Override
    public boolean create(Transaction transaction) {
        if (transaction == null || transactions.containsKey(String.valueOf(transaction.getTransactionID()))) {
            return false;
        }
        
        transactions.put(String.valueOf(transaction.getTransactionID()), transaction);
        return true;
    }
    
    @Override
    public boolean update(String id, Transaction transaction) {
        if (id == null || transaction == null || !transactions.containsKey(id)) {
            return false;
        }
        
        transactions.put(id, transaction);
        return true;
    }
    
    @Override
    public Transaction getByID(String id) {
        return transactions.get(id);
    }
    
    @Override
    public boolean delete(String id) {
        if (id == null || !transactions.containsKey(id)) {
            return false;
        }
        
        transactions.remove(id);
        return true;
    }
    
    @Override
    public List<Transaction> getAll() {
        return new ArrayList<>(transactions.values());
    }
    
    /**
     * Finds all transactions for a specific account
     * 
     * @param accountID The ID of the account
     * @return A list of transactions for the specified account
     */
    public List<Transaction> findByAccountID(String accountID) {
        return transactions.values().stream()
                .filter(transaction -> String.valueOf(transaction.getAccountID()).equals(accountID))
                .collect(Collectors.toList());
    }
}
