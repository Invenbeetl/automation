package pages;

import utils.ScreenShotMaker;
import utils.WebDriverWrapper;
import utils.WebElementsActions;

public class Demo {

    public WebElementsActions web;
    public CustomerPage customerPage;
    public HomePage homePage;
    public LoginPage loginPage;
    public ProductCategoryPage productCategoryPage;
    public ProductListPage productListPage;
    public RegPage regPage;
    public ScreenShotMaker screenShotMaker;

    public Demo(WebDriverWrapper driver) {
        web  = new WebElementsActions(driver);
        customerPage = new CustomerPage(driver);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        productCategoryPage = new ProductCategoryPage(driver);
        productListPage = new ProductListPage(driver);
        regPage = new RegPage(driver);
        screenShotMaker = new ScreenShotMaker(driver);
    }

}