package selenium.notes;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import selenium.BaseClass;


public class NotesAppPageObject extends BaseClass
{
    public NotesAppPageObject(WebDriver driver){
        super(driver);
    }

    @FindBy(css = "button[aria-label='add']")
    private WebElement addButton;
    @FindBy(xpath = "//button[normalize-space()='Add Note']")
    private WebElement plusIcon;
    @FindBy(xpath = "//input[@id='note-title']")
    private WebElement title;
    @FindBy(css = "#note-title")
    private WebElement update_title;
    @FindBy(xpath = "//textarea[@id='note-description']")
    private WebElement description;
    @FindBy(xpath = "//textarea[@placeholder='Description.....']")
    private WebElement updateDescription;
    @FindBy(xpath = "//button[normalize-space()='Done']")
    private WebElement doneButton;
    @FindBy(xpath = "(//button[normalize-space()='UPDATE'])[1]")
    private WebElement updateButton;
    @FindBy(xpath = "//*[@data-testid='SearchIcon']")
    private WebElement searchButton;
    @FindBy(xpath = "//input[@id='notes-search']")
    private WebElement searchButtonBox;
    @FindBy(xpath = "//button[@id='action-menu']")
    private WebElement optionButton;
    @FindBy(css = "#action-menu")
    private WebElement optionButtonForCompletedNote;
    @FindBy(xpath = "//li[normalize-space()='Edit']")
    private WebElement editOption;
    @FindBy(xpath = "//li[normalize-space()='Delete']")
    private WebElement deleteOption;
    @FindBy(xpath = "//li[@role='menuitem']")
    private WebElement deleteOptionForDoneNote;
    @FindBy(xpath = "//button[@aria-label='Click to mark as done']")
    private WebElement markAsCompleteButton;
    @FindBy(xpath = "//button[@aria-label='Click to mark as undone']")
    private WebElement markAsUnDoneButton;
    @FindBy(xpath = "//p[normalize-space()='Done notes']")
    private WebElement doneSection;
    @FindBy(xpath = "//p[normalize-space()='Active notes']")
    private WebElement activeSection;
    @FindBy(xpath = "//button[normalize-space()='OK']")
    private WebElement okButton;
    @FindBy(css = "#panel1a-header")
    private WebElement completedNote;
    @FindBy(xpath = "//p[normalize-space()='No Record Found']")
    private WebElement noRecordFound;
    @FindBy(xpath = "//p[normalize-space()='1–1 of 1']")
    private WebElement noteCount;
    @FindBy(xpath = "(//div[normalize-space()='Title length should be 3 to 50 characters'])[1]")
    private WebElement tileValidationMessage;
    @FindBy(xpath = "(//div[normalize-space()='Description should be 5 to 500 characters'])[1]")
    private WebElement descriptionValidationMessage;
    @FindBy(xpath = "//p[normalize-space()='Active notes']/../../following-sibling::div/div/div/p")
    private WebElement notename;
    @FindBy(xpath = "//p[normalize-space()='Done notes']/../../following-sibling::div/div/div/p")
    private WebElement donenotename;
    @FindBy(xpath = "(//button[@title='Go to next page'])[1]")
    private WebElement activePagination;
    @FindBy(xpath = "(//button[@title='Go to next page'])[2]")
    private WebElement donePagination;
    @FindBy(xpath = "//*[contains(text(),'My Linked Notes')]")
    private WebElement myLinkedNote;
    @FindBy(xpath = "//*[contains(text(),'My All Notes')]")
    private WebElement myAllNote;
    @FindBy(xpath = "//*[normalize-space()='My Private Notes']")
    private WebElement myPrivateNote;
    @FindBy(xpath = "//*[contains(text(),'All Notes')]")
    private WebElement allNotes;
    @FindBy(xpath = "//*[contains(text(),'My Linked')]")
    private WebElement linkedNote;
    @FindBy(xpath = "//*[contains(text(),'Newest First')]")
    private WebElement newestFirst;
    @FindBy(xpath = "//*[normalize-space()='Oldest First']")
    private WebElement oldestFirst;
    @FindBy(xpath = "//*[normalize-space()='Title - A to Z']")
    private WebElement atoz;
    @FindBy(xpath = "//*[normalize-space()='Title - Z to A']")
    private WebElement ztoa;

