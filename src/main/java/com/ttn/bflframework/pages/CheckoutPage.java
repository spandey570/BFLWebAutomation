package com.ttn.bflframework.pages;

import com.ttn.bflframework.utils.*;
import org.openqa.selenium.By;

import java.io.IOException;

public class CheckoutPage {

    public UIUtils utils ;
    public WaitUtils wUtils;
    public VerifyUtils vUtils;


    String usrDirectory= System.getProperty("user.dir");
    String filePath=usrDirectory+"\\src\\main\\java\\com\\ttn\\bflframework\\testdata";
    String fileName= GenericUtils.getDataFromConfig("FileName");
    String sheetName="CheckoutPage";

    private By emailTxt = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "emailTxt", "Locator"));
    private By pwdTxt = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "pwdTxt", "Locator"));
    private By forgotPasswordLink = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "forgotPasswordLink", "Locator"));
    private By signInBtn = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "signInBtn", "Locator"));
    private By fbLink = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "fbLink", "Locator"));
    private By gmailLink = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "gmailLink", "Locator"));
    private By guestUserEmailTxt = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "guestUserEmailTxt", "Locator"));
    private By continueAsGuestBtn = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "continueAsGuestBtn", "Locator"));
    private By standardDelivery = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "standardDelivery", "Locator"));
    private By addressDescription = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "addressDescription", "Locator"));
    private By changeAddressBtn = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "changeAddressBtn", "Locator"));
    private By addNewAddress = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "addNewAddress", "Locator"));
    private By continueAddressBtn = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "continueAddressBtn", "Locator"));
    private By firstTimeNewAddress = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "firstTimeNewAddress", "Locator"));



    public CheckoutPage(UIUtils utils,WaitUtils wUtils,VerifyUtils vUtils) throws IOException {
        this.utils=utils;
        this.wUtils =wUtils;
        this.vUtils = vUtils;
    }

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
        wUtils.checkForPageLoad(60);
    }

    public void clickForgotPasswordLink() {
        wUtils.eWaitForElementVisible(forgotPasswordLink, 30);
        utils.click(forgotPasswordLink, "Click on forgot password button");
        wUtils.checkForPageLoad(60);
    }

    public void clickFbLink() {
        wUtils.eWaitForElementVisible(fbLink, 30);
        utils.click(fbLink, "Click on facebook link");
        wUtils.checkForPageLoad(60);
    }

    public void clickGmailLink() {
        wUtils.eWaitForElementVisible(gmailLink, 30);
        utils.click(gmailLink, "Click on gmail link");
        wUtils.checkForPageLoad(60);
    }

    public void enterGuestUsermail(String userName) {
        wUtils.eWaitForElementVisible(guestUserEmailTxt, 30);
        utils.Type(guestUserEmailTxt, "Enter guest username", userName);
    }

    public void clickContinueAsGuestBtn() {
        wUtils.eWaitForElementVisible(continueAsGuestBtn, 30);
        utils.click(continueAsGuestBtn, "Click on continue as guest button");
        wUtils.checkForPageLoad(60);
    }

    public void verifyStandardDeliveryOption(String expectedOption)
    {
        wUtils.eWaitForElementVisible(standardDelivery,30);
        String actualOption= utils.getText(standardDelivery,"Delivery type option");
        vUtils.verifyStringEquals(actualOption,expectedOption,"Verify the standard delivery type option",true);

    }

    public void clickChangeAddressBtn() {
        wUtils.eWaitForElementVisible(changeAddressBtn, 30);
        utils.click(changeAddressBtn, "Click on change address button");

    }

    public void clickAddNewAddressBtn() {

        wUtils.eWaitForElementVisible(addNewAddress, 30);
        utils.click(addNewAddress, "Click on add new address button");
    }

    public void clickContinueBtn() {
        wUtils.eWaitForElementVisible(continueAddressBtn, 30);
        utils.click(continueAddressBtn, "Click on continue with this address button");

    }

    public void addUserNewAddress() {
        boolean addressStatus= isDeliveryAddressPresent();
        if (addressStatus==false) {
            wUtils.eWaitForElementVisible(firstTimeNewAddress, 30);
            utils.click(firstTimeNewAddress, "Click on add new address button");
        }
        else {
            clickAddNewAddressBtn();
        }

    }

    public boolean isDeliveryAddressPresent()
    {
        wUtils.eWaitForElementVisible(addressDescription,30);
        return utils.isElementPresent(addressDescription,"Verify that delivery address is present");
    }



}
