package utilities;

/**
 * A class to maintain info about each client.
 *
 * @author Li Liu
 */
public class UserInfo {

    private int id;
    private String name;
    private String email;

    /**
     * Constructor.
     *
     * @param id name
     * @param name name
     * @param email email
     */
    public UserInfo(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Getter of name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter if email.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter of id.
     * @return id
     */
    public int getId() {
        return id;
    }
}
