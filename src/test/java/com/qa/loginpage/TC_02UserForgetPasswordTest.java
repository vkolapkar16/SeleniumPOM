package com.qa.loginpage;

import org.testng.annotations.Test;
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

public class TC_02UserForgetPasswordTest extends Baseclass
{
	LoginPage lp;
	
	@Test(priority = 1)
	public void userForgetPassowrd() throws InterruptedException
	{
		lp= new LoginPage(driver);
		//testCaseId="2493";
		
		lp.clickonForgetPassword();
		Thread.sleep(5000);
		lp.sendKeysEmailID("vishwajeet.kolapkar@tech-trail.com");
		Thread.sleep(5000);
		lp.clickonSendLinkbtn();
		Thread.sleep(1000);
		
		
		String messageFetched= driver.findElement(By.xpath("//span [text()='Email Sent Successfully']")).getText();
		String expectedMessage = "Email Sent Successfully";
		
		Assert.assertEquals(messageFetched.toLowerCase(), expectedMessage.toLowerCase(), "User Failed to Login on QuickAssist");
		Reporter.log("User Login Successfully Test Case Passed");
		System.out.println("User Login Successfully Test Case Passed");
		
		Thread.sleep(3000);
		driver.navigate().refresh();
	}
	
	@AfterMethod
	public void quitBrowser()
	{
		driver.quit();
	}
}