package com.ttn.bflframework.pages;

import com.ttn.bflframework.utils.*;
import org.openqa.selenium.By;

import java.io.IOException;

public class QuicklookPage {

    public UIUtils utils;
    public WaitUtils wUtils;
    public VerifyUtils vUtils;


    String filePath = "D:\\Selenium\\ExcelTestData";
    String fileName = GenericUtils.getDataFromConfig("FileName");
    String sheetName = "QuicklookPage";

    public QuicklookPage(UIUtils utils, WaitUtils wUtils, VerifyUtils vUtils) throws IOException {
        this.utils = utils;
        this.wUtils = wUtils;
        this.vUtils = vUtils;
    }

    private By productDetails = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "productDetails", "Locator"));
    private By addToCartBtn = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "addToCartBtn", "Locator"));
    private By addItemToWishlist = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "addItemToWishlist", "Locator"));
    private By removeItemFromWishlist = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "removeItemFromWishlist", "Locator"));
    private By seeProductDetails = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "seeProductDetails", "Locator"));
    private By closeQuicklook = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "closeQuicklook", "Locator"));

    public void verifyProductDetailsOnQL(String expected)
    {
        wUtils.eWaitForElementVisible(productDetails,30);
        String actual= utils.getText(productDetails,"Product details at quicklook page");
        vUtils.verifyStringEquals(actual,expected,"Verify the product details on quicklook page",true);
    }

    public void addItemToWishlist()
    {
        wUtils.eWaitForElementVisible(addItemToWishlist,30);
        utils.click(addItemToWishlist,"Click on wishlist-icon to add the item in the wishlist");
    }

    public void clickAddToCartBtn()
    {
        wUtils.eWaitForElementVisible(addToCartBtn,30);
        utils.click(addToCartBtn,"Click on Add To Cart Button");
    }

    public void removeItemFromWishlist()
    {
        wUtils.eWaitForElementVisible(removeItemFromWishlist,30);
        utils.click(removeItemFromWishlist,"Click on wishlist-icon to remove the item from the wishlist");
    }

    public void clickOnSeeProductDetails()
    {
        wUtils.eWaitForElementVisible(seeProductDetails,30);
        utils.click(seeProductDetails,"Click on see product details link");
    }

    public void clickOnCloseQuicklookBtn()
    {
        wUtils.eWaitForElementVisible(closeQuicklook,30);
        utils.click(closeQuicklook,"Click on close(x) inorder to close the quicklook window");
    }
}
