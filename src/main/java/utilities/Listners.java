package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Listners extends Base implements ITestListener {
	public ExtentTest test;
	ExtentReports extent=Extendsreport.extendrep();
	
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		log.info("Test Started "+result.getMethod().getMethodName());
		test= extent.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		log.info("Test Passed "+result.getMethod().getMethodName());
		test.pass("Test passed");
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		WebDriver driver=null;
		log.error("Test failed"+methodName);
		test.fail("Failed"+result.getThrowable());
		
		  try { 
		  driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance()); 
		  } catch (Exception e1) { 
			  // TODOAuto-generated catch block
		  
		  }
		  
		try {
			test.addScreenCaptureFromPath(getscreenshot(methodName,driver), methodName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
		extent.flush();
	}

}
