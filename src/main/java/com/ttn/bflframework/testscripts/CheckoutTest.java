package com.ttn.bflframework.testscripts;

import com.ttn.bflframework.pages.PageClassObjects;
import com.ttn.bflframework.utils.BaseUtils;
import com.ttn.bflframework.utils.UIUtils;
import com.ttn.bflframework.utils.VerifyUtils;
import com.ttn.bflframework.utils.WaitUtils;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class CheckoutTest extends BaseUtils {

    public PageClassObjects pageObjects;
    @BeforeMethod
    public void test() throws IOException {
        UIUtils utils = new UIUtils(driver,testReport);
        WaitUtils wUtils = new WaitUtils(driver);
        VerifyUtils vUtils= new VerifyUtils(driver,testReport);
        pageObjects= new PageClassObjects(utils,wUtils,vUtils);

    }

    public void checkoutAsRegisteredUser()
    {
     //   pageObjects.common.countrySelection("Oman");
        pageObjects.home.mouseHoverOnProfileIcon();
        pageObjects.home.clickSignIn();
    //    pageObjects.signIn.enterUsermail("srikant8@mailnator.com");
     //   pageObjects.signIn.enterPassword("12345678");
        pageObjects.signIn.clickSignIn();
        pageObjects.header.clickNew();
        String details= pageObjects.plp.getFirstProductDescription();
        String price= pageObjects.plp.getFirstProductPrice();
        pageObjects.plp.mousehoverOnFirstProduct();
        pageObjects.plp.clickQuickLookBtn();
        pageObjects.ql.clickOnSeeProductDetails();
        pageObjects.pdp.selectAvailableItemSize();
        pageObjects.pdp.clickAddToCartBtn();
      //  pageObjects.header.getCartItemCount();
        pageObjects.header.clickOnCart();
       // pageObjects.cart.verifyCartPageHeading("My Cart1 Item(s)");
       // pageObjects.cart.verifyProductDescription(details);
        //pageObjects.cart.verifyProductPrice(price);
        pageObjects.cart.getItemSize();
        String deliveryOption= pageObjects.cart.getDeliveryType();
        pageObjects.cart.getTotalPrice();
        //pageObjects.cart.removeItemFromCart();
        //pageObjects.cart.verifyEmptyCart("Hey it feels so light!");
        pageObjects.cart.clickCheckoutBtn();
        pageObjects.checkout.enterUsermail("sp@mailnator.com");
        pageObjects.checkout.enterPassword("12345678");
        pageObjects.checkout.clickSignIn();
        pageObjects.checkout.verifyStandardDeliveryOption(deliveryOption);
        pageObjects.checkout.addUserNewAddress();
        pageObjects.checkout.clickContinueBtn();


    }

}
