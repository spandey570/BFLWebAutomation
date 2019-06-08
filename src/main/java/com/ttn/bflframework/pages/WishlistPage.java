package com.ttn.bflframework.pages;

import com.ttn.bflframework.utils.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.io.IOException;

public class WishlistPage {

    public UIUtils utils ;
    public WaitUtils wUtils;
    public VerifyUtils vUtils;


    String usrDirectory= System.getProperty("user.dir");
    String filePath=usrDirectory+"\\src\\main\\java\\com\\ttn\\bflframework\\testdata";
    String fileName= GenericUtils.getDataFromConfig("FileName");
    String sheetName="WishlistPage";

    public WishlistPage(UIUtils utils,WaitUtils wUtils,VerifyUtils vUtils) throws IOException {
        this.utils=utils;
        this.wUtils =wUtils;
        this.vUtils = vUtils;
    }

    private By firstProductName = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "firstProductName", "Locator"));
    private By firstProductDescription = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "firstProductDescription", "Locator"));
    private By firstProductPrice = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "firstProductPrice", "Locator"));
    private By firstProductOldPrice = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "firstProductOldPrice", "Locator"));
    private By firstProductDiscount = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "firstProductDiscount", "Locator"));
    private By addToCartBtn = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "addToCartBtn", "Locator"));
    private By deleteItem = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "deleteItem", "Locator"));
    private By wishlistHeading = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "wishlistHeading", "Locator"));
    private By emptyWishlist = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "emptyWishlist", "Locator"));
    private By dropdownSelector = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "dropdownSelector", "Locator"));
    private By sizeSelection = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "sizeSelection", "Locator"));

    public void verifyProductOldPrice(String expected)
    {
        wUtils.iWaitForSeconds(30);
        String actual= utils.getText(firstProductOldPrice,"Product old price of the product inside wishlist");
        vUtils.verifyStringEquals(actual, expected,"Verify the old price of the product inside wishlist",true);
    }

    public void verifyProductDescription(String expected)
    {
        wUtils.iWaitForSeconds(30);
        String name= utils.getText(firstProductName,"Product name at wishlist page");
        String description= utils.getText(firstProductDescription,"Product description inside wishlist");
        String actual= name+description;
        vUtils.verifyStringEquals(actual, expected,"Verify the product description inside wishlist",true);
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

    public void verifyWishlistUrl()
    {
      utils.verifyUrlExist("wishlist");

    }

    public void selectItemSize()
    {
        wUtils.eWaitForElementVisible(dropdownSelector,30);
        utils.click(dropdownSelector,"Click on size selection dropdown");
        String size= utils.getText(sizeSelection,"First available size");
        utils.click(sizeSelection,"Click on "+size+" option");
    }
}
