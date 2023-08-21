package selenium.entrypoint;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.BaseClass;

public class EntryPointPageObject extends BaseClass {

    public EntryPointPageObject(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "(//input[@disabled='true'])[1]")
    public WebElement sessionKey;


    public String getSessionKey() {
        this.waitForElementVisibility(sessionKey, TIMEOUT_5);
        return sessionKey.getAttribute("value");
    }
}
