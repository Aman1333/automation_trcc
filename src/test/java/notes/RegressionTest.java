package notes;

import org.junit.Assert;
import org.testng.annotations.*;
import selenium.BaseClass;
import selenium.BaseTest;
import selenium.cms.CmsHeaderBarObject;
import selenium.cms.CmsLoginPageObject;
import selenium.notes.NotesAppPageObject;
import selenium.trcc.TrccPanelPageObject;

import java.io.Console;
import java.util.PriorityQueue;

import static common.Helper.getRandomInt;
import static constants.TestData.*;

@Listeners(common.CustomListener.class)
public class RegressionTest extends BaseTest
{

    BaseClass baseClass;
    CmsLoginPageObject cmsLoginPageObject;
    CmsHeaderBarObject cmsHeaderBarObject;
    TrccPanelPageObject trccPanelPageObject;
    NotesAppPageObject notesAppPageObject;
    String notes_name;
    String notes_name_new;
    String notes_name_lp;
    String updated_notes_name;
    String updated_notes_name_lp;
    @BeforeClass(alwaysRun = true)
    public void initiate() {
        driver = getDriver();
        cmsLoginPageObject = new CmsLoginPageObject(driver);
        cmsHeaderBarObject = new CmsHeaderBarObject(driver);
        notesAppPageObject = new NotesAppPageObject(driver);
        trccPanelPageObject = new TrccPanelPageObject(driver);
        baseClass = new BaseClass(driver);
    }
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
    public  void deleteNote(String noteName)
    {
        notesAppPageObject.searchNote(noteName)
                .checkActiveSectionAvailability()
                .selectOption()
                .deleteActiveNotes()
                .clickOnOkButton();
    }
    @AfterMethod(alwaysRun = true)
    public void refreshPage()
    {
        baseClass.refresh();
    }

    public void loginOnLeadPage(String url)
    {
        logWrite.info("Test: Login to CMS with user " + CMS_STAGE_USER);
        baseClass.openPage(url);
        logWrite.info("Attempting to login using test user credentials");
       logWrite.info("Asserting that user is logged in with display name: " + DISPLAY_NAME);
    }


