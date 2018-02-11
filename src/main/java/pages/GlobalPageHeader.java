package pages;

import org.apache.log4j.Logger;
import utils.ClassNameUtil;
import utils.WebDriverWrapper;

public abstract class GlobalPageHeader extends Page{
    
    private static Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    public GlobalPageHeader(WebDriverWrapper dr) {
        super(dr);
    }

    public GlobalPageHeader(WebDriverWrapper dr, String page) {
        super(dr, page);
    }
    
    // private WebElementsActions webElementActions;

    //public GlobalPageHeader(WebDriver driver) {
        //webElementActions = new WebElementsActions(driver);
   // }

    public void clickAccountButton() {
        web.clickButton("globalmenu.page.account.button");
    }

    public void clickRegLink() {
        web.clickLink("globalmenu.page.reg.link");
    }

    public void clickLogInLink() {
        web.clickLink("globalmenu.page.login.link");
    }

    public void clickMenuCategotyLink() {
        web.clickLink("globalmenu.page.menuheaderlink.link");
    }

}
