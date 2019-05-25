package com.ttn.bflframework.pages;

import com.ttn.bflframework.utils.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.io.IOException;

public class ProductListingPage {

    public UIUtils utils;
    public WaitUtils wUtils;
    public VerifyUtils vUtils;


    String filePath = "D:\\Selenium\\ExcelTestData";
    String fileName = GenericUtils.getDataFromConfig("FileName");
    String sheetName = "ProductListingPage";

    public ProductListingPage(UIUtils utils, WaitUtils wUtils, VerifyUtils vUtils) throws IOException {
        this.utils = utils;
        this.wUtils = wUtils;
        this.vUtils = vUtils;
    }

    private By firstProduct= By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "firstProduct", "Locator"));
    private By firstProductDetails= By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "firstProductDetails", "Locator"));
    private By firstProductPrice= By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "firstProductPrice", "Locator"));
    private By quickLookBtn= By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "quickLookBtn", "Locator"));
    private By emptyWishlistIcon= By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "emptyWishlistIcon", "Locator"));
    private By filledWishlistIcon= By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "filledWishlistIcon", "Locator"));
    private By firstProductOldPrice= By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "firstProductOldPrice", "Locator"));
    private By discountOnFirstProduct= By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "firstProductDiscount", "Locator"));

    public void mousehoverOnFirstProduct()
    {
        wUtils.eWaitForElementVisible(firstProduct,30);
        utils.mouseHover(firstProduct,"Mouse hover on first product of product listing page");
    }


    public void clickFirstProduct()
    {
        wUtils.eWaitForElementVisible(firstProduct,30);
        utils.click(firstProduct,"Click on first product of product listing page");
    }

    public String getFirstProductDetails()
    {
        wUtils.eWaitForElementVisible(firstProductDetails,30);
        String details= utils.getText(firstProductDetails,"Fetch the details of first product");
        return details;
    }

    public String getFirstProductPrice()
    {
        wUtils.eWaitForElementVisible(firstProductPrice,30);
        String price= utils.getText(firstProductPrice,"Fetch the price of first product");
        return price;
    }

    public String getFirstProductOldPrice()
    {
        wUtils.iWaitForSeconds(30);
        boolean status= utils.isElementPresent(firstProductOldPrice,"Verify the presence of old price in case of discounted item");
        if(status==true) {
            wUtils.eWaitForElementVisible(firstProductOldPrice, 30);
            String oldPrice = utils.getText(firstProductOldPrice, "Fetch the old price of first product");
            return oldPrice;
        }
        else
        {
            return "Discount is not applied on this product";
        }

    }


    public String getFirstProductDiscount()
    {

        wUtils.iWaitForSeconds(30);
        boolean status= utils.isElementPresent(discountOnFirstProduct,"Verify the visibility of discount tag");
        if(status==true) {
            wUtils.eWaitForElementVisible(discountOnFirstProduct, 30);
            String discount = utils.getText(discountOnFirstProduct, "Fetch the discount percentage on first product");
            return discount;
        }
        else
        {
            return "Discount is not applied on this product";
        }

    }


    public void clickQuickLookBtn()
    {
        wUtils.eWaitForElementVisible(quickLookBtn,30);
        utils.click(quickLookBtn,"Click on quicklook button");
    }

    public void addToWishlist()
    {
        wUtils.eWaitForElementVisible(emptyWishlistIcon,30);
        utils.javaScriptClick(emptyWishlistIcon,"Click on whishlist icon to add item to wishlist");
       // utils.click(emptyWishlistIcon,"Click on wishlist icon to add item to wishlist");
    }

    public void removeFromWislist()
    {
        wUtils.eWaitForElementVisible(filledWishlistIcon,30);
        utils.javaScriptClick(filledWishlistIcon,"Click on wishlist icon to remove the item from the wishlist");
       // utils.click(filledWishlistIcon,"Click on wishlist icon to remove the item from the wishlist");
    }

}
