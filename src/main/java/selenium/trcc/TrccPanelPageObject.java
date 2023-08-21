package selenium.trcc;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.BaseClass;

public class TrccPanelPageObject extends BaseClass
{

    public TrccPanelPageObject(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//p[@id='reminder']")
    private WebElement reminderIcon;
    @FindBy(xpath = "//*[@id='notes']")
    private WebElement notesIcon;
    @FindBy(xpath = "//a[normalize-space()='Unassigned TCS Only']")
    private WebElement cmsScreen;
    @FindBy(xpath = "//mat-label[@class='agent-name ng-star-inserted']")
    private WebElement nameLabel;
    @FindBy(xpath = "//div[contains(text(),'All Notes')]")
    private WebElement notesApp;
    @FindBy(xpath = "(//span[@class='close close-rounded inline'])[1]")
    private WebElement pop_up;



    public TrccPanelPageObject openReminder() {
        this.waitForElementVisibility(cmsScreen, TIMEOUT_20);
        this.waitForElementVisibility(reminderIcon, TIMEOUT_15);
        while(this.waitForElementVisibility(pop_up, TIMEOUT_10))
        {
            pop_up.click();
        }
        while(!this.waitForElementVisibility(nameLabel, TIMEOUT_15))
        {
            reminderIcon.click();
        }

               return this;
    }

    public TrccPanelPageObject openNotes() {
        this.waitForElementVisibility(cmsScreen, TIMEOUT_10);
        this.waitForElementVisibility(notesIcon, TIMEOUT_10);
        while(this.waitForElementVisibility(pop_up, TIMEOUT_10))
        {
            pop_up.click();
        }
        while(!this.waitForElementToBeClickable(notesApp, TIMEOUT_10))
        {
            notesIcon.click();
        }
        return this;
    }
}
