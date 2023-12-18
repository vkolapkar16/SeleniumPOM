package com.xuriti.dev.Utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.EncryptedDocumentException;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.qa.testrailmanager.TestRailManager;
import com.xuriti.dev.TestBase.Baseclass;
import com.xuriti.dev.TestBase.ConfigLoad;



public class Utility extends Baseclass
{
	
//	public static void takeScreenshotAtEndOfTest() throws IOException 
//	{
//		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		String currentDir = System.getProperty("user.dir");
//		File dstFile = new File("/home/tech-trail/workspace/xd/screenshots/123.png");
//		FileUtils.copyFile(scrFile,dstFile);
//	}
	   
   //FOR DROPDOWNS
   public static void selectfromdropdown(WebElement ele , String Value)
   {
   
   Select drp = new Select(ele);
   List<WebElement> alloptions = drp.getOptions();
   
   for(WebElement option:alloptions)
   {
       if(option.getText().equals(Value))
       {
           option.click();
           break;
       }
       
   }
   }
   
   public static void Selectfromlist(List<WebElement> ele, String Value)
   {
           
   for(WebElement option: ele)
   {
       if(option.getText().contains(Value))
       {
           option.click();
           break;
       }      
   }
          
   }
   
   public static int fetchDataFromTable()
   {
	   int dataOnCurrentPage = 0;
	   
       // Find all rows in the table
       List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));

       // Iterate through the rows, skipping the header row if necessary
       for (int i = 1; i < rows.size(); i++) 
       {
           WebElement row = rows.get(i);
           dataOnCurrentPage++;
       }

       return dataOnCurrentPage;
   }
   
   public static WebDriverWait explicitWait()
   {
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	   return wait;
   }

  
   public static boolean iterateTable(String string, int column)
	{
		int rows=driver.findElements(By.xpath("//table/tbody/tr")).size();
		System.out.println(rows);
		boolean isSuccess=false;
		for(int r=1;r<=rows;r++)
		{

			String Name = driver.findElement(By.xpath("//table/tbody/tr["+r+"]/td["+column+"]")).getText();
			System.out.println(Name);
			if (Name.contentEquals(string))
			{
				isSuccess=true;
				break;
			}
		}
		return isSuccess;
	}

	public static void createExcelSheet() throws IOException, InterruptedException
	{	
        LocalDate currentDate = LocalDate.now();
       
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd"); 
		//LocalDate yesterdayDate = currentDate.minusDays(1);
		String todaysDate = currentDate.format(formatter);
		System.out.println("Invoice Date: " + todaysDate);
	
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sheet1");

		ArrayList<Object[]> empdata=new ArrayList<>();
		empdata.add(new Object[] {"invoice_number","invoice_file","invoice_type","buyer_gst","seller_gst","gst_amount","invoice_amount","Total_Invoice_Amt","invoice_date"});
			
		int rownum=0;

		for (Object[] emp:empdata)			{
			XSSFRow row=(XSSFRow) sheet.createRow (rownum++);
			int cellnum=0;
			for (Object value:emp)
			{
				XSSFCell cell=row.createCell(cellnum++);
				if(value instanceof String)
				cell.setCellValue((String)value);
				if(value instanceof Integer)
				cell.setCellValue((Integer)value);
				if(value instanceof Boolean)
				cell.setCellValue((Boolean) value);
			}
		}
		FileOutputStream outputStream = new FileOutputStream("Invoices/MultiInvoice.xlsx");
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		Thread.sleep(4000);
	}
	
	public static void UpdateExcelSheet(String filepath,int row,String Status) throws IOException, InterruptedException
	{	
        LocalDate currentDate = LocalDate.now();
       
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd"); 
		String todaysDate = currentDate.format(formatter);
	
		FileInputStream file = new FileInputStream(filepath);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        Row excelRow = sheet.getRow(row + 4); 
           
        Cell cell = excelRow.createCell(5, CellType.STRING);
        cell.setCellValue(Status);
		
		FileOutputStream outputStream = new FileOutputStream(filepath);
		workbook.write(outputStream);
		outputStream.close();
		outputStream.close();
        workbook.close();
		Thread.sleep(4000);
	}
	
	public static void setExceldata(String sheetname,int row,int cell,String data)throws IOException,EncryptedDocumentException 
	{
		LocalDate currentDate = LocalDate.now();
	       
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd"); 
		String todaysDate = currentDate.format(formatter);
		
		FileInputStream fis=new FileInputStream("./data/testscript.xlsx");
		Workbook w=WorkbookFactory.create(fis);
		w.getSheet(sheetname).getRow(row).getCell(cell).setCellValue(data);
		FileOutputStream fos=new FileOutputStream("./data/testscript.xlsx");
		w.write(fos);
		w.close();
	}
	
	public static void SetExcelData(String FileName, int i,int row,int cell,String data)throws IOException,EncryptedDocumentException 
	{
		FileInputStream fis=new FileInputStream("Reports/"+FileName);
		Workbook w=WorkbookFactory.create(fis);
		w.getSheetAt(i).getRow(row).getCell(cell).setCellValue(data);
		FileOutputStream fos=new FileOutputStream("Reports/"+FileName);
		w.write(fos);
		w.close();
	}
	
	public static void clearExcelData(String FileName, int sheet)throws IOException,EncryptedDocumentException 
	{
		FileInputStream fis=new FileInputStream("Reports/"+FileName);
		  Workbook workbook = new XSSFWorkbook(fis);
		int count =  workbook.getSheetAt(sheet).getLastRowNum();
		System.out.println("the last row "+count);
		
		for(int i=1;i<=count;i++)
		{
			SetExcelData(FileName, 0, i, 5, "-");
		}
		
		System.out.println("Reports Data Loaded");
	}
	
}
	
