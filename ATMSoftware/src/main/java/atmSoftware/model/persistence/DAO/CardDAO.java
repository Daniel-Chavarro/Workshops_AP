package atmSoftware.model.persistence.DAO;

import atmSoftware.model.generic.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data Access Object for Card entities.
 * Implements in-memory storage using a Map.
 */
public class CardDAO implements InterfaceDAO<Card> {
    
    private Map<String, Card> cards;
    
    /**
     * Constructor initializes the cards map
     */
    public CardDAO() {
        this.cards = new HashMap<>();
    }
    
    @Override
    public boolean create(Card card) {
        if (card == null || cards.containsKey(String.valueOf(card.getCardNumber()))) {
            return false;
        }
        
        cards.put(String.valueOf(card.getCardNumber()), card);
        return true;
    }
    
    @Override
    public boolean update(String id, Card card) {
        if (id == null || card == null || !cards.containsKey(id)) {
            return false;
        }
        
        cards.put(id, card);
        return true;
    }
    
    @Override
    public Card getByID(String id) {
        return cards.get(id);
    }
    
    @Override
    public boolean delete(String id) {
        if (id == null || !cards.containsKey(id)) {
            return false;
        }
        
        cards.remove(id);
        return true;
    }
    
    @Override
    public List<Card> getAll() {
        return new ArrayList<>(cards.values());
    }
}
