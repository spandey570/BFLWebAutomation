package com.ttn.bflframework.utils;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import sun.plugin.util.UIUtil;

import java.io.IOException;
import java.util.List;


public class UIUtils {

    private WebDriver driver;
    private ExtentTest testReport;
    public final static Logger log = Logger.getLogger(UIUtil.class);

    public UIUtils(WebDriver driver, ExtentTest testReport) {
        this.driver = driver;
        this.testReport = testReport;
    }

    public void click(By locator, String description) {
        try {
            driver.findElement(locator).click();
            testReport.log(LogStatus.PASS, description);
            log.info(description);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Could not perform action " + e.getMessage());
            reportTestStepFailure(description, e, true);

        }
    }

    public void Type(By locator, String description, String data) {
        try {
            driver.findElement(locator).sendKeys(data);
            testReport.log(LogStatus.PASS, description + " : " + data);
            log.info(description + " :" + data);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Could not perform action " + e.getMessage());
            reportTestStepFailure(description, e, true);

        }
    }

    public String getText(By locator, String description) {
        try {
            String text = driver.findElement(locator).getText();
            log.info(description+" :"+text);
            testReport.log(LogStatus.PASS, description+" : "+text);
            return text;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in returning text of a webelement!" + e.getMessage());
            reportTestStepFailure(description, e, true);
            return "Error in returning text of a webelement!" + e.getMessage();
        }
    }

    public void moveBack()
    {
        driver.navigate().back();
        log.info("Moved back to last page");
        testReport.log(LogStatus.PASS,("Moved back to last page"));
    }

    public int getElementList(By locator)
    {
        List<WebElement> ele = driver.findElements(locator);
        return ele.size();

    }

    public void verifyUrlExist(String input)
    {
        String currentUrl= getUrl();
        if(currentUrl.contains(input))
        {
            log.info("Verify current url consist "+input+" in the link");
            testReport.log(LogStatus.PASS,"Verify current url consist "+input+" in the link");
        }
        else
        {
            log.info("User is redirected to incorrect url");
            testReport.log(LogStatus.FAIL,"User is redirected to incorrect url");
        }
    }

    public String getAttribute(By locator)
    {
        return driver.findElement(locator).getAttribute("className");
    }

    public void mouseHover(By locator,String description)
    {
        try {
            Actions ac = new Actions(driver);
            ac.moveToElement(driver.findElement(locator)).build().perform();
            log.info(description);
            testReport.log(LogStatus.PASS,description);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error("Could not perform mouse-hover action" + e.getMessage());
            reportTestStepFailure(description, e, true);
        }
    }

    public Boolean isElementPresent(By locator,String description) {
        try {
            if (driver.findElements(locator).size() != 0
                    && driver.findElement(locator).isDisplayed()) {
                log.info(description);
                testReport.log(LogStatus.PASS,description);
                return true;
            } else {
                log.info("Locator: " + locator.toString() + " not present on page");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error occurred while checking the presence of an element on page"
                    + e.getMessage());
            return false;
        }
    }

    public String getUrl()
    {
        return driver.getCurrentUrl();
    }

    public void javaScriptClick(By locator,String description) {
        try {
            if (driver.findElement(locator).isEnabled() && driver.findElement(locator).isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(locator));
                log.info(description);
                testReport.log(LogStatus.PASS,description);
            } else {
                System.out.println("Unable to click on element");
            }
        } catch (Exception e) {
            System.out.println("Unable to click on element " + e.getMessage());
        }
    }


    private void reportTestStepFailure(String description, Exception e, boolean takeScreenshot) {
        if (takeScreenshot) {
            String screenshotName = null;
            try {
                screenshotName = GenericUtils.takeScreenshot(driver);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            testReport.log(LogStatus.ERROR, description + "<br><b>Failed: </b>"
                    + e.getMessage().replace("\n", "<br>") + "<br><b>Snapshot:</b><br>"
                    + testReport.addScreenCapture(screenshotName));
        } else {
            testReport.log(LogStatus.ERROR, description + "<br><b>Failed: </b>"
                    + e.getMessage().replace("\n", "<br>"));
        }
    }
}
