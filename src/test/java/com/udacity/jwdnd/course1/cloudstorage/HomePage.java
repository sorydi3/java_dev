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

    @FindBy(id = "nav-credentials-tab")
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
    public HomePage(WebDriver webdriver) {
        this.driver = webdriver;
        PageFactory.initElements(webdriver, this);
    }
    

    public void logout() {
        logoutButton.click();
    }

    public void gotoHomePage() {
        filesTab.click();
    }

    public void goToFilesTab() {
        filesTab.click();
    }

    public void goToNotesTab() {
        WebElement result = new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(notesTab));
        result.click();
    }

    public void goToCredentialsTab() {
        WebElement result = new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(credentialsTab));
        result.click(); 
    }

    public String getFileSuccess() {
        return fileSuccess.getText();
    }

    public String getFileError() {
        return fileError.getText();
    }

    public String getNoteSuccess() {
        return noteSuccess.getText();
    }


    public String getNoteError() {
        return noteError.getText();
    }


    public String getCredentialSuccess() {
        return credentialSuccess.getText();
    }
}
