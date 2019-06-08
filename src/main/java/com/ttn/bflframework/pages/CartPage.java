package com.ttn.bflframework.pages;

import com.ttn.bflframework.utils.*;
import org.openqa.selenium.By;

import java.io.IOException;

public class CartPage {

    public UIUtils utils ;
    public WaitUtils wUtils;
    public VerifyUtils vUtils;


    String usrDirectory= System.getProperty("user.dir");
    String filePath=usrDirectory+"\\src\\main\\java\\com\\ttn\\bflframework\\testdata";
    String fileName= GenericUtils.getDataFromConfig("FileName");
    String sheetName="CartPage";

    private By productName = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "productName", "Locator"));
    private By productDescription = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "productDescription", "Locator"));
    private By productPrice = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "productPrice", "Locator"));
    private By productOldPrice = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "productOldPrice", "Locator"));
    private By productDiscount = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "productDiscount", "Locator"));
    private By myCartHeading = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "myCartHeading", "Locator"));
    private By checkoutBtn = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "checkoutBtn", "Locator"));
    private By totalPrice = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "totalPrice", "Locator"));
    private By itemSize = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "itemSize", "Locator"));
    private By moveToWishlist = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "moveToWishlist", "Locator"));
    private By closeButton = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "closeButton", "Locator"));
    private By deliveryType = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "deliveryType", "Locator"));
    private By loyalty = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "loyalty", "Locator"));
    private By emptyCart = By.xpath(ExcelUtils.getCellValue(filePath, fileName, sheetName, "emptyCart", "Locator"));


    public CartPage(UIUtils utils,WaitUtils wUtils,VerifyUtils vUtils) throws IOException {
        this.utils=utils;
        this.wUtils =wUtils;
        this.vUtils = vUtils;
    }

    public void verifyProductOldPrice(String expected)
    {
        wUtils.iWaitForSeconds(30);
        String actual= utils.getText(productOldPrice,"Product old price of the product inside cart page");
        vUtils.verifyStringEquals(actual, expected,"Verify the old price of the product inside cart page",true);
    }

    public void verifyProductDescription(String expected)
    {
        wUtils.iWaitForSeconds(30);
        String name= utils.getText(productName,"Product name at cart page");
        String description= utils.getText(productDescription,"Product description inside cart page");
        String actual= name+description;
        vUtils.verifyStringEquals(actual, expected,"Verify the product description inside cart page",true);
    }

    public void verifyProductDiscount(String expected)
    {
        wUtils.iWaitForSeconds(30);
        String actual= utils.getText(productDiscount,"Product discount percentage inside cart page");
        vUtils.verifyStringEquals(actual, expected,"Verify the discount percentage of the product inside cart page",true);
    }

    public void verifyProductPrice(String expected)
    {
        wUtils.eWaitForElementVisible(productPrice,30);
        String actual= utils.getText(productPrice,"Product price of the product inside cart page");
        vUtils.verifyStringEquals(actual, expected,"Verify the price of the product inside cart page",true);
    }

    public void verifyCartPageHeading(String expected)
    {
        wUtils.eWaitForElementVisible(myCartHeading,30);
        String actual= utils.getText(myCartHeading,"Heading of cart page");
        vUtils.verifyStringEquals(actual, expected,"Verify the heading of cart page",true);
    }

    public void clickCheckoutBtn()
    {
        wUtils.eWaitForElementVisible(checkoutBtn,30);
        utils.click(checkoutBtn,"Click on checkout button");
        wUtils.checkForPageLoad(30);
    }

    public String getTotalPrice()
    {
        wUtils.eWaitForElementVisible(totalPrice,30);
        return utils.getText(totalPrice,"Total Price at the time of checkout");
    }

    public String getItemSize()
    {
        wUtils.eWaitForElementVisible(itemSize,30);
        return utils.getText(itemSize,"Selected size at checkout page");
    }

    public void clickMoveToWishlistBtn()
    {
        wUtils.eWaitForElementVisible(moveToWishlist,30);
        utils.click(moveToWishlist,"Click on moveToWishlist button");
    }

    public void removeItemFromCart()
    {
        wUtils.eWaitForElementVisible(closeButton,30);
        utils.click(closeButton,"Click on close(x)button to remove the item from the cart page");
    }

    public String getDeliveryType()
    {
        wUtils.eWaitForElementVisible(deliveryType,30);
        return utils.getText(deliveryType,"Selected delivery mode at checkout page");
    }

    public void verifyEmptyCart(String expected)
    {
        wUtils.eWaitForElementVisible(emptyCart,30);
        String actual= utils.getText(emptyCart,"Empty cart message");
        vUtils.verifyStringEquals(actual, expected,"Verify that cart is empty",true);
    }

    }

