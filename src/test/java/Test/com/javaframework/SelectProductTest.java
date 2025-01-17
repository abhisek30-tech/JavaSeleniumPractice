package Test.com.javaframework;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Base.BaseTest;
import pageObjects.HomePage;
import pageObjects.ProductPage;

public class SelectProductTest extends BaseTest {
	 WebDriver driver;
	 HomePage homePage;
	 ProductPage productPage;
	public SelectProductTest() {
		 super();
	 }
	
	@Parameters({ "browser" })
	@BeforeMethod
	public void setup(String browser) {

		// driver =  initializeBrowserandDriver(prop.getProperty("browserName"));
		driver =  initializeBrowserandDriver(browser);
        homePage =new HomePage(driver);
	}
	@AfterMethod
	public void teardown() {
		driver.close();
	}
	@Test
	public void verifyMonitorProductSelection() {
		
		homePage.clickOnHeaderComponent();
		productPage=homePage.clickOnComponentOptionMonitor();
		productPage.verifyProductHeader("Monitor");
		
		
	}
	
	
}
