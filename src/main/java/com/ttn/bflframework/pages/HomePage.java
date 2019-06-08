package com.ttn.bflframework.pages;

import com.ttn.bflframework.utils.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.io.IOException;

public class HomePage {

    public UIUtils utils ;
    public WaitUtils wUtils;
    public VerifyUtils vUtils;


    String usrDirectory= System.getProperty("user.dir");
    String filePath=usrDirectory+"\\src\\main\\java\\com\\ttn\\bflframework\\testdata";
    String fileName= GenericUtils.getDataFromConfig("FileName");
    String sheetName="HomePage";



    private By profileIcon = By.xpath(ExcelUtils.getCellValue(filePath,fileName,sheetName,"profileIcon","Locator"));
    private By signInBtn = By.xpath(ExcelUtils.getCellValue(filePath,fileName,sheetName,"signInBtn","Locator"));
    private By user= By.xpath(ExcelUtils.getCellValue(filePath,fileName,sheetName,"user","Locator"));


    public HomePage(UIUtils utils,WaitUtils wUtils,VerifyUtils vUtils) throws IOException {
        this.utils=utils;
        this.wUtils =wUtils;
        this.vUtils = vUtils;
    }

    public void mouseHoverOnProfileIcon()
    {
        wUtils.checkForPageLoad(60);
        wUtils.eWaitForElementVisible(profileIcon,30);
        utils.mouseHover(profileIcon,"Mouse hover on profile icon");
    }

    public void clickSignIn()
    {
        wUtils.eWaitForElementVisible(signInBtn,30);
        utils.click(signInBtn,"Click on Sign-In ");
    }

    public void verifyLoggedInUserName(String expected)
    {
        wUtils.eWaitForElementVisible(user,30);
        String actual= utils.getText(user,"Fetch the logged in user name");
        vUtils.verifyStringEquals(actual,expected,"Verify the logged in user name",true);
    }
}
