package utils;

import org.apache.log4j.Logger;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
    private static final String PROPERTY_FILE = "src\\main\\resources\\Application.properties";

    public static String loadProperty(String name) {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(PROPERTY_FILE));
        } catch (IOException e) {
            Assert.fail("Incorrect property name - " + name);
        }
        String value = "";
        if (name != null) {
            value = props.getProperty(name);
        }
        return value;
    }

    public static void setProperty(String name, String value) {
        Properties props = new Properties();

        props.setProperty(name, value);

        if (name == null || value == null) {
            Assert.fail("Set up Incorrect property name - " + name
                    + "\n or property value - " + value);
        }
    }

}
