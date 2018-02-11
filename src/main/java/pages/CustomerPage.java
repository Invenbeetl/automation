package pages;

import org.apache.log4j.Logger;
import utils.ClassNameUtil;
import utils.WebDriverWrapper;

public class CustomerPage extends GlobalPageHeader{

    private static final String PAGE = "...";
    private static Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    public CustomerPage(WebDriverWrapper dr) {
        super(dr, PAGE);
    }

    public boolean isRegistrationSuccessful() {
        if (web.isElementPresent("customer.page.sucessfulreg.text")) {
            return true;
        }
        return false;
    }

    public boolean isLoginSuccessful() {
        if (web.isElementPresent("customer.page.sucessfullogin.text")) {
            return true;
        }
        return false;
    }
}
