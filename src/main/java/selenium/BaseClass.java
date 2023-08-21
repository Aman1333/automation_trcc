package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass extends BaseTest {

    public static final int TIMEOUT_5 = 5;
    public static final int TIMEOUT_2 = 2;

    public static final int TIMEOUT_10 = 10;
    public static final int TIMEOUT_15 = 15;

    public static final int TIMEOUT_20 = 20;
    public static final int TIMEOUT_30 = 30;

    public static final int TIMEOUT_40 = 40;
    public static final int TIMEOUT_50 = 50;

    protected WebDriver driver;

    public BaseClass(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean waitForElementVisibility(WebElement locator, int $seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, $seconds);
            wait.until(ExpectedConditions.visibilityOf(locator));
            return true;
        } catch (NoSuchElementException e) {
            logWrite.info("Element not found!");
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean waitForElementToBeClickable(WebElement locator, int $seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, $seconds);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        } catch (NoSuchElementException e) {
            logWrite.info("Element not found!");
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean waitForElementVisibilityByXpath(By xpath, int $seconds) {
        try {
            logWrite.info("Waiting for element " + xpath);
            WebDriverWait wait = new WebDriverWait(driver, $seconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
            return true;
        } catch (NoSuchElementException e) {
            logWrite.warning("Element was not found!");
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean waitForElementInvisibility(WebElement locator, int $seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, $seconds);
            wait.until(ExpectedConditions.invisibilityOf(locator));
            return true;
        } catch (NoSuchElementException e) {
            logWrite.warning("Element was not found!");
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void openPage(String $page) {
        driver.get($page);
        new WebDriverWait(driver, 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).
                        executeScript("return document.readyState").equals("complete"));
    }

    public void clickWithHover(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).click(element).build().perform();
    }

    public WebElement scrollTo(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }
    public WebElement clear(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].value ='';", element);
         return element;
    }

    public WebElement fill(WebElement element, String value) {
        scrollTo(element);
        //clear(element);
        element.clear();
        element.sendKeys(value);
        return element;
    }

    public WebElement click(WebElement element) {
        scrollTo(element);
        element.click();
        return element;
    }

    public void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void refresh()
    {
        //driver.navigate().refresh();
        driver.navigate().to(driver.getCurrentUrl());

    }

    public boolean isElementPresent(WebElement element){
        try{
            element.isDisplayed();
            logWrite.info("Element exist!");
            return true;
        }
        catch(NoSuchElementException e){
            logWrite.info("Element not present!");
            return false;
        }
    }

    public Boolean isElementClickable(WebElement locator) {
        try {
            locator.click();
            return true;
        }
        catch (NoSuchElementException e) {
            throw(e);
        }
        catch (Exception e) {
            return false;
        }
    }

    public void close() {
        driver.close();
    }
}
