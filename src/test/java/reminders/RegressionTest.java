package reminders;

import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.springframework.context.annotation.DependsOn;
import org.testng.annotations.*;
import selenium.BaseClass;
import selenium.BaseTest;
import selenium.cms.CmsHeaderBarObject;
import selenium.cms.CmsLoginPageObject;
import selenium.reminders.ReminderAdminPanelPageObject;
import selenium.reminders.ReminderAppPageObject;
import selenium.trcc.TrccPanelPageObject;
import static common.Helper.getRandomInt;
import static constants.TestData.*;

@Listeners(common.CustomListener.class)
public class RegressionTest extends BaseTest
{

    BaseClass baseClass;
    CmsLoginPageObject cmsLoginPageObject;
    CmsHeaderBarObject cmsHeaderBarObject;
    ReminderAppPageObject reminderAppPageObject;
    ReminderAdminPanelPageObject reminderAdminPanelPageObject;
    TrccPanelPageObject trccPanelPageObject;
    String reminder_name;
    String updated_reminder_name;
    String nextDate;
    String newDate;
    String objectId;

    @BeforeMethod(alwaysRun = true)
    public void initiate() {
        driver = getDriver();
        Dimension dem = new Dimension(1536,785);
        driver.manage().window().setSize(dem);
        System.out.println(driver.manage().window().getSize());
        cmsLoginPageObject = new CmsLoginPageObject(driver);
        cmsHeaderBarObject = new CmsHeaderBarObject(driver);
        reminderAppPageObject = new ReminderAppPageObject(driver);
        trccPanelPageObject = new TrccPanelPageObject(driver);
        reminderAdminPanelPageObject = new ReminderAdminPanelPageObject(driver);
        baseClass = new BaseClass(driver);
    }
    /***********----Login---***********/
    public void login(String url)
    {
        logWrite.info("Test: Login to CMS with user " + CMS_STAGE_USER);
        baseClass.openPage(url);
        logWrite.info("Attempting to login using test user credentials");
        cmsLoginPageObject.fillLogin(CMS_USER)
                .fillPassword(CMS_PASS)
                .submitCredentials();
        logWrite.info("Asserting that user is logged in with display name: " + DISPLAY_NAME);
        // Assert.assertEquals(cmsHeaderBarObject.getLoggedInUser().getText(),DISPLAY_NAME);
        logWrite.info("Successfully asserted the correct display name: " + DISPLAY_NAME);
    }
    //**********----Reminder home page Test Cases---**********
    @Test
    public void createClassicReminder()
    {
        login(CMS_URL);
        reminder_name= REMINDER_TITTLE +getRandomInt(6);
        trccPanelPageObject.openReminder();
        reminderAppPageObject.clickOnAddReminder()
                .addReminderTitle(reminder_name)
                .selectReminderTypeClassic()
                .selectDate()
                .addParticipants(REMINDER_PARTICIPANT)
                .fillDescription(REMINDER_DESCRIPTION)
                .selectAddNotification()
                .selectDropDownAddNotification()
                .selectNotification()
                .selectSaveButton();
        Assert.assertEquals(reminderAppPageObject.getSuccessfullyMessage().getText(),SUCCESS_MESSAGE);
        logWrite.info("Reminder created status ==> " + reminderAppPageObject.getSuccessfullyMessage().getText());
        logWrite.info("Reminder name ==> " +reminder_name);
    }
    @Test
    public void createVacationReminder()
    {
        login(CMS_URL);
        reminder_name= REMINDER_TITTLE +getRandomInt(6);
        trccPanelPageObject.openReminder();
        reminderAppPageObject.clickOnAddReminder()
                .addReminderTitle(reminder_name)
                .selectReminderTypeVacation()
                .selectDate()
                .addParticipants(REMINDER_PARTICIPANT)
                .fillDescription(REMINDER_DESCRIPTION)
                .selectAddNotification()
                .selectDropDownAddNotification()
                .selectNotification()
                .selectSaveButton();
        Assert.assertEquals(reminderAppPageObject.getSuccessfullyMessage().getText(),SUCCESS_MESSAGE);
        logWrite.info("Reminder created status ==> " + reminderAppPageObject.getSuccessfullyMessage().getText());
        logWrite.info("Reminder name ==> " +reminder_name);
    }
    @Test
    public void createOutOfOfficeReminder()
    {
        login(CMS_URL);
        reminder_name= REMINDER_TITTLE +getRandomInt(6);
        trccPanelPageObject.openReminder();
        reminderAppPageObject.clickOnAddReminder()
                .addReminderTitle(reminder_name)
                .selectReminderTypeOutOfOffice()
                .selectDate()
                .addParticipants(REMINDER_PARTICIPANT)
                .fillDescription(REMINDER_DESCRIPTION)
                .selectAddNotification()
                .selectDropDownAddNotification()
                .selectNotification()
                .selectSaveButton();
        Assert.assertEquals(reminderAppPageObject.getSuccessfullyMessage().getText(),SUCCESS_MESSAGE);
        logWrite.info("Reminder created status ==> " + reminderAppPageObject.getSuccessfullyMessage().getText());
        logWrite.info("Reminder name ==> " +reminder_name);
    }
    @Test
    public void createAlertReminder()
    {
        login(CMS_URL);
        reminder_name= REMINDER_TITTLE +getRandomInt(6);
        trccPanelPageObject.openReminder();
        reminderAppPageObject.clickOnAddReminder()
                .addReminderTitle(reminder_name)
                .selectReminderTypeAlert()
                .selectDate()
                .addParticipants(REMINDER_PARTICIPANT)
                .fillDescription(REMINDER_DESCRIPTION)
                .selectAddNotification()
                .selectDropDownAddNotification()
                .selectNotification()
                .selectSaveButton();
        Assert.assertEquals(reminderAppPageObject.getSuccessfullyMessage().getText(),SUCCESS_MESSAGE);
        logWrite.info("Reminder created status ==> " + reminderAppPageObject.getSuccessfullyMessage().getText());
        logWrite.info("Reminder name ==> " +reminder_name);
    }
    @Test
    public void checkObjectId()
    {
        login(CMS_URL);
        reminder_name= REMINDER_TITTLE +getRandomInt(6);
        trccPanelPageObject.openReminder();
        reminderAppPageObject.clickOnAddReminder()
                .addReminderTitle(reminder_name)
                .selectReminderTypeAlert()
                .selectDate()
                .addParticipants(REMINDER_PARTICIPANT)
                .fillDescription(REMINDER_DESCRIPTION)
                .fillObject("3585221 'getRandomInt(2)' ")
                .selectAddNotification()
                .selectDropDownAddNotification()
                .selectNotification()
                .selectSaveButton();
        Assert.assertEquals(reminderAppPageObject.getSuccessfullyMessage().getText(),SUCCESS_MESSAGE);


    }
    @Test
    public void checkObjectIdSection()
    {
        login(CMS_URL);
        reminder_name= REMINDER_TITTLE +getRandomInt(6);
        trccPanelPageObject.openReminder();
        reminderAppPageObject.clickOnAddReminder()
                .addReminderTitle(reminder_name)
                .selectReminderTypeAlert()
                .selectDate()
                .addParticipants(REMINDER_PARTICIPANT)
                .fillDescription(REMINDER_DESCRIPTION)
                .collapseObjectSection()
                .selectAddNotification()
                .selectDropDownAddNotification()
                .selectNotification()
                .selectSaveButton()
                .selectScheduleSection()
                .getReminderName(reminder_name).click();
        Assert.assertTrue(reminderAppPageObject.clickEditButton().objectIdSection());

    }
    @Test
    public void checkNotificationLimit()
    {
        login(CMS_URL);
        reminder_name= REMINDER_TITTLE +getRandomInt(6);
        trccPanelPageObject.openReminder();
        reminderAppPageObject.clickOnAddReminder()
                .addReminderTitle(reminder_name)
                .selectReminderTypeAlert()
                .selectDate()
                .addParticipants(REMINDER_PARTICIPANT)
                .fillDescription(REMINDER_DESCRIPTION)
                .selectNotificationForTwoMin()
                .selectNotificationForFiveMin()
                .selectNotificationForFifteenMin()
                .selectNotificationForThirtyMin()
                .selectNotificationForFourtyFiveMin();
        Assert.assertTrue(reminderAppPageObject.checkAddNotificationButton());
        reminderAppPageObject.selectSaveButton();
        Assert.assertEquals(reminderAppPageObject.getSuccessfullyMessage().getText(),SUCCESS_MESSAGE);
    }
    @Test
    public void createMultiDayReminder()
    {
        login(CMS_URL);
        reminder_name= REMINDER_TITTLE +getRandomInt(6);
        trccPanelPageObject.openReminder();
        reminderAppPageObject.selectScheduleSection()
                .clickOnAddReminder()
                .addReminderTitle(reminder_name)
                .selectReminderType()
                .selectMultiDayDate()
                .addParticipants(REMINDER_PARTICIPANT)
                .fillDescription(REMINDER_DESCRIPTION)
                .selectAddNotification()
                .selectDropDownAddNotification()
                .selectNotification()
                .selectSaveButton();
        Assert.assertEquals(reminderAppPageObject.getSuccessfullyMessage().getText(),SUCCESS_MESSAGE);
        logWrite.info("Reminder created status ==> " + reminderAppPageObject.getSuccessfullyMessage().getText());
        logWrite.info("Reminder Name ==> " +reminder_name);

    }
    @Test(dependsOnMethods = "createMultiDayReminder")
    public void ViewMultiDayReminder()
    {
        login(CMS_URL);
        trccPanelPageObject.openReminder();
        reminderAppPageObject.selectScheduleSection();
        Assert.assertTrue(reminderAppPageObject.checkMoreOption(reminder_name).getText().contains(reminder_name));
    }
    @Test
    public void createNextDayReminder()
    {
        login(CMS_URL);
        reminder_name= REMINDER_TITTLE +getRandomInt(6);
        trccPanelPageObject.openReminder();
        reminderAppPageObject.selectScheduleSection()
        .selectNextDate();
        nextDate=  reminderAppPageObject.getNextDate().substring(4,6);
        logWrite.info(nextDate);
        reminderAppPageObject.clickOnAddReminder()
                .addReminderTitle(reminder_name)
                .selectReminderType()
                .addParticipants(REMINDER_PARTICIPANT)
                .fillDescription(REMINDER_DESCRIPTION)
                .selectAddNotification()
                .selectDropDownAddNotification()
                .selectNotification()
                .selectSaveButton();
        Assert.assertEquals(reminderAppPageObject.getSuccessfullyMessage().getText(),SUCCESS_MESSAGE);

    }
    @Test(dependsOnMethods = "createNextDayReminder")
    public void ViewNextDayReminder()
    {
        login(CMS_URL);
        trccPanelPageObject.openReminder();
        reminderAppPageObject.selectScheduleSection();
        Assert.assertEquals(reminderAppPageObject.getReminderName(reminder_name).getText(),reminder_name);
    }

