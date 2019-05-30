package com.ttn.bflframework.testscripts;

import com.ttn.bflframework.pages.PageClassObjects;
import com.ttn.bflframework.utils.BaseUtils;
import com.ttn.bflframework.utils.UIUtils;
import com.ttn.bflframework.utils.VerifyUtils;
import com.ttn.bflframework.utils.WaitUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseUtils {

    public PageClassObjects pageObjects;
    @BeforeMethod
    public void test() throws IOException {
        UIUtils utils = new UIUtils(driver,testReport);
        WaitUtils wUtils = new WaitUtils(driver);
        VerifyUtils vUtils= new VerifyUtils(driver,testReport);
        pageObjects= new PageClassObjects(utils,wUtils,vUtils);

    }

    @Test(description = "Verify that user is able to add the item in the cart from product detail page")
    public void addProductToCartFromPDP()
    {
        pageObjects.common.countrySelection("Oman");
        pageObjects.home.mouseHoverOnProfileIcon();
        pageObjects.home.clickSignIn();
        pageObjects.signIn.enterUsermail("srikant8@mailnator.com");
        pageObjects.signIn.enterPassword("12345678");
        pageObjects.signIn.clickSignIn();
        pageObjects.header.clickNew();
        String details= pageObjects.plp.getFirstProductDescription();
        String price= pageObjects.plp.getFirstProductPrice();
        pageObjects.plp.mousehoverOnFirstProduct();
        pageObjects.plp.clickQuickLookBtn();
        pageObjects.ql.clickOnSeeProductDetails();
        pageObjects.pdp.selectAvailableItemSize();
        pageObjects.pdp.clickAddToCartBtn();
        pageObjects.header.getCartItemCount();
        pageObjects.header.clickOnCart();
        pageObjects.cart.verifyCartPageHeading("My Cart1 Item(s)");
        pageObjects.cart.verifyProductDescription(details);
        pageObjects.cart.verifyProductPrice(price);
        pageObjects.cart.getItemSize();
        pageObjects.cart.getDeliveryType();
        pageObjects.cart.getTotalPrice();
        pageObjects.cart.removeItemFromCart();
        pageObjects.cart.verifyEmptyCart("Hey it feels so light!");

    }

    @Test(description = "Verify that user is able to add the item in the cart from wishlist page")
    public void addProductToCartFromWishlist()
    {
        pageObjects.common.countrySelection("Oman");
        pageObjects.home.mouseHoverOnProfileIcon();
        pageObjects.home.clickSignIn();
        pageObjects.signIn.enterUsermail("srikant8@mailnator.com");
        pageObjects.signIn.enterPassword("12345678");
        pageObjects.signIn.clickSignIn();
        pageObjects.header.clickNew();
        String details= pageObjects.plp.getFirstProductDescription();
        String price= pageObjects.plp.getFirstProductPrice();
        pageObjects.plp.mousehoverOnFirstProduct();
        pageObjects.plp.addToWishlist();
        pageObjects.header.getWishlistItemCount();
        pageObjects.header.clickOnWishlist();
        pageObjects.wishlist.verifyWishlistUrl();
        pageObjects.wishlist.verifyProductDescription(details);
        pageObjects.wishlist.selectItemSize();
        pageObjects.wishlist.clickAddToCartBtn();
        pageObjects.header.getCartItemCount();
        pageObjects.header.clickOnCart();
        pageObjects.cart.verifyCartPageHeading("My Cart1 Item(s)");
        pageObjects.cart.verifyProductDescription(details);
        pageObjects.cart.verifyProductPrice(price);
        pageObjects.cart.getItemSize();
        pageObjects.cart.clickMoveToWishlistBtn();
        pageObjects.header.getWishlistItemCount();
        pageObjects.header.clickOnWishlist();
        pageObjects.wishlist.verifyProductDescription(details);
        pageObjects.wishlist.deleteItemFromTheWishlist();
        pageObjects.wishlist.verifyWishlistIsEmpty("Wishlist is Empty!");

    }

    @Test(description = "Verify that user is able to add the item in the cart from quicklook page")
    public void addRemoveProductFromQL()
    {
        pageObjects.common.countrySelection("Oman");
        pageObjects.home.mouseHoverOnProfileIcon();
        pageObjects.home.clickSignIn();
        pageObjects.signIn.enterUsermail("srikant8@mailnator.com");
        pageObjects.signIn.enterPassword("12345678");
        pageObjects.signIn.clickSignIn();
        pageObjects.header.clickNew();
        String details= pageObjects.plp.getFirstProductDescription();
        String price= pageObjects.plp.getFirstProductPrice();
        pageObjects.plp.mousehoverOnFirstProduct();
        pageObjects.plp.clickQuickLookBtn();
        pageObjects.ql.verifyProductDescriptionOnQL(details);
        pageObjects.ql.selectAvailableItemSize();
        pageObjects.ql.clickAddToCartBtn();
        pageObjects.ql.clickOnCloseQuicklookBtn();
        pageObjects.header.getCartItemCount();
        pageObjects.header.clickOnCart();
        pageObjects.cart.verifyCartPageHeading("My Cart1 Item(s)");
        pageObjects.cart.verifyProductDescription(details);
        pageObjects.cart.verifyProductPrice(price);
        pageObjects.cart.getItemSize();
        pageObjects.cart.getDeliveryType();
        pageObjects.cart.getTotalPrice();
        pageObjects.cart.removeItemFromCart();
        pageObjects.cart.verifyEmptyCart("Hey it feels so light!");
    }

}
