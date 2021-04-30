package Scripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilities.Base;

public class Sign_Validation extends Base{
	public WebDriver driver;
	@BeforeTest
	public void initialize() throws IOException 
	{
	
		 driver =initialise();

	}

	@Test
	public void Sign_in() throws InterruptedException {
		String expectedtitle="IMDb: Ratings, Reviews, and Where to Watch the Best Movies & TV Shows";
		driver.get(prop.getProperty("URL"));
		log.info("Page Opened");
		driver.findElement(By.xpath("//div[text()='Sign In']")).click();
		driver.findElement(By.xpath("//a/span[text()='Sign in with IMDb']")).click();
		driver.findElement(By.id("ap_email")).sendKeys(prop.getProperty("email"));
		driver.findElement(By.id("ap_password")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.id("signInSubmit")).click();
		log.info("user logged in");
		Thread.sleep(1000);
		String actualtitle = driver.getTitle();
		log.info("Home page displayed"+actualtitle);
		Assert.assertEquals(actualtitle, expectedtitle);
		
	}
	@AfterTest
	public void Save_close() {
		
		driver.quit();
		
	}
}