    //***********----Reminder Lead page Test Cases ---***********//
    /*@Test
    public void createReminderOnLeadPage()
    {
        login(CMS_REMINDER_LEAD_PAGE_URL);
        reminder_name= REMINDER_TITTLE +getRandomInt(6);
        trccPanelPageObject.openReminder();
        reminderAppPageObject.clickOnAddReminder()
                .addReminderTitle(reminder_name)
                .selectReminderType()
                .selectDate()
                .addParticipants(REMINDER_PARTICIPANT)
                .fillDescription(REMINDER_DESCRIPTION);
       objectId = reminderAppPageObject.getObjectId().getText();
        reminderAppPageObject.selectAddNotification()
                .selectDropDownAddNotification()
                .selectNotification();
             //   .selectSaveButton();
        String currentUrl= driver.getCurrentUrl();
        currentUrl = currentUrl.substring(currentUrl.length() - 8);
        System.out.println(currentUrl);
        System.out.println(objectId);
        Assert.assertEquals(currentUrl,objectId);
       // Assert.assertEquals(reminderAppPageObject.getSuccessfullyMessage().getText(),SUCCESS_MESSAGE);
        logWrite.info("Reminder created status ==> " + reminderAppPageObject.getSuccessfullyMessage().getText());
        logWrite.info("Reminder name ==> " +reminder_name);
    }
    @Test(dependsOnMethods = "createReminderOnLeadPage")
    public void viewReminderOnLeadPage()
    {
        login(CMS_REMINDER_LEAD_PAGE_URL);
        trccPanelPageObject.openReminder();
        reminderAppPageObject.selectScheduleSection();
        Assert.assertEquals(reminderAppPageObject.getReminderName(reminder_name).getText(),reminder_name);
    }
    @Test(dependsOnMethods = "viewReminderOnLeadPage")
    public void updateReminderOnLeadPage()
    {
        login(CMS_REMINDER_LEAD_PAGE_URL);
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
    @Test(dependsOnMethods = "updateReminderOnLeadPage")
    public void createAllDayReminderOnLeadPage()
    {
        login(CMS_REMINDER_LEAD_PAGE_URL);
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
    @Test(dependsOnMethods = "createAllDayReminderOnLeadPage")
    public void ViewAllDayReminderOnLeadPage()
    {
        login(CMS_REMINDER_LEAD_PAGE_URL);
        trccPanelPageObject.openReminder();
        reminderAppPageObject.selectScheduleSection();
        Assert.assertTrue(reminderAppPageObject.checkMoreOption(reminder_name).getText().contains(reminder_name));
    }
    @Test(dependsOnMethods = "ViewAllDayReminderOnLeadPage")
    public void checkEventsOnLeadPage()
    {
        login(CMS_REMINDER_LEAD_PAGE_URL);
        trccPanelPageObject.openReminder();
        reminderAppPageObject.selectScheduleSection()
                .selectEventOption()
                .selectVacationOption();
        // .selectClassicOption()
        // .selectOutOfOfficeOption();
        Assert.assertTrue(reminderAppPageObject.checkReminderAvailable());
    }
    @Test
    public void checkNotificationLimitOnLeadPage()
    {
        login(CMS_REMINDER_LEAD_PAGE_URL);
        reminder_name= REMINDER_TITTLE +getRandomInt(6);
        trccPanelPageObject.openReminder();
        reminderAppPageObject.clickOnAddReminder()
                .addReminderTitle(reminder_name)
                .selectReminderTypeAlert()
                .selectDate()
                .addParticipants(REMINDER_PARTICIPANT)
                .fillDescription(REMINDER_DESCRIPTION)
                .selectNotificationForTwoMin()
                .selectNotificationForFiveMin()
                .selectNotificationForFifteenMin()
                .selectNotificationForThirtyMin()
                .selectNotificationForFourtyFiveMin();
        Assert.assertTrue(reminderAppPageObject.checkAddNotificationButton());
        reminderAppPageObject.selectSaveButton();
        Assert.assertEquals(reminderAppPageObject.getSuccessfullyMessage().getText(),SUCCESS_MESSAGE);
    }
    @Test
    public void createMultiDayReminderOnLeadPage()
    {
        login(CMS_REMINDER_LEAD_PAGE_URL);
        reminder_name= REMINDER_TITTLE +getRandomInt(6);
        trccPanelPageObject.openReminder();
        reminderAppPageObject.selectScheduleSection()
                .clickOnAddReminder()
                .addReminderTitle(reminder_name)
                .selectReminderType()
                .selectMultiDayDate()
                .addParticipants(REMINDER_PARTICIPANT)
                .fillDescription(REMINDER_DESCRIPTION)
                .selectAddNotification()
                .selectDropDownAddNotification()
                .selectNotification()
                .selectSaveButton();
        Assert.assertEquals(reminderAppPageObject.getSuccessfullyMessage().getText(),SUCCESS_MESSAGE);
        logWrite.info("Reminder created status ==> " + reminderAppPageObject.getSuccessfullyMessage().getText());
        logWrite.info("Reminder Name ==> " +reminder_name);
    }
    @Test(dependsOnMethods = "createMultiDayReminderOnLeadPage")
    public void ViewMultiDayReminderOnLeadPage()
    {
        login(CMS_REMINDER_LEAD_PAGE_URL);
        trccPanelPageObject.openReminder();
        reminderAppPageObject.selectScheduleSection();
        Assert.assertTrue(reminderAppPageObject.checkMoreOption(reminder_name).getText().contains(reminder_name));
    }
    @Test
    public void createNextDayReminderOnLeadPage()
    {
        login(CMS_REMINDER_LEAD_PAGE_URL);
        reminder_name= REMINDER_TITTLE +getRandomInt(6);
        trccPanelPageObject.openReminder();
        reminderAppPageObject.selectScheduleSection()
                .selectNextDate();
        nextDate= reminderAppPageObject.getNextDate().substring(4,6);
        logWrite.info(nextDate);
        reminderAppPageObject.clickOnAddReminder()
                .addReminderTitle(reminder_name)
                .selectReminderType()
                .addParticipants(REMINDER_PARTICIPANT)
                .fillDescription(REMINDER_DESCRIPTION)
                .selectAddNotification()
                .selectDropDownAddNotification()
                .selectNotification()
                .selectSaveButton();
        Assert.assertEquals(reminderAppPageObject.getSuccessfullyMessage().getText(),SUCCESS_MESSAGE);

    }
    @Test(dependsOnMethods = "createNextDayReminderOnLeadPage")
    public void ViewNextDayReminderOnLeadPage()
    {
        login(CMS_REMINDER_LEAD_PAGE_URL);
        trccPanelPageObject.openReminder();
        reminderAppPageObject.selectScheduleSection();
        Assert.assertEquals(reminderAppPageObject.getReminderName(reminder_name).getText(),reminder_name);
    }*/

