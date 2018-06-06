package com.Jabong.qa.TestUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import com.Jabong.qa.TestBase.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestUtil extends TestBase{

	/********************************************************************************************/
	/****** Reusable class for capturing Screenshot and Returning the Screenshot Location. ******/
	/********************************************************************************************/
	public static String CaptureScreenshots(String screensShotName) throws IOException
	{
		File srcfile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcfile, new File("/Users/arka/Documents/workspace/JabongPOM/Screenshots/"+screensShotName+".jpeg"));
		System.out.println("=========Screenshot Captured=========");
		String ScreenShotLocation="/Users/arka/Documents/workspace/JabongPOM/Screenshots/"+screensShotName+".jpeg";
		return ScreenShotLocation;
	}
	
	/********************************************************************************************/
	/******       Reusable class for capturing Static User Credentials in HashMaps.        ******/
	/********************************************************************************************/
	public HashMap<String,String> SignInUserNamePassword()
	{
		HashMap<String,String> UserCredentials= new HashMap<String, String>();
		UserCredentials.put("UserCredential", "arka.imps@gmail.com_password@123");
		return UserCredentials;
	}
	
	/********************************************************************************************/
	/******       Reusable class for Extent Report and Re-running Failed Scripts.          ******/
	/********************************************************************************************/
	public static ExtentReports report;
	public static ExtentTest logger;
	
	public static void SetUpExtentReportService()
	{
		report= new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html",true);
		report.addSystemInfo("Host Name", "Arkajyoti Nag");
		report.addSystemInfo("Environment", "Automation Test");
	}
	
	public static void TearDownExtentReportService(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			logger.log(LogStatus.FAIL, "Test Case Failed "+result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed with Exception "+result.getThrowable());
			String ScreenshotLoc=TestUtil.CaptureScreenshots("Failed_"+result.getName()+"_"+(new Date()));
			logger.log(LogStatus.FAIL, "Adding Screenshot for Failed Test Case "+logger.addScreencast(ScreenshotLoc));
		}else if(result.getStatus()==ITestResult.SUCCESS)
		{
			logger.log(LogStatus.PASS, "Test Case Passed "+result.getName());
			String ScreenshotLoc=TestUtil.CaptureScreenshots("Passed_"+result.getName()+"_"+(new Date()));
			logger.log(LogStatus.PASS, "Adding Screenshot for Passed Test Case "+logger.addScreencast(ScreenshotLoc));
		}else if(result.getStatus()==ITestResult.SKIP)
		{
			logger.log(LogStatus.SKIP, "Test Case SKipped "+result.getName());
			String ScreenshotLoc=TestUtil.CaptureScreenshots("Passed_"+result.getName()+"_"+(new Date()));
			logger.log(LogStatus.SKIP, "Adding Screenshot for Skipped Test Case "+logger.addScreencast(ScreenshotLoc));
		}
	}
	
	/********************************************************************************************/
	/******       Reusable class for capturing Data Driven Test using Apache POI.          ******/
	/********************************************************************************************/
	public static Workbook book;
	public static Sheet sheet;
	static FileInputStream ip=null;
	
	public static Object[][] getTestData(String SheetName) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		ip=new FileInputStream(prop.getProperty("sheetLocation"));
		book=WorkbookFactory.create(ip);
		sheet=book.getSheet(SheetName);
		Object[][] data= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				System.out.println(data[i][k]);
			}
		}
		return data;
	}
}
