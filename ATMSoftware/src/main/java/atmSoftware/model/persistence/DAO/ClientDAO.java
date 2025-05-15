package atmSoftware.model.persistence.DAO;

import atmSoftware.model.generic.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data Access Object for Client entities.
 * Implements in-memory storage using a Map.
 */
public class ClientDAO implements InterfaceDAO<Client> {
    
    private Map<String, Client> clients;
    
    /**
     * Constructor initializes the clients map
     */
    public ClientDAO() {
        this.clients = new HashMap<>();
    }
    
    @Override
    public boolean create(Client client) {
        if (client == null || clients.containsKey(String.valueOf(client.getId()))) {
            return false;
        }
        
        clients.put(String.valueOf(client.getId()), client);
        return true;
    }
    
    @Override
    public boolean update(String id, Client client) {
        if (id == null || client == null || !clients.containsKey(id)) {
            return false;
        }
        
        clients.put(id, client);
        return true;
    }
    
    @Override
    public Client getByID(String id) {
        return clients.get(id);
    }
    
    @Override
    public boolean delete(String id) {
        if (id == null || !clients.containsKey(id)) {
            return false;
        }
        
        clients.remove(id);
        return true;
    }
    
    @Override
    public List<Client> getAll() {
        return new ArrayList<>(clients.values());
    }
}
