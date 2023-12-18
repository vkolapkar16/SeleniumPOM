package com.qa.loginpage;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.PageClass.LoginPage;
import com.xuriti.dev.TestBase.Baseclass;
import com.xuriti.dev.Utility.Utility;

public class TC_01UserLoginTest extends Baseclass
{
	@BeforeClass()
	public void startbrowser() throws IOException
	{
		setupBrowser("chrome");
	}
	
	LoginPage lp;
	
	@Test(priority = 1)
	public void userlogin() throws InterruptedException, IOException
	{
		String filepath = "LPTC.xlsx";
		//testCaseId="2492";
		Utility.clearExcelData(filepath, 0);
		
		Thread.sleep(5000);
		lp= new LoginPage(driver);
		lp.sendKeysEmailID("vishwajeet.kolapkar@tech-trail.com");
		Thread.sleep(5000);
		lp.sendKeysPassword("Techtrail@123");
		lp.clickOnLogin();
		Thread.sleep(5000);
		
		String messageFetched= driver.getTitle();
		String expectedMessage = "TicketingTool";
		
		
		try {
				Assert.assertEquals(messageFetched.toLowerCase(), expectedMessage.toLowerCase(), "User Failed to Login on QuickAssist");
				Utility.SetExcelData("LPTC.xlsx", 0, 1, 5, "Pass");
				Reporter.log("User Login Successfully Test Case Passed");
				System.out.println("User Login Successfully Test Case Passed");
			}
		catch (AssertionError e) 
			{	Utility.SetExcelData("LPTC.xlsx", 0, 1, 5, "TestCaseFailed");
				System.out.println("User Login Successfully Test Case Failed");
				Reporter.log("User Login Successfully Test Case Passed");
				Assert.fail();
			}
		
		Thread.sleep(3000);
		driver.navigate().refresh();
	}
	
	@AfterMethod
	public void quitBrowser()
	{
		driver.quit();
	}
}