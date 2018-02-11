package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utils.ClassNameUtil;
import utils.WebDriverWrapper;
import utils.WebElementsActions;

public class RegPage extends GlobalPageHeader {

    private static Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    public RegPage(WebDriverWrapper dr) {
        super(dr);
    }

    public void enterName(String name) {
        web.input("reg.page.name.inputfield", name);
    }

    public void lastName(String surname) {
        web.input("reg.page.lastname.inputfield", surname);
    }

    public void enterEmail(String email) {
        web.input("reg.page.email.inputfield", email);
    }

    public void enterPassword(String password) {
        web.input("reg.page.password.inputfield", password);
    }

    public void enterPasswordConfirm(String passwordConfirm) {
        web.input("reg.page.confirmpassword.inputfield", passwordConfirm);
    }

    public void clickRegButton() {
        web.submitButton("reg.page.register.button");
    }


}
