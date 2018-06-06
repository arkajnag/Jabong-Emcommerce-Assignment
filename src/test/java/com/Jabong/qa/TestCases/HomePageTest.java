package com.Jabong.qa.TestCases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.Jabong.qa.TestBase.TestBase;
import com.Jabong.qa.TestUtil.TestUtil;
import com.Jabong.qa.pages.HomePage;

public class HomePageTest extends TestBase{

	HomePage hmPage;
	public HomePageTest()
	{
		super();
	}
	
	@BeforeTest
	public void SetUpExtentReport()
	{
		TestUtil.SetUpExtentReportService();
	}
	
	@BeforeMethod()
	public void SetUp()
	{
		Initialisation();
		hmPage=new HomePage();
	}
	
	@Test(priority=1)
	public void VerifyHomePageTitleTest()
	{
		TestUtil.logger=TestUtil.report.startTest("VerifyHomePageTitleTest");
		String ActualHomePageTitle=hmPage.VerifyHomePageTitle();
		Assert.assertEquals(ActualHomePageTitle, prop.getProperty("HomePageTitle"), "Home Page Title match as expected");
	}
	
	@DataProvider
	public Object[][] getTestDataSheet() throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		Object[][] data=TestUtil.getTestData("SearchItem");
		return data;
	}
	@Test(priority=2,dataProvider="getTestDataSheet")
	public void SearchItemServiceTest(String SearchItemName)
	{
		TestUtil.logger=TestUtil.report.startTest("SearchItemServiceTest");
		hmPage.SearchItemService(SearchItemName);
	}
	
	@AfterMethod()
	public void TearDown(ITestResult result) throws IOException
	{
		TestUtil.TearDownExtentReportService(result);
		TestUtil.report.endTest(TestUtil.logger);
		driver.quit();
	}
	
	@AfterTest()
	public void TearDownExtentReport()
	{
		TestUtil.report.flush();
		TestUtil.report.close();
	}
}
