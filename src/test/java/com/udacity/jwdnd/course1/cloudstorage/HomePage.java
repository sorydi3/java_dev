package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    @FindBy(id = "nav-files-tab")
    private WebElement filesTab;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(xpath = "//a[contains(text(),'Credentials')]")
    private WebElement credentialsTab;

    @FindBy(id = "fileSuccess")
    private WebElement fileSuccess;

    @FindBy(id = "fileError")
    private WebElement fileError;

    @FindBy(id = "noteSuccess")
    private WebElement noteSuccess;

    @FindBy(id = "noteError")
    private WebElement noteError;

    @FindBy(id = "credentialSuccess")
    private WebElement credentialSuccess;

    @FindBy(id = "credentialError")
    private WebElement credentialError;

    private WebDriver driver;

    //constructor
    private  int timeout = CloudStorageApplicationTests.TIMEOUT;
    public HomePage(WebDriver webdriver) {
        this.driver = webdriver;
        PageFactory.initElements(webdriver, this);
    }
    

    public void logout() {
        addWait(logoutButton, timeout).click(); //logoutButton.click();
    }

    public void goToFilesTab() {
        addWait(filesTab, timeout).click(); //filesTab.click();
    }

    public void goToNotesTab() {
        addWait(notesTab, timeout).click(); 
    }

    public void goToCredentialsTab() {
        addWait(credentialsTab, timeout).click(); 
    }

    private WebElement addWait(WebElement element, int time) {
        return new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(element));
    }

}
