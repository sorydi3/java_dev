package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CredentialPage {
    @FindBy(id="credential-url")
    private WebElement credentialUrl;

    @FindBy(id="credential-username")
    private WebElement credentialUsername;

    @FindBy(id="credential-password")
    private WebElement credentialPassword;

    @FindBy(id="credential-add")
    private WebElement credentialAdd;

    @FindBy(id="credential-edit")
    private WebElement credentialEdit;

    @FindBy(id="credential-delete")
    private WebElement credentialDelete;

    @FindBy(id="credential-save")
    private WebElement credentialSave;

    @FindBy(id="credential-success")
    private WebElement credentialSuccess;

    @FindBy(id="credential-error")
    private WebElement credentialError;


    /*
     * Constructor
     */
    public CredentialPage(WebDriver webdriver) {
        PageFactory.initElements(webdriver, this);
    }

    public void addNewCredential(String url, String username, String password) {
        credentialAdd.click();
        credentialUrl.sendKeys(url);
        credentialUsername.sendKeys(username);
        credentialPassword.sendKeys(password);
        credentialSave.click();
    }

    public void editCredential(String url, String username, String password) {
        credentialEdit.click();
        credentialUrl.clear();
        credentialUrl.sendKeys(url);
        credentialUsername.clear();
        credentialUsername.sendKeys(username);
        credentialPassword.clear();
        credentialPassword.sendKeys(password);
        credentialSave.click();
    }

    public void deleteCredential() {
        credentialDelete.click();
    }

    public String getCredentialSuccess() {
        return credentialSuccess.getText();
    }

    public String getCredentialError() {
        return credentialError.getText();
    }

    public String getCredentialUrl() {
        return credentialUrl.getAttribute("value");
    }

    public String getCredentialUsername() {
        return credentialUsername.getAttribute("value");
    }

    public String getCredentialPassword() {
        return credentialPassword.getAttribute("value");
    }


}
