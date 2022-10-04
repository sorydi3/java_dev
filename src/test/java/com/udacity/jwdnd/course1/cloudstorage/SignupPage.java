package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "buttonSignUp")
    private WebElement submitButton;

    @FindBy(id = "signup-link")
    private WebElement loginLink;

    @FindBy(id = "signup-error")
    private WebElement signupError;

    @FindBy(id = "signup-success")
    private WebElement signupSuccess;

    //constructor
    public SignupPage(WebDriver webdriver) {
        PageFactory.initElements(webdriver, this);
    }

    public void signup(String firstName, String lastName, String username, String password) {
        inputFirstName.sendKeys(firstName);
        inputLastName.sendKeys(lastName);
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
        submitButton.click();
    }

    public void login() {
        loginLink.click();
    }

    public String getSignupError() {
        return signupError.getText();
    }

    public String getSignupSuccess() {
        return signupSuccess.getText();
    }
    
}
