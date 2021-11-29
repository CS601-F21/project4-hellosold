package utilities;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.assertEquals;


public class ConfigTest {
    private Config config = null;
    private static final String configFilename = "config.json";

    @BeforeEach
    public void startup() {
        Gson gson = new Gson();
        try {
            config = gson.fromJson(new FileReader(configFilename), Config.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Test
    public void getRedirect_uri() {
        String expect = "https://bd15-67-169-155-8.ngrok.io/login";
        String actual = config.getRedirect_uri();
        assertEquals(expect, actual);
    }

    @Test
    public void getClient_id() {
        String expect = "2464212157.2684442583664";
        String actual = config.getClient_id();
        assertEquals(expect, actual);
    }

    @Test
    public void getClient_secret() {
        String expect = "40cfeaeddd4de35ee4a21132b1ff31d6";
        String actual = config.getClient_secret();
        assertEquals(expect, actual);
    }

    @Test
    public void getDatabase() {
        String expect = "user013";
        String actual = config.getDatabase();
        assertEquals(expect, actual);
    }

    @Test
    public void getUsername() {
        String expect = "user013";
        String actual = config.getUsername();
        assertEquals(expect, actual);
    }

    @Test
    void getPassword() {
        String expect = "user013";
        String actual = config.getPassword();
        assertEquals(expect, actual);
    }
}