import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.Config;
import utilities.LoginUtilities;

import java.io.FileNotFoundException;
import java.io.FileReader;


import static org.junit.Assert.assertEquals;

public class LoginUtilityTest {

    private static Config config;
    private static final String configFilename = "config.json";

    @BeforeEach
    public void startUp() {
        Gson gson = new Gson();
        try {
            config = gson.fromJson(new FileReader(configFilename), Config.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Test
    public void testSlackTokenURL() {
        String code = "2464212157.2799936774416.061dfa3b7b21c3aa444c006351777aaf7887f38c8410ce2dc53bbe8ea51d3ac0";
        String url = LoginUtilities.generateSlackTokenURL(config.getClient_id(), config.getClient_secret(),
               code, config.getRedirect_uri());
        String expect = "https://slack.com/api/openid.connect.token?" +
                "client_id=2464212157.2684442583664&" +
                "client_secret=40cfeaeddd4de35ee4a21132b1ff31d6&" +
                "code=2464212157.2799936774416.061dfa3b7b21c3aa444c006351777aaf7887f38c8410ce2dc53bbe8ea51d3ac0&" +
                "redirect_uri=https://61ec-67-169-155-8.ngrok.io/login";
        assertEquals(expect, url);
    }

    @Test
    public void testSlackAuthorizationURL() {
        String session = "node01ejj2bdd2ttxg1kpshw6gnhqmt0";
        String nonce = LoginUtilities.generateNonce(session);
        String url = LoginUtilities.generateSlackAuthorizeURL(config.getClient_id(), session, nonce,
                config.getRedirect_uri());
        String expect = "https://slack.com/openid/connect/authorize?" +
                "response_type=code&" +
                "scope=openid%20profile%20email&" +
                "client_id=2464212157.2684442583664&" +
                "state=node01ejj2bdd2ttxg1kpshw6gnhqmt0&" +
                "nonce=789c1d9b0047ea79265a0ee3a27d4e9102a490e1e3a6496cc815c59f26d9ebbd&" +
                "redirect_uri=https://61ec-67-169-155-8.ngrok.io/login";
        assertEquals(expect, url);
    }

}
