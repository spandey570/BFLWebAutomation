package com.ttn.bflframework.utils;


import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


import java.io.IOException;

public class VerifyUtils {

    private WebDriver driver;
    private ExtentTest testReport;
    public final static Logger log = Logger.getLogger(VerifyUtils.class);

    public VerifyUtils(WebDriver driver, ExtentTest testReport)
    {
        this.driver=driver;
        this.testReport=testReport;
    }


    public void verifyStringEquals(String actual, String expected, String description, boolean takeScreenshot) {
        try {
            Assert.assertEquals(actual, expected);
            log.info("Assertion "+description+" is successful");
            testReport.log(LogStatus.PASS,description);
            System.out.println("Assertion successful");
        }
        catch (AssertionError e)
        {
            reportTestStepFailure(description, e,takeScreenshot);
            log.info("Assertion Failure: "+e);
        }
    }

    public void verifyIntEquals(int actual, int expected, String description, boolean takeScreenshot) {
        try {
            Assert.assertEquals(actual,expected);
            log.info("Assertion "+description+" is successful");
            testReport.log(LogStatus.PASS,description);
            System.out.println("Assertion successful");
        }
        catch (AssertionError e)
        {
            reportTestStepFailure(description, e,takeScreenshot);
            log.info("Assertion Failure: "+e);
        }
    }


    private void reportTestStepFailure(String description, AssertionError e, boolean takeScreenshot) {
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
