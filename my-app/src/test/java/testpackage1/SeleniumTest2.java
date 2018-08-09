package testpackage1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class SeleniumTest2{
	
	public WebDriver driver;
	
	@Test(priority=1)
	public void clickonFirstProduct() throws InterruptedException {
		System.out.println("In clickonFirstproduct test");
		driver.findElement(By.xpath("//li[@id='result_0']//img[@class='s-access-image cfMarker']")).click();
		System.out.println("Clicked on first product");
	}	
	@Test(priority=2)
	public void checkAvailability() throws InterruptedException
	{
		System.out.println("In checkAvailability test");
		Thread.sleep(3000);
		Select dropdown = new Select(driver.findElement(By.name("quantity")));
		List<WebElement> quantity = dropdown.getOptions();
		System.out.println("Quantity available is "+ quantity.size());
		if(quantity.size() > 1)
		{
			System.out.println("More than one item is available");
		}
		else
		{
			System.out.println("item is unavailable");
		}
		
	}

}