package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utils.ClassNameUtil;
import utils.WebDriverWrapper;
import utils.WebElementsActions;

public class ProductCategoryPage extends GlobalPageHeader {

    private static Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    public ProductCategoryPage(WebDriverWrapper dr) {
        super(dr);
    }

    public void clickProductCategoryLink() {
        web.clickLink("productcategoty.page.item.link");
    }
}
