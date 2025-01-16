package Listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utils.MyExtentReporter;
import Utils.Utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import java.awt.event.*;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITest;
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import java.io.*; 
import java.net.*; 

public class MyListeners implements ITestListener{
	ExtentReports extentReport;
	ExtentTest extentTest;
	@Override
	public void onStart(ITestContext context) {
		extentReport = MyExtentReporter.generateExtentReport();
		System.out.println("Execution of the Project Test Started");
	}
	@Override
	public void onTestStart(ITestResult result) {
		//String testName = result.getTestName();
		Method method = result.getMethod().getConstructorOrMethod().getMethod();
		String testName  = method.getName();
	    extentTest = extentReport.createTest(testName);
	    extentTest.log(Status.INFO,testName +" started executing");
		//System.out.println(testName +" started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Method method = result.getMethod().getConstructorOrMethod().getMethod();
		String testName  = method.getName();
		 extentTest = extentReport.createTest(testName);
		 extentTest.log(Status.PASS,testName +" Completed Successfully");
		//System.out.println(testName +" Completed Successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver =null;
		Method method = result.getMethod().getConstructorOrMethod().getMethod();
		String testName  = method.getName();
		 extentTest = extentReport.createTest(testName);
		 try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
        String destinationScreenshotPath= Utilities.captureScreenshot(driver, testName);
		 extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		//System.out.println(testName +" Failed");
		 extentTest.log(Status.INFO,result.getThrowable());
		 extentTest.log(Status.FAIL,testName +" Failed");
		 
		 
		 
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		Method method = result.getMethod().getConstructorOrMethod().getMethod();
		String testName  = method.getName();
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP,testName +" Skipped");
//		System.out.println(testName +" Skipped");
//		System.out.println(result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();  // to actually print every details on the report
        File htmlfile=new File(System.getProperty("user.dir")+"\\test-output\\ExtentReport\\extentReport.html");
		try {
			Desktop.getDesktop().browse(htmlfile.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
