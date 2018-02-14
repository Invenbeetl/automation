package tests;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.Demo;
import utils.ClassNameUtil;
import utils.UiMappingSingleton;
import utils.WebDriverFactory;
import utils.WebDriverWrapper;

import java.util.concurrent.TimeUnit;

public class Fixtures {

    private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
    public static WebDriverWrapper driver;
    public static Demo demo;

    @BeforeTest
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

    @AfterMethod
    public void afterMethodTearDown(ITestResult testResult){
        if(!testResult.isSuccess()){
            LOG.error("<<<------- Test " + testResult.getMethod().getMethodName() +
                    " failed!-------->>>");
            demo.screenShotMaker.takeScreenShot(testResult.getMethod().getMethodName());
        }

    }

    @AfterTest
    public static void tearDown() {

        if (driver != null) {
            driver.close();
        }
    }
}
