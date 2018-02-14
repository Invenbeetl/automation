package pages;

import org.apache.log4j.Logger;
import utils.ClassNameUtil;
import utils.PropertyLoader;
import utils.WebDriverWrapper;

public class LoginPage extends GlobalPageHeader {

    private static final String LOGIN_PAGE_URL = "http://magento-demo.lexiconn.com/customer/account/login/";
    private static Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    public LoginPage(WebDriverWrapper dr) {
        super(dr, LOGIN_PAGE_URL);
    }


    public void enterLoginEmailData(String login) {
        web.input("login.page.username.inputfield", login);
    }

    public void enterPasswordData(String password) {
        web.input("login.page.password.inputfield", password);
    }

    public void enterValidCredentials() {
        enterLoginEmailData(PropertyLoader.loadProperty("credentials.valid.login"));
        enterPasswordData(PropertyLoader.loadProperty("credentials.valid.password"));
    }

    public void enterInvalidCredentials() {
        enterLoginEmailData(PropertyLoader.loadProperty("credentials.invalid.login"));
        enterPasswordData(PropertyLoader.loadProperty("credentials.invalid.password"));
    }


    public void clickLoginButton() {
        web.clickButton("login.page.signin.button");
    }

    public void showErrorMessageText() {
        System.out.println(web.getElementText("login.page.error.text"));
    }

    public void pressSpaceInTheLoginData() {
        web.pressSpaceKey("login.page.username.inputfield");
    }

    public void pressSpaceInThePasswordData() {
        web.pressSpaceKey("login.page.password.inputfield");
    }

    public void pressTabInThePasswordData() {
        web.pressTABkey("login.page.password.inputfield");
    }

    public void pressTabInTheLoginData() {
        web.pressTABkey("login.page.username.inputfield");
    }

    public boolean isShowLoginErrorMessage() {
        return web.isElementPresent("login.page.error.message");
    }

    public boolean isAdviceReqEmailMessage() {
        if (web.isElementPresent("login.page.advicereqemail.text")) {
            System.out.println(web.getElementText("login.page.advicereqemail.text"));
            return true;
        }
        return false;
    }

    public boolean isAdviceReqPasswordMessage() {
        if (web.isElementPresent("login.page.advicereqpass.text")) {
            System.out.println(web.getElementText("login.page.advicereqpass.text"));
            return true;
        }
        return false;
    }

}
