package com.qa.PageClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.xuriti.dev.TestBase.Baseclass;

public class LoginPage extends Baseclass
{
	
	@FindBy(xpath = "//input [@formcontrolname='email']")private WebElement emailid;
	@FindBy(xpath = "//input [@formcontrolname='password']")private WebElement password;
	@FindBy(xpath = "//span [text()=' LOGIN ']")private WebElement login;
	@FindBy(xpath = "//a [text()=' Forgot Password ? ']")private WebElement forgetpassword; 
	@FindBy(xpath = "//span [text()=' SEND RECOVERY LINK ']")private WebElement sendlinkbtn;
	
	
	public LoginPage(WebDriver driver)
	{
	PageFactory.initElements(driver,this);	
	}
	
	
	public void sendKeysEmailID(String email)
	{
		emailid.sendKeys(email);
	}
	public void sendKeysPassword(String pwd)
	{
		password.sendKeys(pwd);
	}
	public void clickOnLogin()
	{
		login.click();
	}
	
	public void userLogin(String ID, String PWD)
	{
		emailid.sendKeys(ID);
		password.sendKeys(PWD);
		login.click();
	}
	
	public void clickonForgetPassword()
	{
		forgetpassword.click();
	}
	
	public void clickonSendLinkbtn()
	{
		sendlinkbtn.click();
	}
	
}
