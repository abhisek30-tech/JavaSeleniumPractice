package Test.com.javaframework;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Base.BaseTest;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class ComponentsTest extends BaseTest  {
	
		public WebDriver driver;
		 LoginPage loginPage;
		public ComponentsTest() {
			super();
		}
		
		
		@BeforeMethod
		public void setup() {
		    driver =  initializeBrowserandDriver(prop.getProperty("browserName"));
			HomePage homepage =new HomePage(driver);
		    homepage.clickOnMyAccount();
		    loginPage=homepage.selectLogin();
		}
		@AfterMethod
		public void teardown() {
			driver.close();
		}

}
