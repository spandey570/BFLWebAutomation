package com.ttn.bflframework.pages;

import com.ttn.bflframework.utils.UIUtils;
import com.ttn.bflframework.utils.VerifyUtils;
import com.ttn.bflframework.utils.WaitUtils;

import java.io.IOException;

public class PageClassObjects {


     public HomePage home;
     public SignInPage signIn;
     public ProductListingPage plp;
     public HeaderPage header;
     public WishlistPage wishlist;
     public QuicklookPage ql;

     public PageClassObjects(UIUtils utils, WaitUtils wUtils, VerifyUtils vUtils) throws IOException {
         home= new HomePage(utils,wUtils,vUtils);
         signIn= new SignInPage(utils,wUtils,vUtils);
         plp= new ProductListingPage(utils,wUtils,vUtils);
         header= new HeaderPage(utils,wUtils,vUtils);
         wishlist= new WishlistPage(utils,wUtils,vUtils);
         ql= new QuicklookPage(utils,wUtils,vUtils);
     }

}