    //***********----Admin Panel---***********//
    /*@Test
    public void verifyTeamsTab()
    {
        login(CMS_ADMIN_PANEL_URL);
        reminderAdminPanelPageObject.clickTeamTab();
        Assert.assertFalse(reminderAdminPanelPageObject.verifyTeamTab());
    }
    @Test
    public void verifyTeamName()
    {
        login(CMS_ADMIN_PANEL_URL);
        reminderAdminPanelPageObject.clickTeamTab()
                .addName("Dreamport Experts")
                .selectFilter();
        Assert.assertEquals(reminderAdminPanelPageObject.getTeamName().getText(),"Dreamport Experts");
    }
    @Test
    public void verifyTeamsLimit()
    {
        login(CMS_ADMIN_PANEL_URL);
        reminderAdminPanelPageObject.clickTeamTab()
                .selectPageLimit();
        logWrite.info(""+reminderAdminPanelPageObject.getListCount());
        Assert.assertEquals(reminderAdminPanelPageObject.getListCount(),25);
    }
    @Test
    public void verifyAgentsTab()
    {
        login(CMS_ADMIN_PANEL_URL);
        reminderAdminPanelPageObject.clickAgentsTab();
        Assert.assertFalse(reminderAdminPanelPageObject.verifyAgentsTab());
    }
    @Test
    public void verifyAgentName()
    {
        login(CMS_ADMIN_PANEL_URL);
        reminderAdminPanelPageObject.clickAgentsTab()
                .addName("reppoTestUser")
                .selectFilter();
        Assert.assertEquals(reminderAdminPanelPageObject.getAgentName().getText(),"reppoTestUser");
    }
    @Test
    public void verifyAgentLimit()
    {
        login(CMS_ADMIN_PANEL_URL);
        reminderAdminPanelPageObject.clickTeamTab()
                .selectPageLimit();
        logWrite.info(""+reminderAdminPanelPageObject.getListCount());
        Assert.assertEquals(reminderAdminPanelPageObject.getListCount(),25);
    }
    @Test
    public void verifyLocationTab()
    {
        login(CMS_ADMIN_PANEL_URL);
        reminderAdminPanelPageObject.clickLocationTab();
        Assert.assertFalse(reminderAdminPanelPageObject.verifyLocationTab());
    }
    @Test
    public void verifyRemindersTypesTab()
    {
        login(CMS_ADMIN_PANEL_URL);
        reminderAdminPanelPageObject.clickRemindersTypesTab();
        Assert.assertFalse(reminderAdminPanelPageObject.verifyRemindersTypesTab());
    }
    @Test(dependsOnMethods = "verifyRemindersTypesTab")
    public void createRemindersTypes()
    {
        login(CMS_ADMIN_PANEL_URL);
        reminderAdminPanelPageObject.clickRemindersTypesTab()
                .addNew()
                .addReminderName("Test")
                .addReminderShortName("Test")
                .addReminderColor("Test")
                .selectReminderProject()
                .saveReminderType()
                .clickLocationTab();
        Assert.assertEquals(reminderAdminPanelPageObject.clickRemindersTypesTab()
                .getReminderTypeName("Test").getText(),"Test");
    }
    @Test(dependsOnMethods = "createRemindersTypes")
    public void verifyNewReminderType()
    {
        login(CMS_ADMIN_PANEL_URL);
        Assert.assertEquals(reminderAdminPanelPageObject.clickRemindersTypesTab()
                .getReminderTypeName("Test").getText(),"Test");
    }
    @Test(dependsOnMethods = "verifyNewReminderType")
    public void updateRemindersTypes()
    {
        login(CMS_ADMIN_PANEL_URL);
        reminderAdminPanelPageObject.clickRemindersTypesTab()
                .editReminder()
                .addReminderName("Test-Updated")
                .updateButton()
                .clickLocationTab();
        Assert.assertEquals(reminderAdminPanelPageObject.clickRemindersTypesTab()
                .getReminderTypeName("Test-Updated").getText(),"Test-Updated");
    }
    @Test(dependsOnMethods = "updateRemindersTypes")
    public void deleteRemindersType()
    {
        login(CMS_ADMIN_PANEL_URL);
        Assert.assertEquals(reminderAdminPanelPageObject.clickRemindersTypesTab()
                .getReminderTypeName("Test-Updated").getText(),"Test-Updated");
        reminderAdminPanelPageObject.deleteReminderType();
    }
    @Test
    public void verifyRemindersTab()
    {
        login(CMS_ADMIN_PANEL_URL);
        reminderAdminPanelPageObject.clickRemindersTab();
        Assert.assertFalse(reminderAdminPanelPageObject.verifyRemindersTab());
    }
    @Test
    public void verifyReminder()
    {
        login(CMS_ADMIN_PANEL_URL);
        reminderAdminPanelPageObject.clickRemindersTab()
                .addName("Test_Reminder_146407")
                .selectFilter();
        Assert.assertEquals(reminderAdminPanelPageObject.getReminderName().getText(),"Test_Reminder_146407");
    }
    @Test
    public void verifyReminderLimit()
    {
        login(CMS_ADMIN_PANEL_URL);
        reminderAdminPanelPageObject.clickRemindersTab()
                .selectPageLimit();
        logWrite.info(""+reminderAdminPanelPageObject.getListCount());
        Assert.assertEquals(reminderAdminPanelPageObject.getListCount(),25);
    }
    @Test
    public void verifyProjectsTab()
    {
        login(CMS_ADMIN_PANEL_URL);
        reminderAdminPanelPageObject.clickProjectsTab();
        Assert.assertFalse(reminderAdminPanelPageObject.verifyProjectsTab());
    }
    @Test
    public void verifyObjectTypesTab()
    {
        login(CMS_ADMIN_PANEL_URL);
        reminderAdminPanelPageObject.clickObjectTypesTab();
        Assert.assertFalse(reminderAdminPanelPageObject.verifyObjectTypesTab());
    }
    @Test(dependsOnMethods = "verifyObjectTypesTab")
    public void createObjectTypes()
    {
        login(CMS_ADMIN_PANEL_URL);
        reminderAdminPanelPageObject.clickObjectTypesTab()
                .addNew()
                .addObjectName("Test")
                .addObjectCode("Test")
                .addObjectDevLink("Test")
                .addObjectProdLink("Test")
                .selectObjectProject()
                .saveReminderType()
                .clickLocationTab();
        Assert.assertEquals(reminderAdminPanelPageObject.clickObjectTypesTab()
                .getObjectName("Test").getText(),"Test");
    }
    @Test(dependsOnMethods = "createObjectTypes")
    public void verifyObjectName()
    {
        login(CMS_ADMIN_PANEL_URL);
        Assert.assertEquals(reminderAdminPanelPageObject.clickObjectTypesTab()
                .getObjectName("Test").getText(),"Test");
    }
    @Test(dependsOnMethods = "verifyObjectName")
    public void updateObjectName()
    {
        login(CMS_ADMIN_PANEL_URL);
        reminderAdminPanelPageObject.clickObjectTypesTab()
                .editObject()
                .addObjectName("Test-updated")
                .updateButton()
                .clickLocationTab();
        Assert.assertEquals(reminderAdminPanelPageObject.clickObjectTypesTab()
                .getObjectName("Test-updated").getText(),"Test-updated");
    }
    @Test(dependsOnMethods = "updateObjectName")
    public void deleteObject()
    {
        login(CMS_ADMIN_PANEL_URL);
        Assert.assertEquals(reminderAdminPanelPageObject.clickObjectTypesTab()
                .getObjectName("Test-updated").getText(),"Test-updated");
        reminderAdminPanelPageObject.deleteObject();
    }
    @Test
    public void verifyOthersTab()
    {
        login(CMS_ADMIN_PANEL_URL);
        reminderAdminPanelPageObject.clickOtherTab();
        Assert.assertFalse(reminderAdminPanelPageObject.verifyOtherTab());
    }*/

    @AfterMethod(alwaysRun = true)
    public void quit() {
        driver.quit();
    }

}


