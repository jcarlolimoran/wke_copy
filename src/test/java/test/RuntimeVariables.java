package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RuntimeVariables {
	
	public RuntimeVariables(){
		
			
		    
		
		
	}
	
	public static void  writeVariableFile() throws IOException{
		
		//ReportFile = "TestReport_"+CommonMethods.returnDate()+".xlsx";
		
		
		//Workbook wb1 = new XSSFWorkbook();
	    //FileOutputStream fileOut = new FileOutputStream("TestReport.xlsx");
	    //wb1.write(fileOut);
	    //fileOut.close();
	    
	    Workbook wb2 = new XSSFWorkbook();  // or new XSSFWorkbook();
	    Sheet sheet1 = wb2.createSheet("Variables");

	    XSSFSheet worksheet = (XSSFSheet) wb2.getSheetAt(0);
	    Row row = worksheet.createRow(0);
	    Cell cell =  null;
		
	    cell = row.createCell(0); 
        cell.setCellValue("Variable Name");
	    
	    cell = row.createCell(1); 
        cell.setCellValue("Value");
        
      

	    FileOutputStream fileOut2 = new FileOutputStream("variables.xlsx");
	    wb2.write(fileOut2);
	    fileOut2.close();
	}
	
	public static String getVariable(String vname) throws IOException{
		int x=1;
		int totalvariables;
		String s = null;
		boolean scanmore = true;
	
		FileInputStream fsIP= new FileInputStream(new File("variables.xlsx")); //Read the spreadsheet that needs to be updated
    
		XSSFWorkbook wb = new XSSFWorkbook(fsIP); //Access the workbook
      
		XSSFSheet worksheet = wb.getSheet("Variables"); //Access the worksheet, so that we can update / modify it.
      
		Cell cell = null; // declare a Cell object
		totalvariables = worksheet.getLastRowNum();
    
		Row row = null;
   // row = worksheet.getRow(r-1);
//	cell = row.getCell(c-1);
    
	
    
	
		while(x<=totalvariables && scanmore)
		{
			row = worksheet.getRow(x);
			cell = row.getCell(0);
		
			
		if(cell.getStringCellValue().equals(vname)){
				scanmore=false;
				cell=row.getCell(1);
				
				String test1 = cell.toString();
				
			/*	
				if(test1.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")){
					double v = cell.getNumericCellValue();
		        	int v1 = (int) v;
		        	s= Integer.toString(v1);
		        	return s;
					
				} */
				s= cell.getStringCellValue();
				
				
				
				
				
				}
		x++;
	 
		}
 
	
		fsIP.close();
    
		return s;
}
	
	public static void writeVariable(String vname, String value) throws Exception{
		FileInputStream fsIP= new FileInputStream(new File("variables.xlsx")); //Read the spreadsheet that needs to be updated
		
		XSSFWorkbook wb = new XSSFWorkbook(fsIP);
		XSSFSheet worksheet = wb.getSheetAt(0);
		Cell cell = null;
		int newRow = worksheet.getLastRowNum() + 1;
		Row row = worksheet.createRow(newRow);
		
		cell = row.createCell(0); 
        cell.setCellValue(vname);
		
		cell = row.createCell(1); 
        cell.setCellValue(value);
        
     
        
        
        fsIP.close();
        
        FileOutputStream output_file =new FileOutputStream(new File("variables.xlsx")); 
        wb.write(output_file); //write changes
        
        output_file.close();
        
       
	}
	

}
