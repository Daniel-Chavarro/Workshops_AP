package atmSoftware.model.generic;

/**
 * Represents a bank client with personal information.
 */
public class Client {
    private int id;
    private String name;
    private String lastName;

    /**
     * Default constructor
     */
    public Client() {
    }

    /**
     * Constructor with all fields
     * 
     * @param id Client's unique identifier
     * @param name Client's first name
     * @param lastName Client's last name
     */
    public Client(int id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    /**
     * @return Client's unique identifier
     */
    public int getId() {
        return id;
    }

    /**
     * @param id Client's unique identifier to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Client's first name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Client's first name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Client's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName Client's last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
