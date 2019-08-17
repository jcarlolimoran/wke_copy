package test;


import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.testautomationguru.utility.CompareMode;
import com.testautomationguru.utility.PDFUtil;



public class Tests {
	CommonMethods cm = new CommonMethods();
	WriteReport writereport = new WriteReport();
	writeHtml writehtml = new writeHtml();
	String parentHandle;
	String windowHandle;
	ArrayList tabs;
	String parentW;
	String childW;
	String CurrentWeek, WeekNo;
	String TestDescription, Action, InputKey, LocatorID, LocatorType, validation, isVariable, Obj;
	public String URLvariable;
	public static String testSuiteGlobal, TestDescriptionGlobal, ActionGlobal;
	public static String  TestReportHeaderPassed,TestReportHeaderFailed ;
	public static int toStep, toStep2;
	
	
	//String numPattern = "^(?:0|[1-9][0-9]*)\.[0-9]+$";
	//WebDriver d;
	
	public Tests(){
		
		
	}
	
	
	public void ExecuteStep(int stepnumber, String suite) throws Exception{
		
		
		String step[] = new String  [7];
		int i;
		int colnumber=1;
		FileInputStream fsIP= new FileInputStream(new File("Tests.xlsx")); //Read the spreadsheet that needs to be updated
        
        XSSFWorkbook wb = new XSSFWorkbook(fsIP); //Access the workbook
          
       // XSSFSheet worksheet = wb.getSheetAt(0); //Access the worksheet, so that we can update / modify it.
        XSSFSheet worksheet = wb.getSheet(suite);
        Cell cell = null; // declare a Cell object
        
      //  int NumberOfTests = worksheet.getLastRowNum();
     
     //   Row row = worksheet.createRow(newRow);
        
        Row row = null;
        row = worksheet.getRow(stepnumber);
        
        for(i=0;i<=6;i++){
        	//cell = row.getCell(i);
        	
        	
        	step[i] = row.getCell(colnumber).toString();  
        	//System.out.println(cell);
        	colnumber++;
        }
        
        TestDescription = step[0];
        Action = step[1];
        InputKey =  step[2];
        validation =  step[3];
        isVariable =  step[4];
     //   LocatorID=  step[5];
     //   LocatorType=  step[6];
        
        
        if(step[6].equals("null")){
        	if(!step[5].equals("null")){
        		System.out.println("Getting object identifier from object library....");
        		Obj=CommonMethods.getObject(step[5]);
        		String[] ob = Obj.split("LOCATOR");
        		LocatorID=ob[0];
        		LocatorType=ob[1];      
        	}
        }
        else
        	 if(step[6].equals("variableLocatorID")){
             	if(!step[5].equals("null")){
             		System.out.println("Getting object identifier from object library and replacing LOCATORID");
             		Obj=CommonMethods.getObject(step[5]);
             		String[] ob = Obj.split("LOCATOR2");
             		LocatorID=ob[0];
             		LocatorType=ob[1];      
             	}
             }
        	 else
        {
        		System.out.println("Getting object identifier from excel keyword list....");
        		LocatorID=  step[5];
        		LocatorType=  step[6];
                
        	}
        
        
        testSuiteGlobal=suite;
        TestDescriptionGlobal=TestDescription;
        
        MainTest.screen.display("EXECUTING TEST SUITE: "+suite,"STEP: "+TestDescription, Action);
        
        //TestReportHeaderPassed = "PASSED    \t\t   Test Duration:" ;
       // writeHtml.startTable();
        ActionGlobal=Action;
        
        switch (Action){
        	
        case "click":

        		
        		if(isVariable.equals("yes_fromDynamicVariable")){
        		System.out.println("Performing Test# "+ MainTest.currentTest);
        		System.out.print(TestDescription);
        		InputKey=RuntimeVariables.getVariable(InputKey);
        	
        		LocatorType="xpath";
            	LocatorID=LocatorID.replace("LOCATORID", InputKey); // replace the LOCATORID under LocatorID column with dynamic variable
            	System.out.println(LocatorID);
            	
            	cm.Click( MainTest.d, LocatorID, LocatorType);
        		
        		System.out.println(" ");
        		System.out.println("ACTION _keypress initiated");
        		System.out.println(" ");
        		}
        	
        	else{
        		
        	       
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);

        	
        	cm.Click( MainTest.d, LocatorID, LocatorType);
        	System.out.println(" ");
        	System.out.println("ACTION _click initiated");
        	System.out.println(" ");
        	//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest, LocatorID, LocatorType);
        	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _click initiated");
        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _click initiated");
        	        	
        	}
        	break;
        	
        	
        case "SimpleScreenClick":
 	       
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);

        	
        	Runtime.getRuntime().exec("scripts\\SimpleScreenClick");
        	System.out.println(" ");
        	System.out.println("ACTION _SimpleScreenclick initiated");
        	System.out.println(" ");
        	//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest, LocatorID, LocatorType);
        	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _SimpleScreenclick initiated");
        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _SimpleScreenclick initiated");
        	        	
        	
        	break;
        	
