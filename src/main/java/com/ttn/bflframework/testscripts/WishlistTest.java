package com.ttn.bflframework.testscripts;

import com.ttn.bflframework.pages.PageClassObjects;
import com.ttn.bflframework.utils.BaseUtils;
import com.ttn.bflframework.utils.UIUtils;
import com.ttn.bflframework.utils.VerifyUtils;
import com.ttn.bflframework.utils.WaitUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class WishlistTest extends BaseUtils {

    public PageClassObjects pageObjects;
    @BeforeMethod
    public void test() throws IOException {
        UIUtils utils = new UIUtils(driver,testReport);
        WaitUtils wUtils = new WaitUtils(driver);
        VerifyUtils vUtils= new VerifyUtils(driver,testReport);
        pageObjects= new PageClassObjects(utils,wUtils,vUtils);

    }

    @Test(description = "Verify that user is able to add & remove the item from the wishlist page")
    public void addRemoveProductFromWishlist()
    {
        pageObjects.home.mouseHoverOnProfileIcon();
        pageObjects.home.clickSignIn();
        pageObjects.signIn.enterUsermail("srikant8@mailnator.com");
        pageObjects.signIn.enterPassword("12345678");
        pageObjects.signIn.clickSignIn();
        pageObjects.header.clickNew();
        String details= pageObjects.plp.getFirstProductDetails();
        pageObjects.plp.mousehoverOnFirstProduct();
        pageObjects.plp.addToWishlist();
        pageObjects.header.getWishlistItemCount();
        pageObjects.header.clickOnWishlist();
        pageObjects.wishlist.verifyWishlistHeading("My Wishlist 1 Item(s)");
        pageObjects.wishlist.verifyProductDetails(details);
        pageObjects.wishlist.deleteItemFromTheWishlist();
        pageObjects.wishlist.verifyWishlistIsEmpty("Wishlist is Empty!");

    }

    @Test(description = "Verify that user is able to add & remove the item from the product listing page")
    public void addRemoveProductFromProductListing()
    {
        pageObjects.home.mouseHoverOnProfileIcon();
        pageObjects.home.clickSignIn();
        pageObjects.signIn.enterUsermail("srikant8@mailnator.com");
        pageObjects.signIn.enterPassword("12345678");
        pageObjects.signIn.clickSignIn();
        pageObjects.header.clickNew();
        pageObjects.plp.mousehoverOnFirstProduct();
        pageObjects.plp.addToWishlist();
        pageObjects.header.getWishlistItemCount();
        pageObjects.plp.removeFromWislist();
        pageObjects.header.verifyWishlistCountIsZero();
    }

    @Test
    public void addRemoveProductFromQL()
    {
        pageObjects.home.mouseHoverOnProfileIcon();
        pageObjects.home.clickSignIn();
        pageObjects.signIn.enterUsermail("srikant8@mailnator.com");
        pageObjects.signIn.enterPassword("12345678");
        pageObjects.signIn.clickSignIn();
        pageObjects.header.clickNew();
        String details= pageObjects.plp.getFirstProductDetails();
        pageObjects.plp.mousehoverOnFirstProduct();
        pageObjects.plp.clickQuickLookBtn();
        pageObjects.ql.verifyProductDetailsOnQL(details);
        pageObjects.ql.addItemToWishlist();
        pageObjects.ql.clickOnCloseQuicklookBtn();
        pageObjects.header.getWishlistItemCount();
        pageObjects.header.clickOnWishlist();
        pageObjects.wishlist.verifyProductDetails(details);
        pageObjects.wishlist.deleteItemFromTheWishlist();
        pageObjects.wishlist.verifyWishlistIsEmpty("Wishlist is Empty!");
    }
}
