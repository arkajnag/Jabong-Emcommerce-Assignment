package com.Jabong.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Jabong.qa.TestBase.TestBase;

public class HomePage extends TestBase{

	@FindBy(xpath="//a[@id='signin-model-wishlist']")
	WebElement SignInLink;
	
	@FindBy(xpath="//a[contains(text(),'Signup')]")
	WebElement SignUpLink;
	
	@FindBy(xpath="//ul[@id='mainTopNav']/li//a[contains(text(),'women')]")
	WebElement WomenLink;
	
	@FindBy(xpath="//input[@id='search']")
	WebElement SearchTextField;
	
	@FindBy(xpath="//span[@class='top-search-icon hidden-xs']")
	WebElement SearchButton;
	
	WebDriverWait wait;
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String VerifyHomePageTitle()
	{
		String HomePageTitle=driver.getTitle();
		return HomePageTitle;
	}
	
	public JeansPage SearchItemService(String SearchItem)
	{
		wait= new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(SearchTextField));
		SearchTextField.sendKeys(SearchItem);
		wait.until(ExpectedConditions.visibilityOf(SearchButton));
		SearchButton.click();
		return new JeansPage();
	}
}
