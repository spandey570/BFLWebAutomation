package com.ttn.bflframework.testscripts;

import com.ttn.bflframework.utils.ExcelUtils;

import java.io.IOException;

public class TestFile {

     public static void main(String args[]) throws IOException {
         String data= ExcelUtils.getCellValue("D:\\Selenium\\ExcelTestData","Locators_English.xls","Wishlist","firstProductDiscount","Locator");
         System.out.println(data);
     }
}
