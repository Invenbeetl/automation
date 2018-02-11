package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Demo;

public class RegistrationTest extends Fixtures {

    private Demo demo = new Demo(driver);

    @Test
    public void newUserRegistrationTest() {
        demo.homePage.register();
        demo.regPage.enterName("Fedor");
        demo.regPage.lastName("Ivanov");
        demo.regPage.enterEmail("t223332211@gmail.com");
        demo.regPage.enterPassword("teatatata1234");
        demo.regPage.enterPasswordConfirm("teatatata1234");
        demo.regPage.clickRegButton();
        Assert.assertTrue(demo.customerPage.isRegistrationSuccessful(), "Registration test failed");
    }
}
