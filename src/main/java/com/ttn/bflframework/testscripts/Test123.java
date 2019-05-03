package com.ttn.bflframework.testscripts;

import com.ttn.bflframework.utils.BaseUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class Test123 extends BaseUtils {
    public WebDriver driver;
//    @BeforeMethod
//    public void setup()
//    {
////        System.setProperty("webdriver.chrome.driver","D:\\BFLWebAutomation\\BFLWebAutomation\\src\\main\\Drivers\\chromedriver.exe");
////        driver = new ChromeDriver();
//        System.out.println("Chrome Browser is launched");
//    }

    @Test(priority = 1)
    public void test1()
    {
        System.out.println("Test1 Executed");
    }

//    @BeforeMethod
//    public void teardown()
//    {
//      //  driver.close();
//        System.out.println("Browser is closed");
//    }



    @Test(priority = 3)
    public void test3()
    {
        System.out.println("Test3 Executed");
    }

    @Test(priority = 2)
    public void test2()
    {
        System.out.println("Test2 Executed");
    }
}
