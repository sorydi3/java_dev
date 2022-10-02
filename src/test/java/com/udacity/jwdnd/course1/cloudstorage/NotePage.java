package com.udacity.jwdnd.course1.cloudstorage;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotePage {

    WebDriver driver;

    @FindBy(id = "id_addnote_button")
    private WebElement addNewNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "noteSubmitModal")
    private WebElement saveNoteButton;

    @FindBy(xpath = "//button[contains(text(),'Edit')]")
    private WebElement editNoteButton;
    
    @FindBy(xpath = "//a[contains(text(),'Delete')]")
    private WebElement deleteNoteButton;

    @FindBy(id = "id_note_success")
    private WebElement noteSuccess;

    @FindBy(id = "id_note_error")
    private WebElement noteError;

    // Constructor
    public NotePage(WebDriver webdriver) {
        this.driver =webdriver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(webdriver, this);
    }

    public void addNewNote(String title, String description) {
        addNewNoteButton.click();
        noteTitle.sendKeys(title);
        noteDescription.sendKeys(description);
        saveNoteButton.click();
    }

    public void editNote(String title, String description) {
        editNoteButton.click();
        noteTitle.clear();
        noteTitle.sendKeys(title);
        noteDescription.clear();
        noteDescription.sendKeys(description);
        saveNoteButton.click();
    }

    public void deleteNote() {
        WebElement result = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(deleteNoteButton));
        result.click();
    }

    public String getNoteSuccess() {
        return noteSuccess.getText();
    }

    public String getNoteError() {
        return noteError.getText();
    }

    public String getNoteTitle() {
        return noteTitle.getAttribute("value");
    }

    public String getNoteDescription() {
        return noteDescription.getAttribute("value");
    }

    public boolean isNoteDisplayed() {
        return noteTitle.isDisplayed();
    }

    public boolean isNoteTitleDisplayed() {
        return noteTitle.isDisplayed();
    }

    public boolean isNoteDescriptionDisplayed() {
        return noteDescription.isDisplayed();
    }

    public boolean isSaveNoteButtonDisplayed() {
        return saveNoteButton.isDisplayed();
    }

    public boolean isEditNoteButtonDisplayed() {
        return editNoteButton.isDisplayed();
    }

    public boolean isDeleteNoteButtonDisplayed() {
        return deleteNoteButton.isDisplayed();
    }

    public boolean isAddNewNoteButtonDisplayed() {
        WebElement result = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(addNewNoteButton));
        return result.isDisplayed();
    }

    public void editNoteTitle(String noteTitleEdited) {
        editNoteButton.click();
        noteTitle.clear();
        noteTitle.sendKeys(noteTitleEdited);
        saveNoteButton.click();
    }

    public void editNoteDescription(String noteDescriptionEdited) {
        editNoteButton.click();
        noteDescription.clear();
        noteDescription.sendKeys(noteDescriptionEdited);
        saveNoteButton.click();
    }


}
