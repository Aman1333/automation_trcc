package selenium.reminders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import selenium.BaseClass;

import java.util.List;

public class ReminderAdminPanelPageObject extends BaseClass
{
    public ReminderAdminPanelPageObject(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='Teams']")
    private WebElement teamsTab;
    @FindBy(xpath = "//h1[normalize-space()='Teams']")
    private WebElement checkTeamsTab;
    @FindBy(xpath = "//a[normalize-space()='Agents']")
    private WebElement agentsTab;
    @FindBy(xpath = "//h1[normalize-space()='Agents']")
    private WebElement checkAgentsTab;
    @FindBy(xpath = "//a[normalize-space()='Locations']")
    private WebElement locationsTab;
    @FindBy(xpath = "//h1[normalize-space()='Locations index']")
    private WebElement checkLocationsTab;
    @FindBy(xpath = "//a[normalize-space()='Reminder Types']")
    private WebElement remindersTypesTab;
    @FindBy(xpath = "//h1[normalize-space()='Reminder Types']")
    private WebElement checkRemindersTypesTab;
    @FindBy(xpath = "//a[@href='/admin/reminders']")
    private WebElement remindersTab;
    @FindBy(xpath = "//h1[normalize-space()='Reminder Types']")
    private WebElement checkRemindersTab;
    @FindBy(xpath = "//a[normalize-space()='Projects']")
    private WebElement projectsTab;
    @FindBy(xpath = "//h1[normalize-space()='Projects']")
    private WebElement checkProjectsTab;
    @FindBy(xpath = "//a[normalize-space()='Crons']")
    private WebElement othersTab;
    @FindBy(xpath = "//h1[normalize-space()='Scheduled commands']")
    private WebElement checkOthersTab;
    @FindBy(xpath = "//a[normalize-space()='Object Types']")
    private WebElement objectTypesTab;
    @FindBy(xpath = "//h1[normalize-space()='Object Types']")
    private WebElement checkObjectTypesTab;
    @FindBy(xpath = "//input[@placeholder='Searchword...']")
    private WebElement searchBox;
    @FindBy(xpath = "//button[normalize-space()='Filter']")
    private WebElement teamSearchBoxFilter;
    @FindBy(xpath = "(//td[normalize-space()='Dreamport Experts'])[1]")
    private WebElement teamName;
    @FindBy(xpath = "(//td[normalize-space()='reppoTestUser'])[1]")
    private WebElement agentName;
    @FindBy(xpath = "//td[normalize-space()='Test_Reminder_146407']")
    private WebElement reminderName;
    @FindBy(xpath = "//tbody/tr")
    private WebElement getList;
    @FindBy(xpath = "//select[@id='pageLimit']")
    private WebElement pageLimit;
    @FindBy(xpath = "//a[normalize-space()='Create new']")
    private WebElement reminderType;
    @FindBy(xpath = "//input[@id='reminder_types_name']")
    private WebElement reminderTypeName;
    @FindBy(xpath = "//input[@id='reminder_types_short_name']")
    private WebElement reminderTypeShortName;
    @FindBy(xpath = "//input[@id='reminder_types_color']")
    private WebElement reminderTypeColor;
    @FindBy(xpath = "//select[@id='reminder_types_projects']")
    private WebElement reminderTypeProject;
    @FindBy(xpath = "//option[@value='244']")
    private WebElement selectTrccOption;
    @FindBy(xpath = "//label[normalize-space()='Is default']")
    private WebElement selectIsDefault;
    @FindBy(xpath = "//label[normalize-space()='Is public']")
    private WebElement selectIsPublic;
    @FindBy(xpath = "//button[normalize-space()='Save']")
    private WebElement saveButton;
    @FindBy(xpath = "(//td[contains(text(),'Test')])[3]//following-sibling::td/a[1]")
    private WebElement editReminderType;
    @FindBy(xpath = "(//td[contains(text(),'test')])[2]/following-sibling::td/a[1]")
    private WebElement editObject;
    @FindBy(xpath = "//button[normalize-space()='Update']")
    private WebElement updateButton;
    @FindBy(xpath = "(//td[contains(text(),'Test')])[3]//following-sibling::td/a[2]")
    private WebElement deleteReminderType;
    @FindBy(xpath = "//td[normalize-space()='Test-updated']/following-sibling::td/a[2]")
    private WebElement deleteObject;
    @FindBy(xpath = "//a[@class='btn btn-sm btn-danger']")
    private WebElement deleteReminderTypeConfirm;
    @FindBy(xpath = "//input[@id='object_types1_name']")
    private WebElement objectTypeName;
    @FindBy(xpath = "//input[@id='object_types1_code']")
    private WebElement objectTypeCode;
    @FindBy(xpath = "//input[@id='object_types1_object_dev_link']")
    private WebElement objectDevLink;
    @FindBy(xpath = "//input[@id='object_types1_object_prod_link']")
    private WebElement objectProdLink;
    @FindBy(xpath = "//select[@id='object_types1_projects']")
    private WebElement objectTypesProject;
    public ReminderAdminPanelPageObject clickTeamTab()
    {
        this.waitForElementToBeClickable(teamsTab, TIMEOUT_10);
        teamsTab.click();
        return this;
    }
    public boolean verifyTeamTab()
    {
       return this.waitForElementInvisibility(checkTeamsTab, TIMEOUT_5);

    }
    public ReminderAdminPanelPageObject clickAgentsTab()
    {
        this.waitForElementToBeClickable(agentsTab, TIMEOUT_10);
        agentsTab.click();
        return this;
    }
    public boolean verifyAgentsTab()
    {
        return this.waitForElementInvisibility(checkAgentsTab, TIMEOUT_10);

    }
    public ReminderAdminPanelPageObject clickLocationTab()
    {
        this.waitForElementToBeClickable(locationsTab, TIMEOUT_10);
        locationsTab.click();
        return this;
    }
    public boolean verifyLocationTab()
    {
        return this.waitForElementInvisibility(checkLocationsTab, TIMEOUT_10)  ;
    }
    public ReminderAdminPanelPageObject clickRemindersTypesTab()
    {
        this.waitForElementToBeClickable(remindersTypesTab, TIMEOUT_10);
        remindersTypesTab.click();
        return this;
    }
    public boolean verifyRemindersTypesTab()
    {
         return this.waitForElementInvisibility(checkRemindersTypesTab, TIMEOUT_10);
    }
    public ReminderAdminPanelPageObject clickRemindersTab()
    {
        this.waitForElementToBeClickable(remindersTab, TIMEOUT_10);
        remindersTab.click();
        return this;
    }
    public boolean verifyRemindersTab()
    {
        return this.waitForElementInvisibility(checkRemindersTab, TIMEOUT_10);
    }
    public ReminderAdminPanelPageObject clickProjectsTab()
    {
        this.waitForElementToBeClickable(projectsTab, TIMEOUT_10);
        projectsTab.click();
        return this;
    }
    public boolean verifyProjectsTab()
    {
        return this.waitForElementInvisibility(checkProjectsTab, TIMEOUT_10);
    }
    public ReminderAdminPanelPageObject clickObjectTypesTab()
    {
        this.waitForElementToBeClickable(objectTypesTab, TIMEOUT_10);
        objectTypesTab.click();
        return this;
    }
    public boolean verifyObjectTypesTab()
    {
        return this.waitForElementInvisibility(checkObjectTypesTab, TIMEOUT_10);
    }
    public ReminderAdminPanelPageObject clickOtherTab()
    {
        this.waitForElementToBeClickable(othersTab, TIMEOUT_10);
        othersTab.click();
        return this;
    }
    public boolean verifyOtherTab()
    {
        return this.waitForElementInvisibility(checkOthersTab, TIMEOUT_10);
    }
    public ReminderAdminPanelPageObject addName(String teamName)
    {
        this.waitForElementToBeClickable(searchBox, TIMEOUT_10);
        searchBox.sendKeys(teamName);
        return this;
    }
    public ReminderAdminPanelPageObject selectFilter()
    {
        this.waitForElementToBeClickable(teamSearchBoxFilter, TIMEOUT_10);
        teamSearchBoxFilter.click();
        this.waitForElementVisibility(teamName, TIMEOUT_10);
        return this;
    }
    public WebElement getTeamName()
    {
        this.waitForElementVisibility(teamName,TIMEOUT_10);
        return teamName;
    }
    public WebElement getAgentName()
    {
        this.waitForElementVisibility(agentName,TIMEOUT_10);
        return agentName;
    }
    public WebElement getReminderName()
    {
        this.waitForElementVisibility(reminderName,TIMEOUT_10);
        return reminderName;
    }
    public int getListCount()
    {
        this.waitForElementToBeClickable(getList, TIMEOUT_10);
        List<WebElement> totalTeams= driver.findElements(By.xpath("//tbody/tr"));
        return totalTeams.size();

    }
    public ReminderAdminPanelPageObject selectPageLimit()
    {
        this.waitForElementToBeClickable(pageLimit, TIMEOUT_10);
        Select pageLimitDropdown = new Select(pageLimit);
        pageLimitDropdown.selectByIndex(1);
        this.waitForElementVisibility(teamName, TIMEOUT_10);
        return this;
    }
    public ReminderAdminPanelPageObject addNew()
    {
        this.waitForElementToBeClickable(reminderType, TIMEOUT_10);
        reminderType.click();
        return this;
    }
    public ReminderAdminPanelPageObject addReminderName(String name)
    {
        this.waitForElementToBeClickable(reminderTypeName, TIMEOUT_10);
        reminderTypeName.clear();
        reminderTypeName.sendKeys(name);
        return this;
    }
    public ReminderAdminPanelPageObject addReminderShortName(String name)
    {
        this.waitForElementToBeClickable(reminderTypeShortName, TIMEOUT_10);
        reminderTypeShortName.sendKeys(name);
        return this;
    }
    public ReminderAdminPanelPageObject addReminderColor(String name)
    {
        this.waitForElementToBeClickable(reminderTypeColor, TIMEOUT_10);
        reminderTypeColor.sendKeys(name);
        return this;
    }
    public ReminderAdminPanelPageObject selectReminderProject()
    {
        this.waitForElementToBeClickable(reminderTypeProject, TIMEOUT_10);
        reminderTypeProject.click();
        this.waitForElementToBeClickable(selectTrccOption, TIMEOUT_2);
        selectTrccOption.click();
        return this;
    }
    public ReminderAdminPanelPageObject selectIsDefault()
    {
        this.waitForElementToBeClickable(selectIsDefault, TIMEOUT_10);
        selectIsDefault.click();
        return this;
    }
    public ReminderAdminPanelPageObject selectIsPublic()
    {
        this.waitForElementToBeClickable(selectIsPublic, TIMEOUT_10);
        selectIsPublic.click();
        return this;
    }
    public ReminderAdminPanelPageObject saveReminderType()
    {
        this.waitForElementVisibility(saveButton, TIMEOUT_10);
        saveButton.click();
        return this;
    }
    public ReminderAdminPanelPageObject editReminder()
    {
        this.waitForElementToBeClickable(editReminderType, TIMEOUT_10);
        editReminderType.click();
        return this;
    }
    public ReminderAdminPanelPageObject editObject()
    {
        this.waitForElementToBeClickable(editReminderType, TIMEOUT_10);
        editReminderType.click();
        return this;
    }
    public ReminderAdminPanelPageObject updateButton()
    {
        this.waitForElementVisibility(updateButton, TIMEOUT_10);
        updateButton.click();
        return this;
    }
    public WebElement getReminderTypeName (String reminderTypeName)
    {
        this.waitForElementInvisibility(reminderType, TIMEOUT_20);
        return driver.findElement(By.xpath("(//td[contains(text(),'"+reminderTypeName+"')])[1]"));
    }
    public WebElement getObjectName (String objectTypeName)
    {
        this.waitForElementInvisibility(objectTypesTab, TIMEOUT_20);
        return driver.findElement(By.xpath("(//td[contains(text(),'"+objectTypeName+"')])[1]"));
    }
    public ReminderAdminPanelPageObject deleteReminderType()
    {
        this.waitForElementToBeClickable(deleteReminderType, TIMEOUT_10);
        deleteReminderType.click();
        deleteReminderTypeConfirm.click();
        return this;
    }
    public ReminderAdminPanelPageObject deleteObject()
    {
        this.waitForElementToBeClickable(deleteObject, TIMEOUT_10);
        deleteObject.click();
        deleteReminderTypeConfirm.click();
        return this;
    }
    public ReminderAdminPanelPageObject selectObjectProject()
    {
        this.waitForElementToBeClickable(objectTypesProject, TIMEOUT_10);
        objectTypesProject.click();
        this.waitForElementToBeClickable(selectTrccOption, TIMEOUT_2);
        selectTrccOption.click();
        return this;
    }
    public ReminderAdminPanelPageObject addObjectName(String name)
    {
        this.waitForElementToBeClickable(objectTypeName, TIMEOUT_10);
        objectTypeName.clear();
        objectTypeName.sendKeys(name);
        return this;
    }
    public ReminderAdminPanelPageObject addObjectCode(String name)
    {
        this.waitForElementToBeClickable(objectTypeCode, TIMEOUT_10);
        objectTypeCode.sendKeys(name);
        return this;
    }
    public ReminderAdminPanelPageObject addObjectDevLink(String name)
    {
        this.waitForElementToBeClickable(objectDevLink, TIMEOUT_10);
        objectDevLink.sendKeys(name);
        return this;
    }
    public ReminderAdminPanelPageObject addObjectProdLink(String name)
    {
        this.waitForElementToBeClickable(objectProdLink, TIMEOUT_10);
        objectProdLink.sendKeys(name);
        return this;
    }
}
