package Scripts;

import java.io.IOException;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilities.Base;


public class New_Account extends Base{
	public WebDriver driver;
	@BeforeTest
	public void initialize() throws IOException 
	{
	
		 driver =initialise();

	}
	@Test(dataProvider="get_data",dataProviderClass=dataprovider.CommomDP.class)
	public void create_account(Map<String,String> hm) {
		boolean displayed=false;
		String sl = hm.get("SI_No");
		int row=Integer.parseInt(sl);
		String error=null;
		String Actual="Authentication required";
		String sheetname = "Sign_In";
		driver.get(prop.getProperty("URL"));
		log.info("Page Opened");
		driver.findElement(By.xpath("//div[text()='Sign In']")).click();
		driver.findElement(By.xpath("//a[text()='Create a New Account']")).click();
		log.info("Create account");
		driver.findElement(By.cssSelector("input#ap_customer_name")).sendKeys(hm.get("Name"));
		driver.findElement(By.cssSelector("input#ap_email")).sendKeys(hm.get("Email"));
		driver.findElement(By.cssSelector("input#ap_password")).sendKeys(hm.get("Password"));
		driver.findElement(By.cssSelector("input#ap_password_check")).sendKeys(hm.get("Re_enter_PWD"));
		driver.findElement(By.cssSelector("input#continue")).click();
		try {
		displayed = driver.findElement(By.xpath("//span[@class='a-list-item']")).isDisplayed();
		
		}catch(Exception e) {
			ex.writeCellValue(sheetname, row, 6, "Pass");
			log.info("Test passed, moved to next validation screen");
		}
		
		
		if(displayed) {
			
			error=driver.findElement(By.xpath("//span[@class='a-list-item']")).getText();
			ex.writeCellValue(sheetname, row, 6, error);
			log.error("invalid value entered in create account");
		}
		
		String title = driver.getTitle();
		log.info("Page tilte is "+title);
		Assert.assertEquals(Actual, title);
		
	}
	
	@AfterTest
	public void Save_close() throws Exception{
		String path=System.getProperty("user.dir")+"\\Resource\\Team_Vahak.xlsx";
		ex.saveAndClose(path);
		driver.quit();
		
	}
	
}
