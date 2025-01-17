package Test.com.javaframework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Base.BaseTest;
import Utils.Utilities;
import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class LoginTest extends BaseTest {
	public WebDriver driver;
	 LoginPage loginPage;
	public LoginTest() {
		super();
	}
	
    @Parameters("browser")
	@BeforeMethod
	public void setup(String browser) {
	   //driver =  initializeBrowserandDriver(prop.getProperty("browserName"));
	    driver =  initializeBrowserandDriver(browser);
		HomePage homepage =new HomePage(driver);
	    homepage.clickOnMyAccount();
	    loginPage=homepage.selectLogin();
	}
	@AfterMethod
	public void teardown() {
		driver.close();
	}

	@Test(priority=1,testName ="verifyLoginWithValidCredential")
	public void verifyLoginWithValidCredential() {

        AccountPage accountPage = new AccountPage(driver);
		loginPage.enterEmailAdress(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		accountPage.verifyenterYourAccountInformationBannerDisplay();
	}
	
	@Test(priority=5,dataProvider="DataforLogin")
	public void verifyLoginWithValidCredentialDP(String email, String password) {

	        AccountPage accountPage = new AccountPage(driver);
			loginPage.enterEmailAdress(email);
			loginPage.enterPassword(password);
			loginPage.clickOnLoginButton();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			accountPage.verifyenterYourAccountInformationBannerDisplay();
	}
	@Test(priority=2)
	public void verifyLoginWithInValidCredential() {

			loginPage.enterEmailAdress(Utilities.generateEmailTimestamp());
			loginPage.enterPassword(prop.getProperty("validPassword"));
			loginPage.clickOnLoginButton();
			String expectedErrorMessage = dataprop.getProperty("expectedErrorMessage");
			Assert.assertEquals(loginPage.retrieveEmailPasswordWarningText(),expectedErrorMessage,"Warning Message is incorrect");
	}
	@Test(priority=3)
	public void verifyLoginWithInValidEmailInvalidPassword() {
		
			loginPage.enterEmailAdress(dataprop.getProperty("invalidEmail"));
			loginPage.enterPassword(dataprop.getProperty("invalidPassword"));
			loginPage.clickOnLoginButton();
			String expectedErrorMessage = dataprop.getProperty("expectedErrorMessage");
			Assert.assertEquals(loginPage.retrieveEmailPasswordWarningText(),expectedErrorMessage,"Warning Message is incorrect");
	}
	@Test(priority=4)
	public void verifyLoginWithBlankEmailAndPassword() {

			loginPage.clickOnLoginButton();
			String expectedErrorMessage = dataprop.getProperty("expectedErrorMessage");
			Assert.assertEquals(loginPage.retrieveEmailPasswordWarningText(),expectedErrorMessage,"Warning Message is incorrect");
	}

}