        case "rclick":
 	       
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);

        	
        	cm.RightClick( MainTest.d, LocatorID, LocatorType);
        	System.out.println(" ");
        	System.out.println("ACTION _rclick initiated");
        	System.out.println(" ");
        	//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest, LocatorID, LocatorType);
        	//writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _rclick initiated");
        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _rclick initiated");
        	        	
        	
        	break;	
        	
        	
        	     	
        case "dbclick": 
        	
        	
        	
        		System.out.println("Performing Test# "+ MainTest.currentTest);
            	System.out.print(TestDescription);
            	
            	
            	cm.dblClick( MainTest.d, LocatorID, LocatorType);
            	System.out.println(" ");
            	System.out.println("ACTION dblClick initiated");
            	System.out.println(" ");
            
            	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION dblClick initiated");
            	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION dblClick initiated");
        		
        	break;
        	
        	
        case "savelinkas": // parameters 'file path' , 'url' from Runtime variable
        	String[] savelinkas = cm.StringParser(InputKey);
        	
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        	File filelinktosave = new File(savelinkas[0]); // "downloadedfiles\\parentChild_ImageReport.pdf"
            URL url = new URL(RuntimeVariables.getVariable(savelinkas[1]));
        	
        	FileUtils.copyURLToFile(url,filelinktosave);
        	
        		
        		
        	
        	
        	
	
		    
        	break;
        	
        	
        
        	
        	
       
        	
        case "saveAsNumericAsVariable":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        	String var_val2 = cm.returnString(MainTest.d, LocatorID, LocatorType);
        	
        	Pattern numericpattern = Pattern.compile("\\d+");
        	Matcher matcher1 = numericpattern.matcher(var_val2);
        	if (matcher1.find())
        	{
        		
        		
        		var_val2=matcher1.group(0);
        	    
        	}
 
        	
        	RuntimeVariables.writeVariable(InputKey, var_val2);
        	
        	
        	//cm.MouseHover( MainTest.d, LocatorID, LocatorType);
        	System.out.println(" ");
        	System.out.println("ACTION _saveAsVariable initiated - "+var_val2+"  Variable saved as "+InputKey);
        	System.out.println(" ");
        	
        	//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"_Mouse hover was initiated", LocatorID, LocatorType);
        	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _saveAsVariable initiated.. Variable saved as variable to "+InputKey);
        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _saveAsVariable initiated.. Variable saved as variable to "+InputKey);
        	break;
        	
        	
        
    
        	
        case "ClickSkipIfNoElement":
            
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
  			if(isVariable.equals("DOM")){
				JavascriptExecutor js = (JavascriptExecutor) MainTest.d ;
				String script = InputKey;
				 Boolean st = (Boolean) js.executeScript(script);
				 if(!st) {
					 cm.ClickSkipIfNoElement(MainTest.d, LocatorID, LocatorType);
					 
				 }
				 
				//System.out.println("toggle status "+stringToValidate);
			}
			else{	
				cm.ClickSkipIfNoElement(MainTest.d, LocatorID, LocatorType);
				}

        	
        	
        	System.out.println(" ");
        	System.out.println("ACTION _ClickSkipIfNoElement initiated");
        	System.out.println(" ");
        	//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest, LocatorID, LocatorType);
        	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _ClickSkipIfNoElement initiated");
        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _ClickSkipIfNoElement initiated");
        	break;
        	
        case "ClickonElementLocation":
            
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);

        	
        	cm.ClickonElementLocation(MainTest.d, LocatorID, LocatorType, InputKey);
        	System.out.println(" ");
        	System.out.println("ACTION _ClickonElementLocation initiated");
        	System.out.println(" ");
        	//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest, LocatorID, LocatorType);
        	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _ClickonElementLocation initiated");
        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _ClickonElementLocation initiated");
        	break;
        	
        case "clickCell":
        	int rows;
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        	
        	
        	String[] arr2 = cm.StringParser(InputKey);
        	String rowID = arr2[0];
        	String colID = arr2[1];
        	
        	
        	
        	
        	System.out.println(" ");
        	System.out.println("Action click is initiated");
        	System.out.println(" ");
        	System.out.println(cm.returnString(MainTest.d, LocatorID, LocatorType));
        	
        	break;
        
        case "pressEnter":
        	
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        	cm.PressEnter(MainTest.d, LocatorID, LocatorType);
        	System.out.println(" ");
        	System.out.println("Action pressEnter is initiated");
        	System.out.println(" ");
        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _pressEnter initiated");
        	
        	break;
        	
        	
        case "pressTab":
        	
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        	cm.PressTab(MainTest.d, LocatorID, LocatorType);
        	System.out.println(" ");
        	System.out.println("Action pressTab is initiated");
        	System.out.println(" ");
        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _pressTab initiated");
        	
        	break;
        	
  
        	
        case "keypress":
        	//Overlay.display(TestDescription, Action);
        	if(isVariable.equals("no")){
        		System.out.println("Performing Test# "+ MainTest.currentTest);
        		System.out.print(TestDescription);
        	//System.out.println("using identifier "+ LocatorType);
        	    
        		if(InputKey.endsWith(".0"))
        		{InputKey = InputKey.replace(".0", "");}
        		
        		cm.SendKeys(MainTest.d, LocatorID, InputKey, LocatorType);
        		System.out.println(" ");
        		System.out.println("ACTION _keypress initiated");
        		System.out.println(" ");
        	}
        	else if(isVariable.equals("yes")){
        		System.out.println("Performing Test# "+ MainTest.currentTest);
        		System.out.print(TestDescription);
        	
        		InputKey=CommonMethods.getVariable(InputKey);
        		cm.SendKeys(MainTest.d, LocatorID, InputKey, LocatorType);
        		System.out.println(" ");
        		System.out.println("ACTION _keypress initiated");
        		System.out.println(" ");
        		}
        	
        	else if(isVariable.equals("yes_fromDynamicVariable")){
        		System.out.println("Performing Test# "+ MainTest.currentTest);
        		System.out.print(TestDescription);
        	
        		InputKey=RuntimeVariables.getVariable(InputKey);
        		cm.SendKeys(MainTest.d, LocatorID, InputKey, LocatorType);
        		System.out.println(" ");
        		System.out.println("ACTION _keypress initiated");
        		System.out.println(" ");
        		}
        	
        	
        	else if(isVariable.equals("randomgenerated")){
        		System.out.println("Performing Test# "+ MainTest.currentTest);
        		System.out.print(TestDescription);
        	
        		String randomnumber = "Story_Automation_"+ String.valueOf(ThreadLocalRandom.current().nextInt(100, 1000000 + 1));
        		RuntimeVariables.writeVariable(InputKey, randomnumber);
        		
        		
        		cm.SendKeys(MainTest.d, LocatorID, randomnumber, LocatorType);
        		System.out.println(" ");
        		System.out.println("ACTION _keypress initiated");
        		System.out.println(" ");
        		}
        	
        	else if(isVariable.equals("ImagePath")){
        		System.out.println("Performing Test# "+ MainTest.currentTest);
        		System.out.print(TestDescription);
        	
        		String ImagePath = CommonMethods.getPath("images\\page1.jpg", CommonMethods.getVariable(InputKey));
        		System.out.println("ImagePath "+ ImagePath);
        		
        		cm.SendKeys(MainTest.d, LocatorID, ImagePath, LocatorType);
        		System.out.println(" ");
        		System.out.println("ACTION _keypress initiated");
        		System.out.println(" ");
        		}
        	
         	else if(isVariable.equals("wDate")){
        		System.out.println("Performing Test# "+ MainTest.currentTest);
        		System.out.print(TestDescription);
        	
        		String randomnumber = "Tester_"+cm.returnDate();
        		RuntimeVariables.writeVariable(InputKey, randomnumber);
        		
        		
        		cm.SendKeys(MainTest.d, LocatorID, randomnumber, LocatorType);
        		System.out.println(" ");
        		System.out.println("ACTION _keypress initiated");
        		System.out.println(" ");
        		}
        	
         	else if(isVariable.equals("wDate2")){
        		System.out.println("Performing Test# "+ MainTest.currentTest);
        		System.out.print(TestDescription);
        	
        		String randomnumber = InputKey+" "+cm.returnDate();
        		if(InputKey.contains("@email")) {
        			 randomnumber=randomnumber.replace("@email", "");
        			 randomnumber = randomnumber+"@mailinator.com";
        			 randomnumber=randomnumber.replace(" ", "");
        			
        		}
        		
        		
        		RuntimeVariables.writeVariable(InputKey, randomnumber);
        		
        		
        		cm.SendKeys(MainTest.d, LocatorID, randomnumber, LocatorType);
        		System.out.println(" ");
        		System.out.println("ACTION _keypress initiated");
        		System.out.println(" ");
        		}
        	
        	//use this if you need to save variable with prefix, use validation field as prefix
        	//InputKey=variable name, validation=variable value
         	else if(isVariable.equals("saveAsVariable")){
        		System.out.println("Performing Test# "+ MainTest.currentTest);
        		System.out.print(TestDescription);
        	
        		//String randomnumber = validation+"_"+ String.valueOf(ThreadLocalRandom.current().nextInt(100, 1000000 + 1));
        		RuntimeVariables.writeVariable(InputKey, validation);
        		
        		
        		cm.SendKeys(MainTest.d, LocatorID, validation, LocatorType);
        		System.out.println(" ");
        		System.out.println("ACTION _keypress initiated");
        		System.out.println(" ");
        		}
          	else if(isVariable.equals("wDateLastDayNextMonth")){
          		System.out.println("Performing Test# "+ MainTest.currentTest);
        		System.out.print(TestDescription);
        	
        		String date1 = CommonMethods.getLastDayNextMonth();
        		
        		
        		
        		cm.SendKeys(MainTest.d, LocatorID, date1, LocatorType);
        		System.out.println(" ");
        		System.out.println("ACTION _keypress initiated");
        		System.out.println(" ");
        		}
        	else if(isVariable.equals("Date")){
        		System.out.println("Performing Test# "+ MainTest.currentTest);
        		System.out.print(TestDescription);
        	
        		String randomnumber = cm.returnDate();
        		RuntimeVariables.writeVariable(InputKey, randomnumber);
        		
        		
        		cm.SendKeys(MainTest.d, LocatorID, randomnumber, LocatorType);
        		System.out.println(" ");
        		System.out.println("ACTION _keypress initiated");
        		System.out.println(" ");
        		}
     
            		
            		
        	
          	else if(isVariable.equals("useRobot")){
        		System.out.println("Performing Test# "+ MainTest.currentTest);
        		System.out.print(TestDescription);
        	
        		Robot robotSendKey = new Robot();
        		
        		String strSendKey = InputKey;
        		Character[] charObjectArray = strSendKey.chars().mapToObj(c -> (char)c).toArray(Character[]::new); 
        		
        		for(int x=0; x<charObjectArray.length;x++){
        			
        			switch(charObjectArray[x]){
        				
        			case 'a':
        				robotSendKey.keyPress(KeyEvent.VK_A);
        				robotSendKey.keyRelease(KeyEvent.VK_A);
        				break;
        				
        			case 'b':
        				robotSendKey.keyPress(KeyEvent.VK_B);
        				robotSendKey.keyRelease(KeyEvent.VK_B);
        				break;
        				
        			case 'c':
        				robotSendKey.keyPress(KeyEvent.VK_C);
        				robotSendKey.keyRelease(KeyEvent.VK_C);
        				break;
        			
        			case 'd':
        				robotSendKey.keyPress(KeyEvent.VK_D);
        				robotSendKey.keyRelease(KeyEvent.VK_D);
        				break;
        			
        			case 'e':
        				robotSendKey.keyPress(KeyEvent.VK_E);
        				robotSendKey.keyRelease(KeyEvent.VK_E);
        				break;
        				
        			case 'f':
        				robotSendKey.keyPress(KeyEvent.VK_F);
        				robotSendKey.keyRelease(KeyEvent.VK_F);
        				break;
        			case 'g':
        				robotSendKey.keyPress(KeyEvent.VK_G);
        				robotSendKey.keyRelease(KeyEvent.VK_G);
        				break;
        			case 'h':
        				robotSendKey.keyPress(KeyEvent.VK_H);
        				robotSendKey.keyRelease(KeyEvent.VK_H);
        				break;
        			case 'i':
        				robotSendKey.keyPress(KeyEvent.VK_I);
        				robotSendKey.keyRelease(KeyEvent.VK_I);
        				break;
        			case 'j':
        				robotSendKey.keyPress(KeyEvent.VK_J);
        				robotSendKey.keyRelease(KeyEvent.VK_J);
        				break;
        			case 'o':
        				robotSendKey.keyPress(KeyEvent.VK_O);
        				robotSendKey.keyRelease(KeyEvent.VK_O);
        				break;
        				
        			case 'r':
        				robotSendKey.keyPress(KeyEvent.VK_R);
        				robotSendKey.keyRelease(KeyEvent.VK_R);
        				break;
        				
        			case 's':
        				robotSendKey.keyPress(KeyEvent.VK_S);
        				robotSendKey.keyRelease(KeyEvent.VK_S);
        				break;
        			case '1':
        				robotSendKey.keyPress(KeyEvent.VK_1);
        				robotSendKey.keyRelease(KeyEvent.VK_1);
        				break;
        				
        			case '2':
        				robotSendKey.keyPress(KeyEvent.VK_2);
        				robotSendKey.keyRelease(KeyEvent.VK_1);
        				break;
        			case '3':
        				robotSendKey.keyPress(KeyEvent.VK_3);
        				robotSendKey.keyRelease(KeyEvent.VK_1);
        				break;
        			
        			}
        			
        			CommonMethods.delay(500);
        			
        		}
        		
        	
        		
        		System.out.println(" ");
        		System.out.println("ACTION _keypress useRobot");
        		System.out.println(" ");
        		}
        	
         	else if(isVariable.equals("DateToday")){
         		System.out.println("Performing Test# "+ MainTest.currentTest);
        		System.out.print(TestDescription);
        	
        		String randomnumber = cm.returnTodayDateddMMYY();
        		RuntimeVariables.writeVariable(InputKey, randomnumber);
        		
        		
        		cm.SendKeys(MainTest.d, LocatorID, randomnumber, LocatorType);
        		System.out.println(" ");
        		System.out.println("ACTION _keypress initiated");
        		System.out.println(" ");
        		}
        	
        	else if(isVariable.equals("inputplusrandomgenerated")){
        		System.out.println("Performing Test# "+ MainTest.currentTest);
        		System.out.print(TestDescription);
        	
        		String randomnumber2 = String.valueOf(ThreadLocalRandom.current().nextInt(100, 1000000 + 1));
        		String input_plus_randomnumber = InputKey + randomnumber2;
        		RuntimeVariables.writeVariable(InputKey, randomnumber2);
        		
        		
        		cm.SendKeys(MainTest.d, LocatorID, input_plus_randomnumber, LocatorType);
        		System.out.println(" ");
        		System.out.println("ACTION _keypress initiated");
        		System.out.println(" ");
        		}
        	
        	else if(isVariable.equals("yes_fromProp")){
        		System.out.println("Performing Test# "+ MainTest.currentTest);
        		System.out.print(TestDescription);
        	
        		InputKey=CommonMethods.getVariableFromProperties(InputKey);
        		cm.SendKeys(MainTest.d, LocatorID, InputKey, LocatorType);
        		System.out.println(" ");
        		System.out.println("ACTION _keypress initiated: data from properties file");
        		System.out.println(" ");
        		}
        	
        	//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest, LocatorID, LocatorType);
        	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _keypress initiated");
        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _keypress initiated");
        	//CommonMethods.delay(2000);
        	break;
        	
        case "clearInputBox":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        
        	
        	cm.clearElement( MainTest.d, LocatorID, LocatorType);
        	System.out.println(" ");
        	System.out.println("ACTION _clearInputBox initiated");
        	System.out.println(" ");
        	
        	//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"_Clear InputBox Initiated", LocatorID, LocatorType);
        	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _clearInputBox initiated");
        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _clearInputBox initiated");
        	
        	break;
        	
        
        	
        
        	
      
  		
  		
        case "validation":
        	String stringToValidate = null;
        		System.out.println("Performing Test# "+ MainTest.currentTest);
        		System.out.println(TestDescription);
        		
        		
        		
        		if(isVariable.equals("no")){
        		
        			// use DOM in validation
        			if(InputKey.equals("DOM")){
        				JavascriptExecutor js = (JavascriptExecutor) MainTest.d ;
        				String script = LocatorID;
        				 Boolean st = (Boolean) js.executeScript(script);
        				 stringToValidate=st.toString();
        				//System.out.println("toggle status "+stringToValidate);
        			}
        			else{	
        				stringToValidate = cm.returnString(MainTest.d, LocatorID, LocatorType);
        				}
        			
        			
        			stringToValidate=stringToValidate.trim();
        		validation=validation.trim();
        		
        		if(validation.endsWith(".0"))
        		{validation = validation.replace(".0", "");}
        		
        		
        		
        		//System.out.println("validation is "+validation);
        		
        		if(stringToValidate.contains(validation) && !stringToValidate.equals(validation)){ 
        			System.out.println("EXPECTED STRING: "+ validation);
        			System.out.println("ACTUAL STRING SEEN: "+ stringToValidate);
        			stringToValidate=validation;
        			Assert.assertEquals(stringToValidate, validation);			
        			System.out.println("ACTION _validation 'partial validation' succeeded...\n");
        			System.out.println(" ");
        			//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"_PARTIAL VALIDATION SUCCEEDED", LocatorID, LocatorType);
                	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validation - partial validation succeeded");
                	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validation - partial validation succeeded");
        			break;
        		}
        		else if(stringToValidate.equals(validation) || stringToValidate.equals(validation.toUpperCase())){
        			System.out.println(" ");
        			Assert.assertEquals(stringToValidate, validation);
        			System.out.println("EXPECTED STRING: "+ validation);
        			System.out.println("ACTUAL STRING SEEN: "+ stringToValidate);
        			System.out.println("ACTION _validation 'complete validation' succeeded...");
        			System.out.println(" ");
        			
        			//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"COMPLETE VALIDATION SUCCEEDED", LocatorID, LocatorType);
                	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validation - complete validation succeeded");
                	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validation - complete validation succeeded");
        			break;
        			
        			
        		}
        		else{
        			System.out.println(" ");
        			System.out.println("EXPECTED STRING: "+ validation);
        			System.out.println("ACTUAL STRING SEEN: "+ stringToValidate);
        			System.out.println("ACTION _validation.. FAILED ");
        			System.out.println(stringToValidate);
        			cm.scshotshighlight(MainTest.d, suite+"_Test_Step_"+MainTest.currentTest, LocatorID, LocatorType);
        			writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validation - validation FAILED");
        			writehtml.writeEntryFailed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validation - \n validation FAILED - Expected: "+validation +" Actual: "+stringToValidate);
        		//	cm.scshots_validationIssue(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription, "ACTION _validation FAILED ; Expected: "+validation +" Actual: "+s);
        			
        			Assert.fail("ACTION _validation FAILED ; Expected: "+validation +" Actual: "+stringToValidate);
        		}
        		
        		
        		}
        		else 
        			if(isVariable.equals("yes")){
        				validation = CommonMethods.getVariable(validation);
        				String s = cm.returnString(MainTest.d, LocatorID, LocatorType);
                		if(s.contains(validation) && !s.equals(validation)){ 
                			System.out.println("EXPECTED STRING: "+ validation);
                			System.out.println("ACTUAL STRING SEEN: "+ s);
                			s=validation;
                			Assert.assertEquals(s, validation);			
                			System.out.println("ACTION _validation 'partial validation' succeeded...\n");
                			System.out.println(" ");
                			//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"_PARTIAL VALIDATION SUCCEEDED", LocatorID, LocatorType);
                        	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validation - partial validation succeeded");
                        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validation - partial validation succeeded");
                			break;
                		}
                		else if(s.equals(validation)){
                			System.out.println(" ");
                			
                			Assert.assertEquals(s, validation);
                			System.out.println("EXPECTED STRING: "+ validation);
                			System.out.println("ACTUAL STRING SEEN: "+ s);
                			System.out.println("ACTION _validation 'complete validation' succeeded...");
                			System.out.println(" ");
                			
                		//	cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"COMPLETE VALIDATION SUCCEEDED", LocatorID, LocatorType);
                        	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validation - complete validation succeeded");
                        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validation - complete validation succeeded");
                        	System.out.println(" ");
                			break;
                			
                			
                		}
                		else{
                			System.out.println(" ");
                		//	System.out.println("validation failed.. Element not found");
                			System.out.println(" ");
                			cm.scshotshighlight(MainTest.d, suite+"_Test_Step_"+MainTest.currentTest, LocatorID, LocatorType);
                			writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validation - validation FAILED");
                			writehtml.writeEntryFailed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validation - validation FAILED- Expected: "+validation +" Actual: "+s);
                		//	cm.scshots_validationIssue(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription, "ACTION _validation FAILED ; Expected: "+validation +" Actual: "+s);
                		//	cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"ACTION _validation FAILED-Expected- "+validation +" Actual- "+s, LocatorID, LocatorType);
                			
                			Assert.fail("ACTION _validation FAILED ; Expected: "+validation +" Actual: "+s);
                		}
        				
        				
        			}
        			else
        		if(isVariable.equals("OR")){
        			String [] val = cm.StringParser(validation);
    				String v1 = val[0];
    				String v2= val[1];
    				String s = cm.returnString(MainTest.d, LocatorID, LocatorType);
            		if(s.equals(v1) || s.equals(v2)){ 
            			System.out.println("EXPECTED STRING: "+ validation);
            			System.out.println("ACTUAL STRING SEEN: "+ s);
            			
            			System.out.println("ACTION _validation 'OR validation' succeeded...\n");
            			System.out.println(" ");
            			//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"_PARTIAL VALIDATION SUCCEEDED", LocatorID, LocatorType);
                    	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validation - partial validation succeeded");
                    	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validation - partial validation succeeded");
            			break;
            		}
            		
            		else{
            			System.out.println(" ");
            		//	System.out.println("validation failed.. Element not found");
            			System.out.println(" ");
            			cm.scshotshighlight(MainTest.d, suite+"_Test_Step_"+MainTest.currentTest, LocatorID, LocatorType);
            			writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validation - validation FAILED");
            			writehtml.writeEntryFailed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validation - validation FAILED- Expected: "+validation +" Actual: "+s);
            		//	cm.scshots_validationIssue(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription, "ACTION _validation FAILED ; Expected: "+validation +" Actual: "+s);
            		//	cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"ACTION _validation FAILED-Expected- "+validation +" Actual- "+s, LocatorID, LocatorType);
            			
            			Assert.fail("ACTION _validation FAILED ; Expected: "+validation +" Actual: "+s);
            		}
    				
    				
    			}
    		
        		
    
    		
        		
        		
        		
        		System.out.println(" ");
        		break;
        		
        case "hover":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        	
        	cm.MouseHover( MainTest.d, LocatorID, LocatorType);
        	System.out.println(" ");
        	System.out.println("ACTION _hover initiated");
        	System.out.println(" ");
        	
        	//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"_Mouse hover was initiated", LocatorID, LocatorType);
        	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _hover initiated");
        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _hover initiated");
        	
        	break;
        	
        case "convertStudentNameForUserLogSelect":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        	String newStudentName = "Automation, Student1 ("+RuntimeVariables.getVariable("student1")+")";
        	RuntimeVariables.writeVariable("Student1_forLog", newStudentName);
        	
        	break;
        	
        case "saveAsVariable":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	String[] arr3 = null;
        	String var_val = cm.returnString(MainTest.d, LocatorID, LocatorType);
        	
        	if(!isVariable.equals("no")) {
        	 arr3 = cm.StringParser(isVariable);
        	
        	
        	
   
        	 if(arr3[0].contains("replaceCharIndex")) {   // to replace character in string based on index and save to variable
            		
                    	String indx1 = arr3[1];
                    	String indx2 = arr3[2];
                    	String new1 = arr3[3];
                    	var_val=var_val.substring(Integer.parseInt(indx1), Integer.parseInt(indx2));
                    	System.out.println("substring "+ var_val);
                    	var_val=var_val.concat(new1);
                    	System.out.println(var_val);
            		
            	}
        	}
        	
        	RuntimeVariables.writeVariable(InputKey, var_val);
        	
        	
        	//cm.MouseHover( MainTest.d, LocatorID, LocatorType);
        	System.out.println(" ");
        	System.out.println("ACTION _saveAsVariable initiated - "+var_val+"  Variable saved as "+InputKey);
        	System.out.println(" ");
        	
        	//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"_Mouse hover was initiated", LocatorID, LocatorType);
        	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _saveAsVariable initiated.. Variable saved as variable to "+InputKey);
        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _saveAsVariable initiated.. Variable saved as variable to "+InputKey);
        	break;
        	
        
        	
        	
       
        	
   
        	
        	
        case "validateFromDynamicVariable":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        	if(isVariable.equals("yes")){
				validation = RuntimeVariables.getVariable(validation);
				String s = cm.returnString(MainTest.d, LocatorID, LocatorType);
				
				
        	
        		
				if(s.contains(validation) && !s.equals(validation)){ 
        			System.out.println("EXPECTED STRING: "+ validation);
        			System.out.println("ACTUAL STRING SEEN: "+ s);
        			s=validation;
        			Assert.assertEquals(s, validation);			
        			System.out.println("ACTION _validation 'partial validation' succeeded...\n");
        			System.out.println(" ");
        			//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"_PARTIAL VALIDATION SUCCEEDED", LocatorID, LocatorType);
                	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validation - partial validation succeeded");
                	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validation - partial validation succeeded");
        			break;
				} else
				if(s.equals(validation)){
        			System.out.println(" ");
        			
        			Assert.assertEquals(s, validation);
        			System.out.println("EXPECTED STRING: "+ validation);
        			System.out.println("ACTUAL STRING SEEN: "+ s);
        			System.out.println("ACTION _validateFromDynamicVariable - complete validation succeeded...");
        			System.out.println(" ");
        			
        			//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"COMPLETE VALIDATION SUCCEEDED", LocatorID, LocatorType);
                	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validateFromDynamicVariable - complete validation succeeded...");
                	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validateFromDynamicVariable - complete validation succeeded...");
                	
                	System.out.println(" ");
        			break;
        			
        			
        		}
        		else{
        			System.out.println(" ");
        		//	System.out.println("validation failed.. Element not found");
        			System.out.println(" ");
        			
        			cm.scshotsAlert(suite+"_Test_Step_"+MainTest.currentTest);
        			writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validateFromDynamicVariable");
        			writehtml.writeEntryFailed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validateFromDynamicVariable");
        			
        			Assert.fail("ACTION _validateFromDynamicVariable " + "Expected: "+validation + " Actual: "+s);
        		}
				
				
			}
        	
   
        	
        	
       
        	
        
        
        	
        	
        case "select":
        	//Overlay.display(TestDescription, Action);
        	//System.out.println("Performing Test# "+ MainTest.currentTest);
        	//System.out.print(TestDescription);
        	
         	if(isVariable.equals("no")){
        		System.out.println("Performing Test# "+ MainTest.currentTest);
        		System.out.print(TestDescription);
        	//System.out.println("using identifier "+ LocatorType);
        	    
        		if(InputKey.endsWith(".0"))
        		{InputKey = InputKey.replace(".0", "");}
        		cm.Select(MainTest.d, LocatorID, LocatorType, InputKey);
        		
        		
        	}
         	else if(isVariable.equals("yes_fromDynamicVariable")){
        		System.out.println("Performing Test# "+ MainTest.currentTest);
        		System.out.print(TestDescription);
        	
        		InputKey=RuntimeVariables.getVariable(InputKey);
        		cm.Select(MainTest.d, LocatorID, LocatorType, InputKey);
        		System.out.println(" ");
        		System.out.println("ACTION _keypress initiated");
        		System.out.println(" ");
        		}
        	else if(isVariable.equals("yes")){
        		System.out.println("Performing Test# "+ MainTest.currentTest);
        		System.out.print(TestDescription);
        	
        		InputKey=CommonMethods.getVariable(InputKey);
        		cm.Select(MainTest.d, LocatorID, LocatorType, InputKey);
        		
        		}
         	
        	else if(isVariable.equals("yes_fromProp")){
        		System.out.println("Performing Test# "+ MainTest.currentTest);
        		System.out.print(TestDescription);
        	
        		InputKey=CommonMethods.getVariableFromProperties(InputKey);
        		cm.Select(MainTest.d, LocatorID, LocatorType, InputKey);
        		
        		}
        	
        	//cm.Select(MainTest.d, LocatorID, LocatorType, InputKey);
        	//cm.MouseHover( MainTest.d, LocatorID, LocatorType);
        	System.out.println(" ");
        	System.out.println("ACTION _select initiated");
        	System.out.println(" ");
        	
        //	cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"was selected", LocatorID, LocatorType);
        	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _select initiated - "+InputKey+ " was selected");
        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _select initiated - "+InputKey+ " was selected");
        	break;
        	
        case "selectbyindex":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        	if(InputKey.endsWith(".0"))
    		{InputKey = InputKey.replace(".0", "");}
        	
        	cm.SelectByIndex(MainTest.d, LocatorID, LocatorType, Integer.parseInt(InputKey));
        	//cm.MouseHover( MainTest.d, LocatorID, LocatorType);
        	System.out.println(" ");
        	System.out.println("ACTION _selectbyvalue initiated");
        	System.out.println(" ");
        	
        //	cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"was selected", LocatorID, LocatorType);
        	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _selectbyvalue initiated - "+InputKey+ " was selected");
        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _selectbyvalue initiated - "+InputKey+ " was selected");
        	break;	
        
        	

        	
        	
  
        	
        	
        case "validateElementVisible":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        	if(isVariable.equals("yes_fromDynamicVariable")){
        		InputKey=RuntimeVariables.getVariable(InputKey);
        		
        		if(validation.equals("CurrentDate")) {
        			LocatorID=LocatorID.replace("DATE", CommonMethods.returnDateVariableFormat("MM/dd/yyyy"));
        			
        		}
            	
        		LocatorType="xpath";
            	LocatorID=LocatorID.replace("LOCATORID", InputKey); // replace the LOCATORID under LocatorID column with dynamic variable
            	System.out.println(LocatorID);
            	
            	cm.CheckExistence(MainTest.d, LocatorID, LocatorType);
     	       
            	if(CommonMethods.isEqual){
            		System.out.println(" ");
            		System.out.println("ACTION _validateElementVisible - Element is visible");
                	System.out.println(" ");
            		writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED","ACTION _validateElementVisible - Element is visible");
            		writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED","ACTION _validateElementVisible - Element is visible");
            		//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"Element_IS_VISIBLE", LocatorID, LocatorType);
            	}
            	else
            	{
            		//System.out.println("Element NOT seen");
            		System.out.println(" ");
            		System.out.println("ACTION _validateElementVisible - Element is NOT visible");
                	System.out.println(" ");
            		//writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED","Element not found!!");
                	cm.scshotsAlert(suite+"_Test_Step_"+MainTest.currentTest);
                	//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_"+MainTest.currentTest, LocatorID, LocatorType);
        			writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validateElementVisible - validation FAILED");
        			writehtml.writeEntryFailed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validateElementVisible  - Element is not visible");
            		Assert.fail("ACTION _validateElementVisible - Element is NOT visible - "+LocatorID+" Cannot be found"+ " using "+LocatorType);
            		
            	}
        		
        		
        	
        	} else
        		
        	{
        		cm.CheckExistence(MainTest.d, LocatorID, LocatorType);
        	       
            	if(CommonMethods.isEqual){
            		System.out.println(" ");
            		System.out.println("ACTION _validateElementVisible - Element is visible");
                	System.out.println(" ");
            		writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED","ACTION _validateElementVisible - Element is visible");
            		writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED","ACTION _validateElementVisible - Element is visible");
            		//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"Element_IS_VISIBLE", LocatorID, LocatorType);
            	}
            	else
            	{
            		//System.out.println("Element NOT seen");
            		System.out.println(" ");
            		System.out.println("ACTION _validateElementVisible - Element is NOT visible");
                	System.out.println(" ");
            		//writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED","Element not found!!");
                	cm.scshotsAlert(suite+"_Test_Step_"+MainTest.currentTest);
                	//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_"+MainTest.currentTest, LocatorID, LocatorType);
        			writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validateElementVisible - validation FAILED");
        			writehtml.writeEntryFailed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validateElementVisible  - Element is not visible");
            		Assert.fail("ACTION _validateElementVisible - Element is NOT visible - "+LocatorID+" Cannot be found"+ " using "+LocatorType);
            		
            	}
            	
        		
        		
        		
        		
        	}
        /*	try{
        	Assert.assertTrue(MainTest.d.findElement(By.id(LocatorID)).isDisplayed());
        	}catch(Exception e){
        		
        		//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"Element_IS_NOT_VISIBLE", LocatorID, LocatorType);
            	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", InputKey+ "was selected");
        	}
        	*/
        	
        	
        	break;

        case "comparePDF": // parameters 'reference file' ,downloadpath+newPDF name
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        	boolean isPDFEqual=false;
        	
        	String[] pdfs = cm.StringParser(InputKey);
        	
        	
    
       // 	String fileOLD="C:\\Users\\JunCarlo\\Downloads\\30491617-A.pdf";
      //	  String fileNEW="C:\\Users\\JunCarlo\\Downloads\\30491586.pdf";

      	  PDFUtil pdfUtil = new PDFUtil();

      	  pdfUtil.setCompareMode(CompareMode.VISUAL_MODE);
    	  System.out.println("Reference "+ CommonMethods.getVariableFromProperties(pdfs[0]) );
      	  System.out.println("To Verify" + CommonMethods.getVariableFromProperties(pdfs[1])+"\\"+pdfs[2]);
      	  isPDFEqual=pdfUtil.compare(CommonMethods.getVariableFromProperties(pdfs[0]), CommonMethods.getVariableFromProperties(pdfs[1])+"\\"+pdfs[2]);
      
   
       
        	if(isPDFEqual){
        		System.out.println(" ");
        		System.out.println("ACTION _comparePDF - PDF matched!");
            	System.out.println(" ");
        		writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED","ACTION comparePDF - PDF matched!");
        		writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED","ACTION comparePDF - PDF matched!");
        		//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"Element_IS_VISIBLE", LocatorID, LocatorType);
        	}
        	else
        	{
        		//System.out.println("Element NOT seen");
        		System.out.println(" ");
        		System.out.println("ACTION _comparePDF - PDF not matched!");
            	System.out.println(" ");
        		//writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED","Element not found!!");
            	cm.scshotsAlert(suite+"_Test_Step_"+MainTest.currentTest);
            	//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_"+MainTest.currentTest, LocatorID, LocatorType);
    			writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION comparePDF - PDF not matched FAILED");
    			writehtml.writeEntryFailed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION comparePDF  - PDF not matched");
        		Assert.fail("ACTION comparePDF - pdf not matched - ");
        		
        	}
        /*	try{
        	Assert.assertTrue(MainTest.d.findElement(By.id(LocatorID)).isDisplayed());
        	}catch(Exception e){
        		
        		//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"Element_IS_NOT_VISIBLE", LocatorID, LocatorType);
            	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", InputKey+ "was selected");
        	}
        	*/
        	
        	
        	break;

        	
        case "wait":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        	double delayTime1 = Double.parseDouble(InputKey);
        	int delayTime = (int) delayTime1;
        	CommonMethods.delay(delayTime);
        	
        	System.out.println("ACTION _wait initiated");
        	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _wait - Test delayed for "+InputKey+"milliseconds");
        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _wait - Test delayed for "+InputKey+"milliseconds");
        	
        	System.out.println(" ");
        	System.out.println(" ");
        	break;
        	
      
        	
        
        case "handleAlert":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        	
        	String alert = InputKey.toLowerCase();
        	
        	if(alert.equals("accept")){
        	//	cm.scshotsAlert(suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"Alert Displayed");
        		//cm.scshots(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"Alert Displayed");
        		cm.AcceptAlert(MainTest.d);
        		System.out.println("ACTION _handleAlert initiated - Alert Accepted");
        		writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _handleAlert - Alert Accepted");
        		writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _handleAlert - Alert Accepted");
        	
        		
        	}else if(alert.equals("dismiss")){
        		//cm.scshots(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"Alert Displayed");
        	//	cm.scshotsAlert(suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"Alert Displayed");
        		cm.DismissAlert(MainTest.d);
        		System.out.println("ACTION _handleAlert initiated - Alert Dismissed");
        		writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _handleAlert - Alert Dismissed");
        		writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _handleAlert - Alert Dismissed");
        		
        		
        	}
        	
        	
        	
        	
        	
        	System.out.println(" ");
        	System.out.println(" ");
        	break;
        
        	
        case "clickToOpenNewwWindow":
        	
        	if(isVariable.equals("yes_fromDynamicVariable")){
        		System.out.println("Performing Test# "+ MainTest.currentTest);
        		System.out.print(TestDescription);
        		InputKey=RuntimeVariables.getVariable(InputKey);
        		
        		parentHandle = MainTest.d.getWindowHandle(); // get the current window handle
        	
        		LocatorType="xpath";
            	LocatorID=LocatorID.replace("LOCATORID", InputKey); // replace the LOCATORID under LocatorID column with dynamic variable
            	System.out.println(LocatorID);
            	
            	cm.Click( MainTest.d, LocatorID, LocatorType);
            	for (String winHandle : MainTest.d.getWindowHandles()) {
          		   MainTest.d.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
          		}
        		
        		System.out.println(" ");
        		System.out.println("ACTION _keypress initiated");
        		System.out.println(" ");
        		}
        	
        	else{
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        	parentHandle = MainTest.d.getWindowHandle(); // get the current window handle
    		 // click some link that opens a new window
        //	cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"_Action click to open new window was initiated", LocatorID, LocatorType);
        	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _clickToOpenNewWindow initated");
        	
        	cm.Click( MainTest.d, LocatorID, LocatorType);
        	for (String winHandle : MainTest.d.getWindowHandles()) {
     		   MainTest.d.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
     		}
        	
        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _clickToOpenNewWindow initated");
        	System.out.println(" ");
        	System.out.println("ACTION _clickToOpenNewWindow initated");
        	System.out.println(" ");
        	
        	}
        	
        	break;
        	
        case "openNewTab":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        	parentW = MainTest.d.getWindowHandle();
        	
        	//MainTest.d.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        	//MainTest.d.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
    		
        	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _openNewTab initated");
        	
        	
        	JavascriptExecutor js = (JavascriptExecutor) MainTest.d;
        	js.executeScript("window.open('" +InputKey+"','_blank');");
        	
        	//Get the current window handle
        	

        	//Get the list of window handles
        	tabs = new ArrayList (MainTest.d.getWindowHandles());
        	System.out.println(tabs.size());
        	//Use the list of window handles to switch between windows
        	System.out.println("tabs on 1 index "+tabs.get(1));
        	//System.out.println("tabs on 0 index "+tabs.get(0));
        	System.out.println("windowHandle "+parentW);
        	
        	MainTest.d.switchTo().window((String) tabs.get(1));
        	MainTest.d.get(InputKey);
        	
        	
       
        	
        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _openNewTab initated");
        	System.out.println(" ");
        	System.out.println("ACTION _openNewTab initated");
        	System.out.println(" ");
        	
        	break;
        	
        case "switchToMainTab":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	//MainTest.d.close(); // close newly opened window when done with it
        	//Switch back to original window
        //	MainTest.d.switchTo().window(mainWindowHandle);
        	//MainTest.d.switchTo().defaultContent();
        	MainTest.d.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "\t");
        	MainTest.d.switchTo().window(parentW);
    		
    		writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _switchToMainTab initated");
    		writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _switchToMainTab initated");
    		
    		System.out.println(" ");
    		System.out.println("ACTION _switchToMainTab initated");
    		System.out.println(" ");
        	break;
        	
        case "switchToChildTab":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	//MainTest.d.close(); // close newly opened window when done with it
    		//MainTest.d.switchTo().window(winHandle1); // switch back to the original window
    		
        	MainTest.d.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "\t");
        	MainTest.d.switchTo().window((String) tabs.get(1));
        	
    		writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _switchToChildTab initated");
    		writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _switchToChildTab initated");
    		
    		System.out.println(" ");
    		System.out.println("ACTION _switchToChildTab initated");
    		System.out.println(" ");
        	break;
        	
        case "backToMainWindow":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	MainTest.d.close(); // close newly opened window when done with it
    		MainTest.d.switchTo().window(parentHandle); // switch back to the original window
    		
    		writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _backToMainWindow initated");
    		writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _backToMainWindow initated");
    		
    		System.out.println(" ");
    		System.out.println("ACTION _backToMainWindow initated");
    		System.out.println(" ");
        	break;
        	
        case "clickToBackMainWindow":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        	
        	
        	cm.Click( MainTest.d, LocatorID, LocatorType);
        	//MainTest.d.close();
        	MainTest.d.switchTo().window(parentHandle); // switch back to the original window
        	
        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _clickToBackMainWindow initated");
        	System.out.println(" ");
        	System.out.println("ACTION _clickToBackMainWindow initated");
        	System.out.println(" ");
        	
        	break;
        	
        	
        	
        case "getHashMD5": // InputKey= file / validation=hash string
        	
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        	//Create checksum for this file
        	File filehash = new File(InputKey);
        	 
        	//Use MD5 algorithm
        	MessageDigest md5Digesthash = MessageDigest.getInstance("MD5");
        	 
        	//Get the checksum
        	        	 
        	//see checksum
        	System.out.println(CommonMethods.getFileChecksum(md5Digesthash, filehash));
        	break;
        	
        	
        case "writeCurrentDateToVariable": 
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        	
        		String fdate=CommonMethods.returnDateVariableFormat(isVariable);
        		RuntimeVariables.writeVariable(InputKey, fdate);            	
        		
        		
        	
        	
        	
        	break;
        
        
        	
        case "compareFileMD5": // InputKey= file, refer to data.properties for variable / validation=hash string
        	String[] compareMD5 = cm.StringParser(InputKey);
        	
        	
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        	//Create checksum for this file
        	File file = new File(CommonMethods.getVariableFromProperties(compareMD5[0])+"\\"+compareMD5[1]);
        	 
        	//Use MD5 algorithm
        	MessageDigest md5Digest = MessageDigest.getInstance("MD5");
        	 
        	//Get the checksum
        	String checksum = CommonMethods.getFileChecksum(md5Digest, file);
        	 
        	//see checksum
        	System.out.println(checksum);
        	
        	
        	
        	
    		if(isVariable.equals("no")){
        		

        		
        	 if(checksum.equals(validation)){
        			System.out.println(" ");
        			Assert.assertEquals(checksum, validation);
        			System.out.println("EXPECTED STRING: "+ validation);
        			System.out.println("ACTUAL STRING SEEN: "+ checksum);
        			System.out.println("ACTION compareFileMD5 -  Validation Succeeded");
        			System.out.println(" ");
        			
        		//	cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"COMPLETE VALIDATION SUCCEEDED", LocatorID, LocatorType);
                	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _compareFileMD5 - Complete Validation Succeeded");
                	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _compareFileMD5 - Complete Validation Succeeded");
        			break;
        			
        			
        		}
        		else{
        			System.out.println(" ");
        			System.out.println("EXPECTED STRING: "+ validation);
        			System.out.println("ACTUAL STRING SEEN: "+ checksum);
        			System.out.println("ACTION compareFileMD5 - Validation FAILED ");
        			System.out.println(checksum);
        			
        			cm.scshotsAlert(suite+"_Test_Step_"+MainTest.currentTest);
        			writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _compareFileMD5 - Validation FAILED");
        			writehtml.writeEntryFailed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _compareFileMD5 - Validation FAILED");
        			
        			Assert.fail("ACTION _compareFileMD5 - Validation FAILED, "+LocatorID+" Cannot be found"+ " using "+LocatorType);
        		}
        		
        		
        		}
        		else 
        			if(isVariable.equals("yes")){
        				validation = CommonMethods.getVariable(validation);
        				
                	 if(checksum.equals(validation)){
                			System.out.println(" ");
                			
                			Assert.assertEquals(checksum, validation);
                			System.out.println("EXPECTED STRING: "+ validation);
                			System.out.println("ACTUAL STRING SEEN: "+ checksum);
                			System.out.println("ACTION _compareFileMD5 - Complete Validation Succeeded");
                			System.out.println(" ");
                			
                	//		cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"COMPLETE VALIDATION SUCCEEDED", LocatorID, LocatorType);
                        	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _compareFileMD5 - Complete Validation Succeeded");
                        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _compareFileMD5 - Complete Validation Succeeded");
                        	System.out.println(" ");
                			break;
                			
                			
                		}
                		else{
                			System.out.println(" ");
                			System.out.println("EXPECTED STRING: "+ validation);
                			System.out.println("ACTUAL STRING SEEN: "+ checksum);
                			System.out.println("ACTION compareFileMD5 - Validation FAILED ");
                			System.out.println(checksum);
                			
                			cm.scshotsAlert(suite+"_Test_Step_"+MainTest.currentTest);
                			writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _compareFileMD5 - Validation FAILED");
                			writehtml.writeEntryFailed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _compareFileMD5 - Validation FAILED");
                			Assert.fail("ACTION _compareFileMD5 - Validation FAILED");
                			
                		}
        				
        				
        			}
        		
        		
        		
        		System.out.println(" ");
        	
        	
        	
        	break;
        	

        	
        	
        	
        case "validationURL":
        	//Overlay.display(TestDescription, Action);
    		System.out.println("Performing Test# "+ MainTest.currentTest);
    		System.out.println(TestDescription);
    		
    		if(isVariable.equals("no")){
    		
    			String s = cm.returnURL(MainTest.d);
    			System.out.println("EXPECTED STRING: "+ validation);
    			System.out.println("ACTUAL STRING SEEN: "+ s);
    			
    		//	if(validation.endsWith("/")){ validation = validation.substring(0, validation.length()-1);}
    				
    			if(s.equals(validation)){
    				System.out.println(" ");
    				Assert.assertEquals(s, validation);
    				System.out.println("EXPECTED STRING: "+ validation);
    				System.out.println("ACTUAL STRING SEEN: "+ s);
    				System.out.println("ACTION _validationURL initiated - Complete Validation Succeeded");
    				System.out.println(" ");
    			
    				//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"COMPLETE VALIDATION SUCCEEDED", LocatorID, LocatorType);
    				writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationURL initiated - Complete Validation Succeeded");
    				writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationURL initiated - Complete Validation Succeeded");
    			break;
    			
    			
    			}else
    				if(s.contains(validation)){ 
    						System.out.println("EXPECTED STRING: "+ validation);
    						System.out.println("ACTUAL STRING SEEN: "+ s);
    						s=validation;
    						Assert.assertEquals(s, validation);			
    						System.out.println("ACTION _validationURL initiated - Partial Validation Succeeded");
    						System.out.println(" ");
    						//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"_PARTIAL VALIDATION SUCCEEDED", LocatorID, LocatorType);
    						writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationURL initiated - Partial Validation Succeeded");
    						writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationURL initiated - Partial Validation Succeeded");
    						break;
    										}
    			else{
    					System.out.println(" ");
    					System.out.println("ACTION _validationURL initiated - Validation FAILED");
    					System.out.println(s);
    					
    					cm.scshotsAlert(suite+"_Test_Step_"+MainTest.currentTest);
    					writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validationURL initiated - Validation FAILED: EXPECTED:"+validation+ " ACTUAL:"+s);
    					writehtml.writeEntryFailed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validationURL initiated - Validation FAILED: EXPECTED:"+validation+ " ACTUAL:"+s);
    					System.out.println(" ");
    					
    					Assert.fail("ACTION _validationURL initiated - Validation FAILED");
    					break;
    			
    		}
    		
    		
    		}
    		else 
    			if(isVariable.equals("yes")){
    				validation = CommonMethods.getVariable(validation);
    				String s = cm.returnURL(MainTest.d);
    				if(s.equals(validation)){
        				System.out.println(" ");
        				Assert.assertEquals(s, validation);
        				System.out.println("EXPECTED STRING: "+ validation);
        				System.out.println("ACTUAL STRING SEEN: "+ s);
        				System.out.println("ACTION _validationURL initiated - Complete Validation Succeeded");
        				System.out.println(" ");
        			
        				//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"COMPLETE VALIDATION SUCCEEDED", LocatorID, LocatorType);
        				writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationURL initiated - Complete Validation Succeeded");
        				writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationURL initiated - Complete Validation Succeeded");
        			break;
        			
        			
        			}else
        				if(s.contains(validation)){ 
        						System.out.println("EXPECTED STRING: "+ validation);
        						System.out.println("ACTUAL STRING SEEN: "+ s);
        						s=validation;
        						Assert.assertEquals(s, validation);			
        						System.out.println("ACTION _validationURL initiated - Partial Validation Succeeded");
        						System.out.println(" ");
        						//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"_PARTIAL VALIDATION SUCCEEDED", LocatorID, LocatorType);
        						writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationURL initiated - Partial Validation Succeeded");
        						writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationURL initiated - Partial Validation Succeeded");
        						break;
        										}
        			else{
        					System.out.println(" ");
        					System.out.println("ACTION _validationURL initiated - Validation FAILED");
        					System.out.println(s);
        					
        					cm.scshotsAlert(suite+"_Test_Step_"+MainTest.currentTest);
        					writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validationURL initiated - Validation FAILED: EXPECTED:"+validation+ " ACTUAL:"+s);
        					writehtml.writeEntryFailed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validationURL initiated - Validation FAILED: EXPECTED:"+validation+ " ACTUAL:"+s);
        					System.out.println(" ");
        					
        					Assert.fail("ACTION _validationURL initiated - Validation FAILED");
        					break;
        			
        		}
    				
    	
    									}
    	
        case "validationAlertMessage":
        	//Overlay.display(TestDescription, Action);
    		System.out.println("Performing Test# "+ MainTest.currentTest);
    		System.out.println(TestDescription);
    		
    		if(isVariable.equals("no")){
    		
    			String s = cm.getAlertMessage(MainTest.d);
    			if(s.equals(validation)){
    				System.out.println(" ");
    				Assert.assertEquals(s, validation);
    				System.out.println("EXPECTED STRING: "+ validation);
    				System.out.println("ACTUAL STRING SEEN: "+ s);
    				System.out.println("ACTION _validationAlertMessage - Validation Succeeded");
    				System.out.println(" ");
    			
    				//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"COMPLETE VALIDATION SUCCEEDED", LocatorID, LocatorType);
    				writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationAlertMessage - Validation Succeeded");
    				writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationAlertMessage - Validation Succeeded");
    			break;
    			
    			
    		}
    		else{
    			System.out.println(" ");
    			System.out.println("ACTION _validationAlertMessage - Validation FAILED");
    			System.out.println(s);
    			
    			cm.scshotsAlert(suite+"_Test_Step_"+MainTest.currentTest);
    			writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validationAlertMessage - Validation FAILED");
    			writehtml.writeEntryFailed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validationAlertMessage - Validation FAILED");
    			
    			Assert.fail("ACTION _validationAlertMessage - Validation FAILED");
    			System.out.println(" ");
        		break;
    			
    		}
    		
    		
    		}
    		else 
    			if(isVariable.equals("yes")){
    				validation = CommonMethods.getVariable(validation);
    				String s = cm.getAlertMessage(MainTest.d);
            	 if(s.equals(validation)){
            			System.out.println(" ");
            			
            			Assert.assertEquals(s, validation);
            			System.out.println("EXPECTED STRING: "+ validation);
            			System.out.println("ACTUAL STRING  SEEN: "+ s);
            			System.out.println("ACTION _validationAlertMessage - Validation Succeeded");
            			System.out.println(" ");
            			
            			//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"URL VALIDATION SUCCEEDED", LocatorID, LocatorType);
                    	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationAlertMessage - Validation Succeeded");
                    	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationAlertMessage - Validation Succeeded");
                    	System.out.println(" ");
            			break;
            			
            			
            		}
            		else{
            			System.out.println(" ");
            			System.out.println("ACTION _validationAlertMessage - Validation FAILED");
            			System.out.println(" ");
            			
            			cm.scshotsAlert(suite+"_Test_Step_"+MainTest.currentTest);
            			writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validationAlertMessage - Validation FAILED");
            			writehtml.writeEntryFailed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validationAlertMessage - Validation FAILED");
            			
            			Assert.fail("ACTION _validationAlertMessage - Validation FAILED");
            			System.out.println(" ");
                		break;
            			}
    				
    	
    									}
    		
    		
        case "validationValueOfElement":
        	//Overlay.display(TestDescription, Action);
    		System.out.println("Performing Test# "+ MainTest.currentTest);
    		System.out.println(TestDescription);
    		
    		if(isVariable.equals("no")){
    		
    		String s = cm.returnValue(MainTest.d, LocatorID, LocatorType);
    		s=s.trim();
    		validation=validation.trim();
    		
    		if(validation.endsWith(".0"))
    		{validation = validation.replace(".0", "");}
    		
    		if(s.contains(validation) && !s.equals(validation)){ 
    			System.out.println("EXPECTED STRING: "+ validation);
    			System.out.println("ACTUAL STRING SEEN: "+ s);
    			s=validation;
    			Assert.assertEquals(s, validation);			
    			System.out.println("ACTION _validationValueOfElement - Partial Validation Succeeded");
    			System.out.println(" ");
    		//	cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"_PARTIAL VALIDATION SUCCEEDED", LocatorID, LocatorType);
            	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationValueOfElement - Partial Validation Succeeded");
            	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationValueOfElement - Partial Validation Succeeded");
    			break;
    		}
    		else if(s.equals(validation)){
    			System.out.println(" ");
    			Assert.assertEquals(s, validation);
    			System.out.println("EXPECTED STRING: "+ validation);
    			System.out.println("ACTUAL STRING SEEN: "+ s);
    			System.out.println("ACTION _validationValueOfElement - Complete Validation Succeeded");
    			System.out.println(" ");
    			
    		//	cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"COMPLETE VALIDATION SUCCEEDED", LocatorID, LocatorType);
            	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationValueOfElement - Complete Validation Succeeded");
            	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationValueOfElement - Complete Validation Succeeded");
    			break;
    			
    			
    		}
    		else{
    			System.out.println(" ");
    			System.out.println("EXPECTED STRING: "+ validation);
    			System.out.println("ACTUAL STRING SEEN: "+ s);
    			System.out.println("ACTION _validationValueOfElement - Validation FAILED ");
    			System.out.println(s);
    			
    			cm.scshotsAlert(suite+"_Test_Step_"+MainTest.currentTest);
    			writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validationValueOfElement - Validation FAILED");
    			writehtml.writeEntryFailed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validationValueOfElement - Validation FAILED");
    			
    			Assert.fail("ACTION _validationValueOfElement - Validation FAILED, "+LocatorID+" Cannot be found"+ " using "+LocatorType);
    		}
    		
    		
    		}
    		else 
    			if(isVariable.equals("yesRuntimeV")){
    				validation = RuntimeVariables.getVariable(validation);
    				String s = cm.returnValue(MainTest.d, LocatorID, LocatorType);
            		if(s.contains(validation) && !s.equals(validation)){ 
            			System.out.println("EXPECTED STRING: "+ validation);
            			System.out.println("ACTUAL STRING SEEN: "+ s);
            			s=validation;
            			Assert.assertEquals(s, validation);			
            			System.out.println("ACTION _validationValueOfElement - Partial Validation Succeeded");
            			System.out.println(" ");
            	//		cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"_PARTIAL VALIDATION SUCCEEDED", LocatorID, LocatorType);
                    	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "PARTIAL VALIDATION SUCCEEDED");
                    	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "PARTIAL VALIDATION SUCCEEDED");
            			
            			break;
            		}
            		else if(s.equals(validation)){
            			System.out.println(" ");
            			
            			Assert.assertEquals(s, validation);
            			System.out.println("EXPECTED STRING: "+ validation);
            			System.out.println("ACTUAL STRING SEEN: "+ s);
            			System.out.println("ACTION _validationValueOfElement - Complete Validation Succeeded");
            			System.out.println(" ");
            			
            	//		cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"COMPLETE VALIDATION SUCCEEDED", LocatorID, LocatorType);
                    	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationValueOfElement - Complete Validation Succeeded");
                    	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationValueOfElement - Complete Validation Succeeded");
                    	System.out.println(" ");
            			break;
            			
            			
            		}
            		else{
            			System.out.println(" ");
            			System.out.println("ACTION _validationValueOfElement - Validation FAILED");
            			System.out.println(" ");
            			
            			cm.scshotsAlert(suite+"_Test_Step_"+MainTest.currentTest);
            			writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validationValueOfElement - Validation FAILED");
            			writehtml.writeEntryFailed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validationValueOfElement - Validation FAILED");
            			Assert.fail("ACTION _validationValueOfElement - Validation FAILED, "+LocatorID+" Cannot be found"+ " using "+LocatorType);
            			
            		}
    				
    				
    			}
    		
    		
    		
    		System.out.println(" ");
    		break;
    		
        case "validationAttributeOfElement":
        	//Overlay.display(TestDescription, Action);
    		System.out.println("Performing Test# "+ MainTest.currentTest);
    		System.out.println(TestDescription);
    		
    		if(isVariable.equals("no")){
    		
    		String s = cm.returnAttribute(MainTest.d, LocatorID, LocatorType,InputKey);
    		s=s.trim();
    		validation=validation.trim();
    		
    		if(validation.endsWith(".0"))
    		{validation = validation.replace(".0", "");}
    		
    		if(s.contains(validation) && !s.equals(validation)){ 
    			System.out.println("EXPECTED STRING: "+ validation);
    			System.out.println("ACTUAL STRING SEEN: "+ s);
    			s=validation;
    			Assert.assertEquals(s, validation);			
    			System.out.println("ACTION _validationValueOfElement - Partial Validation Succeeded");
    			System.out.println(" ");
    		//	cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"_PARTIAL VALIDATION SUCCEEDED", LocatorID, LocatorType);
            	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationValueOfElement - Partial Validation Succeeded");
            	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationValueOfElement - Partial Validation Succeeded");
    			break;
    		}
    		else if(s.equals(validation)){
    			System.out.println(" ");
    			Assert.assertEquals(s, validation);
    			System.out.println("EXPECTED STRING: "+ validation);
    			System.out.println("ACTUAL STRING SEEN: "+ s);
    			System.out.println("ACTION _validationValueOfElement - Complete Validation Succeeded");
    			System.out.println(" ");
    			
    		//	cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"COMPLETE VALIDATION SUCCEEDED", LocatorID, LocatorType);
            	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationValueOfElement - Complete Validation Succeeded");
            	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationValueOfElement - Complete Validation Succeeded");
    			break;
    			
    			
    		}
    		else{
    			System.out.println(" ");
    			System.out.println("EXPECTED STRING: "+ validation);
    			System.out.println("ACTUAL STRING SEEN: "+ s);
    			System.out.println("ACTION _validationValueOfElement - Validation FAILED ");
    			System.out.println(s);
    			
    			cm.scshotsAlert(suite+"_Test_Step_"+MainTest.currentTest);
    			writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validationValueOfElement - Validation FAILED");
    			writehtml.writeEntryFailed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validationValueOfElement - Validation FAILED");
    			
    			Assert.fail("ACTION _validationValueOfElement - Validation FAILED, "+LocatorID+" Cannot be found"+ " using "+LocatorType);
    		}
    		
    		
    		}
    		else 
    			if(isVariable.equals("yes_fromDynamicVariable")){
    				validation = CommonMethods.getVariable(validation);
    				String s = cm.returnValue(MainTest.d, LocatorID, LocatorType);
            		if(s.contains(validation) && !s.equals(validation)){ 
            			System.out.println("EXPECTED STRING: "+ validation);
            			System.out.println("ACTUAL STRING SEEN: "+ s);
            			s=validation;
            			Assert.assertEquals(s, validation);			
            			System.out.println("ACTION _validationValueOfElement - Partial Validation Succeeded");
            			System.out.println(" ");
            	//		cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"_PARTIAL VALIDATION SUCCEEDED", LocatorID, LocatorType);
                    	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "PARTIAL VALIDATION SUCCEEDED");
                    	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "PARTIAL VALIDATION SUCCEEDED");
            			
            			break;
            		}
            		else if(s.equals(validation)){
            			System.out.println(" ");
            			
            			Assert.assertEquals(s, validation);
            			System.out.println("EXPECTED STRING: "+ validation);
            			System.out.println("ACTUAL STRING SEEN: "+ s);
            			System.out.println("ACTION _validationValueOfElement - Complete Validation Succeeded");
            			System.out.println(" ");
            			
            	//		cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"COMPLETE VALIDATION SUCCEEDED", LocatorID, LocatorType);
                    	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationValueOfElement - Complete Validation Succeeded");
                    	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validationValueOfElement - Complete Validation Succeeded");
                    	System.out.println(" ");
            			break;
            			
            			
            		}
            		else{
            			System.out.println(" ");
            			System.out.println("ACTION _validationValueOfElement - Validation FAILED");
            			System.out.println(" ");
            			
            			cm.scshotsAlert(suite+"_Test_Step_"+MainTest.currentTest);
            			writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validationValueOfElement - Validation FAILED");
            			writehtml.writeEntryFailed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validationValueOfElement - Validation FAILED");
            			Assert.fail("ACTION _validationValueOfElement - Validation FAILED, "+LocatorID+" Cannot be found"+ " using "+LocatorType);
            			
            		}
    				
    				
    			}
    		
    		
    		
    		System.out.println(" ");
    		break;
    		
    	
        case "goToiFrame":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        	cm.goToiFrame(MainTest.d, LocatorID, LocatorType);

        	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _goToiFrame - initiated");
        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _goToiFrame - initiated");
        	System.out.println(" ");
        	System.out.println("ACTION _goToiFrame - initiated");
        	System.out.println(" ");
        	
        	break;
        	
        case "moveOutFromiFrame":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
    		MainTest.d.switchTo().defaultContent();
    		writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _moveOutFromiFrame - initiated");
    		writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _moveOutFromiFrame - initiated");
    		System.out.println("ACTION _moveOutFromiFrame - initiated");
    		System.out.println(" ");
        	break;
        	
        case "scrollDown":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	String [] s = cm.StringParser(InputKey);
        	String up = s[0];
        	String down= s[1];
    		
        
        	JavascriptExecutor jse = (JavascriptExecutor)MainTest.d;
        	jse.executeScript("window.scrollBy("+up+","+down+")", "");
        	
        	
    		writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _scrollDown- initiated");
    		writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _scrollDown- initiated");
    		System.out.println(" ");
    		System.out.println("ACTION _scrollDown - initiated ");
    		System.out.println(" ");
        	break;
        	
        case "scrollUp":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	String [] s1 = cm.StringParser(InputKey);
        	String up1 = s1[0];
        	String down1= s1[1];
    		
        
        	JavascriptExecutor jse1 = (JavascriptExecutor)MainTest.d;
        	jse1.executeScript("window.scrollBy("+up1+","+down1+")", "");
        	
        	
    		writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _scrollUp - initiated");
    		writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _scrollUp - initiated");
    		System.out.println(" ");
    		System.out.println("ACTION _scrollUp - initiated");
    		System.out.println(" ");
        	break;
        	
        case "gotoURL":
        	
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        	if(isVariable.equals("yes")){cm.gotoURL(MainTest.d, CommonMethods.getVariable(InputKey));}
        	else{
        	cm.gotoURL(MainTest.d, InputKey);
        	}
        	
    		writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _gotoURL - initiated");
    		writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _gotoURL - initiated");
    		System.out.println(" ");
    		System.out.println("ACTION _gotoURL - initiated");
    		System.out.println(" ");
        	break;
        	
        case "refreshPage":
        	
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        
        	MainTest.d.navigate().refresh();
        	
        	
    		writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _refreshPage - initiated");
    		writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _refreshPage - initiated");
    		System.out.println(" ");
    		System.out.println("ACTION _refreshPage - initiated");
    		System.out.println(" ");
        	break;
        case "goBack":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
    		MainTest.d.navigate().back();
        	
        	
    		writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _goBack - initiated");
    		writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _goBack - initiated");
    		System.out.println(" ");
    		System.out.println("ACTION _goBack - initiated");
    		System.out.println(" ");
        	break;
        	
        case "validateElementNotPresent":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	
        	MainTest.d.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        	cm.CheckNonExistence(MainTest.d, LocatorID, LocatorType);
        	
        	
        	if(!CommonMethods.isEqual){
        		System.out.println(" ");
        		System.out.println("ACTION _validateElementNotPresent - Element Not Found");
            	System.out.println(" ");
        		writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED","ACTION _validateElementNotPresent - Element Not Found");
        		writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED","ACTION _validateElementNotPresent - Element Not Found");
        		//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"Element_IS_NOT_VISIBLE", LocatorID, LocatorType);
        	}
        	else
        	{
        		//System.out.println("Element NOT seen");
        		System.out.println(" ");
        		System.out.println("ACTION _validateElementNotPresent - Element is Found");
            	System.out.println(" ");
            	cm.scshotshighlight(MainTest.d, suite+"_Test_Step_"+MainTest.currentTest, LocatorID, LocatorType);
        		writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED","ACTION _validateElementNotPresent - Element is Found");
        		writehtml.writeEntryFailed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED","ACTION _validateElementNotPresent - Element is Found");
        		Assert.fail("ACTION _validateElementNotPresent - Element is Found, "+LocatorID+" Element can be found"+ " using "+LocatorType);
        		
        	}
        	
        	MainTest.d.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        	break;
        	
        	
       
    		
        case "waitForElement":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        
    		
        	cm.waitForElement(MainTest.d, LocatorID, LocatorType);
        	
    		writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _waitForElement - initiated");
    		writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _waitForElement - initiated");
    		System.out.println(" ");
    		System.out.println("ACTION _waitForElement - initiated");
    		System.out.println(" ");
        	break;
        	        	
        	
        case "waitForElementSkipIfNotFound":
        	//Overlay.display(TestDescription, Action);
        	//toStep=Integer.parseInt(InputKey);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        
        	if(InputKey.endsWith(".0"))
    		{InputKey = InputKey.replace(".0", "");}
        	
        	cm.waitForElementSkipIfNotFound(MainTest.d, LocatorID, LocatorType);
        	
        	if(CommonMethods.isElementFoundForWaitElement){
        		writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION __waitForElementSkipIfNotFound - Element found");
        		writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _waitForElementSkipIfNotFound - Element found");
        		System.out.println(" ");
        		System.out.println("ACTION _waitForElementSkipIfNotFound - Element found");
        		System.out.println(" ");
        	}
        	else{
        		
        		toStep=Integer.parseInt(InputKey);
        		System.out.println(" ");
        		System.out.println("ACTION _waitForElementSkipIfNotFound - Element not found.. Test will skip to step# "+InputKey);
        		System.out.println(" ");
        	}
        	//CommonMethods.isElementFoundForWaitElement=false;
        		
        	break;
        	
        case "convertToXlsx":
        
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print("Writing Test Label on report.. "+ TestDescription);
        	String[] f_Source_Target = cm.StringParser(InputKey);
        	
    		
        	CommonMethods.csvToXLSX(MainTest.dPath+f_Source_Target[0], MainTest.dPath+f_Source_Target[1]);
        	
    		writehtml.writeTestLabel(suite, "Performing Test# "+ MainTest.currentTest, TestDescription);
    		System.out.println(" ");
    		System.out.println("ACTION _writeTestLabel - initiated");
    		System.out.println(" ");
        	break;
        	
        case "copyFileToDirectory":
        	
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print("Writing Test Label on report.. "+ TestDescription);
        	String home = System.getProperty("user.home");
    		File n = CommonMethods.getLastModifiedFile(new File(home+"\\Downloads"));
    		System.out.println(n.getAbsolutePath());
    		CommonMethods.copyFileUsingStream(n, new File(MainTest.dPath+InputKey));
        	break;
        	
        case "writeTestLabel":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print("Writing Test Label on report.. "+ TestDescription);
        
    		
        	
        	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _writeTestLabel - initiated");
    		writehtml.writeTestLabel(suite, "Performing Test# "+ MainTest.currentTest, TestDescription);
    		System.out.println(" ");
    		System.out.println("ACTION _writeTestLabel - initiated");
    		System.out.println(" ");
        	break;
        	
       
        	
   
        		
        	
        case "validateClass":
        	//Overlay.display(TestDescription, Action);
        	System.out.println("Performing Test# "+ MainTest.currentTest);
        	System.out.print(TestDescription);
        	String cl;
    		
        	cl=cm.getClass(MainTest.d, LocatorID, LocatorType);
        	System.out.println(cl);
        	
        //	String s = cm.returnString(MainTest.d, LocatorID, LocatorType);
    	//	s=s.trim();
        	if(cl.equals(validation)){
    			System.out.println(" ");
    			Assert.assertEquals(cl, validation);
    			System.out.println("EXPECTED STRING: "+ validation);
    			System.out.println("ACTUAL STRING SEEN: "+ cl);
    			System.out.println("ACTION _validation 'CLASS verifcation succeeded");
    			System.out.println(" ");
    			
    			//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"COMPLETE VALIDATION SUCCEEDED", LocatorID, LocatorType);
            	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validateClass - complete validation succeeded");
            	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION _validateClass - complete validation succeeded");
    			break;
    				
    		}
    		else{
    			System.out.println(" ");
    			System.out.println("EXPECTED STRING: "+ validation);
    			System.out.println("ACTUAL STRING SEEN: "+ cl);
    			System.out.println("ACTION _validation.. FAILED ");
    			//System.out.println(s);
    			cm.scshotshighlight(MainTest.d, suite+"_Test_Step_"+MainTest.currentTest, LocatorID, LocatorType);
    			writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validateClass - validation FAILED");
    			writehtml.writeEntryFailed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION _validateClass - \n validation FAILED - Expected: "+validation +" Actual: "+cl);
    		//	cm.scshots_validationIssue(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription, "ACTION _validation FAILED ; Expected: "+validation +" Actual: "+s);
    			
    			Assert.fail("ACTION _validateClass FAILED ; Expected: "+validation +" Actual: "+cl);
    		}
    		
        	
    	
        	break;
        	
        	
        	
        
      
  
        	
        	

        	     	 	
        	
       
        	
     
 	   	
 	   	
    case "checkDownloadFileExistence":
   
    	System.out.println("Performing Test# "+ MainTest.currentTest);
    	System.out.print(TestDescription);
    	Boolean tf;

    	tf=CommonMethods.checkDownloadFileExistince();
   
    	if(tf){
    		System.out.println(" ");
    		System.out.println("ACTION checkDownloadFileExistince - Downloaded file is present on directory");
        	System.out.println(" ");
    		writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED","ACTION _checkDownloadFileExistince - Downloaded file is present on directory");
    		writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED","ACTION _checkDownloadFileExistince - Downloaded file is present on directory");
    		//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"Element_IS_VISIBLE", LocatorID, LocatorType);
    	}
    	else
    	{
    		//System.out.println("Element NOT seen");
    		System.out.println(" ");
    		System.out.println("ACTION checkDownloadFileExistince - FAIL -Downloaded file is NOT present on directory");
        	System.out.println(" ");
    		//writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED","Element not found!!");
        	cm.scshotsAlert(suite+"_Test_Step_"+MainTest.currentTest);
        	//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_"+MainTest.currentTest, LocatorID, LocatorType);
			writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION checkDownloadFileExistince - FAIL -Downloaded file is NOT present on directory");
			writehtml.writeEntryFailed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION checkDownloadFileExistince - FAIL -Downloaded file is NOT present on directory");
    		Assert.fail("ACTION checkDownloadFileExistince - FAIL -Downloaded file is NOT present on directory- "+LocatorID+" Cannot be found"+ " using "+LocatorType);
    		
    	}

    	
    	
    	break;
    	
    	
    case "getElementAttribute":  // element attribute saved to runtime variable
    	   
    	System.out.println("Performing Test# "+ MainTest.currentTest);
    	System.out.print(TestDescription);
    	

    	String Element_Attr = cm.getElementAttribute(MainTest.d, LocatorID, LocatorType, validation);
    	RuntimeVariables.writeVariable(InputKey, Element_Attr);
    	
    	
    	
    	System.out.println(" ");
    	System.out.println("ACTION getElementAttribute initiated - "+Element_Attr+"  Variable saved as "+InputKey);
    	System.out.println(" ");
    	
    	//cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"_Mouse hover was initiated", LocatorID, LocatorType);
    	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION getElementAttribute initiated.. Variable saved as variable to "+InputKey);
    	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION getElementAttribute initiated.. Variable saved as variable to "+InputKey);
    	break;
    	
    	
    case "verifyExcelContent":
    	String[] RowColFile = cm.StringParser(InputKey);
    
    	
		System.out.println("Performing Test# "+ MainTest.currentTest);
		System.out.println(TestDescription);
		
		if(isVariable.equals("no")){
		
		String s11 = CommonMethods.getStringFromExcelFile(Integer.parseInt(RowColFile[0]), Integer.parseInt(RowColFile[1]), RowColFile[2]);
		
		s11=s11.trim();
		validation=validation.trim();
		
	
		
		if(validation.endsWith(".0"))
		{validation = validation.replace(".0", "");}
		
		if(s11.contains(validation)){ 
			System.out.println("EXPECTED STRING: "+ validation);
			System.out.println("ACTUAL STRING SEEN ON FILE: "+ s11);
			s11=validation;
			Assert.assertEquals(s11, validation);			
			System.out.println("ACTION verifyExcelContent - Partial Validation Succeeded");
			System.out.println(" ");
		//	cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"_PARTIAL VALIDATION SUCCEEDED", LocatorID, LocatorType);
        	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION verifyExcelContent - Partial Validation Succeeded");
        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION verifyExcelContent - Partial Validation Succeeded");
			break;
		}
		else if(s11.equals(validation)){
			System.out.println(" ");
			Assert.assertEquals(s11, validation);
			System.out.println("EXPECTED STRING: "+ validation);
			System.out.println("ACTUAL STRING SEEN: "+ s11);
			System.out.println("ACTION verifyExcelContent - Complete Validation Succeeded");
			System.out.println(" ");
			
		//	cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"COMPLETE VALIDATION SUCCEEDED", LocatorID, LocatorType);
        	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION verifyExcelContent - Complete Validation Succeeded");
        	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION verifyExcelContent - Complete Validation Succeeded");
			break;
			
			
		}
		else{
			System.out.println(" ");
			System.out.println("EXPECTED STRING: "+ validation);
			System.out.println("ACTUAL STRING SEEN: "+ s11);
			System.out.println("ACTION verifyExcelContent - Validation FAILED ");
			System.out.println(s11);
			
			cm.scshotsAlert(suite+"_Test_Step_"+MainTest.currentTest);
			writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION verifyExcelContent - Validation FAILED");
			writehtml.writeEntryFailed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION verifyExcelContent - Validation FAILED");
			
			Assert.fail("ACTION verifyExcelContent - Validation FAILED");
		}
		
		
		}
		else 
			if(isVariable.equals("yes_fromDynamicVariable")){
				validation = RuntimeVariables.getVariable(validation);
				
				String s11 = CommonMethods.getStringFromExcelFile(Integer.parseInt(RowColFile[0]), Integer.parseInt(RowColFile[1]), RowColFile[2]);
				System.out.println("EXPECTED STRING: "+ validation);
				System.out.println("ACTUAL STRING SEEN ON FILE: "+ s11);
				
        		if(s11.contains(validation)){ 
        			System.out.println("EXPECTED STRING: "+ validation);
        			System.out.println("ACTUAL STRING SEEN ON FILE: "+ s11);
        			s11=validation;
        			Assert.assertEquals(s11, validation);			
        			System.out.println("ACTION verifyExcelContent - Partial Validation Succeeded");
        			System.out.println(" ");
        	//		cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"_PARTIAL VALIDATION SUCCEEDED", LocatorID, LocatorType);
                	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "PARTIAL VALIDATION SUCCEEDED");
                	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "PARTIAL VALIDATION SUCCEEDED");
        			
        			break;
        		}
        		else if(s11.equals(validation)){
        			System.out.println(" ");
        			
        			Assert.assertEquals(s11, validation);
        			System.out.println("EXPECTED STRING: "+ validation);
        			System.out.println("ACTUAL STRING SEEN: "+ s11);
        			System.out.println("ACTION verifyExcelContent - Complete Validation Succeeded");
        			System.out.println(" ");
        			
        	//		cm.scshotshighlight(MainTest.d, suite+"_Test_Step_#"+MainTest.currentTest+"_"+TestDescription+"COMPLETE VALIDATION SUCCEEDED", LocatorID, LocatorType);
                	writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION verifyExcelContent - Complete Validation Succeeded");
                	writehtml.writeEntryPassed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "PASSED", "ACTION verifyExcelContent - Complete Validation Succeeded");
                	System.out.println(" ");
        			break;
        			
        			
        		}
        		else{
        			System.out.println(" ");
        			System.out.println("ACTION verifyExcelContent - Validation FAILED");
        			System.out.println(" ");
        			
        			cm.scshotsAlert(suite+"_Test_Step_"+MainTest.currentTest);
        			writereport.writeEntry(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION verifyExcelContent - Validation FAILED");
        			writehtml.writeEntryFailed(suite,"Performing Test# "+ MainTest.currentTest,TestDescription, "FAILED", "ACTION verifyExcelContent - Validation FAILED");
        			Assert.fail("ACTION verifyExcelContent - Validation FAILED, "+LocatorID+" Cannot be found"+ " using "+LocatorType);
        			
        		}
				
				
			}
		
		
		
		System.out.println(" ");
		break;
    	
    	
    	
    	
    
    	
   
      	
    
  		
     
      	
      
        	
        } //end of switch
        
    
        
        
        fsIP.close(); //Close the InputStream
         

        
        
        
		
	}
	
	public int getNumberTests(String suite) throws IOException{
		FileInputStream fsIP= new FileInputStream(new File("Tests.xlsx")); //Read the spreadsheet that needs to be updated
        
        XSSFWorkbook wb = new XSSFWorkbook(fsIP); //Access the workbook
          
        XSSFSheet worksheet = wb.getSheet(suite); //Access the worksheet, so that we can update / modify it.
          
        Cell cell = null; // declare a Cell object
        
         int NumberOfTests = worksheet.getLastRowNum();
        
        fsIP.close();
        
        return NumberOfTests;
		
	}
	
	
	
	
	
	

	
	

}
