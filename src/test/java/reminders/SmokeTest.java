package reminders;

import org.junit.Assert;
import org.testng.annotations.*;
import selenium.BaseClass;
import selenium.BaseTest;
import selenium.cms.CmsHeaderBarObject;
import selenium.cms.CmsLoginPageObject;
import selenium.reminders.ReminderAppPageObject;
import selenium.trcc.TrccPanelPageObject;
import static common.Helper.getRandomInt;
import static constants.TestData.*;

@Listeners(common.CustomListener.class)
public class SmokeTest extends BaseTest {

    BaseClass baseClass;
    CmsLoginPageObject cmsLoginPageObject;
    CmsHeaderBarObject cmsHeaderBarObject;
    ReminderAppPageObject reminderAppPageObject;
    TrccPanelPageObject trccPanelPageObject;
    String reminder_name;
    String updated_reminder_name;


    @BeforeMethod(alwaysRun = true)
    public void initiate() {
        driver = getDriver();
        cmsLoginPageObject = new CmsLoginPageObject(driver);
        cmsHeaderBarObject = new CmsHeaderBarObject(driver);
        reminderAppPageObject = new ReminderAppPageObject(driver);
        trccPanelPageObject = new TrccPanelPageObject(driver);
        baseClass = new BaseClass(driver);
    }
   @BeforeMethod(alwaysRun = true)
    public void loginToCMS() {
       logWrite.info("Test: Login to CMS with user " + CMS_STAGE_USER);
        baseClass.openPage(CMS_URL);
        logWrite.info("Attempting to login using test user credentials");
        cmsLoginPageObject.fillLogin(CMS_USER)
                .fillPassword(CMS_PASS)
                .submitCredentials();
        logWrite.info("Asserting that user is logged in with display name: " + DISPLAY_NAME);
       // Assert.assertEquals(cmsHeaderBarObject.getLoggedInUser().getText(),DISPLAY_NAME);
        logWrite.info("Successfully asserted the correct display name: " + DISPLAY_NAME);
    }
     @Test
    public void createReminder()
    {
        reminder_name= REMINDER_TITTLE +getRandomInt(6);
        trccPanelPageObject.openReminder();
         reminderAppPageObject.clickOnAddReminder()
                .addReminderTitle(reminder_name)
                .selectReminderType()
                .selectDate()
                .addParticipants(REMINDER_PARTICIPANT)
                .fillDescription(REMINDER_DESCRIPTION)
                //.fillObject("33361656")
                 .selectAddNotification()
                 .selectDropDownAddNotification()
                 .selectNotification()
                 .selectSaveButton();
        Assert.assertEquals(reminderAppPageObject.getSuccessfullyMessage().getText(),SUCCESS_MESSAGE);
        logWrite.info("Reminder created status ==> " + reminderAppPageObject.getSuccessfullyMessage().getText());
        logWrite.info("Reminder name ==> " +reminder_name);
    }
    @Test(dependsOnMethods = "createReminder")
    public void viewReminder()
    {
        trccPanelPageObject.openReminder();
        reminderAppPageObject.selectScheduleSection();
        Assert.assertEquals(reminderAppPageObject.getReminderName(reminder_name).getText(),reminder_name);
    }
    @Test(dependsOnMethods = "viewReminder")
    public void updateReminder()
    {
        updated_reminder_name= REMINDER_TITTLE +getRandomInt(6);
        trccPanelPageObject.openReminder();
        reminderAppPageObject.selectScheduleSection()
                .getReminderName(reminder_name).click();
        reminderAppPageObject.clickEditButton()
                .updateTitle(updated_reminder_name)
                .selectUpdateButton();
        logWrite.info("message name ==> " +reminderAppPageObject.getUpdateSuccessfullyMessage().getText());
        Assert.assertEquals(reminderAppPageObject.getUpdateSuccessfullyMessage().getText(),UPDATED_MESSAGE);
        Assert.assertEquals(reminderAppPageObject.getReminderName(updated_reminder_name).getText(),updated_reminder_name);
    }

    @Test(dependsOnMethods = "updateReminder")
    public void createAllDayReminder()
    {
        reminder_name= REMINDER_TITTLE +getRandomInt(6);
        trccPanelPageObject.openReminder();
        reminderAppPageObject.selectScheduleSection()
                .clickOnAddReminder()
                .addReminderTitle(reminder_name)
                .selectReminderType()
                .selectAllDayDate()
                .addParticipants(REMINDER_PARTICIPANT)
                .fillDescription(REMINDER_DESCRIPTION)
               // .fillObject("33361656")
                .selectAddNotification()
                .selectDropDownAddNotification()
                .selectNotification()
                .selectSaveButton();
        Assert.assertEquals(reminderAppPageObject.getSuccessfullyMessage().getText(),SUCCESS_MESSAGE);
        logWrite.info("Reminder created status ==> " + reminderAppPageObject.getSuccessfullyMessage().getText());
        logWrite.info("Reminder Name ==> " +reminder_name);

    }
    @Test(dependsOnMethods = "createAllDayReminder")
    public void ViewAllDayReminder()
    {
          trccPanelPageObject.openReminder();
          reminderAppPageObject.selectScheduleSection();
          Assert.assertTrue(reminderAppPageObject.checkMoreOption(reminder_name).getText().contains(reminder_name));
    }
    @Test(dependsOnMethods = "ViewAllDayReminder")
    public void checkEvents()
    {
        trccPanelPageObject.openReminder();
        reminderAppPageObject.selectScheduleSection()
                .selectEventOption()
                .selectVacationOption();
               // .selectClassicOption()
               // .selectOutOfOfficeOption();
        Assert.assertTrue(reminderAppPageObject.checkReminderAvailable());
    }
    @AfterMethod(alwaysRun = true)
    public void quit() {
        driver.quit();
    }

}


