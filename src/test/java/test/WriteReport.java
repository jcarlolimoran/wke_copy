package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class WriteReport {
	public static  String ReportFile;
	
	public WriteReport(){
		
		
		
	}
	
	public static void writeReportFile() throws IOException{
		
		ReportFile = "TestReport_"+CommonMethods.returnDate()+".xlsx";
		
		
		//Workbook wb1 = new XSSFWorkbook();
	    //FileOutputStream fileOut = new FileOutputStream("TestReport.xlsx");
	    //wb1.write(fileOut);
	    //fileOut.close();
	    
	    Workbook wb2 = new XSSFWorkbook();  // or new XSSFWorkbook();
	    Sheet sheet1 = wb2.createSheet("new sheet");

	    XSSFSheet worksheet = (XSSFSheet) wb2.getSheetAt(0);
	    Row row = worksheet.createRow(0);
	    Cell cell =  null;
		
	    cell = row.createCell(0); 
        cell.setCellValue("Test Suite");
	    
	    cell = row.createCell(1); 
        cell.setCellValue("TestStep");
        
        cell = row.createCell(2); 
        cell.setCellValue("Description");
        
        cell = row.createCell(3); 
        cell.setCellValue("Result");
        
        cell = row.createCell(4); 
        cell.setCellValue("Comment");

	    FileOutputStream fileOut2 = new FileOutputStream(ReportFile);
	    wb2.write(fileOut2);
	    fileOut2.close();
	    
	}
	
	
	public void writeEntry(String suite, String TestStep, String Description, String Result, String Comment) throws Exception{
		FileInputStream fsIP= new FileInputStream(new File(ReportFile)); //Read the spreadsheet that needs to be updated
		
		XSSFWorkbook wb = new XSSFWorkbook(fsIP);
		XSSFSheet worksheet = wb.getSheetAt(0);
		Cell cell = null;
		int newRow = worksheet.getLastRowNum() + 1;
		Row row = worksheet.createRow(newRow);
		
		cell = row.createCell(0); 
        cell.setCellValue(suite);
		
		cell = row.createCell(1); 
        cell.setCellValue(TestStep);
        
        cell = row.createCell(2); 
        cell.setCellValue(Description);
        
        cell = row.createCell(3); 
        cell.setCellValue(Result);
        
        cell = row.createCell(4); 
        cell.setCellValue(Comment);
        
        
                  
        
        
        fsIP.close();
        
        FileOutputStream output_file =new FileOutputStream(new File(ReportFile)); 
        wb.write(output_file); //write changes
        
        output_file.close();
        
       // savepicture();
	}
	
	public void savepicture() throws Exception{
		FileInputStream fsIP= new FileInputStream(new File("TestReport.xlsx")); //Read the spreadsheet that needs to be updated
		
		XSSFWorkbook wb = new XSSFWorkbook(fsIP);
		XSSFSheet worksheet = wb.getSheetAt(1);
		Cell cell = null;
		int newRow = worksheet.getLastRowNum() + 1;
		Row row = worksheet.createRow(newRow);
		
		
        
   	 /* Read input PNG / JPG Image into FileInputStream Object*/
        InputStream my_banner_image = new FileInputStream(CommonMethods.screenshot);
        /* Convert picture to be added into a byte array */
        byte[] bytes = IOUtils.toByteArray(my_banner_image);
        /* Add Picture to Workbook, Specify picture type as PNG and Get an Index */
        int my_picture_id = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
        /* Close the InputStream. We are ready to attach the image to workbook now */
        my_banner_image.close();                
        /* Create the drawing container */
        XSSFDrawing drawing = worksheet.createDrawingPatriarch();
        /* Create an anchor point */
        XSSFClientAnchor my_anchor = new XSSFClientAnchor();
        /* Define top left corner, and we can resize picture suitable from there */
        my_anchor.setCol1(2);
        my_anchor.setRow1(1);           
        /* Invoke createPicture and pass the anchor point and ID */
        XSSFPicture  my_picture = drawing.createPicture(my_anchor, my_picture_id);
        /* Call resize method, which resizes the image */
       // my_picture.resize(); 
        my_picture.resize();
       // my_picture.resize(50, 50);
                  
        
        
        fsIP.close();
        
        FileOutputStream output_file =new FileOutputStream(new File("TestReport.xlsx")); 
        wb.write(output_file); //write changes
        
        output_file.close();
		
		
       
		
		
	}
	

}
