package selenium.reminders;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.BaseClass;


public class ReminderAppPageObject extends BaseClass
{
    public ReminderAppPageObject(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//mat-icon[normalize-space()='add']")
    private WebElement plusIcon;
    @FindBy(xpath = "//*[contains(@placeholder,'Add Title')]")
    private WebElement addTitle;
    @FindBy(xpath = "//button[normalize-space()='Vacations']")
    private WebElement reminderTypeVacation;
    @FindBy(xpath = "//button[normalize-space()='Classic']")
    private WebElement reminderTypeClassic;
    @FindBy(xpath = "//button[normalize-space()='Out of office']")
    private WebElement reminderTypeOoo;
    @FindBy(xpath = "//button[normalize-space()='Alert']")
    private WebElement reminderTypeAlert;
    @FindBy(xpath = "//button[normalize-space()='Vacations']")
    private WebElement reminderType;
    @FindBy(xpath = "//*[contains(@aria-label,'Participants')]")
    private WebElement inputParticipants;
    @FindBy(xpath = "//div[@class='rmnd-participant-name']//div[1]")
    private WebElement selectParticipants;
    @FindBy(xpath = "//a[normalize-space()='Add Description']")
    private WebElement addDescription;
    @FindBy(xpath = "//*[contains(@formcontrolname,'description')]")
    private WebElement addDescriptionText;
    @FindBy(xpath = "//a[normalize-space()='Add Object Type']")
    private WebElement addObject;
    @FindBy(xpath = "//div[normalize-space()='Object Name']")
    private WebElement objectType;
    @FindBy(xpath = "//span[normalize-space()='CMS']")
    private WebElement chooseObjectType;
    @FindBy(xpath = "//*[contains(@formcontrolname,'object_id')]")
    private WebElement addObjectId;
    @FindBy(xpath = " //mat-icon[@class='mat-icon notranslate rmnd-object-drop-down material-icons mat-icon-no-color ng-star-inserted']")
    private WebElement collapseObjectId;
    @FindBy(xpath = "//button[@id='saveReminderTRCCButton']")
    private WebElement saveButton;
    @FindBy(xpath = "(//*[normalize-space()='Update'])[2]")
    private WebElement updateButton;
    @FindBy(xpath = "//div[@aria-label='Successfully added reminder!!']")
    private WebElement successfullyMessage;
    @FindBy(xpath = "///div[@aria-label='Got some error while updating the reminder']")
    private WebElement errorMessage;
    @FindBy(xpath = "//div[@aria-label='Successfully updated reminder!!']")
    private WebElement updateSuccessfullyMessage;
    @FindBy(xpath = "//*[text()='Schedule']")
    private WebElement ScheduleSection;
    @FindBy(css = "#circle")
    private WebElement createdReminderCheck;
    @FindBy(xpath = "//mat-icon[normalize-space()='edit']")
    private WebElement editButton;
    @FindBy(xpath = "//*[contains(@formcontrolname,'start_date')]")
    private WebElement startDate;
    @FindBy(xpath = "//*[contains(@formcontrolname,'end_date')]")
    private WebElement endDate;
    @FindBy(xpath = "(//mat-icon[normalize-space()='keyboard_arrow_right'])[1]")
    private WebElement nextDate;
    @FindBy(css = "span[class='date-format cus-pointer'] strong")
    private WebElement nextDateText;
    @FindBy(xpath = "//button[@aria-label='Next month']")
    private WebElement nextMonth;
    @FindBy(xpath = "//div[@class='mat-calendar-body-cell-content mat-focus-indicator'][normalize-space()='1']")
    private WebElement firstDate;
    @FindBy(xpath = "//div[@class='mat-calendar-body-cell-content mat-focus-indicator'][normalize-space()='2']")
    private WebElement secondDate;
    @FindBy(xpath = "//label[normalize-space()='All Day']")
    private WebElement allDayCheckBox;
    @FindBy(css = "a[class='pointer']")
    private WebElement moreOption;
    @FindBy(xpath = "//div[normalize-space()='Events']")
    private WebElement eventsOption;
    @FindBy(xpath = "//span[normalize-space()='Vacations']")
    private WebElement vacationOption;
    @FindBy(xpath = "//span[normalize-space()='Classic']")
    private WebElement classicOption;
    @FindBy(xpath = "//span[normalize-space()='Out Of Office']")
    private WebElement outOfOfficeOption;
    @FindBy(xpath = "//div[normalize-space()='No Reminder Scheduled']")
    private WebElement noReminderSection;
    @FindBy(xpath = "(//span[@class='close close-rounded inline'])[1]")
    private WebElement pop_up;
    @FindBy(xpath = "(//*[normalize-space()='Add Notification'])[1]")
    private WebElement add_notification;
    @FindBy(xpath = "(//*[normalize-space()='10 minutes before'])[2]")
    private WebElement select_notification_dropdown;
    @FindBy(xpath = "(//*[normalize-space()='2 minutes before'])[2]")
    private WebElement add_notification_2_min;
    @FindBy(xpath = "(//*[normalize-space()='5 minutes before'])[2]")
    private WebElement add_notification_5_min;
    @FindBy(xpath = "(//*[normalize-space()='15 minutes before'])[2]")
    private WebElement add_notification_15_min;
    @FindBy(xpath = "(//*[normalize-space()='30 minutes before'])[2]")
    private WebElement add_notification_30_min;
    @FindBy(xpath = "(//*[normalize-space()='45 minutes before'])[2]")
    private WebElement add_notification_45_min;
    @FindBy(xpath = "(//*[normalize-space()='Title is required'])[2]")
    private WebElement validationMessage;
    @FindBy(xpath = "//span[@class='rmnd-time ng-star-inserted']")
    private WebElement getDate;
    @FindBy(xpath = "//div[@id='trcc-plugin-sidenav']/div[1]/span[2]")
    private WebElement getObjectId;

       public ReminderAppPageObject clickOnAddReminder()
    {
        this.waitForElementToBeClickable(plusIcon, TIMEOUT_20);
        plusIcon.click();
        return this;
    }
    public ReminderAppPageObject addReminderTitle(String title)
    {
        this.waitForElementToBeClickable(addTitle, TIMEOUT_10);
        addTitle.sendKeys(title);
        return this;
    }
    public boolean validateErrorMessage()
    {
        this.waitForElementToBeClickable(validationMessage, TIMEOUT_10);
        if(validationMessage.getText().equals("Title is required"))
         return true;
        return false;
    }
    public ReminderAppPageObject selectReminderType()
    {
        this.waitForElementToBeClickable(reminderType, TIMEOUT_10);
        reminderType.click();
        return this;
    }
    public ReminderAppPageObject selectReminderTypeClassic()
    {
        this.waitForElementToBeClickable(reminderTypeClassic, TIMEOUT_10);
        reminderTypeClassic.click();
        return this;
    }
    public ReminderAppPageObject selectReminderTypeVacation()
    {
        this.waitForElementToBeClickable(reminderTypeVacation, TIMEOUT_10);
        reminderTypeVacation.click();
        return this;
    }
    public ReminderAppPageObject selectReminderTypeOutOfOffice()
    {
        this.waitForElementToBeClickable(reminderTypeOoo, TIMEOUT_10);
        reminderTypeOoo.click();
        return this;
    }
    public ReminderAppPageObject selectReminderTypeAlert()
    {
        this.waitForElementToBeClickable(reminderTypeAlert, TIMEOUT_10);
        reminderTypeAlert.click();
        return this;
    }
    public ReminderAppPageObject addParticipants(String participant)
    {
        inputParticipants.sendKeys(participant);
        this.waitForElementToBeClickable(selectParticipants, TIMEOUT_10);
        selectParticipants.click();
        return this;
    }
    public ReminderAppPageObject fillDescription(String description)
    {
        this.waitForElementToBeClickable(addDescription, TIMEOUT_10);
        addDescription.click();
        this.waitForElementToBeClickable(addDescriptionText, TIMEOUT_10);
        addDescriptionText.click();
        addDescriptionText.sendKeys(description);
        return this;
    }
    public ReminderAppPageObject fillObject(String objectID)
    {
       this.waitForElementToBeClickable(objectType, TIMEOUT_10);
        addObject.click();
        objectType.click();
        chooseObjectType.click();
       this.waitForElementToBeClickable(addObjectId, TIMEOUT_10);
        addObjectId.click();
        addObjectId.sendKeys(objectID);
        return this;
    }
    public ReminderAppPageObject collapseObjectSection()
    {
        this.waitForElementToBeClickable(addObject, TIMEOUT_10);
        addObject.click();
        collapseObjectId.click();
        return this;
    }
    public boolean objectIdSection()
    {
        boolean result =false;
        if(!this.waitForElementVisibility(addObject, TIMEOUT_10)) {
            return true;
        }
        return result;
    }
    public ReminderAppPageObject selectSaveButton()
    {
        this.waitForElementToBeClickable(saveButton, TIMEOUT_20);
        saveButton.click();
        return this;
    }
    public ReminderAppPageObject selectUpdateButton()
    {
        this.waitForElementToBeClickable(updateButton, TIMEOUT_10);
        updateButton.click();
        return this;
    }
    public ReminderAppPageObject selectScheduleSection()
    {
        this.waitForElementInvisibility(createdReminderCheck,TIMEOUT_20);
        this.waitForElementToBeClickable(ScheduleSection, TIMEOUT_10);
        while(this.waitForElementVisibility(pop_up, TIMEOUT_10))
        {
            pop_up.click();
        }
        ScheduleSection.click();
        return this;
    }
    public WebElement getSuccessfullyMessage () {
        this.waitForElementVisibility(successfullyMessage,TIMEOUT_10);
        return successfullyMessage;
    }
    public WebElement getUpdateSuccessfullyMessage () {
        this.waitForElementVisibility(updateSuccessfullyMessage,TIMEOUT_10);
        return updateSuccessfullyMessage;
    }
   public WebElement getReminderName (String reminderName) {
       this.waitForElementInvisibility(createdReminderCheck,TIMEOUT_20);
       return driver.findElement(By.xpath("//div[@class='list-name']//*[text()='"+reminderName+"']"));
    }
    public ReminderAppPageObject clickEditButton()
    {
        this.waitForElementToBeClickable(editButton, TIMEOUT_10);
        editButton.click();
        return this;
    }
    public ReminderAppPageObject updateTitle(String newTile)
    {
        this.waitForElementToBeClickable(addTitle, TIMEOUT_10);
        addTitle.clear();
        addTitle.sendKeys(newTile);
        return this;
    }
    public ReminderAppPageObject selectNextDate()
    {
        this.waitForElementToBeClickable(nextDate, TIMEOUT_10);
        nextDateText.getText();
        nextDate.click();
        return this;
    }
    public String getNextDate()
    {
        this.waitForElementToBeClickable(nextDateText, TIMEOUT_10);
       return nextDateText.getText();
    }
    public ReminderAppPageObject selectDate()
    {
        this.waitForElementToBeClickable(startDate, TIMEOUT_10);
        startDate.click();
        this.waitForElementToBeClickable(nextMonth, TIMEOUT_10);
        nextMonth.click();
        this.waitForElementToBeClickable(firstDate, TIMEOUT_10);
        firstDate.click();
        return this;
    }
    public ReminderAppPageObject selectAllDayDate()
    {
        this.waitForElementToBeClickable(startDate, TIMEOUT_10);
        startDate.click();
        this.waitForElementToBeClickable(nextMonth, TIMEOUT_10);
        nextMonth.click();
        this.waitForElementToBeClickable(firstDate, TIMEOUT_10);
        firstDate.click();
        this.waitForElementToBeClickable(allDayCheckBox, TIMEOUT_10);
        allDayCheckBox.click();
        return this;
    }
    public ReminderAppPageObject selectMultiDayDate()
    {
        this.waitForElementToBeClickable(startDate, TIMEOUT_10);
        startDate.click();
        this.waitForElementToBeClickable(nextMonth, TIMEOUT_10);
        nextMonth.click();
        this.waitForElementToBeClickable(firstDate, TIMEOUT_10);
        firstDate.click();
        this.waitForElementToBeClickable(endDate, TIMEOUT_10);
        endDate.click();
        this.waitForElementToBeClickable(secondDate, TIMEOUT_15);
        secondDate.click();
        return this;
    }
    public WebElement checkMoreOption(String reminderName)
    {
        this.waitForElementVisibility(createdReminderCheck,TIMEOUT_10);
        if(this.waitForElementToBeClickable(moreOption, TIMEOUT_10))
            moreOption.click();
        return driver.findElement(By.xpath("//*[contains(text(),'"+reminderName+"')]"));

    }
    public String checkNextDateReminder()
    {
        this.waitForElementVisibility(getDate,TIMEOUT_20);
        return getDate.getText();

    }
    public ReminderAppPageObject selectEventOption()
    {
        this.waitForElementToBeClickable(eventsOption, TIMEOUT_10);
        this.waitForElementInvisibility(createdReminderCheck,TIMEOUT_20);
        eventsOption.click();
        this.waitForElementInvisibility(createdReminderCheck,TIMEOUT_20);
        return this;
    }
    public ReminderAppPageObject selectVacationOption()
    {
        this.waitForElementToBeClickable(vacationOption, TIMEOUT_20);
        vacationOption.click();
         return this;
    }
    public ReminderAppPageObject selectClassicOption()
    {
        this.waitForElementToBeClickable(classicOption, TIMEOUT_10);
        classicOption.click();
        return this;
    }
    public WebElement getObjectId()
    {
        this.waitForElementVisibility(getObjectId, TIMEOUT_5);
        return getObjectId;
    }
    public ReminderAppPageObject selectOutOfOfficeOption()
    {
        this.waitForElementToBeClickable(outOfOfficeOption, TIMEOUT_10);
        outOfOfficeOption.click();
        return this;
    }
    public boolean checkReminderAvailable()
    {
       if(!this.waitForElementToBeClickable(noReminderSection, TIMEOUT_10))
       {return true;}
       return  false;
    }
    public boolean checkAddNotificationButton()
    {
        boolean result = false;
        if (!this.waitForElementInvisibility(add_notification, TIMEOUT_10)) {
            result = true;
        }
        return result;
    }
    public ReminderAppPageObject selectAddNotification()
    {
        this.waitForElementToBeClickable(add_notification, TIMEOUT_10);
        add_notification.click();;
        return this;
    }
    public ReminderAppPageObject selectDropDownAddNotification()
    {
        this.waitForElementToBeClickable(select_notification_dropdown, TIMEOUT_10);
        select_notification_dropdown.click();;
        return this;
    }
    public ReminderAppPageObject selectNotificationForTwoMin()
    {
        //this.waitForElementToBeClickable(add_notification, TIMEOUT_10);
       // add_notification.click();;
        this.waitForElementToBeClickable(select_notification_dropdown, TIMEOUT_10);
        select_notification_dropdown.click();;
        this.waitForElementToBeClickable(add_notification_2_min, TIMEOUT_10);
        add_notification_2_min.click();;
        return this;
    }
    public ReminderAppPageObject selectNotificationForFiveMin()
    {
        this.waitForElementToBeClickable(add_notification, TIMEOUT_10);
        add_notification.click();;
        this.waitForElementToBeClickable(select_notification_dropdown, TIMEOUT_10);
        select_notification_dropdown.click();;
        this.waitForElementToBeClickable(add_notification_5_min, TIMEOUT_10);
        add_notification_5_min.click();;
        return this;
    }
    public ReminderAppPageObject selectNotificationForFifteenMin()
    {
        this.waitForElementToBeClickable(add_notification, TIMEOUT_10);
        add_notification.click();;
        this.waitForElementToBeClickable(select_notification_dropdown, TIMEOUT_10);
        select_notification_dropdown.click();;
        this.waitForElementToBeClickable(add_notification_15_min, TIMEOUT_10);
        add_notification_15_min.click();;
        return this;
    }
    public ReminderAppPageObject selectNotificationForThirtyMin()
    {
        this.waitForElementToBeClickable(add_notification, TIMEOUT_10);
        add_notification.click();;
        this.waitForElementToBeClickable(select_notification_dropdown, TIMEOUT_10);
        select_notification_dropdown.click();;
        this.waitForElementToBeClickable(add_notification_30_min, TIMEOUT_10);
        add_notification_30_min.click();;
        return this;
    }
    public ReminderAppPageObject selectNotificationForFourtyFiveMin()
    {
        this.waitForElementToBeClickable(add_notification, TIMEOUT_10);
        add_notification.click();;
        this.waitForElementToBeClickable(select_notification_dropdown, TIMEOUT_10);
        select_notification_dropdown.click();;
        this.waitForElementToBeClickable(add_notification_45_min, TIMEOUT_10);
        add_notification_45_min.click();;
        return this;
    }
    public ReminderAppPageObject selectNotification()
    {
        this.waitForElementToBeClickable(add_notification_15_min, TIMEOUT_10);
        add_notification_15_min.click();;
        return this;
    }

}