    public NotesAppPageObject clickAddIcon()
    {
        this.waitForElementToBeClickable(addButton, TIMEOUT_15);
        addButton.click();
        return this;
    }
    public NotesAppPageObject clickPlusIcon()
    {
        this.waitForElementToBeClickable(plusIcon, TIMEOUT_5);
        plusIcon.click();
        return this;
    }
    public NotesAppPageObject addTitle(String titleText)
    {
        this.waitForElementToBeClickable(title, TIMEOUT_15);
         title.sendKeys(titleText);
        return this;
    }
    public NotesAppPageObject addUpdateTitle(String titleText)
    {
        this.waitForElementToBeClickable(update_title, TIMEOUT_15);
        String back = Keys.BACK_SPACE.toString();
        update_title.sendKeys(back+back+back+back+back+back+back+back+back+back
                +back+back+back+back+back+back+back+back+ titleText);
        return this;
    }
    public NotesAppPageObject addUpdateDescription(String descriptionText)
    {
        this.waitForElementToBeClickable(description, TIMEOUT_15);
        String back = Keys.BACK_SPACE.toString();
        clear(description);
        //((JavascriptExecutor) driver).executeScript("arguments[0].value ='';", description);
        description.sendKeys(descriptionText);
       // fill(description,descriptionText);
        return this;
    }
    public NotesAppPageObject addDescription(String descriptionText)
    {
        this.waitForElementToBeClickable(description, TIMEOUT_10);
        description.sendKeys(descriptionText);
        return this;
    }
    public NotesAppPageObject saveNote()
    {
        this.waitForElementToBeClickable(doneButton, TIMEOUT_10);
        doneButton.click();
        this.waitForElementInvisibility(title,TIMEOUT_20);
        return this;
    }
    public NotesAppPageObject doneNote()
    {
        this.waitForElementVisibility(doneButton, TIMEOUT_20);
        doneButton.click();
        this.waitForElementInvisibility(title,TIMEOUT_10);
        return this;
    }
    public NotesAppPageObject searchNote(String name)
    {
        this.waitForElementToBeClickable(searchButton, TIMEOUT_10);
        searchButton.click();
        searchButtonBox.clear();
        searchButtonBox.sendKeys(name);
        this.waitForElementVisibility(noteCount, TIMEOUT_10);
        this.waitForElementVisibilityByXpath(By.xpath("//p[normalize-space()='"+name+"']"), TIMEOUT_10);
        return this;
    }
    public NotesAppPageObject searchNoteBox(String name)
    {
        this.waitForElementToBeClickable(searchButtonBox, TIMEOUT_10);
        String back = Keys.BACK_SPACE.toString();
        searchButtonBox.sendKeys(back+back+back+back+back+back+back+back+back+back
                +back+back+back+back+back+back+back+back+ name);
         return this;
    }
    public NotesAppPageObject selectOption()
    {
        this.waitForElementVisibility(noteCount, TIMEOUT_10);
        this.waitForElementToBeClickable(optionButton, TIMEOUT_10);
        optionButton.click();
        return this;
    }
    public NotesAppPageObject selectOptionForCompletedNote()
    {
        this.waitForElementVisibility(noteCount, TIMEOUT_10);
        this.waitForElementToBeClickable(optionButtonForCompletedNote, TIMEOUT_10);
        optionButtonForCompletedNote.click();
        return this;
    }
    public WebElement getTitleValidationMessage ()
    {
        this.waitForElementVisibility(tileValidationMessage, TIMEOUT_10);
        return tileValidationMessage;
    }
    public WebElement getDescriptionValidationMessage ()
    {
        this.waitForElementVisibility(descriptionValidationMessage, TIMEOUT_10);
        return descriptionValidationMessage;
    }
    public WebElement getNotesName (String notesName)
    {
        this.waitForElementVisibilityByXpath(By.xpath("(//p[normalize-space()='"+notesName+"'])[1]"), TIMEOUT_10);
        return driver.findElement(By.xpath("(//p[normalize-space()='"+notesName+"'])[1]"));
    }
    public WebElement getNotesNameOnLead ()
    {
        this.waitForElementVisibility(notename, TIMEOUT_10);
        return notename;
    }
    public WebElement getDoneNotesNameOnLead ()
    {
        this.waitForElementVisibility(donenotename, TIMEOUT_10);
        return donenotename;
    }
    public WebElement getNotesDescription ()
    {
        this.waitForElementVisibility(description, TIMEOUT_10);
        return description;
    }
    public WebElement getNotesTitle ()
    {
        this.waitForElementVisibility(title, TIMEOUT_10);
        return title;
    }
    public WebElement verifyNotesVisibility  ()
    {
        this.waitForElementVisibility(noRecordFound, TIMEOUT_10);
        return noRecordFound;
    }
    public NotesAppPageObject selectMarkAsComplete()
    {
        this.waitForElementToBeClickable(markAsCompleteButton, TIMEOUT_10);
        this.waitForElementVisibility(noteCount, TIMEOUT_10);
        markAsCompleteButton.click();
        return this;
    }
    public NotesAppPageObject selectMarkAsUnDone()
    {
        this.waitForElementToBeClickable(markAsUnDoneButton, TIMEOUT_10);
        this.waitForElementVisibility(noteCount, TIMEOUT_10);
        markAsUnDoneButton.click();
        return this;
    }
    public NotesAppPageObject checkDoneSectionAvailability()
    {
        this.waitForElementVisibility(doneSection, TIMEOUT_10);
        this.waitForElementVisibility(noteCount, TIMEOUT_10);
        return this;
    }
    public NotesAppPageObject checkActiveSectionAvailability()
    {
        this.waitForElementVisibility(activeSection, TIMEOUT_10);
        this.waitForElementVisibility(noteCount, TIMEOUT_10);
        return this;
    }
    public NotesAppPageObject editNotes()
    {
        this.waitForElementToBeClickable(editOption, TIMEOUT_10);
        editOption.click();
        return this;
    }
    public boolean editDeletedNote()
    {
        this.waitForElementInvisibility(editOption, TIMEOUT_10);
         return true;
    }
    public boolean clickOnActiveNotesNextPage()
    {
        if(this.waitForElementInvisibility(activePagination, TIMEOUT_10))
            activePagination.click();
        this.waitForElementInvisibility(searchButton, TIMEOUT_10);
        return true;
    }
    public boolean clickOnDoneNotesNextPage()
    {
        if(this.waitForElementInvisibility(donePagination, TIMEOUT_10))
            donePagination.click();
        this.waitForElementInvisibility(searchButton, TIMEOUT_10);
        return true;
    }
    public NotesAppPageObject deleteActiveNotes()
    {
        this.waitForElementToBeClickable(deleteOption, TIMEOUT_10);
        deleteOption.click();
        return this;
    }
    public NotesAppPageObject deleteCompletedNote()
    {
        this.waitForElementToBeClickable(deleteOptionForDoneNote, TIMEOUT_10);
        deleteOptionForDoneNote.click();
        return this;
    }
    public NotesAppPageObject clickOnOkButton()
    {
        this.waitForElementToBeClickable(okButton, TIMEOUT_10);
        okButton.click();
        this.waitForElementToBeClickable(plusIcon, TIMEOUT_10);
        return this;
    }
    public NotesAppPageObject checkCompleteNote()
    {
        this.waitForElementToBeClickable(completedNote, TIMEOUT_10);
        return this;
    }
    public NotesAppPageObject clickMyLinkedNote()
    {
        this.waitForElementToBeClickable(myLinkedNote, TIMEOUT_10);
        myLinkedNote.click();
        return this;
    }
    public NotesAppPageObject clickMyAllNote()
    {
        this.waitForElementToBeClickable(myAllNote, TIMEOUT_10);
        myAllNote.click();
        return this;
    }
    public NotesAppPageObject clickMyPrivateNote()
    {
        this.waitForElementToBeClickable(myPrivateNote, TIMEOUT_10);
        myPrivateNote.click();
        return this;
    }
    public NotesAppPageObject clickAllNotes()
    {
        this.waitForElementToBeClickable(allNotes, TIMEOUT_10);
        allNotes.click();
        return this;
    }
    public NotesAppPageObject clickLinkedNote()
    {
        this.waitForElementToBeClickable(linkedNote, TIMEOUT_10);
        linkedNote.click();
        return this;
    }
    public NotesAppPageObject clickOnNewestFirst()
    {
        this.waitForElementToBeClickable(newestFirst, TIMEOUT_10);
        newestFirst.click();
       // ztoa.click();
        return this;
    }

    public NotesAppPageObject clickOnOldestFirst()
    {
        this.waitForElementToBeClickable(oldestFirst, TIMEOUT_10);
        oldestFirst.click();
        return this;
    }
    public NotesAppPageObject clickOnAtoZ()
    {
        this.waitForElementToBeClickable(atoz, TIMEOUT_10);
       // this.waitForElementToBeClickable(allNotes, TIMEOUT_10);
        atoz.click();
        return this;
    }
    public NotesAppPageObject clickOnZtoA()
    {
        this.waitForElementToBeClickable(ztoa, TIMEOUT_15);
        ztoa.click();
        return this;
    }

}
