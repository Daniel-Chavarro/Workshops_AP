package atmSoftware.model.persistence.DAO;

import java.util.List;

/**
 * Generic interface for Data Access Objects.
 * Provides CRUD operations for entities of type T.
 *
 * @param <T> The type of entity this DAO handles
 */
public interface InterfaceDAO<T> {
    
    /**
     * Creates a new entity in the data store
     * 
     * @param object The entity to create
     * @return true if creation was successful, false otherwise
     */
    boolean create(T object);
    
    /**
     * Updates an existing entity in the data store
     * 
     * @param id The ID of the entity to update
     * @param object The updated entity
     * @return true if update was successful, false otherwise
     */
    boolean update(String id, T object);
    
    /**
     * Retrieves an entity by its ID
     * 
     * @param ID The ID of the entity to retrieve
     * @return The entity if found, null otherwise
     */
    T getByID(String ID);
    
    /**
     * Deletes an entity from the data store
     * 
     * @param id The ID of the entity to delete
     * @return true if deletion was successful, false otherwise
     */
    boolean delete(String id);
    
    /**
     * Retrieves all entities from the data store
     * 
     * @return A list of all entities
     */
    List<T> getAll();
}