    public void createNotesByPlusIcon()
    {
        //login(CMS_URL);
        notes_name= NOTES_TITTLE +getRandomInt(6);
        trccPanelPageObject.openNotes();
        notesAppPageObject.clickPlusIcon()
                .addTitle(notes_name)
                .addDescription(NOTES_DESCRIPTION)
                .saveNote();
        Assert.assertEquals(notesAppPageObject.getNotesName(notes_name).getText(),notes_name);
    }
    @Test
    public void createNotesWithLogin()
    {
        login(CMS_URL);
        notes_name= NOTES_TITTLE +getRandomInt(6);
        trccPanelPageObject.openNotes();
        notesAppPageObject.clickPlusIcon()
                .addTitle(notes_name)
                .addDescription(NOTES_DESCRIPTION)
                .saveNote();
        Assert.assertEquals(notesAppPageObject.getNotesName(notes_name).getText(),notes_name);
       deleteNote(notes_name);
    }
    @Test(dependsOnMethods = "createNotesWithLogin")
    public void editNotesDescription()
    {
       notes_name=NOTES_TITTLE +getRandomInt(6);
        updated_notes_name= NOTES_TITTLE +getRandomInt(6);
       trccPanelPageObject.openNotes();
        notesAppPageObject.clickPlusIcon()
                .addTitle(notes_name)
                .addDescription(NOTES_DESCRIPTION)
                .saveNote()
                .searchNote(notes_name)
                .checkActiveSectionAvailability()
                .selectOption()
                .editNotes()
                .addUpdateTitle(updated_notes_name)
                .addUpdateDescription(NOTES_DESCRIPTION_UPDATED)
                .doneNote()
                .searchNoteBox(updated_notes_name)
                .selectOption()
                .editNotes();
        Assert.assertTrue(notesAppPageObject.getNotesName(updated_notes_name).getText().contains(updated_notes_name));
        Assert.assertTrue(notesAppPageObject.getNotesDescription().getText().contains(NOTES_DESCRIPTION_UPDATED));
        baseClass.refresh();
        trccPanelPageObject.openNotes();
        deleteNote(updated_notes_name);
    }
    @Test(dependsOnMethods = "editNotesDescription")
    public void checkTitleValidation()
    {
        trccPanelPageObject.openNotes();
        notesAppPageObject.clickAddIcon()
                .addTitle("A")
                .saveNote();
        logWrite.info(" "+notesAppPageObject.getTitleValidationMessage().getText());
        logWrite.info(" "+NOTES_TITTLE_VALIDATION_MSG);
        Assert.assertTrue(notesAppPageObject.getTitleValidationMessage().getText().contains(NOTES_TITTLE_VALIDATION_MSG));

    }
    @Test(dependsOnMethods = "checkTitleValidation")
    public void checkDescriptionValidation()
    {
        trccPanelPageObject.openNotes();
        notesAppPageObject.clickAddIcon()
                .addDescription("A")
                .saveNote();
        Assert.assertTrue(notesAppPageObject.getDescriptionValidationMessage().getText().contains(NOTES_DESCRIPTION_VALIDATION_MSG));
    }
    @Test(dependsOnMethods = "checkDescriptionValidation")
    public void checkNextActiveNotesPage()
    {
        trccPanelPageObject.openNotes();
        Assert.assertTrue(notesAppPageObject.clickOnActiveNotesNextPage());
    }
    @Test(dependsOnMethods = "checkNextActiveNotesPage")
    public void checkNextDoneNotesPage()
    {
        trccPanelPageObject.openNotes();
        Assert.assertTrue(notesAppPageObject.clickOnDoneNotesNextPage());
    }
    @Test(dependsOnMethods = "checkNextDoneNotesPage")
    public void checkFiltersForPrivateNote()
    {
        createNotesByPlusIcon();
        notesAppPageObject.clickAllNotes()
                .clickMyPrivateNote();
        Assert.assertEquals(notesAppPageObject.getNotesName(notes_name).getText(),notes_name);

    }
    @Test(dependsOnMethods = "checkFiltersForPrivateNote")
    public void checkFiltersForLinkedNote()
    {
        trccPanelPageObject.openNotes();
        notesAppPageObject.clickAllNotes()
                .clickLinkedNote();
        Assert.assertFalse(notesAppPageObject.getNotesNameOnLead().getText().contains(updated_notes_name));
    }
    @Test(dependsOnMethods = "checkFiltersForLinkedNote")
    public void ViewNotes()
    {
        notes_name= NOTES_TITTLE +getRandomInt(6);
        trccPanelPageObject.openNotes();
        notesAppPageObject.clickPlusIcon()
                .addTitle(notes_name)
                .addDescription(NOTES_DESCRIPTION)
                .saveNote();
        notesAppPageObject.searchNote(notes_name);
        Assert.assertEquals(notesAppPageObject.getNotesName(notes_name).getText(),notes_name);
    }
    @Test(dependsOnMethods = "ViewNotes")
    public void createNotesWithoutTile()
    {
        trccPanelPageObject.openNotes();
        notesAppPageObject.clickAddIcon()
                .addDescription(NOTES_DESCRIPTION)
                .saveNote()
                .searchNote(NOTES_DESCRIPTION)
                .selectOption()
                .editNotes();
        Assert.assertTrue(notesAppPageObject.getNotesDescription().getText().contains(NOTES_DESCRIPTION));
        Assert.assertTrue(notesAppPageObject.getNotesTitle().getText().isEmpty());
        baseClass.refresh();
        trccPanelPageObject.openNotes();
        deleteNote(NOTES_DESCRIPTION);
    }
    @Test(dependsOnMethods = "createNotesWithoutTile")
    public void createNotesWithoutDescription()
    {
        trccPanelPageObject.openNotes();
        notesAppPageObject.clickAddIcon()
                .addTitle(NOTES_TITTLE)
                .saveNote()
                .searchNote(NOTES_TITTLE);
        Assert.assertTrue(notesAppPageObject.getNotesName(NOTES_TITTLE).getText().contains(NOTES_TITTLE));
        baseClass.refresh();
        trccPanelPageObject.openNotes();
        deleteNote(NOTES_TITTLE);
    }
    @Test(dependsOnMethods = "createNotesWithoutDescription")
    public void checkEditOptionForCompletedNote()
    {
        createNotesByPlusIcon();
        trccPanelPageObject.openNotes();
        notesAppPageObject.searchNote(notes_name)
                .checkActiveSectionAvailability()
                .selectMarkAsComplete()
                .searchNoteBox(notes_name)
                .selectOption();
        Assert.assertTrue(notesAppPageObject.editDeletedNote());
        baseClass.refresh();
        trccPanelPageObject.openNotes();
        deleteNote(notes_name);
    }
    @Test(dependsOnMethods = "checkEditOptionForCompletedNote")
    public void checkAtoZFilter()
    {

        trccPanelPageObject.openNotes();
        notes_name= "AAAAAAAA-" +getRandomInt(6);
        notesAppPageObject.clickPlusIcon()
                .addTitle(notes_name)
                .addDescription("Testing filters")
                .saveNote()
                .clickOnNewestFirst()
                .clickOnAtoZ();
        Assert.assertEquals(notesAppPageObject.getNotesName(notes_name).getText(),notes_name);
        deleteNote(notes_name);
    }
    @Test(dependsOnMethods = "checkAtoZFilter")
    public void checkNewestFirstFilter()
    {
       notes_name= "AAA-" +getRandomInt(6);
        trccPanelPageObject.openNotes();
        notesAppPageObject.clickPlusIcon()
                .addTitle(notes_name)
                .addDescription("Testing filters")
                .saveNote();
        Assert.assertTrue(
                notesAppPageObject.clickOnNewestFirst()
                        .clickOnAtoZ()
                        .clickOnActiveNotesNextPage()
        );
        notesAppPageObject .clickOnAtoZ()
                .clickOnNewestFirst();
        Assert.assertEquals(notesAppPageObject.getNotesName(notes_name).getText(),notes_name);
       deleteNote(notes_name);
    }
    @Test(dependsOnMethods = "checkNewestFirstFilter")
    public void checkZtoAZFilter()
    {
        notes_name= "ZZZ" +getRandomInt(6);
        trccPanelPageObject.openNotes();
        notesAppPageObject.clickPlusIcon()
                .addTitle(notes_name)
                .addDescription("Testing filters")
                .saveNote()
                .clickOnNewestFirst()
                .clickOnZtoA();
        Assert.assertEquals(notesAppPageObject.getNotesName(notes_name).getText(),notes_name);
        baseClass.refresh();
        trccPanelPageObject.openNotes();
        deleteNote(notes_name);
    }
    @Test(dependsOnMethods = "checkZtoAZFilter")
    public void checkOldestFirstFilter()
    {
       // login(CMS_URL);
        trccPanelPageObject.openNotes();
        notesAppPageObject
                .clickOnNewestFirst()
                .clickOnOldestFirst();
        logWrite.info(notesAppPageObject.getDoneNotesNameOnLead().getText());
        Assert.assertTrue(notesAppPageObject.getNotesNameOnLead().getText().contains("Test_Notes_264660"));
    }
    @Test(dependsOnMethods = "checkOldestFirstFilter")
    public void checkDeletedNotes()
    {
        createNotesByPlusIcon();
        baseClass.refresh();
        trccPanelPageObject.openNotes();
        notesAppPageObject.searchNote(notes_name)
                .checkActiveSectionAvailability()
                .selectOption()
                .deleteActiveNotes()
                .clickOnOkButton();
        Assert.assertTrue(notesAppPageObject.verifyNotesVisibility().getText().contains(NOTES_NOT_FOUND_MESSAGE));

    }

