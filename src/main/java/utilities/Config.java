package utilities;

/**
 * A class parse necessary configuration info.
 *
 * @author Li Liu
 */
public class Config {
    // redirect_uri, client_id, client_secret are consistent with the naming
    // convention in the slack API
    // https://api.slack.com/authentication/sign-in-with-slack
    private String redirect_uri;
    private String client_id;
    private String client_secret;

    // database, username, and password are used for connect JDBC
    private String database;
    private String username;
    private String password;

    /**
     * Constructor.
     * @param redirect_uri redirect_uri
     * @param client_id client_id
     * @param client_secret client_secret
     * @param database database
     * @param username username
     * @param password password
     */
    public Config(String redirect_uri, String client_id, String client_secret,
                  String database, String username, String password) {
        this.redirect_uri = redirect_uri;
        this.client_id = client_id;
        this.client_secret = client_secret;

        this.database = database;
        this.username = username;
        this.password = password;
    }

    /**
     * Getter of redirect_uri.
     * @return redirect_uri
     */
    public String getRedirect_uri() {
        return redirect_uri;
    }

    /**
     * Getter of client_id.
     * @return client_id
     */
    public String getClient_id() {
        return client_id;
    }

    /**
     * Getter of client_secret.
     * @return client_secret
     */
    public String getClient_secret() {
        return client_secret;
    }

    /**
     * Getter of database.
     * @return database
     */
    public String getDatabase() {
        return database;
    }

    /**
     * Getter of username.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter of password.
     * @return password
     */
    public String getPassword() {
        return password;
    }
}
