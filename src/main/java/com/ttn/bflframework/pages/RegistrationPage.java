package com.ttn.bflframework.pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.ttn.bflframework.utils.UIUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.io.IOException;

public class RegistrationPage {
    public WebDriver driver;
    public ExtentTest testReport;
    Logger log = Logger.getLogger(RegistrationPage.class);
    UIUtils utils = new UIUtils(driver,testReport);


    private By firstName= By.xpath("//*[@ng-model='FirstName1']");
    private By lastName= By.xpath("//*[@ng-model='LastName']");
    private By maleRadioBtn= By.xpath("//*[@value='Male']");

    public RegistrationPage(UIUtils utils)
    {
        this.utils=utils;
    }

    public void fillFirstName(String name)
    {

        utils.Type(firstName,"Enter First Name",name);
        log.info(name + " is entered as firsttname");


    }

    public void fillLastName(String name)
    {

        utils.Type(lastName,"Enter Last Name",name);
        log.info(name + " is entered as lasttname");


    }

    public void selectMale() throws IOException {
        utils.click(maleRadioBtn,"Male is selected");
        driver.findElement(maleRadioBtn).click();
        log.info("Male is selected");
    }

}