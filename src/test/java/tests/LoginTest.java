package tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Demo;

public class LoginTest extends Fixtures {

    @BeforeMethod
    public void methodSetUp() {
        demo.loginPage.openPage();
    }

    @Test
    public void validCredentialsLoginTest() {
        demo.loginPage.enterValidCredentials();
        demo.loginPage.clickLoginButton();
        Assert.assertTrue(demo.customerPage.isLoginSuccessful(), "Login with the valid credentials failed!");
    }

    @Test
    public void nonExistUserLoginTest() {
        System.out.println("****************************************");
        System.out.println(demo);
        demo.homePage.logIn();
        demo.loginPage.enterInvalidCredentials();
        demo.loginPage.clickLoginButton();
        Assert.assertTrue(demo.loginPage.isShowLoginErrorMessage(), "Login with the invalid credentials failed!");
    }

//    @Test
    public void emptyUserLoginTest() {
        demo.homePage.logIn();
        demo.loginPage.enterLoginEmailData("");
        demo.loginPage.enterPasswordData("qweqrt");
        demo.loginPage.clickLoginButton();
        Assert.assertTrue(demo.loginPage.isAdviceReqEmailMessage(), "No error message with empty email input");
    }

//    @Test
    public void emptyUserPasswordTest() {
        demo.homePage.logIn();
        demo.loginPage.enterLoginEmailData("email@gmail.com");
        demo.loginPage.enterPasswordData("");
        demo.loginPage.clickLoginButton();
        Assert.assertTrue(demo.loginPage.isAdviceReqPasswordMessage(), "No error message with empty pass input");
    }

//    @Test
    public void emptyUserLoginAndPasswordTest() {
        demo.homePage.logIn();
        demo.loginPage.enterLoginEmailData("");
        demo.loginPage.enterPasswordData("");
        demo.loginPage.clickLoginButton();
        Assert.assertTrue(demo.loginPage.isAdviceReqEmailMessage(), "No error message with empty email input");
        Assert.assertTrue(demo.loginPage.isAdviceReqPasswordMessage(), "No error message with empty pass input");
    }

//    @Test
    public void incorrectUserLoginMaskTest() {
        demo.homePage.logIn();
        demo.loginPage.enterLoginEmailData("t22333221 @gmail.com");
        demo.loginPage.enterPasswordData("qwerty123");
        demo.loginPage.clickLoginButton();
        Assert.assertFalse(demo.customerPage.isLoginSuccessful(), "Incorrect mask test failed"); //TODO show popup message
    }

//    @Test
    public void spacesInTheUserLoginAndPasswordTest() {
        demo.homePage.logIn();
        demo.loginPage.pressSpaceInTheLoginData();
        demo.loginPage.pressSpaceInThePasswordData();
        demo.loginPage.clickLoginButton();
        Assert.assertFalse(demo.customerPage.isLoginSuccessful(),"Spaces in login & password test failed");
    }

//    @Test
    public void tabsInTheUserLoginAndPasswordTest() {
        demo.homePage.logIn();
        demo.loginPage.pressTabInTheLoginData();
        demo.loginPage.pressTabInThePasswordData();
        demo.loginPage.clickLoginButton();
        Assert.assertFalse(demo.customerPage.isLoginSuccessful(),"Tabs in login & password test failed");
    }
}
