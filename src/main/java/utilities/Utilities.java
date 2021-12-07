package utilities;

import com.google.gson.Gson;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

public class Utilities {
    public static final String configFileName = "config.json";
    public static final String session = "JSESSIONID=node0347w505ah1ko6xovy6o8t6ro0.node0";

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

    public static boolean isDigit(String id) {
        System.out.println("from utility class: " + id);
//        if (id.length() < 2) {
//            return false;
//        } else {
//            id = id.substring(1); // id ->/{id}
//        }
        for (char c : id.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if the html is valid and false otherwise.
     * Taken from Wellformed.java example from
     * http://www.edankert.com/validate.html#Sample_Code
     * @param html
     * @return
     */
    public static boolean isValid(String html) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(false);
            factory.setNamespaceAware(true);
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.parse(new InputSource(new StringReader(html)));
            return true;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
