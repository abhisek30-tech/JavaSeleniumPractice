package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

	WebDriver driver;
	@FindBy(xpath="//div[@id='product-category']/div/div/h2")
	WebElement ProductHeader;
	
	
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public boolean verifyProductHeader(String productName) {
	return ProductHeader.getText().equalsIgnoreCase(productName);
		
	}
	
	
}
