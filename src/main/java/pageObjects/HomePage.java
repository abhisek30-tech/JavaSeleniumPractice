package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    
	@FindBy (xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy (linkText="Login")
	private WebElement LoginOption;
	@FindBy (linkText="Register")
	private WebElement RegisterOption;
	@FindBy(css="input[name='search']")
	private WebElement SearchboxText;
	@FindBy(css="span[class='input-group-btn']")
	private WebElement SearchButton;
	@FindBy (xpath="//input[@id='button-search']/following-sibling::p")
	private WebElement invalidSearchMessageText;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		//to overcome the stale element exception, 
		//everytime this is called it will initialize
		//all the web elements on this class
	}
	
	public void clickOnMyAccount() {
		
		myAccountDropMenu.click();
	}
	
	public LoginPage selectLogin() {
		LoginOption.click();
		return new LoginPage(driver);
		
	}
	
	public RegisterPage selectRegister() {
		RegisterOption.click();
		return new RegisterPage(driver);
		
	}
	public void enterSearchText(String searchText) {
		SearchboxText.sendKeys(searchText);
		
	}
	public SearchPage clickOnSearchButton() {
		SearchButton.click();
		return new SearchPage(driver);
		
	}
	public boolean verifyInvalidSearchText() {
		return invalidSearchMessageText.isDisplayed();
		
	}
}
