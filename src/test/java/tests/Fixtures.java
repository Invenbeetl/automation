package tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.Demo;
import utils.UiMappingSingleton;
import utils.WebDriverFactory;
import utils.WebDriverWrapper;

import java.util.concurrent.TimeUnit;

public class Fixtures {

    public static WebDriverWrapper driver;
    public static Demo demo;

    @BeforeSuite
    public static void setUp() {
        driver = WebDriverFactory.initDriver();
        demo = new Demo(driver);
        UiMappingSingleton.getInstance();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void deleteCookiesAndGoToTestPage() {
        driver.manage().deleteAllCookies();
    }

    @AfterSuite
    public static void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }
}
