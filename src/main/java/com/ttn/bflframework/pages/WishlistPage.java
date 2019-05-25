package com.ttn.bflframework.pages;

import com.ttn.bflframework.utils.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.io.IOException;

public class WishlistPage {

    public UIUtils utils ;
    public WaitUtils wUtils;
    public VerifyUtils vUtils;


    String filePath="D:\\Selenium\\ExcelTestData";
    String fileName= GenericUtils.getDataFromConfig("FileName");
    String sheetName="WishlistPage";

    public WishlistPage(UIUtils utils,WaitUtils wUtils,VerifyUtils vUtils) throws IOException {
        this.utils=utils;
        this.wUtils =wUtils;
        this.vUtils = vUtils;
    }

    private By firstProductDetails = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "firstProductDetails", "Locator"));
    private By firstProductPrice = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "firstProductPrice", "Locator"));
    private By firstProductOldPrice = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "firstProductOldPrice", "Locator"));
    private By firstProductDiscount = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "firstProductDiscount", "Locator"));
    private By addToCartBtn = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "addToCartBtn", "Locator"));
    private By deleteItem = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "deleteItem", "Locator"));
    private By wishlistHeading = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "wishlistHeading", "Locator"));
    private By emptyWishlist = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "emptyWishlist", "Locator"));

    public void verifyProductOldPrice(String expected)
    {
        wUtils.iWaitForSeconds(30);
        String actual= utils.getText(firstProductOldPrice,"Product old price of the product inside wishlist");
        vUtils.verifyStringEquals(actual, expected,"Verify the old price of the product inside wishlist",true);
    }

    public void verifyProductDetails(String expected)
    {
        wUtils.iWaitForSeconds(30);
        String actual= utils.getText(firstProductDetails,"Product Details inside wishlist");
        vUtils.verifyStringEquals(actual, expected,"Verify the product details inside wishlist",true);
    }

    public void verifyProductDiscount(String expected)
    {
        wUtils.iWaitForSeconds(30);
        String actual= utils.getText(firstProductDiscount,"Product discount percentage inside wishlist");
        vUtils.verifyStringEquals(actual, expected,"Verify the discount percentage of the product inside wishlist",true);
    }

    public void clickAddToCartBtn()
    {
        wUtils.iWaitForSeconds(30);
        utils.click(addToCartBtn,"Click on Add To Cart button");
    }

    public void deleteItemFromTheWishlist()
    {
        wUtils.iWaitForSeconds(30);
        utils.click(deleteItem,"Click on cross(x) button to delete the item from the wishlist");
    }

    public void verifyWishlistHeading(String expected)
    {
        wUtils.iWaitForSeconds(30);
        String actual= utils.getText(wishlistHeading,"Heading of wishlist page");
        vUtils.verifyStringEquals(actual, expected,"Verify the heading of wishlist page",true);
    }

    public void verifyWishlistIsEmpty(String expected)
    {
        wUtils.iWaitForSeconds(30);
        String actual= utils.getText(emptyWishlist,"Fetch the text incase whishlist is empty");
        vUtils.verifyStringEquals(actual, expected,"Verify that wishlist is empty",true);
    }
}
