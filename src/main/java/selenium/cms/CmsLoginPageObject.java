package selenium.cms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.BaseClass;
import selenium.entrypoint.EntryPointPageObject;

public class CmsLoginPageObject extends BaseClass {

    @FindBy(xpath = "//*[@name='login']")
    private WebElement loginInput;

    @FindBy(xpath = "//*[@name='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitInput;
    @FindBy(xpath = "//loading-spinner")
    private WebElement loginSpinnerInput;

    public CmsLoginPageObject(WebDriver driver) {
        super(driver);
    }

    public CmsLoginPageObject fillLogin(String $login) {
        this.waitForElementToBeClickable(submitInput, TIMEOUT_10);
        this.waitForElementVisibility(loginInput, TIMEOUT_10);
        loginInput.sendKeys($login);
        return this;
    }

    public CmsLoginPageObject fillPassword(String $password) {
        this.waitForElementVisibility(passwordInput, TIMEOUT_10);
        passwordInput.sendKeys($password);
        return this;
    }

    public void submitCredentials() {
        submitInput.click();
        this.waitForElementInvisibility(loginSpinnerInput, TIMEOUT_20);
    }

    public EntryPointPageObject submitCredentialsToEntryPoint() {
        submitInput.click();
        return new EntryPointPageObject(driver);
    }
}
