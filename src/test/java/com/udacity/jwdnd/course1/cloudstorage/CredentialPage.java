package com.udacity.jwdnd.course1.cloudstorage;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialPage {
    @FindBy(id="credential-url")
    private WebElement credentialUrl;

    @FindBy(id="credential-username")
    private WebElement credentialUsername;

    @FindBy(id="credential-password")
    private WebElement credentialPassword;

    @FindBy(xpath ="//Button[contains(text(),'+ Add a New Credential')]")
    private WebElement credentialAdd;

    @FindBy(xpath ="//Button[contains(text(),'Editt')]")
    private WebElement credentialEdit;

    @FindBy(xpath ="//a[contains(text(),'Deletee')]")
    private WebElement credentialDelete;

    @FindBy(id ="credSubmitModal")
    private WebElement credentialSave;

    private WebDriver driver;

    private int timeout = 8;
    /*
     * Constructor
     */
    public CredentialPage(WebDriver webdriver) {
        this.driver = webdriver;
        PageFactory.initElements(driver, this);
    }

    public void addNewCredential(String url, String username, String password) {
        addWait(credentialAdd, timeout).click();
        addWait(credentialUrl, timeout).sendKeys(url);
        addWait(credentialUsername, timeout).sendKeys(username);
        addWait(credentialPassword, timeout).sendKeys(password);
        addWait(credentialSave, timeout).click();
        moveToCredentialTab();
    }

    public void editCredential(String url, String username, String password) {
        addWait(credentialEdit, timeout).click();
        addWait(credentialUrl, timeout).clear();
        addWait(credentialUrl, timeout).sendKeys(url);
        addWait(credentialUsername, timeout).clear();
        addWait(credentialUsername, timeout).sendKeys(username);
        addWait(credentialPassword, timeout).clear();
        addWait(credentialPassword, timeout).sendKeys(password);
        addWait(credentialSave, timeout).click();
        moveToCredentialTab();
    }

    public void clickEditCredential() {
        addWait(credentialEdit, timeout).click();
    }

    public void clickAddCredential() {
        addWait(credentialAdd, timeout).click();
    }

    public void deleteCredential() {
        addWait(credentialDelete, timeout).click();
        moveToCredentialTab();
    }

    public void editCredentialUrl(String credentialUrlEdited) {
        addWait(credentialEdit, timeout).click();
        addWait(credentialUrl, timeout).clear();
        addWait(credentialUrl, timeout).sendKeys(credentialUrlEdited);
        addWait(credentialSave, timeout).click();
        moveToCredentialTab();
    }

    public void editCredentialUsername(String credentialUsernameEdited) {
        addWait(credentialEdit, timeout).click();
        addWait(credentialUsername, timeout).clear();
        addWait(credentialUsername, timeout).sendKeys(credentialUsernameEdited);
        addWait(credentialSave, timeout).click();
        moveToCredentialTab();
    }

    public void editCredentialPassword(String credentialPasswordEdited) {
        addWait(credentialEdit, timeout).click();
        addWait(credentialPassword, timeout).clear();
        addWait(credentialPassword, timeout).sendKeys(credentialPasswordEdited);
        addWait(credentialSave, timeout).click();
        moveToCredentialTab();
    }

    private void moveToCredentialTab() {
        ResultPage resultPage = new ResultPage(driver);
        resultPage.goTohomePage();
        HomePage homePage = new HomePage(driver);
        homePage.goToCredentialsTab();
    }

    private WebElement addWait(WebElement element, int time) {
        return new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(element));
    }
}
