package Scripts;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilities.Base;

public class Get_Movie extends Base{
	public WebDriver driver;
	@BeforeTest
	public void initialize() throws IOException 
	{
	
		 driver =initialise();

	}
	@Test
	public void check_movie() {
		driver.get(prop.getProperty("URL"));
		log.info("Page Opened");
		driver.findElement(By.xpath("//div[text()='Menu']")).click();
		List<WebElement> menu = driver.findElements(By.xpath("//a[@role='menuitem']"));
		Iterator<WebElement> it = menu.iterator();
		while(it.hasNext()) {
			WebElement next = it.next();
			String text = next.getText();
			if(text.equalsIgnoreCase(prop.getProperty("MenuItem"))) {
				next.click();
				log.info("Clicked on menu item");
				break;
			}
		}
		WebElement sort = driver.findElement(By.id("lister-sort-by-options"));
		Select s1 = new Select(sort);
		s1.selectByVisibleText("Release Date");
		log.info("Sorted by release date");
//		s1.selectByValue("Release Date");
		List<WebElement> movies = driver.findElements(By.xpath("//tr/td[2]/a"));
		LinkedList<WebElement> ls = new LinkedList<WebElement>(movies);
		Iterator<WebElement> di = ls.descendingIterator();
		WebElement n1 = di.next();
		System.out.println(n1.getText());
		log.info("Last movie is "+n1.getText());
		n1.click();
		String releasedate = driver.findElement(By.xpath("//div/a[@title='See more release dates']")).getText();
		System.out.println("movie release date is "+releasedate);
		
	}
	@AfterTest
	public void Save_close() {
		
		driver.quit();
		
	}

}
