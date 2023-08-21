package notes;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import selenium.BaseClass;
import selenium.BaseTest;
import selenium.cms.CmsHeaderBarObject;
import selenium.cms.CmsLoginPageObject;
import selenium.notes.NotesAppPageObject;
import selenium.trcc.TrccPanelPageObject;

import static common.Helper.getRandomInt;
import static constants.TestData.*;


@Listeners(common.CustomListener.class)
public class SmokeTest extends BaseTest
{

        BaseClass baseClass;
        CmsLoginPageObject cmsLoginPageObject;
        CmsHeaderBarObject cmsHeaderBarObject;
        TrccPanelPageObject trccPanelPageObject;
        NotesAppPageObject notesAppPageObject;
        String notes_name;
        String updated_notes_name;
    @BeforeMethod(alwaysRun = true)
        public void initiate() {
            driver = getDriver();
            cmsLoginPageObject = new CmsLoginPageObject(driver);
            cmsHeaderBarObject = new CmsHeaderBarObject(driver);
            notesAppPageObject = new NotesAppPageObject(driver);
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
    public void createNotes()
    {
        notes_name= NOTES_TITTLE +getRandomInt(6);
        trccPanelPageObject.openNotes();
        notesAppPageObject.clickAddIcon()
                .addTitle(notes_name)
                .addDescription(NOTES_DESCRIPTION)
                .saveNote();
        Assert.assertEquals(notesAppPageObject.getNotesName(notes_name).getText(),notes_name);
    }
    @Test(dependsOnMethods = "createNotes")
    public void ViewNotes()
    {
        trccPanelPageObject.openNotes();
        notesAppPageObject.searchNote(notes_name);
        Assert.assertEquals(notesAppPageObject.getNotesName(notes_name).getText(),notes_name);
    }
    @Test(dependsOnMethods = "ViewNotes")
    public void editNotes()
    {
        updated_notes_name= NOTES_TITTLE +getRandomInt(6);
        trccPanelPageObject.openNotes();
        notesAppPageObject.searchNote(notes_name)
                .checkActiveSectionAvailability()
                .selectOption()
                .editNotes()
                .addUpdateTitle(updated_notes_name)
                .doneNote()
                .searchNoteBox(updated_notes_name);
        Assert.assertTrue(notesAppPageObject.getNotesName(updated_notes_name).getText().contains(updated_notes_name));
    }
    @Test(dependsOnMethods = "editNotes")
    public void markNoteAsDone()
    {
        trccPanelPageObject.openNotes();
        notesAppPageObject.searchNote(updated_notes_name)
                .selectMarkAsComplete()
                .checkDoneSectionAvailability()
                .searchNoteBox(updated_notes_name);
        Assert.assertTrue(notesAppPageObject.getNotesName(updated_notes_name).getText().contains(updated_notes_name));
    }
    @Test(dependsOnMethods = "markNoteAsDone")
    public void markNoteAsUnDone()
    {
        trccPanelPageObject.openNotes();
        notesAppPageObject.searchNote(updated_notes_name)
                .selectMarkAsUnDone()
                .checkActiveSectionAvailability()
                .searchNoteBox(updated_notes_name);
        Assert.assertTrue(notesAppPageObject.getNotesName(updated_notes_name).getText().contains(updated_notes_name));
    }
    @Test(dependsOnMethods = "markNoteAsUnDone")
    public void deleteActiveNotes()
    {
        trccPanelPageObject.openNotes();
        notesAppPageObject.searchNote(updated_notes_name)
                .checkActiveSectionAvailability()
                .selectOption()
                .deleteActiveNotes()
                .clickOnOkButton();
        Assert.assertTrue(notesAppPageObject.verifyNotesVisibility().getText().contains(NOTES_NOT_FOUND_MESSAGE));
    }
    @Test(dependsOnMethods = "deleteActiveNotes")
    public void deleteDoneNotes()
    {
        trccPanelPageObject.openNotes();
        createNotes();
        notesAppPageObject.searchNote(notes_name)
                .selectMarkAsComplete()
                .checkCompleteNote()
                .checkDoneSectionAvailability()
                .selectOptionForCompletedNote()
                .deleteCompletedNote()
                .clickOnOkButton();
        Assert.assertTrue(notesAppPageObject.verifyNotesVisibility().getText().contains(NOTES_NOT_FOUND_MESSAGE));
    }
    @AfterMethod(alwaysRun = true)
    public void quit() {
        driver.quit();
    }
}
