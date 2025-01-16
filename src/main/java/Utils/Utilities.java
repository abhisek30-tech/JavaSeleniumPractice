package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
	
	public static final int IMPLICIT_WAIT_TIME =10;
	public static final int PAGE_LOAD_WAIT_TIME =60;
	
	public static String generateEmailTimestamp() {
		Date date = new Date();
		String timestamp= date.toString().replace(" ","_").replace(":","_");
	    return "TestEmail"+timestamp+"@gmail.com";
	}
	
	public static Object[][] getDataFromExcel(String sheetName) {
		
		File excelDataFile = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\DataExcel.xlsx");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(excelDataFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);
		 int rowcount=sheet.getLastRowNum();
		 int columncount=sheet.getRow(0).getLastCellNum();
		 
		 Object [][] data = new Object[rowcount][columncount];
		 
		 for (int i=0;i<rowcount;i++) {
			 XSSFRow row1=sheet.getRow(i+1);
			 for(int j=0;j<columncount;j++) {
				 
				XSSFCell cell=row1.getCell(j);
				CellType cellType =cell.getCellType();
				
				switch(cellType){
					
				case STRING:
					data[i][j]=cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					break;	
					
				}
				
			 }
			 
		 }
		return data;
	}
	
	public static String captureScreenshot(WebDriver driver, String testName) {
		
		 File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 String destinationScreenshotPath=System.getProperty("user.dir")+"\\test-output\\Screenshots\\"+testName+".png";
		 try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return destinationScreenshotPath;
	}
}
