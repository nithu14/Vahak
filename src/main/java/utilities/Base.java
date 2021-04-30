package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Base {
	public static WebDriver driver1;
	public static ExcelReadwrite ex;
	public Properties prop;
	public static Logger log =LogManager.getLogger(Base.class.getName());
	
	
	
	public WebDriver initialise() throws IOException {
		String project = System.getProperty("user.dir");
		
		//initialise driver
		System.setProperty("webdriver.chrome.driver", project+"\\Resource\\chromedriver.exe");
		driver1=   new ChromeDriver();
		driver1.manage().window().maximize();
		driver1.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//define property file
		FileInputStream fis = new FileInputStream(project+"\\Resource\\gobol.properties");
		prop = new Properties();
		prop.load(fis);
		
		return driver1;
		 		
	}
	
	
	public static String getscreenshot(String Testname,WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"//Reports//"+Testname+".png";
		FileUtils.copyFile(source, new File(path));
		return path;
	}
	
	/*
	 * @AfterMethod public void Teardown() {
	 * 
	 * 
	 * 
	 * 
	 * }
	 */
	

}
