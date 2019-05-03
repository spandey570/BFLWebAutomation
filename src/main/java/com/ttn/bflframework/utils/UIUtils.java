package com.ttn.bflframework.utils;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import sun.plugin.util.UIUtil;

import java.io.IOException;



public class UIUtils{

     private WebDriver driver;
     private ExtentTest testReport;
    public final static Logger log = Logger.getLogger(UIUtil.class);

    public UIUtils(WebDriver driver, ExtentTest testReport)
    {
        this.driver=driver;
        this.testReport=testReport;
    }

    public void click(By locator, String description) throws IOException {
        try {
            driver.findElement(locator).click();
            testReport.log(LogStatus.PASS, description);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error("Could not perform action " + e.getMessage());
            reportTestStepFailure(description,e,true);

        }
    }

    public void Type(By locator, String description,String data)
    {
        try {
            driver.findElement(locator).sendKeys(data);
            testReport.log(LogStatus.PASS, description);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error("Could not perform action " + e.getMessage());
            reportTestStepFailure(description,e,true);

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
