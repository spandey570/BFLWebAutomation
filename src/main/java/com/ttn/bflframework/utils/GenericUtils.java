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

    public static WebDriver driver;
    static File DestFile = new File("D:\\BFLWebAutomation\\BFLWebAutomation\\src\\main\\resources\\ScreenShots");

    public static String getDataFromConfig(String key) {
        String value = null;
        Properties prop = new Properties();
        File f = new File("D:\\BFLWebAutomation\\BFLWebAutomation\\src\\main\\resources\\config.properties");
        FileInputStream fip = null;
        try {
            fip = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(fip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String takeScreenshot() throws IOException {

        String screenshotName = "image" + System.currentTimeMillis() + ".png";
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(SrcFile, DestFile);
        return screenshotName;

    }

    }