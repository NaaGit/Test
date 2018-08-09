package com.pack.common.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductDetails {

	public WebDriver driver;
	HomePage homepage = new HomePage(driver);
	By availQuantity = By.name("quantity");

	ProductDetails(WebDriver driver) {
		this.driver = driver;
	}

	public int checkAvailability(String product) {
		int size = 0;
		// ResultsOfSearch results = homepage.searchByProduct(product);
		// results.clickFirstProductInResults(product);
		try
		{
			if(driver.findElement(availQuantity).isDisplayed()) 
			{
				Select dropdown = new Select(driver.findElement(availQuantity));
				List<WebElement> quantity = dropdown.getOptions();
				size = quantity.size();
			}
		}catch (NoSuchElementException ex) {
			System.out.println("Product is not available or login to check the product quantity");
		}
		return size;
	}

}
