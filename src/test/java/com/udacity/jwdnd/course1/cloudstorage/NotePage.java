package com.udacity.jwdnd.course1.cloudstorage;

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
    private int timeout = CloudStorageApplicationTests.TIMEOUT;
    // Constructor
    public NotePage(WebDriver webdriver) {
        this.driver =webdriver;
        PageFactory.initElements(driver, this);
    }

    public void addNewNote(String title, String description) {
        addWait(addNewNoteButton, timeout).click();
        addWait(noteTitle, timeout).sendKeys(title);
        addWait(noteDescription, timeout).sendKeys(description);
        addWait(saveNoteButton, timeout).click();
        moveToNoteTab();
    }

    public void editNote(String title, String description) {
        addWait(editNoteButton,timeout).click();
        addWait(noteTitle, timeout).clear();
        addWait(noteTitle, timeout).sendKeys(title);
        addWait(noteDescription, timeout).clear();
        addWait(noteDescription, timeout).sendKeys(description);
        addWait(saveNoteButton, timeout).click();
        moveToNoteTab();
    }

    public void deleteNote() {
        addWait(deleteNoteButton, timeout).click();
        moveToNoteTab();
    }

    public boolean isAddNewNoteButtonDisplayed() {
        WebElement result = new WebDriverWait(driver, CloudStorageApplicationTests.TIMEOUT).until(ExpectedConditions.elementToBeClickable(addNewNoteButton));
        return result.isDisplayed();
    }

    public WebElement addWait(WebElement element, int time) {
        return new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(element));
    }

    private void moveToNoteTab() {
        ResultPage resultPage = new ResultPage(driver);
        resultPage.goTohomePage();
        HomePage homePage = new HomePage(driver);
        homePage.goToNotesTab();
    }


}
