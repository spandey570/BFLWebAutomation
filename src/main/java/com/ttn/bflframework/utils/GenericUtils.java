package com.ttn.bflframework.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GenericUtils {



    public static String getDataFromConfig(String key) throws IOException {

        Properties prop = new Properties();
        File f = new File("D:\\BFLWebAutomation\\BFLWebAutomation\\src\\main\\resources\\config.properties");

        FileInputStream fip = new FileInputStream(f);

         prop.load(fip);
         String value= prop.getProperty(key);
         return value;
    }

    public static String takeScreenshot(WebDriver driver) throws IOException {

        String screenshotName = "image" + System.currentTimeMillis() + ".png";
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(SrcFile, new File("D:\\BFLWebAutomation\\BFLWebAutomation\\src\\main\\resources\\ScreenShots\\"+screenshotName));
        return screenshotName;

    }



    }