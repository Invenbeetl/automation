package pages;

import org.apache.log4j.Logger;
import utils.ClassNameUtil;
import utils.WebDriverWrapper;

public class HomePage extends GlobalPageHeader {

    private static final String HOME_PAGE_URL = "http://magento-demo.lexiconn.com";
    private static Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    public HomePage(WebDriverWrapper dr) {
        super(dr, HOME_PAGE_URL);
    }

    public void logIn() {
        clickAccountButton();
        clickLogInLink();
    }

    public void register() {
        clickAccountButton();
        clickRegLink();
    }
}
