package utilities;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Utilities {
    public static final String configFileName = "config.json";

    /**
     * Read in the configuration file.
     *
     */
    public static Config readConfig() {
        Config config = null;
        Gson gson = new Gson();
        try {
            config = gson.fromJson(new FileReader(configFileName), Config.class);
            if (config == null) {
                System.exit(1);
            }
        } catch (FileNotFoundException nfn) {
            System.err.println("Config file config.json not found: " + nfn.getMessage());
        }
        return config;
    }
}
