package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FilePage {
    @FindBy(id = "fileUpload")
    private WebElement fileUpload;

    @FindBy(id = "fileSubmit")
    private WebElement fileSubmit;

    @FindBy(id = "fileSuccess")
    private WebElement fileSuccess;

    @FindBy(id = "fileError")
    private WebElement fileError;

    @FindBy(id = "fileDelete")
    private WebElement fileDelete;

    //constructor
    public FilePage(WebDriver webdriver) {
        PageFactory.initElements(webdriver, this);
    }
    

    public void uploadFile(String filePath) {
        fileUpload.sendKeys(filePath);
        fileSubmit.click();
    }

    public void deleteFile() {
        fileDelete.click();
    }

    public String getFileSuccess() {
        return fileSuccess.getText();
    }

    public String getFileError() {
        return fileError.getText();
    }

    public String getFileName() {
        return fileUpload.getAttribute("value");
    }


}
