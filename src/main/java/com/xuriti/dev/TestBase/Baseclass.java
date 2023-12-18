package com.xuriti.dev.TestBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.testrailmanager.TestRailManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.utility.nullability.NeverNull.ByDefault;


@Listeners(com.xuriti.dev.Utility.Testlisteners.class)
public class Baseclass 
{
	public static WebDriver driver;
	protected String testCaseId;
	
	
	 @Parameters("browser")
	public void setupBrowser(@Optional("chrome")String browser) throws IOException 
	{
		 ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-gpu");
			options.addArguments("--disable-dev-shm-usage"); //Overcome limited resource problem
		//	options.addArguments("headless", "window-size=1400,800");
	        options.addArguments("--remote-allow-origins=*");
	    if (browser.equalsIgnoreCase("chrome")) 
	    {
          
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        } 
 //           else if (browser.equalsIgnoreCase("firefox")) 
//        {
//            System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver");
//            WebDriverManager.firefoxdriver().setup();
//            driver = new FirefoxDriver();
//        }
		
		driver.get("http://quickassist.tech-trail.com/#/");
	 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}

	public org.openqa.selenium.support.ui.WebDriverWait waits()
	{
		org.openqa.selenium.support.ui.WebDriverWait  Wait = new org.openqa.selenium.support.ui.WebDriverWait
				(driver, Duration.ofMillis(5000));
		return Wait;
	}
	
	
//	@AfterMethod
//	public void addResultsToTestRail(ITestResult result){
//	if(result.getStatus() == ITestResult.SUCCESS) 
//	{
//		TestRailManager.addResultsForTestCase(testCaseId,TestRailManager.TEST_CASE_PASS_STATUS, "");
//	}
//	else if(result.getStatus() == ITestResult.FAILURE) 
//	{
//		TestRailManager.addResultsForTestCase(testCaseId,TestRailManager.TEST_CASE_FAIL_STATUS,"test got failed..."+ result.getTestName()+ ": FAILED");
//	}
//	}
}
