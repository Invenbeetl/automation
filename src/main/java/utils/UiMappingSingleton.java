package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class UiMappingSingleton {
    private volatile static UiMappingSingleton classInstance;
    private static Properties properties = new Properties();
    private volatile static Map<String, String> uiMapping;

    private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    private UiMappingSingleton() {
        uiMapping = new HashMap<>();
        long start = System.nanoTime();
        loadDataFromUIMappingFile();
        for (final Map.Entry<Object, Object> entry : properties.entrySet()) {
            uiMapping.put((String) entry.getKey(), (String) entry.getValue());
        }
        LOG.info("\t** UIMappingSingleton creation time - " + (System.nanoTime() - start) + "nanoseconds **");
    }

    public static synchronized UiMappingSingleton getInstance() {
        if (classInstance == null) {
            LOG.info("\t** UIMappingSingleton getInstance() class A **");
            classInstance = new UiMappingSingleton();
            LOG.info("\t** Object - " + classInstance + " **");
            return classInstance;
        }
        LOG.info("\t** UIMappingSingleton getInstance() class B **");
        LOG.info("\t** Object - " + classInstance + " **");
        return classInstance;
    }

    /**
     *  This method Load locators data from uiMapping.properties which
     *  path is specified in application.properties with key: #uiMappingFile.
     *  Return value from .properties file
     *
     *  @return properties
     */
    public static Properties loadDataFromUIMappingFile() {
        File file = new File(PropertyLoader.loadProperty("uiMapping"));

        if (file.exists()) {
            try {
                properties.load(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            } catch (IOException e) {
                Assert.fail("Can not find UIMappingFile in class path!");
            }
        }

        return properties;
    }

    //TODO Maven
//    private static String getValueFromFile(String key, String fileName) throws IOException {
//        Properties p = new Properties();
//        try {
//            p.load(new FileInputStream(fileName));
//        } catch (IOException e) {
//            throw new IllegalStateException("Incorrect file path: " + e.getMessage());
//        }
//        return (p.getProperty(key));
//    }


    public static By ui(String key) {
        String[] partsOfLocators = uiMapping.get(key).split("\"");
        String findMethod = partsOfLocators[0];
        String locatorValue = partsOfLocators[1];

        // Return By class with appropriate method and locatorValue
        switch (findMethod) {
            case "id":
                return By.id(locatorValue);
            case "xpath":
                return By.xpath(locatorValue);
            case "css":
                return By.cssSelector(locatorValue);
            case "name":
                return By.name(locatorValue);
            default:
                return By.xpath(locatorValue);
        }
    }

    public static String uiStr(String key) {
        String[] partsOfLocators = uiMapping.get(key).split("\"");
        String locatorValue = partsOfLocators[1];

        return locatorValue;
    }
}
