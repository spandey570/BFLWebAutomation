package com.ttn.bflframework.pages;

import com.ttn.bflframework.utils.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.io.IOException;

public class SignInPage {

    public UIUtils utils;
    public WaitUtils wUtils;
    public VerifyUtils vUtils;


    String filePath = "D:\\Selenium\\ExcelTestData";
    String fileName = GenericUtils.getDataFromConfig("FileName");
    String sheetName = "SignInPage";


    public SignInPage(UIUtils utils, WaitUtils wUtils, VerifyUtils vUtils) throws IOException {
        this.utils = utils;
        this.wUtils = wUtils;
        this.vUtils = vUtils;
    }

    private By emailTxt = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "emailTxt", "Locator"));
    private By pwdTxt = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "pwdTxt", "Locator"));
    private By forgotPasswordLink = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "forgotPasswordLink", "Locator"));
    private By signInBtn = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "signInBtn", "Locator"));
    private By fbLink = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "fbLink", "Locator"));
    private By gmailLink = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "gmailLink", "Locator"));

    public void enterUsermail(String userName) {
        wUtils.eWaitForElementVisible(emailTxt, 30);
        utils.Type(emailTxt, "Enter username", userName);
    }

    public void enterPassword(String password) {
        wUtils.eWaitForElementVisible(emailTxt, 30);
        utils.Type(pwdTxt, "Enter password", password);
    }

    public void clickSignIn() {
        wUtils.eWaitForElementVisible(emailTxt, 30);
        utils.click(signInBtn, "Click on Sign-In button");
        wUtils.iWaitForSeconds(10);
    }

}


