package com.pack.common.pageobjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class HomePage {
	public WebDriver driver;

	String resultsText = "products found";
	String title = "Electronics Site | Homepage";
	String cartTitle = "Your Shopping Cart ";
	String checkOutTitle = "Checkout";

	// page elements
	By searchBox = By.xpath("//input[@id='js-site-search-input']");
	By results = By.xpath("//div[@class='pagination-bar-results']");
	By productdetails;
	By addToCart = By.xpath("//ul[@class='product__listing product__list']/li[1]//button[@type='submit']");
	By sort = By.id("sortOptions1");
	By checkoutbtn = By.xpath("//a[@class='btn btn-primary btn-block add-to-cart-button']");
	By headingCheckOut = By.xpath(
			"//span[@class='headline-text']//span[@class='headline-text'][contains(text(),'Added to Your Shopping Cart')]");
	// By OrderTotal = By.xpath("")
	By checkOutCart = By.xpath(
			"//div[@class='cart__actions border']//button[@class='btn btn-primary btn-block btn--continue-checkout js-continue-checkout-button'][contains(text(),'Check Out')]");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean IsElementPresent(By locator) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try {
			driver.findElement(locator);
			// If element is found set the timeout back and return true
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			return true;
		} catch (NoSuchElementException e) {
			// If element isn't found, set the timeout and return false
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			return false;
		}

	}

	public boolean verifyTitle() {
		boolean flag = false;
		if (driver.getTitle().contains(title)) {
			flag = true;
			return flag;
		} else {
			System.out.println("The title is incorrect");
			return flag;
		}
	}

	public boolean searchByProduct(String product) throws InterruptedException {
		boolean flag = false;
		if (verifyTitle()) {
			if (IsElementPresent(searchBox)) {
				Reporter.log("Clearing any previous searches", true);
				driver.findElement(searchBox).clear();
				Reporter.log("searching for product " + product, true);
				driver.findElement(searchBox).sendKeys(product, Keys.ENTER);

				try {
					if (driver.findElement(results).isDisplayed()) {
						Reporter.log("The results displayed are:", true);
						Reporter.log(driver.findElement(results).getText(), true);
						flag = true;
					}
				} catch (Exception e) {
					Reporter.log("results are not found for the product" + product, true);
				}

			} else {
				Reporter.log(searchBox + "is not displayed", true);
			}
		} else {
			Reporter.log("Page is not displayed correctly", true);
		}
		return flag;

	}

	public boolean addtocart() throws InterruptedException {
		boolean flag = false;
		if (IsElementPresent(addToCart)) {
			driver.findElement(addToCart).click();
			Reporter.log("Added to cart", true);
			Thread.sleep(2000);
			flag = true;
		} else {
			Reporter.log("Add to cart is not present", true);
		}

		return flag;
	}

	public boolean checkOut() throws InterruptedException {
		boolean flag = false;
		if (driver.findElement(headingCheckOut).isDisplayed()) {
			if (IsElementPresent(checkoutbtn)) {

				System.out.println("CheckOut is displayed..clicking on it");
				driver.findElement(checkoutbtn).click();
				if (driver.getTitle().contains(cartTitle)) {
					/*
					 * Reporter.log("In cart page..verify the cart details",true);
					 * Thread.sleep(3000); Reporter.log("Scrolling down" , true); JavascriptExecutor
					 * jse = (JavascriptExecutor)driver; jse.executeScript("window.scrollBy(0,500)",
					 * "");
					 */
					if (IsElementPresent(checkOutCart)) {
						driver.findElement(checkOutCart).click();
						if (driver.getTitle().contains(checkOutTitle)) {
							flag = true;
						}

					}

				} else {
					Reporter.log("Not in cart page", true);
				}
			}
		} else {
			System.out.println("PopUp : Adding to shopping cart is not displayed");
		}
		return flag;
	}

	public boolean sortingResults(String sortBy) throws InterruptedException {
		boolean flag = false;
		String trimSort;
		Select select = new Select(driver.findElement(sort));
		List<WebElement> sortOptions = select.getOptions();
		for (int i = 1; i < sortOptions.size(); i++) {
			trimSort = sortOptions.get(i).getText().trim().toLowerCase();
			sortBy = sortBy.toLowerCase();
			if (trimSort.contains(sortBy)) {
				sortOptions.get(i).click();
				Reporter.log("selected sort by" + sortBy, true);
				Thread.sleep(4000);
				Reporter.log(driver.getCurrentUrl(), true);
				String cUrl = driver.getCurrentUrl();
				String subStr = trimSort.substring(0, 3).toLowerCase();
				System.out.println("subString is " + subStr);
				if (cUrl.contains("sort=" + subStr)) {
					Reporter.log("successfully sorted the results by " + sortBy, true);
					flag = true;
					break;
				}

				else {
					Reporter.log(" results are not sorted correctly", true);
				}

			}

		}
		return flag;
	}

}
