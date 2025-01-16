package Test.com.javaframework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseTest;
import pageObjects.HomePage;
import pageObjects.SearchPage;

public class SearchTest extends BaseTest {
 
	public WebDriver driver;
	SearchPage searchPage;
	HomePage homePage;
	 public SearchTest() {
		 super();
	 }
	
	@BeforeMethod
	public void setup() {
	    driver =  initializeBrowserandDriver(prop.getProperty("browserName"));
        homePage =new HomePage(driver);
	}
	@AfterMethod
	public void teardown() {
		driver.close();
	}
	@Test
	public void verifySearchWithValidProduct() {
	
		homePage.enterSearchText("hp");
		searchPage =homePage.clickOnSearchButton();
	
		Assert.assertTrue(searchPage.hpSearchResultVisible(),"Search did not produce any result");
		
	}
	
	@Test
	public void verifySearchWithInValidProduct() {
	
		homePage.enterSearchText("Invalid");
		homePage.clickOnSearchButton();
		
		Assert.assertTrue(homePage.verifyInvalidSearchText(),"Correct Message is not displayed");
		
	}
	
	
	@Test
	public void verifySearchWithNoProduct() {
		
	
		
		homePage.clickOnSearchButton();
		
		Assert.assertTrue(homePage.verifyInvalidSearchText(),"Correct Message is not displayed");		
	}
	

}
