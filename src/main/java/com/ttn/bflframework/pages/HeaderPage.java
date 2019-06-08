package com.ttn.bflframework.pages;

import com.ttn.bflframework.utils.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.io.IOException;

public class HeaderPage {

    public UIUtils utils;
    public WaitUtils wUtils;
    public VerifyUtils vUtils;


    String usrDirectory= System.getProperty("user.dir");
    String filePath=usrDirectory+"\\src\\main\\java\\com\\ttn\\bflframework\\testdata";

    String fileName = GenericUtils.getDataFromConfig("FileName");
    String sheetName = "HeaderPage";




    private By newNavBar = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "new", "Locator"));
    private By wishlistItem = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "wishlistItemCount", "Locator"));
    private By wishlistBucket = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "wishlistBucket", "Locator"));
    private By cartBucket = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "cartBucket", "Locator"));
    private By cartItemCount = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "cartItemCount", "Locator"));


    public HeaderPage(UIUtils utils, WaitUtils wUtils, VerifyUtils vUtils) throws IOException {
        this.utils = utils;
        this.wUtils = wUtils;
        this.vUtils = vUtils;
    }

    public void clickNew() {
        wUtils.eWaitForElementVisible(newNavBar, 60);
        utils.click(newNavBar, "Click on New option on navigation bar");
    }

    public int getWishlistItemCount() {
       wUtils.eWaitForElementVisible(wishlistBucket, 30);





        boolean status = utils.isElementPresent(wishlistItem,"Verify the visibility of wishlist item count element");
        if (status == true) {
            String wishlistCount = utils.getText(wishlistItem, "Wishlist item count");
            return Integer.parseInt(wishlistCount);
        }
        else
        {
            return 0;
        }
    }

    public int getCartItemCount() {
        wUtils.iWaitForSeconds(30);
        boolean status = utils.isElementPresent(cartItemCount,"Verify the visibility of cart item count element");
        if (status == true) {
            wUtils.eWaitForElementVisible(cartItemCount, 30);
            String cartCount = utils.getText(cartItemCount, "Cart item Count is");
            return Integer.parseInt(cartCount);
        }
        else
        {
            return 0;
        }
    }

    public void clickOnWishlist()
    {
        wUtils.eWaitForElementVisible(wishlistBucket,30);
        utils.javaScriptClick(wishlistBucket,"Click on wishlist bucket icon");

    }

    public void clickOnCart()
    {
        wUtils.eWaitForElementVisible(cartBucket,30);
        utils.javaScriptClick(cartBucket,"Click on cart bucket icon");

    }

    public int verifyWishlistCountIsZero()
    {
        int actualCount= getWishlistItemCount();
        vUtils.verifyIntEquals(actualCount,0,"Verify that wishlist count is zero",true);
        return actualCount;
    }
}
