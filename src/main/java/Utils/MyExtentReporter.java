package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MyExtentReporter {

	public static ExtentReports generateExtentReport() {
		
		ExtentReports extentReport = new ExtentReports();
		File reportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReport\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFile);
		
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setReportName("JavaFramework Extent Report");
		sparkReporter.config().setDocumentTitle("JavaFramework Test Result");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		extentReport.attachReporter(sparkReporter);
		File file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		Properties configProp = new Properties();
		try {
			FileInputStream fis = new FileInputStream(file);
				configProp.load(fis);
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		extentReport.setSystemInfo("ApplicationURL", configProp.getProperty("url"));
		extentReport.setSystemInfo("Browser", configProp.getProperty("browserName"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReport;
	}
	
}
