package pages;

import org.apache.log4j.Logger;
import org.testng.Assert;
import utils.ClassNameUtil;
import utils.WebDriverWrapper;
import utils.WebElementsActions;

import java.util.NoSuchElementException;

public abstract class Page {

    private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    public WebDriverWrapper webDriverWrapper;
    public WebElementsActions web;

    private String PAGE;

        public Page(WebDriverWrapper dr, String page) {
            webDriverWrapper = dr;
            PAGE = page;
            web = new WebElementsActions(dr);
        }

        public Page(WebDriverWrapper dr) {
            webDriverWrapper = dr;
            web = new WebElementsActions(dr);
        }


        /*
         * Open Page in a browser
         */
        public boolean openPage(){
            try{
                LOG.info("Start open page.");
                webDriverWrapper.get(PAGE);
                webDriverWrapper.getCurrentUrl();
            } catch (Exception e){
                LOG.error("Error in open page!\n");
                return false;
            }
            LOG.info("Page open successful.");
            return true;
        }

        /*
         * Verification Page open correct. Check on pageLocator
         */
        public boolean isOpenPage(String checkLocator){
                if (web.isElementPresent(checkLocator)) {
                    LOG.info("Page: Check is page open. " + checkLocator + " is present!");
                    LOG.info(ClassNameUtil.getCurrentClassName() + ": Page is open.");
                    return true;
                } else {
                    LOG.error("Page: Error with check page!\n");
                    Assert.fail("Incorrect swatch");
                }
            return false;
        }

        /*
         * Get page title for verification correct switch between pages
         */
        public String getTitle() {
            return webDriverWrapper.getTitle();
        }

        public String getCurrentPageURL() {
            return webDriverWrapper.getCurrentUrl();
        }

        public void deleteAllCookies() {
            webDriverWrapper.manage().deleteAllCookies();
        }


    }


//    private static Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
//
//    static String testUrl = "http://magento-demo.lexiconn.com";
//    static WebElementsActions webElementActions;
//
//    public static void goToBaseUrl(WebDriver driver) {
//        driver.get(testUrl);
//    }

