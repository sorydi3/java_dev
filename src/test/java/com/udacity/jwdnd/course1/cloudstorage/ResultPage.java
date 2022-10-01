package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultPage {
    @FindBy(id = "success")
    private WebElement success;

    @FindBy(id = "error")
    private WebElement error;

    @FindBy(id = "id_link_homePage")
    private WebElement link_homePage;

    private WebDriver driver;

    //constructor
    public ResultPage(WebDriver webdriver) {
        this.driver = webdriver;
        PageFactory.initElements(webdriver, this);
    }

    public String getSuccess() {
        return success.getText();
    }

    public String getError() {
        return error.getText();
    }

    public void goTohomePage() {
        WebElement result = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(link_homePage));
        result.click();
    }

}
