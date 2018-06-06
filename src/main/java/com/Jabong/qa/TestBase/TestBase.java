package com.Jabong.qa.TestBase;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class TestBase {

	public static Properties prop;
	public static WebDriver driver;
	public TestBase()
	{
		prop=new Properties();
		try{
		FileInputStream ip=new FileInputStream("/Users/arka/Documents/workspace/JabongPOM/src/main/java/com/Jabong/qa/Config/config.properties");
		prop.load(ip);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void Initialisation()
	{
		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "/Users/arka/Documents/workspace/chromedriver");
			ChromeOptions options= new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-extensions");
			options.setAcceptInsecureCerts(true);
			driver=new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			driver.get(prop.getProperty("url"));
		}else
		{
			System.setProperty("webdriver.gecko.driver", "/Users/arka/Documents/workspace/geckodriver");
			FirefoxOptions options=new FirefoxOptions();
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-extensions");
			options.setAcceptInsecureCerts(true);
			driver=new FirefoxDriver(options);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			driver.get(prop.getProperty("url"));			
		}
	}
}
