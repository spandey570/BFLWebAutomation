package com.ttn.bflframework.utils;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseUtils {
   public WebDriver driver;
   public ExtentReports extent;
   public ExtentTest testReport;
   Logger log = Logger.getLogger(BaseUtils.class);
   public String url;
   public String envkey;
   public String browserkey;

   public UIUtils utils;

   String usrDirectory= System.getProperty("user.dir");



@BeforeTest
public void config()
{
   //Extent Report Setup in BaseLib
   extent = new ExtentReports(usrDirectory+"\\src\\main\\AutomationReport\\AutomationReport.html", true);
   extent.addSystemInfo("HostName", "Srikant Pandey");
   extent.addSystemInfo("Environment", "Web");
   extent.loadConfig(new File(usrDirectory+"\\src\\main\\resources\\extent-config.xml"));

   //Logger Setup in BaseLib
   PropertyConfigurator.configure(usrDirectory+"\\src\\main\\resources\\log4j.properties");
}

@BeforeMethod
protected void startReporting(Method method) {
   String testName;

   testName = this.getClass().getSimpleName() + " : " + method.getName();
   System.out.println(method.getAnnotation(Test.class).description());
   testReport = extent.startTest(testName, method.getAnnotation(Test.class).description());

   log.info("Extent report logging started for " + testName);
   System.out.println(">>>>> Execution started: " + testName);
   testReport.log(LogStatus.INFO, "Test execution started.");
}


@BeforeClass()

   public void setUp() throws IOException {

   String isRunSameBrowser = GenericUtils.getDataFromConfig("isRunSameBrowser");
   if(isRunSameBrowser.equalsIgnoreCase("Yes")) {
      browserkey = GenericUtils.getDataFromConfig("browser");
      driverInitilization(browserkey);
      envkey = GenericUtils.getDataFromConfig("environment");
      url = getEnvironmentURL(envkey);
      driver.get(url);
      driver.manage().deleteAllCookies();
      driver.manage().window().maximize();
   }

}

   @BeforeMethod()

   public void setUpMultiInstance() throws IOException {

      String isRunSameBrowser = GenericUtils.getDataFromConfig("isRunSameBrowser");
      if(isRunSameBrowser.equalsIgnoreCase("No")) {
         browserkey = GenericUtils.getDataFromConfig("browser");
         driverInitilization(browserkey);
         envkey = GenericUtils.getDataFromConfig("environment");
         url = getEnvironmentURL(envkey);
         driver.get(url);
         driver.manage().deleteAllCookies();
         driver.manage().window().maximize();
      }

   }



   private String getEnvironmentURL(String environment) throws IOException {
      switch (environment.toUpperCase()) {
         case "TEST":
            return "https://bflweb:bflwebttn14@@bfltest-web-client.qa3.tothenew.net/"+GenericUtils.getDataFromConfig("language");
         case "DEV":
            return "https://bflweb:bflwebttn14@@www.bfl-web-client.qa3.tothenew.net/"+GenericUtils.getDataFromConfig("language");
         case "UAT":
            return "https:/bflweb:bflwebttn14@@/bfluat-web-client.qa3.tothenew.net/"+GenericUtils.getDataFromConfig("language");

            default:
            return GenericUtils.getDataFromConfig("url");
      }
   }

   public void driverInitilization(String browser)
{
     System.out.println(browser+" is going to launch");
   if (browser.equalsIgnoreCase("Chrome")) {
      System.setProperty("webdriver.chrome.driver", usrDirectory+"\\src\\main\\Test\\Chrome74\\chromedriver.exe");
      driver = new ChromeDriver();
      log.info(browser+ "browser instance is launching");
   } else if (browser.equalsIgnoreCase("Firefox")) {
      System.setProperty("webdriver.gecko.driver", usrDirectory+"\\src\\main\\Drivers\\geckodriver.exe");
      driver = new FirefoxDriver();
      log.info(browser+ "browser instance is launching");
   } else if (browser.equalsIgnoreCase("Edge")) {
      System.setProperty("webdriver.edge.driver", "");
      driver = new EdgeDriver();
      log.info(browser+ "browser instance is launching");
   } else if (browser.equalsIgnoreCase("Safari")) {
      System.setProperty("webdriver.safari.driver", "");
      driver = new SafariDriver();
      log.info(browser+ "browser instance is launching");
   }
}


   @AfterMethod
protected void reportFailure(ITestResult result, Method method) {
   String testName = this.getClass().getSimpleName() + " : " + method.getName();
   if (result.getStatus() == ITestResult.FAILURE) {
      try {
           String screenshotName = GenericUtils.takeScreenshot(driver);
         testReport.log(LogStatus.FAIL, "<b>Test case failed with exception: </b><br>" +
                 result.getThrowable().toString().replace("\n", "<br>") +
                 "<br><b>Snapshot:</b><br>" + testReport.addScreenCapture(screenshotName));
      } catch (Exception e) {
         log.error("Unable to add screenshot to reports");
      }
   } else if (result.getStatus() == ITestResult.SKIP) {
      testReport.log(LogStatus.SKIP, "<b>Test case skipped with message: </b>" + result.getThrowable());
   }
   System.out.println(">>>>> Execution ended: " + testName);
   testReport.log(LogStatus.INFO, "Test execution completed.");
   extent.endTest(testReport);
}
   @AfterClass(enabled = true)


   protected void tearDown() throws IOException {
      String isRunSameBrowser = GenericUtils.getDataFromConfig("isRunSameBrowser");
      if(isRunSameBrowser.equalsIgnoreCase("Yes")) {
      driver.quit();
   }}

   @AfterMethod(enabled = true)

   protected void tearDownMultiInstance() throws IOException {
      String isRunSameBrowser = GenericUtils.getDataFromConfig("isRunSameBrowser");
      if (isRunSameBrowser.equalsIgnoreCase("No")) {
         driver.quit();
      }
   }


   @AfterSuite(alwaysRun = true)
   protected void endReporting() {
      extent.flush();
      extent.close();


   }

}


