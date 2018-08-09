package testpackage1;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.config.Config;
import com.pack.base.TestBaseSetup;
import com.pack.common.pageobjects.HomePage;

@Listeners(com.config.ExecutionReport.class)

public class ProductSearchTest extends TestBaseSetup {

	Config config = new Config();
	HomePage basePage = new HomePage(driver);

	@BeforeClass
	public void setUp() {
		driver = getDriver();
	}

	/**
	 * searchByProductTest --> searches the product from the search box
	 * 
	 * @param product
	 * @throws InterruptedException
	 * 
	 *             /* basePage.sortingResults() can take below valid parameter
	 *             values Hence pass only these below values for successful sorting
	 *             Top rated, Price (Highest First) Price (Lowest First) Name
	 *             (Lowest First) Name (Highest First)
	 * 
	 */

	@Parameters({ "product" })
	@Test(enabled = true)
	public void CheckOutProductTest(String product) throws InterruptedException {
		boolean flag;
		System.out.println("Inside the searchbyproductTest");
		HomePage basePage = new HomePage(driver);
		Reporter.log("Title for the page is:  " + driver.getTitle(), true);
		if (basePage.searchByProduct(product)) {
			if (basePage.sortingResults("Price (Highest First)")) {
				Reporter.log("adding first" + product + "to the cart", true);
				if (basePage.addtocart()) {
					Reporter.log("added to the cart", true);
					Reporter.log("Checkingout the product", true);
					flag = basePage.checkOut();
					if (flag) {
						Reporter.log("Product successfully checkedout", true);
					}
				} else {
					Reporter.log("not added to cart successfully", true);
				}
			} else {
				Reporter.log("sorting was not correctly performed", true);
			}
		} else {
			Reporter.log("search results are not found for the product.. hence not adding to cart", true);
			Reporter.log("contiune shopping by searching ..", true);
		}

	}
/*
	@AfterMethod
	public void screenshot(ITestResult result) {
		Reporter.log("In screenshot()", true);
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				TakesScreenshot screenshot = (TakesScreenshot) driver;
				File src = screenshot.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(src, new File("./screenshots/" + result.getName() + ".png"));
				System.out.println("Successfully captured a screenshot");
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
		}

	}
	*/

}