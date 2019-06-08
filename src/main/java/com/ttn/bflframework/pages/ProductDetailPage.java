package com.ttn.bflframework.pages;

import com.ttn.bflframework.utils.*;
import org.openqa.selenium.By;

import java.io.IOException;

public class ProductDetailPage {
    public UIUtils utils ;
    public WaitUtils wUtils;
    public VerifyUtils vUtils;


    String usrDirectory= System.getProperty("user.dir");
    String filePath=usrDirectory+"\\src\\main\\java\\com\\ttn\\bflframework\\testdata";
    String fileName= GenericUtils.getDataFromConfig("FileName");
    String sheetName="ProductDetailPage";

    private By productName= By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "productName", "Locator"));
    private By productDescription= By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "productDescription", "Locator"));
    private By productPrice= By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "productPrice", "Locator"));
    private By productOldPrice= By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "productOldPrice", "Locator"));
    private By productDiscount= By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "productDiscount", "Locator"));
    private By addToCartBtn= By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "addToCartBtn", "Locator"));
    private By addItemToWishlist= By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "addItemToWishlist", "Locator"));
    private By removeItemFromWishlist= By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "removeItemFromWishlist", "Locator"));
    private By sizeList= By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "sizeList", "Locator"));



    public ProductDetailPage(UIUtils utils,WaitUtils wUtils,VerifyUtils vUtils) throws IOException {
        this.utils=utils;
        this.wUtils =wUtils;
        this.vUtils = vUtils;
    }

    public void verifyProductDetailsOnPDP(String expected)
    {
        wUtils.eWaitForElementVisible(productDescription,30);
        String name= utils.getText(productName,"Product name at product detail page");
        String description= utils.getText(productDescription,"Product description at product detail page");
        String actual= name+description;
        vUtils.verifyStringEquals(actual,expected,"Verify the product description on product detail page",true);
    }

    public void addItemToWishlist()
    {
        wUtils.eWaitForElementVisible(addItemToWishlist,30);
        utils.click(addItemToWishlist,"Click on wishlist-icon to add the item in the wishlist");
    }

    public void removeItemFromWishlist()
    {
        wUtils.eWaitForElementVisible(removeItemFromWishlist,30);
        utils.click(removeItemFromWishlist,"Click on wishlist-icon to remove the item from the wishlist");
    }

    public void clickAddToCartBtn()
    {
        wUtils.eWaitForElementVisible(addToCartBtn,30);
        utils.click(addToCartBtn,"Click on Add To Cart Button");
    }

    public void verifyPDPUrlExist()
    {
        utils.verifyUrlExist("product-detail");
    }

    public void selectAvailableItemSize() {
        boolean sizeStaus = utils.isElementPresent(sizeList, "Check the visibility of product size element");
        if (sizeStaus == true) {
            wUtils.eWaitForElementVisible(sizeList, 30);
            int eleCount = utils.getElementList(sizeList);
            for (int i = 1; i <= eleCount; i++) {
                String classN = utils.getAttribute(By.xpath("(//*[@class='size_list']//li)[" + i + "]"));
                if (classN.equalsIgnoreCase("size_not_available")) {
                } else {
                    utils.click(By.xpath("(//*[@class='size_list']//li)[" + i + "]"), "Select the available size");
                    utils.getText(By.xpath("(//*[@class='size_list']//li)[" + i + "]"), "Selected available size is");
                    break;
                }
            }
        }
    }


}
