package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;
	@FindBy(id = "input-firstname")
	private WebElement firstNameField;
	@FindBy(id = "input-lastname")
	private WebElement lastNameField;
	@FindBy(id = "input-email")
	private WebElement EmailField;
	@FindBy(id = "input-telephone")
	private WebElement telephoneField;
	@FindBy(id = "input-password")
	private WebElement passwordField;
	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordField;
	@FindBy(name = "agree")
	private WebElement privacyPolicyAgree;
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;
	@FindBy (xpath="//input[@name='newsletter'][@value='1']")
	private WebElement checkNewsLetter;
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement existingEmailWarningText;
    @FindBy(xpath="//*[@id=\"account\"]/div[2]/div/div")
	private WebElement firstNameMissingWarning;
    @FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
    private WebElement privacyPolicyMissingWarning;
    
	public RegisterPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}

	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}

	public void enterEmailAdress(String emailText) {
		EmailField.sendKeys(emailText);
	}

	public void enterTelephone(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
	}

	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}

	public void enterConfirmPassword(String passwordConfirmText) {
		confirmPasswordField.sendKeys(passwordConfirmText);
	}
	public void selectNewsLetterCheckbox() {
		checkNewsLetter.click();
	}

	public void selectPrivacyPolicy() {
		privacyPolicyAgree.click();
	}
	public AccountSuccessPage clickContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	public String getExistingEmailWarningText() {
		return existingEmailWarningText.getText();
	}
	public String getFirstNameMissingWarningText() {
		return firstNameMissingWarning.getText();
	}
	public String getPrivacyPolicyMissingWarningText() {
		return privacyPolicyMissingWarning.getText();
	}
}
