package com.ttn.bflframework.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WaitUtils {

    private static WebDriver driver;
    public final static Logger log = Logger.getLogger(WaitUtils.class);

    public WaitUtils(WebDriver driver)
    {
        this.driver=driver;
    }

    public void iWaitForSeconds( int sec)
    {
        checkForPageLoad(60);
        log.info("Waiting for "+sec+ "secs implicitly for loading the page");
        driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
    }

    public void iWaitForMinutes(int min)
    {
        checkForPageLoad(60);
        log.info("Waiting for "+min+ "mins implicitly for loading the page");
        driver.manage().timeouts().implicitlyWait(min, TimeUnit.MINUTES);
    }

    public void iSleep(int sec)
    {
        try {
            log.info("Applying hard code wUtils for "+sec+ "secs");
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void eWaitForElementVisible(By locator, int sec)
    {
        try {
            checkForPageLoad(60);
            log.info("Waiting for "+sec+" seconds an element to be visible using String locator: "+ locator.toString());
            WebDriverWait wait = new WebDriverWait(driver, sec);
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(locator)));
            log.info(locator +" is now visible");
        }
        catch (Exception e)
        {
            log.info("Exception Found: "+e.getMessage());
        }
    }

    public void eWaitForElementToBeClickable(By locator, int sec)
    {
        try {
            checkForPageLoad(60);
            log.info("Waiting for "+sec+" seconds an element to be visible using String locator: "+ locator.toString());
            WebDriverWait wait = new WebDriverWait(driver, sec);
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
        }
        catch (Exception e)
        {
            log.info("Exception Found: "+e.getMessage());
        }
    }

    public void checkForPageLoad(int sec) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < sec; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            log.info("Waiting for page load till "+sec+" secs to check If page Is ready after 1 second.");
            //To check page ready state.

            if (js.executeScript("return document.readyState").toString().equals("complete")) {
                log.info("Page is completely loaded after "+(i+1)+" seconds");
                break;


            }

        }
    }

    public boolean waitForPageLoad(int iTimeOut) {
        try {
            Thread.sleep(1000);
            log.info("Waiting For Page load via JS");
            ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor) driver).executeScript(
                            "return document.readyState").equals("complete");
                }
            };
            WebDriverWait wait = new WebDriverWait(driver, iTimeOut);
            wait.until(pageLoadCondition);
            return true;

        } catch (Exception e) {
            log.error("Error Occured waiting for Page Load "
                    + driver.getCurrentUrl());
        }
        return false;
    }


}
