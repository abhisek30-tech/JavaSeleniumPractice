package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
 WebDriver driver;
	
	@FindBy (id="input-email")
	private WebElement loginField;
	@FindBy (id="input-password")
	private WebElement passwordField;
	@FindBy (xpath="//input[@type='submit']")
	private WebElement loginButton;
	@FindBy (xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarning;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public void enterEmailAdress(String loginEmail) {
		loginField.sendKeys(loginEmail);
	}
	
	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public void clickOnLoginButton() {
		loginButton.click();
	}
	
	public String retrieveEmailPasswordWarningText() {
		return emailPasswordNotMatchingWarning.getText();
	}
	
}
