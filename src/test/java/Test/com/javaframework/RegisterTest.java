package Test.com.javaframework;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Base.BaseTest;
import Utils.Utilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.AccountSuccessPage;
import pageObjects.HomePage;
import pageObjects.RegisterPage;

public class RegisterTest extends BaseTest {
 public WebDriver driver;
 RegisterPage registerPage;
 AccountSuccessPage accountSuccessPage;
 public RegisterTest() {
	 super();
 }
 @BeforeMethod
	public void setup() {
	    driver =  initializeBrowserandDriver(prop.getProperty("browserName"));
		HomePage homePage=new HomePage(driver);
	  homePage.clickOnMyAccount();
      registerPage = homePage.selectRegister();
 }
 
	@AfterMethod
	public void teardown() {
		driver.close();
	}
	@Test
	public void verfiyRegisterAccountWithMandatoryFields() {

	registerPage.enterFirstName(dataprop.getProperty("input-firstname"));
	registerPage.enterLastName(dataprop.getProperty("input-lastname"));
	registerPage.enterEmailAdress(Utilities.generateEmailTimestamp());
	registerPage.enterPassword(dataprop.getProperty("input-password"));
	registerPage.enterTelephone(dataprop.getProperty("input-telephone"));
	registerPage.enterConfirmPassword(dataprop.getProperty("input-confirmpassword"));
	registerPage.selectPrivacyPolicy();
	accountSuccessPage=registerPage.clickContinueButton();
	
	String ActualSuccessText = accountSuccessPage.getAccountSuccessText();
	String ExpectedSuccessText = dataprop.getProperty("ExpectedSuccessText");
	Assert.assertTrue(ActualSuccessText.equals(ExpectedSuccessText),"Success Message is Not Displayed or Incorrect");
}
	
	
	@Test
	public void verfiyRegisterAccountWithAllFields() {
	
		registerPage.enterFirstName(dataprop.getProperty("input-firstname"));
		registerPage.enterLastName(dataprop.getProperty("input-lastname"));
		registerPage.enterEmailAdress(Utilities.generateEmailTimestamp());
		registerPage.enterPassword(dataprop.getProperty("input-password"));
		registerPage.enterTelephone(dataprop.getProperty("input-telephone"));
		registerPage.enterConfirmPassword(dataprop.getProperty("input-confirmpassword"));
		registerPage.selectPrivacyPolicy();
		registerPage.selectNewsLetterCheckbox();
		accountSuccessPage=registerPage.clickContinueButton();
		
		String ActualSuccessText = accountSuccessPage.getAccountSuccessText();
		String ExpectedSuccessText = dataprop.getProperty("ExpectedSuccessText");
		Assert.assertTrue(ActualSuccessText.equals(ExpectedSuccessText),"Success Message is Not Displayed or Incorrect");}

	@Test
	public void verfiyRegisterAccountWithExistingEmail() throws InterruptedException {
	
	

		registerPage.enterFirstName(dataprop.getProperty("input-firstname"));
		registerPage.enterLastName(dataprop.getProperty("input-lastname"));
		registerPage.enterEmailAdress(dataprop.getProperty("ExistingEmail"));
		registerPage.enterPassword(dataprop.getProperty("input-password"));
		registerPage.enterTelephone(dataprop.getProperty("input-telephone"));
		registerPage.enterConfirmPassword(dataprop.getProperty("input-confirmpassword"));
		registerPage.selectPrivacyPolicy();
		registerPage.selectNewsLetterCheckbox();
		registerPage.clickContinueButton();
		
		String ActualWarningText = registerPage.getExistingEmailWarningText();
		String ExpectedWarningText =dataprop.getProperty("ExpectedExistingEmailWarningText");
		Thread.sleep(3000);
		Assert.assertTrue(ActualWarningText.equals(ExpectedWarningText),"Warning Message is Not Displayed or Incorrect");
	}
	@Test
	public void verfiyRegisterAccountWithoutAnyDetails() {
	
		registerPage.clickContinueButton();
		String ExpectedPrivacyPolicy =  dataprop.getProperty("ExpectedPrivacyPolicy");
   	   String ActualPrivacyPolicy =registerPage.getPrivacyPolicyMissingWarningText();
	   Assert.assertEquals(ExpectedPrivacyPolicy, ActualPrivacyPolicy);
	   String ExpectedFirstNameWarning = dataprop.getProperty("ExpectedFirstNameWarning");
   	   String ActualFirstNameWarning  = registerPage.getFirstNameMissingWarningText();
   	   Assert.assertEquals(ExpectedFirstNameWarning, ActualFirstNameWarning);
	}
	
}