    //-----Test the lead page----------//
    public void createNotesOnLeadPage()
    {
        notes_name_lp= NOTES_TITTLE +getRandomInt(6);
        trccPanelPageObject.openNotes();
        notesAppPageObject.clickAddIcon()
                .addTitle(notes_name_lp)
                .addDescription(NOTES_DESCRIPTION)
                .saveNote()
                .searchNote(notes_name_lp);
        Assert.assertTrue(notesAppPageObject.getNotesNameOnLead().getText().contains(notes_name_lp));
    }
   @Test(dependsOnMethods = "checkDeletedNotes")
    public void createNotesOnLeadPageWithLogin()
    {
       // initiate();
        loginOnLeadPage(CMS_NOTES_LEAD_PAGE_URL);
       // login(CMS_NOTES_LEAD_PAGE_URL);
        notes_name_lp= NOTES_TITTLE +getRandomInt(6);
        trccPanelPageObject.openNotes();
        notesAppPageObject.clickAddIcon()
                .addTitle(notes_name_lp)
                .addDescription(NOTES_DESCRIPTION)
                .saveNote()
                .searchNote(notes_name_lp);
        Assert.assertTrue(notesAppPageObject.getNotesNameOnLead().getText().contains(notes_name_lp));
    }
    @Test(dependsOnMethods = "createNotesOnLeadPageWithLogin")
    public void checkFiltersOnLeadPage()
    {
        trccPanelPageObject.openNotes();
        notesAppPageObject.clickMyAllNote()
                        .clickMyLinkedNote();
        String currentUrl= driver.getCurrentUrl();
        currentUrl = currentUrl.substring(currentUrl.length() - 8);
        Assert.assertTrue(notesAppPageObject.getNotesNameOnLead().getText().contains(currentUrl));
    }
    @Test(dependsOnMethods = "checkFiltersOnLeadPage")
    public void checkObjectIdOnLeadPage()
    {
        trccPanelPageObject.openNotes();
        notesAppPageObject.searchNote(notes_name_lp);
        String currentUrl= driver.getCurrentUrl();
        currentUrl = currentUrl.substring(currentUrl.length() - 8);
        Assert.assertTrue(notesAppPageObject.getNotesNameOnLead().getText().contains(currentUrl));
    }
    @Test(dependsOnMethods = "checkObjectIdOnLeadPage")
    public void editNotesOnLeadPage()
    {
        updated_notes_name_lp= NOTES_TITTLE +getRandomInt(6);
        trccPanelPageObject.openNotes();
        notesAppPageObject.searchNote(notes_name_lp)
                .checkActiveSectionAvailability()
                .selectOption()
                .editNotes()
                .addUpdateTitle(updated_notes_name_lp)
                .doneNote()
                .searchNoteBox(updated_notes_name_lp);
        Assert.assertTrue(notesAppPageObject.getNotesNameOnLead().getText().contains(updated_notes_name_lp));
    }
    @Test(dependsOnMethods = "editNotesOnLeadPage")
    public void markNoteAsDoneOnLeadPage()
    {
        trccPanelPageObject.openNotes();
        notesAppPageObject.searchNote(updated_notes_name_lp)
                .selectMarkAsComplete()
                .checkDoneSectionAvailability()
                .searchNoteBox(updated_notes_name_lp);
        Assert.assertTrue(notesAppPageObject.getDoneNotesNameOnLead().getText().contains(updated_notes_name_lp));
    }
    @Test(dependsOnMethods = "markNoteAsDoneOnLeadPage")
    public void markNoteAsUnDoneOnLeadPage()
    {
        trccPanelPageObject.openNotes();
        //updated_notes_name ="Test_Notes_016175";
        notesAppPageObject.searchNote(updated_notes_name_lp)
                .selectMarkAsUnDone()
                .checkActiveSectionAvailability()
                .searchNoteBox(updated_notes_name_lp);
        Assert.assertTrue(notesAppPageObject.getNotesNameOnLead().getText().contains(updated_notes_name_lp));
    }
    @Test(dependsOnMethods = "markNoteAsUnDoneOnLeadPage")
    public void deleteActiveNotesOnLeadPage()
    {
        trccPanelPageObject.openNotes();
        notesAppPageObject.searchNote(updated_notes_name_lp)
                .searchNoteBox(updated_notes_name_lp)
                .checkActiveSectionAvailability()
                .selectOption()
                .deleteActiveNotes()
                .clickOnOkButton();
        Assert.assertTrue(notesAppPageObject.getNotesNameOnLead().getText().contains(updated_notes_name_lp));
    }
    @Test(dependsOnMethods = "deleteActiveNotesOnLeadPage")
    public void deleteDoneNotesOnLeadPage()
    {
        createNotesOnLeadPage();
        baseClass.refresh();
        trccPanelPageObject.openNotes();
        notesAppPageObject.searchNote(notes_name_lp)
                .selectMarkAsComplete()
                .checkCompleteNote()
                .checkDoneSectionAvailability()
                .selectOptionForCompletedNote()
                .deleteCompletedNote()
                .clickOnOkButton();
        Assert.assertTrue(notesAppPageObject.verifyNotesVisibility().getText().contains(NOTES_NOT_FOUND_MESSAGE));
    }
    @Test(dependsOnMethods = "deleteDoneNotesOnLeadPage")
    public void checkAtoZFilterOnLeadPage()
    {
        trccPanelPageObject.openNotes();
        notes_name= "AAA-" +getRandomInt(6);
        notesAppPageObject.clickPlusIcon()
                .addTitle(notes_name)
                .addDescription("Testing filters")
                .saveNote();
        notesAppPageObject
                .clickOnNewestFirst()
                .clickOnAtoZ();
        Assert.assertTrue(notesAppPageObject.getNotesNameOnLead().getText().contains("AAA"));
        deleteNote(notes_name);
    }
    @Test(dependsOnMethods = "checkAtoZFilterOnLeadPage")
    public void checkNewestFirstFilterOnLeadPage()
    {
        trccPanelPageObject.openNotes();
        notes_name_new= "AAA-" +getRandomInt(6);
        notesAppPageObject.clickPlusIcon()
                .addTitle(notes_name_new)
                .addDescription("Testing filters")
                .saveNote();
        Assert.assertTrue(
                notesAppPageObject.clickOnNewestFirst()
                        .clickOnAtoZ()
                        .clickOnActiveNotesNextPage()
        );
        notesAppPageObject .clickOnAtoZ()
                .clickOnNewestFirst();
        Assert.assertTrue(notesAppPageObject.getNotesNameOnLead().getText().contains("AAA"));
        deleteNote(notes_name_new);
    }
    @Test(dependsOnMethods = "checkNewestFirstFilterOnLeadPage")
    public void checkZtoAZFilterOnLeadPage()
    {
        notes_name= "ZZZ" +getRandomInt(6);
        trccPanelPageObject.openNotes();
        notesAppPageObject.clickPlusIcon()
                .addTitle(notes_name)
                .addDescription("Testing filters")
                .saveNote()
                .clickOnNewestFirst()
                .clickOnZtoA();
        Assert.assertTrue(notesAppPageObject.getNotesNameOnLead().getText().contains("ZZZ"));
        deleteNote(notes_name);
    }
    @Test(dependsOnMethods = "checkZtoAZFilterOnLeadPage")
    public void checkOldestFirstFilterOnLeadPage()
    {
        trccPanelPageObject.openNotes();
        notesAppPageObject
                .clickOnNewestFirst()
                .clickOnOldestFirst();
        logWrite.info(notesAppPageObject.getDoneNotesNameOnLead().getText());
        Assert.assertTrue(notesAppPageObject.getNotesNameOnLead().getText().contains("Test_Notes_264660"));
    }
    @AfterClass(alwaysRun = true)
    public void quit() {
        driver.quit();
    }

}
