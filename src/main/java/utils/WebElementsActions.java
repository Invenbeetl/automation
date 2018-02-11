package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class WebElementsActions {
    private WebDriver driver;
    private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    public WebElementsActions(WebDriverWrapper driver) {
        this.driver = driver;
    }

    /**
     * Submit a button
     */
    public void submitButton(String buttonLocator) {
        driver.findElement(UiMappingSingleton.ui(buttonLocator)).submit();
    }

    /**
     * Click an element
     */
    public void clickElement(String elementLocator) {
        driver.findElement(UiMappingSingleton.ui(elementLocator)).click();
    }

    /**
     * Click a button
     */
    public void clickButton(String buttonLocator) {
        driver.findElement(UiMappingSingleton.ui(buttonLocator)).click();
        LOG.info("Click on Button " + buttonLocator);
    }

    /**
     * Click link
     */
    public void clickLink(String linkLocator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(UiMappingSingleton.ui(linkLocator))));
        element.click();
        //driver.findElement(UiMappingSingleton.ui(linkLocator)).click();
        LOG.info("Click on Link " + linkLocator);
    }

    public void clickSpecifiedLinkFromTheList(String listLocator, int elementNumber) {
        List<WebElement> list = getElements(listLocator);
        if (!(list.isEmpty())) {
            try {
                list.get(elementNumber).click();
                LOG.info("Click "  + elementNumber + " list element" + listLocator);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                LOG.error(elementNumber + " element is absent in list " + listLocator);
            }
        }
    }

    /**
     * Insert value into text input HTML field
     */
    public void input(String inputLocator, String inputData) {
        driver.findElement(UiMappingSingleton.ui(inputLocator)).clear();
        driver.findElement(UiMappingSingleton.ui(inputLocator)).sendKeys(inputData);
        LOG.info("Input in " + inputLocator + ", value - " + inputData);
    }

    /**
     * Insert value into text input HTML field and Click ENTER for Field which used "Value" in the xpath expression
     */
    public void inputAndClickEnter(String inputLocator, String inputData) {
        driver.findElement(UiMappingSingleton.ui(inputLocator)).clear();
        driver.findElement(UiMappingSingleton.ui(inputLocator)).sendKeys(inputData);
        driver.findElement(UiMappingSingleton.ui(inputLocator)).sendKeys(Keys.ENTER);
        LOG.info("Input in " + inputLocator + ", value - " + inputData);
    }

    /**
     * Method is used to check that element is present on page.
     */
    public boolean isElementPresent(String elementLocator) {
        List<WebElement> list = driver.findElements(UiMappingSingleton.ui(elementLocator));

        if (list.size() == 0) {
            LOG.warn("Element _" + elementLocator + "_is NOT Present in DOM!");
            return false;
        }

        if (list.get(0).isDisplayed()) {
            LOG.info("Element " + elementLocator + " is Present");
            return true;
        } else {
            LOG.error("Element " + elementLocator + " is NOT Displayed Present!");
            return false;
        }
    }

    public boolean isAllElementsPresent(String elementLocator) {
        List<WebElement> list = new ArrayList<>();
        list.addAll(driver.findElements(UiMappingSingleton.ui(elementLocator)));

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isDisplayed()) {
                list.get(i).equals("");
                LOG.info("Element " + list.get(i) + " is present!");
            } else {
                LOG.warn("Element " + list.get(i) + "_; element (" + i + ") is NOT Present!");
                return false;
            }
        }

        LOG.info("Total quantity of " + elementLocator + " - " + list.size());
        return true;
    }


    /**
     * This method is used to agree messages on pop-up windows
     */
    public boolean isAlertPresent() {
        boolean alertPresence = false;
        try {
            Alert alert = driver.switchTo().alert();
            alertPresence = true;
            alert.accept();
        } catch (NoAlertPresentException ex) {
            ex.printStackTrace();
            return alertPresence;
        }
        return alertPresence;
    }


    /**
     * This method is used to get text from pop-up windows
     */
    public String getAlertText() {
        String alertText;
        try {
            Alert alert = driver.switchTo().alert();
            alertText = alert.getText();
            alert.accept();
            LOG.info("Alert text: " + alertText);
        } catch (NoAlertPresentException ex) {
            alertText = "Alert is not found";
            LOG.error("Alert is not found");
            ex.printStackTrace();
        }
        return alertText;
    }

    public void moveToElementAndClick(String moveToLocator, String clickToElement) {
        WebElement webElement = driver.findElement(UiMappingSingleton.ui(moveToLocator));

        Actions actions = new Actions(driver);
        actions.moveToElement(webElement);
        actions.perform();  //!!! always need
        clickButton(clickToElement);

        LOG.info("moved To Element " + moveToLocator + "and clicked on" + clickToElement);
    }

    /**
     * Method#1 for refresh page
     */
    public void refreshPage() {
        driver.navigate().refresh();
        LOG.info("Refresh page performed");
    }

    /**
     * Methods for pressing the keypad buttons
     */
    public void pressSpaceKey(String inputLocator) {
        driver.findElement(UiMappingSingleton.ui(inputLocator)).sendKeys(Keys.SPACE);
        LOG.info("Space clicked on " + inputLocator);
    }

    public void pressEnterKey(String inputLocator) {
        driver.findElement(UiMappingSingleton.ui(inputLocator)).sendKeys(Keys.ENTER);
        LOG.info("Enter clicked on " + inputLocator);
    }

    public void pressESCAPEKey(String inputLocator) {
        driver.findElement(UiMappingSingleton.ui(inputLocator)).sendKeys(Keys.ESCAPE);
        LOG.info("Escape clicked on " + inputLocator);
    }

    public void pressPageUp(String inputLocator) {
        driver.findElement(UiMappingSingleton.ui(inputLocator)).sendKeys(Keys.PAGE_UP);
        LOG.info("PageUp clicked on " + inputLocator);
    }

    public void pressTABkey(String inputLocator) {
        driver.findElement(UiMappingSingleton.ui(inputLocator)).sendKeys(Keys.TAB);
        LOG.info("TAB clicked on " + inputLocator);
    }


    /**
     * This method is used to do Focus to Element And Click
     * Use Actions class
     */
    public void doFocusToElementAndClick(String focusElementLocator, String elementLocator) {
        new Actions(driver).moveToElement(getElement(focusElementLocator)).perform();
        LOG.info("Focus in to element" + focusElementLocator);
        driver.switchTo();
        if (isElementPresent(elementLocator)) {
            clickButton(elementLocator);
        }
    }


    public WebElement getElement(String elementLocator) {
        return driver.findElement(UiMappingSingleton.ui(elementLocator));
    }

    public List<WebElement> getElements(String elementsLocator) {
        return driver.findElements(UiMappingSingleton.ui(elementsLocator));
    }

    public String getElementText(String elementsLocator) {
        return driver.findElement(UiMappingSingleton.ui(elementsLocator)).getText();
    }
}
