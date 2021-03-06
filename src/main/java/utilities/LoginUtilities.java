package utilities;

import com.google.gson.Gson;
import jdbc.DBCPDataSource;
import jdbc.JDBCUtility;
import login.LoginServerConstants;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Map;

/**
 * A utility class with several helper methods.
 */
public class LoginUtilities {

    // for parsing JSON
    private static final Gson gson = new Gson();

    /**
     * Hash the session ID to generate a nonce.
     * Uses Apache Commons Codec
     * See https://www.baeldung.com/sha-256-hashing-java
     * @param sessionId sessionId
     * @return hash of a sessionId
     */
    public static String generateNonce(String sessionId) {
        String sha256hex = DigestUtils.sha256Hex(sessionId);
        return sha256hex;
    }

    /**
     * Generates the URL to make the initial request to the authorize API.
     * @param clientId clientId
     * @param state state
     * @param nonce nonce
     * @param redirectURI redirectURI
     * @return url
     */
    public static String generateSlackAuthorizeURL(String clientId, String state, String nonce, String redirectURI) {

        String url = String.format("https://%s/%s?%s=%s&%s=%s&%s=%s&%s=%s&%s=%s&%s=%s",
                LoginServerConstants.HOST,
                LoginServerConstants.AUTH_PATH,
                LoginServerConstants.RESPONSE_TYPE_KEY,
                LoginServerConstants.RESPONSE_TYPE_VALUE,
                LoginServerConstants.SCOPE_KEY,
                LoginServerConstants.SCOPE_VALUE,
                LoginServerConstants.CLIENT_ID_KEY,
                clientId,
                LoginServerConstants.STATE_KEY,
                state,
                LoginServerConstants.NONCE_KEY,
                nonce,
                LoginServerConstants.REDIRECT_URI_KEY,
                redirectURI
        );
        return url;
    }

    /**
     * Generates the URL to exchange the initial auth for a token.
     * @param clientId clientId
     * @param clientSecret clientSecret
     * @param code code
     * @param redirectURI redirectedURI
     * @return url
     */
    public static String generateSlackTokenURL(String clientId, String clientSecret, String code, String redirectURI) {

        String url = String.format("https://%s/%s?%s=%s&%s=%s&%s=%s&%s=%s",
                LoginServerConstants.HOST,
                LoginServerConstants.TOKEN_PATH,
                LoginServerConstants.CLIENT_ID_KEY,
                clientId,
                LoginServerConstants.CLIENT_SECRET_KEY,
                clientSecret,
                LoginServerConstants.CODE_KEY,
                code,
                LoginServerConstants.REDIRECT_URI_KEY,
                redirectURI
        );
        return url;
    }

    /**
     * Convert a JSON-formatted String to a Map.
     * @param jsonString jsonString
     * @return map
     */
    public static Map<String, Object> jsonStrToMap(String jsonString) {
        Map<String, Object> map = gson.fromJson(new StringReader(jsonString), Map.class);
        return map;
    }

    /**
     * Verify the response from the token API.
     * If successful, returns a ClientInfo object with information about the authed client.
     * Returns null if not successful.
     * @param map map
     * @param sessionId sessionId
     * @return userInfo object
     */
    public static UserInfo verifyTokenResponse(Map<String, Object> map, String sessionId) {
//        for (Map.Entry<String, Object> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " : " + entry.getValue().toString());
//        }

        // verify ok: true
        if(!map.containsKey(LoginServerConstants.OK_KEY) || !(boolean)map.get(LoginServerConstants.OK_KEY)) {
            return null;
        }

        // verify state is the users session cookie id
        if(!map.containsKey(LoginServerConstants.STATE_KEY) || !map.get(LoginServerConstants.STATE_KEY).equals(sessionId)) {
            System.out.println(map.get(LoginServerConstants.STATE_KEY));
            System.out.println("session id is:" + sessionId);
            return null;
        }

        // retrieve and decode id_token
        String idToken = (String)map.get("id_token");
        Map<String, Object> payloadMap = decodeIdTokenPayload(idToken);

        //verify nonce
        String expectedNonce = generateNonce(sessionId);
        String actualNonce = (String) payloadMap.get(LoginServerConstants.NONCE_KEY);
        if(!expectedNonce.equals(actualNonce)) {
            return null;
        }

        // extract name, email from response
        String name = (String) payloadMap.get(LoginServerConstants.NAME_KEY);
        String email = (String) payloadMap.get(LoginServerConstants.EMAIL_KEY);

        // check if user is already in the users tale
        UserInfo user = null;
        int userId = 0;
        try {
            Connection con = DBCPDataSource.getConnection();
            // if user is not in the users table, store user into the table
            user = JDBCUtility.executeSelectUser(con, email);
            if (user.getId() == 0) {
                userId = JDBCUtility.executeAddNewUser(con, name, email);
                return new UserInfo(userId, name, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     *
     * From the Slack documentation:
     * id_token is a standard JSON Web Token (JWT). You can decode it with off-the-shelf libraries in any programming
     * language, and most packages that handle OpenID will handle JWT decoding.
     *
     * Method decodes the String id_token and returns a Map with the contents of the payload.
     *
     * @param idToken idtoken
     * @return map
     */
    private static Map<String, Object> decodeIdTokenPayload(String idToken) {
        // Decoding process taken from:
        // https://www.baeldung.com/java-jwt-token-decode
        String[] chunks = idToken.split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        // convert the id_token payload to a map
        Map<String, Object> payloadMap = gson.fromJson(new StringReader(payload), Map.class);

        return payloadMap;
    }

}
