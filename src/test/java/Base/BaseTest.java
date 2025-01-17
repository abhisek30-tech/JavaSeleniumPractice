package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;

import Utils.Utilities;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
  protected WebDriver driver;
  public Properties prop;
  public Properties dataprop;
  
  public BaseTest() {
		
		try {
		File file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		 FileInputStream fis =new FileInputStream(file);
		 prop = new Properties();
			prop.load(fis);
		} catch (IOException e) {			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	try {
	File file2 = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata.properties");
	 FileInputStream fis2 =new FileInputStream(file2);
	 dataprop = new Properties();
		dataprop.load(fis2);
	} catch (IOException e) {			
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
  

	public WebDriver initializeBrowserandDriver(String browser) {
		
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browser.equals("edge")) {
			WebDriverManager.chromiumdriver().setup();
			driver = new EdgeDriver();
		} else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} 
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_WAIT_TIME));
		driver.get(prop.getProperty("url"));	
	
		return driver;
	}
	
	
	
	@DataProvider(name="DataforLogin")
	public Object[][] supplyDataMethod() {
		
		Object[][] data = Utilities.getDataFromExcel("Login");
				return data;
	}
	
}
