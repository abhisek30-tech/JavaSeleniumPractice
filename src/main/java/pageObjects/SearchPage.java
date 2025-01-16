package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
WebDriver driver;
@FindBy(linkText="HP LP3065")
private WebElement hpSearchResult;



public SearchPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver,this);
}


public boolean hpSearchResultVisible() {
	return hpSearchResult.isDisplayed();
		
}



}
