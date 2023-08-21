package selenium.cms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.BaseClass;

public class CmsHeaderBarObject extends BaseClass {

    public CmsHeaderBarObject(WebDriver driver){
        super(driver);
    }

    @FindBy (css = "#user-button-login")
    public WebElement userMenu;

    @FindBy(xpath = "//a[contains(@href,'/logout')]")
    public WebElement signOutButton;

    @FindBy (xpath = "//*[@id='user-button-login']//strong")
    public WebElement loggedInUserInput;

    public WebElement getLoggedInUser () {
        this.waitForElementVisibility(loggedInUserInput,TIMEOUT_20);
        return loggedInUserInput;
    }

    public void signOut(){
        userMenu.click();
        this.waitForElementToBeClickable(signOutButton, TIMEOUT_10);
        logWrite.info("Clicking the signout button");
        signOutButton.click();
    }

}
