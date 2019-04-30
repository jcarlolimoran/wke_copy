package test;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.xpath.operations.Div;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;



public class CommonMethods {
	
	public WebElement element;
	public static String screenshot, error, errorline2, errorline3, errorline1;
	public static boolean isEqual=false;
	WriteReport writeFailedreport = new WriteReport();
	writeHtml writehtml = new writeHtml();
	public static boolean isElementFoundForWaitElement = true;
	
	//public static int numberofCID;
	
	
	public CommonMethods(){
		
		
	}
	
	public void Click(WebDriver d,String locatorID,String locatorType) throws Exception{
	
		
		try{
		switch (locatorType)
		{
		
		case "id":
			//if(Tests.ActionGlobal.equals("clickToBackMainWindow"))
			highlight(d,d.findElement(By.id(locatorID)));
			d.findElement(By.id(locatorID)).click();
			
			
			
			
			//element = d.findElement(By.id(locatorID));
			break;
			
		case "xpath":
			highlight(d,d.findElement(By.xpath(locatorID)));
			
			d.findElement(By.xpath(locatorID)).click();
			
			break;
		
		case "name":
			highlight(d,d.findElement(By.name(locatorID)));
			d.findElement(By.name(locatorID)).click();
			
			break;
			
		case "cssSelector":
			highlight(d,d.findElement(By.cssSelector(locatorID)));
			d.findElement(By.cssSelector(locatorID)).click();
			
			break;
			
		case "partialLinkText":
			highlight(d,d.findElement(By.partialLinkText(locatorID)));
			d.findElement(By.partialLinkText(locatorID)).click();
			
			break;
			//d.findElement(By.)
			
		case "linkText":
			highlight(d,d.findElement(By.linkText(locatorID)));
			d.findElement(By.linkText(locatorID)).click();
			
			break;
		default:
			System.out.println("Locator Type is invalid");
			break;
			
		}
		
		}catch(Exception e){ 
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			error=e.toString();
			errorline1=error.substring(0, 80);
			errorline2=error.substring(80, 160);
			errorline3=error.substring(160, 240);
			scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			
			
			writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
			Assert.fail();
			}
		
	}
	
public void CreateStudent(WebDriver d) throws Exception{
		d.findElement(By.xpath("//button[@type=\"submit\"]")).click();
		delay(4000);
		boolean f=true;

		while(f){
		if(d.findElement(By.xpath("(//div[contains(@class,\"has-feedback has-error\")])[1]")).isDisplayed() || d.findElement(By.xpath("(//div[contains(@class,\"has-feedback has-error\")])[2]")).isDisplayed() || d.findElement(By.xpath("(//div[contains(@class,\"has-feedback has-error\")])[3]")).isDisplayed() || d.findElement(By.xpath("(//div[contains(@class,\"has-feedback has-error\")])[1]")).isDisplayed())
		{
			d.findElement(By.xpath("//input[@name=\"firstName\"]")).sendKeys("Student One");
			delay(1000);
			d.findElement(By.xpath("//input[@name=\"lastName\"]")).sendKeys("Automation");
			delay(1000);
			d.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys(RuntimeVariables.getVariable("Student1"));
			delay(1000);
			d.findElement(By.xpath("//a[contains(.,\"Generate new random password\")]")).click();
			d.findElement(By.xpath("//button[@type=\"submit\"]")).click();
			delay(4000);
			
		}
		else {
			f=false;
		}
		
		}
		
}

public void EndRecurringJobs(WebDriver d,String locatorID,String locatorType) throws Exception{
	
	
	
		
		try{
		switch (locatorType)
		{
		
		case "id":
			//if(Tests.ActionGlobal.equals("clickToBackMainWindow"))
			highlight(d,d.findElement(By.id(locatorID)));
			d.findElement(By.id(locatorID)).click();
			
			
			
			
			//element = d.findElement(By.id(locatorID));
			break;
			
		case "xpath":
			List<WebElement> list =d.findElements(By.xpath(locatorID));
			
			  for(int i=0;i<list.size();i++){
			        System.out.println(list.get(i).getText());
			        list.get(i).click();
			        delay(2000);
			        AcceptAlert(d);
			        delay(2000);
			    }
			
			
		//	highlight(d,d.findElement(By.xpath(locatorID)));
			
			
			
			break;
		
		case "name":
			highlight(d,d.findElement(By.name(locatorID)));
			d.findElement(By.name(locatorID)).click();
			
			break;
			
		case "cssSelector":
			highlight(d,d.findElement(By.cssSelector(locatorID)));
			d.findElement(By.cssSelector(locatorID)).click();
			
			break;
			
		case "partialLinkText":
			highlight(d,d.findElement(By.partialLinkText(locatorID)));
			d.findElement(By.partialLinkText(locatorID)).click();
			
			break;
			//d.findElement(By.)
			
		case "linkText":
			highlight(d,d.findElement(By.linkText(locatorID)));
			d.findElement(By.linkText(locatorID)).click();
			
			break;
		default:
			System.out.println("Locator Type is invalid");
			break;
			
		}
		
		}catch(Exception e){ 
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			error=e.toString();
			errorline1=error.substring(0, 80);
			errorline2=error.substring(80, 160);
			errorline3=error.substring(160, 240);
			scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			
			
			writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
			Assert.fail();
			}
		
	}
	
	public void dblClick(WebDriver d,String locatorID,String locatorType) throws Exception{
	Actions dclick;
		
		try{
		switch (locatorType)
		{
		
		case "id":
			d.findElement(By.id(locatorID)).click();
			d.findElement(By.id(locatorID)).click();
			
			break;
			
		case "xpath":
	
			d.findElement(By.xpath(locatorID)).click();
			d.findElement(By.xpath(locatorID)).click();
			
			break;
		
		case "name":
			d.findElement(By.name(locatorID)).click();
			d.findElement(By.name(locatorID)).click();
			
			break;
			
		case "cssSelector":
			d.findElement(By.cssSelector(locatorID)).click();
			d.findElement(By.cssSelector(locatorID)).click();
			
			break;
			
		case "partialLinkText":
			d.findElement(By.partialLinkText(locatorID)).click();
			d.findElement(By.partialLinkText(locatorID)).click();
			
			break;
			//d.findElement(By.)
			
		case "linkText":
			d.findElement(By.linkText(locatorID)).click();
			d.findElement(By.linkText(locatorID)).click();
			
			break;
		default:
			System.out.println("Locator Type is invalid");
			break;
			
		}
		
		}catch(Exception e){ 
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			error=e.toString();
			errorline1=error.substring(0, 80);
			errorline2=error.substring(80, 160);
			errorline3=error.substring(160, 240);
			scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			
			
			writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
			Assert.fail();
			}
		
	}
	
	
	public void RightClick(WebDriver d,String locatorID,String locatorType) throws Exception{
	Actions rightclick= new Actions(d);
		
		try{
		switch (locatorType)
		{
		
		case "id":
			d.findElement(By.id(locatorID)).click();
			d.findElement(By.id(locatorID)).click();
			
			break;
			
		case "xpath":
		
	
			WebElement e = d.findElement(By.xpath(locatorID));
			 //	WebDriverWait wait = new WebDriverWait(d, 5); 
			 	Action mouseOverHome = rightclick.moveToElement(e).contextClick().build();
			 	
			
			 	mouseOverHome.perform(); 
			 	
			 	
			 	
			   
			 	
			 	
			break;
		
		case "name":
			d.findElement(By.name(locatorID)).click();
			d.findElement(By.name(locatorID)).click();
			
			break;
			
		case "cssSelector":
			d.findElement(By.cssSelector(locatorID)).click();
			d.findElement(By.cssSelector(locatorID)).click();
			
			break;
			
		case "partialLinkText":
			d.findElement(By.partialLinkText(locatorID)).click();
			d.findElement(By.partialLinkText(locatorID)).click();
			
			break;
			//d.findElement(By.)
			
		case "linkText":
			d.findElement(By.linkText(locatorID)).click();
			d.findElement(By.linkText(locatorID)).click();
			
			break;
		default:
			System.out.println("Locator Type is invalid");
			break;
			
		}
		
		}catch(Exception e){ 
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			error=e.toString();
			errorline1=error.substring(0, 80);
			errorline2=error.substring(80, 160);
			errorline3=error.substring(160, 240);
			scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			
			
			writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
			Assert.fail();
			}
		
	}
		
	
	
	
	
	public void ClickSkipIfNoElement(WebDriver d,String locatorID,String locatorType) throws Exception{
	
		
		try{
		switch (locatorType)
		{
		
		case "id":
			//if(Tests.ActionGlobal.equals("clickToBackMainWindow"))
			highlight(d,d.findElement(By.id(locatorID)));
			d.findElement(By.id(locatorID)).click();
			
			
			
			
			//element = d.findElement(By.id(locatorID));
			break;
			
		case "xpath":
			highlight(d,d.findElement(By.xpath(locatorID)));
			
			d.findElement(By.xpath(locatorID)).click();
			
			break;
		
		case "name":
			highlight(d,d.findElement(By.name(locatorID)));
			d.findElement(By.name(locatorID)).click();
			
			break;
			
		case "cssSelector":
			highlight(d,d.findElement(By.cssSelector(locatorID)));
			d.findElement(By.cssSelector(locatorID)).click();
			
			break;
			
		case "partialLinkText":
			highlight(d,d.findElement(By.partialLinkText(locatorID)));
			d.findElement(By.partialLinkText(locatorID)).click();
			
			break;
			//d.findElement(By.)
			
		case "linkText":
			highlight(d,d.findElement(By.linkText(locatorID)));
			d.findElement(By.linkText(locatorID)).click();
			
			break;
		default:
			System.out.println("Locator Type is invalid");
			break;
			
		}
		
		}catch(Exception e){ 
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			System.out.println("Element not clickable.. skipping to next step");
			}
		
	}


	public boolean ClickSkipIfNoElementReturnBoolean(WebDriver d,String locatorID,String locatorType) throws Exception{
	
	
	try{
	switch (locatorType)
	{
	
	case "id":
		//if(Tests.ActionGlobal.equals("clickToBackMainWindow"))
		highlight(d,d.findElement(By.id(locatorID)));
		d.findElement(By.id(locatorID)).click();
		
		
		
		
		//element = d.findElement(By.id(locatorID));
		break;
		
	case "xpath":
		highlight(d,d.findElement(By.xpath(locatorID)));
		
		d.findElement(By.xpath(locatorID)).click();
		
		break;
	
	case "name":
		highlight(d,d.findElement(By.name(locatorID)));
		d.findElement(By.name(locatorID)).click();
		
		break;
		
	case "cssSelector":
		highlight(d,d.findElement(By.cssSelector(locatorID)));
		d.findElement(By.cssSelector(locatorID)).click();
		
		break;
		
	case "partialLinkText":
		highlight(d,d.findElement(By.partialLinkText(locatorID)));
		d.findElement(By.partialLinkText(locatorID)).click();
		
		break;
		//d.findElement(By.)
		
	case "linkText":
		highlight(d,d.findElement(By.linkText(locatorID)));
		d.findElement(By.linkText(locatorID)).click();
		
		break;
	default:
		System.out.println("Locator Type is invalid");
		break;
		
	}
	
	return true;
	
	}catch(Exception e){ 
		//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
		System.out.println("Element not clickable.. skipping to next step");
		return false;
		}
	
}
	
	public void ClickonElementLocation(WebDriver d,String locatorID,String locatorType, String coordinates) throws Exception{
	
		String [] s = StringParser(coordinates);
    	int x = Integer.parseInt(s[0]);
    	int y = Integer.parseInt(s[1]);
    	Actions clicker = new Actions(d);
    	
		try{
		switch (locatorType)
		{
		
		case "id":
			highlight(d,d.findElement(By.id(locatorID)));
			
			

			clicker.moveToElement(d.findElement(By.id(locatorID))).moveByOffset(x, y).click().build().perform();
		//	d.findElement(By.id(locatorID)).click();
			
			
			
			
			element = d.findElement(By.id(locatorID));
			break;
			
		case "xpath":
			highlight(d,d.findElement(By.xpath(locatorID)));
			
			//d.findElement(By.xpath(locatorID)).click();
			
			

			clicker.moveToElement(d.findElement(By.xpath(locatorID))).moveByOffset(x, y).click().perform();
			
			break;
		
		case "name":
			highlight(d,d.findElement(By.name(locatorID)));
			d.findElement(By.name(locatorID)).click();
			
			break;
			
		case "cssSelector":
			highlight(d,d.findElement(By.cssSelector(locatorID)));
			d.findElement(By.cssSelector(locatorID)).click();
			
			break;
			
		case "partialLinkText":
			highlight(d,d.findElement(By.partialLinkText(locatorID)));
			d.findElement(By.partialLinkText(locatorID)).click();
			
			break;
			//d.findElement(By.)
			
		case "linkText":
			highlight(d,d.findElement(By.linkText(locatorID)));
			d.findElement(By.linkText(locatorID)).click();
			
			break;
		default:
			System.out.println("Locator Type is invalid");
			break;
			
		}
		
		}catch(Exception e){ 
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			error=e.toString();
			errorline1=error.substring(0, 80);
			errorline2=error.substring(80, 160);
			errorline3=error.substring(160, 240);
			scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
			
			Assert.fail();
			}
		
	}
	
	public void SendKeys(WebDriver d,String locatorID, String key, String locatorType) throws Exception
	{
		
		try{
		
		switch (locatorType)
		{
		
		case "id":
			d.findElement(By.id(locatorID)).sendKeys(key);
			
			highlight(d,d.findElement(By.id(locatorID)));
			break;
			
		case "xpath":
			d.findElement(By.xpath(locatorID)).sendKeys(key);
			highlight(d,d.findElement(By.xpath(locatorID)));
			break;
		
		case "name":
			d.findElement(By.name(locatorID)).sendKeys(key);
			highlight(d,d.findElement(By.name(locatorID)));
			break;
			
		case "cssSelector":
			d.findElement(By.cssSelector(locatorID)).sendKeys(key);
			highlight(d,d.findElement(By.cssSelector(locatorID)));
			break;
			
	
			
		default:
			System.out.println("Locator Type is invalid");
			break;
			
		}
		
		} catch(Exception e){
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			error=e.toString();
			errorline1=error.substring(0, 80);
			errorline2=error.substring(80, 160);
			errorline3=error.substring(160, 240);
			scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
			
			Assert.fail();
			
				}
		
		
	}
	
	public void PressEnter(WebDriver d,String locatorID, String locatorType) throws Exception
	{
		
		try{
		
		switch (locatorType)
		{
		
		case "id":
			d.findElement(By.id(locatorID)).sendKeys(Keys.RETURN);
			
			highlight(d,d.findElement(By.id(locatorID)));
			break;
			
		case "xpath":
			d.findElement(By.xpath(locatorID)).sendKeys(Keys.RETURN);
			//highlight(d,d.findElement(By.xpath(locatorID)));
			break;
		
		case "name":
			d.findElement(By.name(locatorID)).sendKeys(Keys.RETURN);
			highlight(d,d.findElement(By.name(locatorID)));
			break;
			
		case "cssSelector":
			d.findElement(By.cssSelector(locatorID)).sendKeys(Keys.RETURN);
			highlight(d,d.findElement(By.cssSelector(locatorID)));
			break;
			
	
			
		default:
			System.out.println("Locator Type is invalid");
			break;
			
		}
		
		} catch(Exception e){
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			error=e.toString();
			errorline1=error.substring(0, 80);
			errorline2=error.substring(80, 160);
			errorline3=error.substring(160, 240);
			scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
			
			Assert.fail();
			
				}
		
		
	}
	
	
	public void PressTab(WebDriver d,String locatorID, String locatorType) throws Exception
	{
		
		try{
		
		switch (locatorType)
		{
		
		case "id":
			d.findElement(By.id(locatorID)).sendKeys(Keys.TAB);
			
			highlight(d,d.findElement(By.id(locatorID)));
			break;
			
		case "xpath":
			d.findElement(By.xpath(locatorID)).sendKeys(Keys.TAB);
			highlight(d,d.findElement(By.xpath(locatorID)));
			break;
		
		case "name":
			d.findElement(By.name(locatorID)).sendKeys(Keys.TAB);
			highlight(d,d.findElement(By.name(locatorID)));
			break;
			
		case "cssSelector":
			d.findElement(By.cssSelector(locatorID)).sendKeys(Keys.TAB);
			highlight(d,d.findElement(By.cssSelector(locatorID)));
			break;
			
	
			
		default:
			System.out.println("Locator Type is invalid");
			break;
			
		}
		
		} catch(Exception e){
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			error=e.toString();
			errorline1=error.substring(0, 80);
			errorline2=error.substring(80, 160);
			errorline3=error.substring(160, 240);
			scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
			
			Assert.fail();
			
				}
		
		
	}
	
	public void clearElement(WebDriver d,String locatorID, String locatorType) throws Exception
	{
		try{
		
		
		switch (locatorType)
		{
		
		case "id":
			d.findElement(By.id(locatorID)).clear();
			highlight(d,d.findElement(By.id(locatorID)));
			break;
			
		case "xpath":
			d.findElement(By.xpath(locatorID)).clear();
			highlight(d,d.findElement(By.xpath(locatorID)));
			break;
		
		case "name":
			d.findElement(By.name(locatorID)).clear();
			highlight(d,d.findElement(By.name(locatorID)));
			break;
			
	
			
		default:
			System.out.println("Locator Type is invalid");
			break;
			
		}
	} catch(Exception e){
		//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
		error=e.toString();
		errorline1=error.substring(0, 80);
		errorline2=error.substring(80, 160);
		errorline3=error.substring(160, 240);
		scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
		writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
		writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
		
		Assert.fail();
		
			}
		
	}
	
	public void CheckExistence(WebDriver d,String locatorID, String locatorType)throws Exception{
		isEqual=false;
		try{
		switch (locatorType)
		{
		
		case "xpath":
			
			highlight(d,d.findElement(By.xpath(locatorID)));
			isEqual=d.findElement(By.xpath(locatorID)).isDisplayed();
			//Assert.assertTrue(isEqual, "Element is visible");
			
			
		break;
		
		case "id":
			
				highlight(d,d.findElement(By.id(locatorID)));
				isEqual=d.findElement(By.id(locatorID)).isDisplayed();
				//Assert.assertTrue(isEqual, "Element is visible");
				
			
		break;
		
		case "name":
			
				highlight(d,d.findElement(By.name(locatorID)));
				isEqual=d.findElement(By.name(locatorID)).isDisplayed();
				//Assert.assertTrue(isEqual, "Element is visible");
				
				
		break;
		
		case "cssSelector":
			
				highlight(d,d.findElement(By.cssSelector(locatorID)));
				isEqual=d.findElement(By.cssSelector(locatorID)).isDisplayed();
				//Assert.assertTrue(isEqual, "Element is visible");
				
		break;
		
	
		case "linkText":
			
				highlight(d,d.findElement(By.linkText(locatorID)));
				isEqual=d.findElement(By.linkText(locatorID)).isDisplayed();
			//	Assert.assertTrue(isEqual, "Element is visible");
			
		break;
		

		
		case "partialLinkText":
			
				highlight(d,d.findElement(By.partialLinkText(locatorID)));
				isEqual=d.findElement(By.partialLinkText(locatorID)).isDisplayed();
			//	Assert.assertTrue(isEqual, "Element is visible");
			
		break;
		
		default:
			System.out.println("Locator Type is invalid");
		break;
		
		}
		
		
		}catch(Exception e){
			error=e.toString();
			errorline1=error.substring(0, 80);
			errorline2=error.substring(80, 160);
			errorline3=error.substring(160, 240);
			scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
			
			Assert.fail();
			
			isEqual=false;}
		
		
		
		
		
	}
	
	
	public void Select(WebDriver d,String locatorID,String locatorType, String selected) throws Exception{
	
		Select item;
		try{
		switch (locatorType)
		{
		
		 case "id":
			 	highlight(d,d.findElement(By.id(locatorID)));
			 	item = new Select(d.findElement(By.id(locatorID)));
			 	item.selectByVisibleText(selected);
			 	
			 	break;
			
		 case "xpath":
			 	highlight(d,d.findElement(By.xpath(locatorID)));
				item = new Select(d.findElement(By.xpath(locatorID)));
				item.selectByVisibleText(selected);
				//item.selectByValue(selected);
				break;
			
		 case "cssSelector":
			 	highlight(d,d.findElement(By.cssSelector(locatorID)));
				item = new Select(d.findElement(By.cssSelector(locatorID)));
				item.selectByVisibleText(selected);
				break;
		
		 case "name":
			 highlight(d,d.findElement(By.name(locatorID)));
				item = new Select(d.findElement(By.name(locatorID)));
				item.selectByVisibleText(selected);
				break;
				
		 case "linkText":
			 	highlight(d,d.findElement(By.linkText(locatorID)));
				item = new Select(d.findElement(By.linkText(locatorID)));
				item.selectByVisibleText(selected);
				break;
		
		 case "partialLinkText":
			 	highlight(d,d.findElement(By.partialLinkText(locatorID)));
				item = new Select(d.findElement(By.partialLinkText(locatorID)));
				item.selectByVisibleText(selected);
				break;
			
		default:
			System.out.println("Locator Type is invalid");
			break;
			
		}
		
		} catch(Exception e){
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			error=e.toString();
			errorline1=error.substring(0, 80);
			errorline2=error.substring(80, 160);
			errorline3=error.substring(160, 240);
			scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
			
			Assert.fail();
			
				}
		
	}
	
	public void SelectByIndex(WebDriver d,String locatorID,String locatorType, int index) throws Exception{
		
		Select item;
		try{
		switch (locatorType)
		{
		
		 case "id":
			 	highlight(d,d.findElement(By.id(locatorID)));
			 	item = new Select(d.findElement(By.id(locatorID)));
			 	//item.selectByVisibleText(selected);
			 	//item.selectByValue(selected);
			 	item.selectByIndex(index);
			 	
			 	break;
			
		 case "xpath":
			 	highlight(d,d.findElement(By.xpath(locatorID)));
				item = new Select(d.findElement(By.xpath(locatorID)));
				//item.selectByVisibleText(selected);
			//	item.selectByValue(selected);
				item.selectByIndex(index);
				break;
			
		 case "cssSelector":
			 	highlight(d,d.findElement(By.cssSelector(locatorID)));
				item = new Select(d.findElement(By.cssSelector(locatorID)));
				//item.selectByVisibleText(selected);
			//	item.selectByValue(selected);
				item.selectByIndex(index);
				break;
		
		 case "name":
			 highlight(d,d.findElement(By.name(locatorID)));
				item = new Select(d.findElement(By.name(locatorID)));
				//item.selectByVisibleText(selected);
			//	item.selectByValue(selected);
				item.selectByIndex(index);
				break;
				
		 case "linkText":
			 	highlight(d,d.findElement(By.linkText(locatorID)));
				item = new Select(d.findElement(By.linkText(locatorID)));
				//item.selectByVisibleText(selected);
		//		item.selectByValue(selected);
				item.selectByIndex(index);
				break;
		
		 case "partialLinkText":
			 	highlight(d,d.findElement(By.partialLinkText(locatorID)));
				item = new Select(d.findElement(By.partialLinkText(locatorID)));
				//item.selectByVisibleText(selected);
		//		item.selectByValue(selected);
				item.selectByIndex(index);
				break;
			
		default:
			System.out.println("Locator Type is invalid");
			break;
			
		}
		
		} catch(Exception e){
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			error=e.toString();
			errorline1=error.substring(0, 80);
			errorline2=error.substring(80, 160);
			errorline3=error.substring(160, 240);
			scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
			
			Assert.fail();
			
				}
		
	}
	

	public void MouseHover(WebDriver d,String locatorID,String locatorType) throws Exception{
	
	Actions builder= new Actions(d);
	
	
	try{
	switch (locatorType)
	{
	
	 case "id":
		 builder = new Actions(d);
		builder.moveToElement(d.findElement(By.id(locatorID))).perform();
		break;
		
	
	
	 case "name":
		builder = new Actions(d);
		builder.moveToElement(d.findElement(By.name(locatorID))).perform();
		break;
	
	
	 case "xpath":
		 	Robot robot = new Robot();
		    robot.mouseMove(0, 1080);
		 	builder = new Actions(d);
		 	WebElement e = d.findElement(By.xpath(locatorID));
		 //	WebDriverWait wait = new WebDriverWait(d, 5); 
		 	Action mouseOverHome = builder.moveToElement(e).build();
		 	
		 	mouseOverHome.perform(); 
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorID)));  

		 	//builder.moveToElement(d.findElement(By.xpath(locatorID))).perform();
			
		 //mouseHoverJScript(d,d.findElement(By.xpath(locatorID)));
		 
		// mouseHoverJScript(d,e);
		 break;
		
		
			
	 case "cssSelector":
		 builder = new Actions(d);
		 builder.moveToElement(d.findElement(By.cssSelector(locatorID))).perform();
			
			break;
			
	 case "partialLinkText":
		 builder = new Actions(d);
		 builder.moveToElement(d.findElement(By.partialLinkText(locatorID))).perform();
			
			break;
		
			
     case "linkText":
    	 builder = new Actions(d);
 		 builder.moveToElement(d.findElement(By.linkText(locatorID))).perform();
			
			break;
		
	default:
		
		break;
		
	}
	
	} catch(Exception e){
		//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
		error=e.toString();
		errorline1=error.substring(0, 150);
		errorline2=error.substring(150, 300);
		errorline3=error.substring(300, 450);
		scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
		writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
		writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
		
		Assert.fail();
		
			}
	//delay(2500);
	
}
	
	public static void mouseHoverJScript(WebDriver d, WebElement HoverElement) {
        try {
            if (isElementPresent(HoverElement)) {

                String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
                ((JavascriptExecutor) d).executeScript(mouseOverScript,HoverElement);

        } else {
            System.out.println("Element was not visible to hover " + "\n");

        }
    } catch (StaleElementReferenceException e) {
        System.out.println("Element with " + HoverElement
                + "is not attached to the page document"
                + e.getStackTrace());
    } catch (NoSuchElementException e) {
        System.out.println("Element " + HoverElement + " was not found in DOM"
                + e.getStackTrace());
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error occurred while hovering"
                + e.getStackTrace());
    }
}

	public static boolean isElementPresent(WebElement element) {
    boolean flag = false;
    try {
        if (element.isDisplayed()
                || element.isEnabled())
            flag = true;
    } catch (NoSuchElementException e) {
        flag = false;
    } catch (StaleElementReferenceException e) {
        flag = false;
    }
    return flag;
}
	
	public void MouseHoverThenClick(WebDriver d,String locatorID,String locatorType) throws Exception{
		
		String [] locators = StringLocatorParser(locatorID);
		String locator1 = locators[0];
		String locator2 = locators[1];
		
		
		
		Actions builder;
		
		
		try{
		switch (locatorType)
		{
		
		 case "id":
			 builder = new Actions(d);
			builder.moveToElement(d.findElement(By.id(locatorID))).perform();
			break;
			
		
		
		 case "name":
			builder = new Actions(d);
			builder.moveToElement(d.findElement(By.name(locatorID))).perform();
			break;
		
		
		 case "xpath":
			 builder = new Actions(d);
			// builder.moveToElement(d.findElement(By.xpath(locatorID))).perform();
			 builder.clickAndHold((d.findElement(By.xpath(locator1)))).moveToElement(d.findElement(By.xpath(locator2))).click().build().perform();
	       //  delay(5000);
			// d.findElement(By.xpath("//div[contains(.,'Remove Bookmark')]")).click();
			 break;
			
			
				
		 case "cssSelector":
			 builder = new Actions(d);
			 builder.moveToElement(d.findElement(By.cssSelector(locatorID))).perform();
				
				break;
				
		 case "partialLinkText":
			 builder = new Actions(d);
			 builder.moveToElement(d.findElement(By.partialLinkText(locatorID))).perform();
				
				break;
			
				
	     case "linkText":
	    	 builder = new Actions(d);
	 		 builder.moveToElement(d.findElement(By.linkText(locatorID))).perform();
				
				break;
			
		default:
			
			break;
			
		}
		
		} catch(Exception e){
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			error=e.toString();
			errorline1=error.substring(0, 80);
			errorline2=error.substring(80, 160);
			errorline3=error.substring(160, 240);
			scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
			
			Assert.fail();
			
				}
		//delay(2500);
		
	}	
	
	public String[] StringLocatorParser(String s){
	String [] arr = s.split("LOCATOR");
	return arr;
	
}
	
	
	public static void delay(int ms){
		
		try {
		    Thread.sleep(ms);                 
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		
	}
	
	
	public void scshots(WebDriver d, String fname) throws IOException{
		
		if(getVariableFromProperties("browser").equals("iOS-Safari")){ 
			File scrFile = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
			org.apache.commons.io.FileUtils.copyFile(scrFile, new File("shots/"+fname+".png"));
			screenshot= "shots/"+fname+".png";
			
		}else{
		//File scrFile = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
		//org.apache.commons.io.FileUtils.copyFile(scrFile, new File("shots\\"+fname+".png"));
		//screenshot= "shots\\"+fname+".png";
			
			if(getVariableFromProperties("TypeOfRun").equals("officialrun")){ 
				File scrFile = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
				org.apache.commons.io.FileUtils.copyFile(scrFile, new File(getVariableFromProperties("Dropbox")+"shots\\"+fname+".png"));
				screenshot= getVariableFromProperties("Dropbox")+"shots\\"+fname+".png";	
				
			}
			else{
				
				File scrFile = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
				screenshot= "ReportLogs\\shots\\"+fname+".png";		
					if(CommonMethods.getVariableFromProperties("MacOSX").equals("Y")) {
						screenshot=screenshot.replace("\\", "//");
						
					}
					
					org.apache.commons.io.FileUtils.copyFile(scrFile, new File(screenshot));
			
		
			
			}
		
		}
	}
	
	public void scshots_Issue(WebDriver d, String fname) throws IOException{
		
		String errorArray[] = new String[3];
		errorArray[0]=errorline1;
		errorArray[1]=errorline2;
		errorArray[2]=errorline3;
		int y_position=100;
		
		
		if(getVariableFromProperties("browser").equals("iOS-Safari")){ 
			File scrFile = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
			System.out.println("taking a screenshot schsots_issue");
			org.apache.commons.io.FileUtils.copyFile(scrFile, new File("shots/"+fname+".png"));
			screenshot= "shots/"+fname+".png";
			
		}else
		{ 
			try{
			//File scrFile = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
			//org.apache.commons.io.FileUtils.copyFile(scrFile, new File("shots\\"+fname+".png"));
			//screenshot= "shots\\"+fname+".png";
			if(getVariableFromProperties("TypeOfRun").equals("officialrun")){ 
				File scrFile = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
				org.apache.commons.io.FileUtils.copyFile(scrFile, new File(getVariableFromProperties("Dropbox")+"shots\\"+fname+".png"));
				screenshot= getVariableFromProperties("Dropbox")+"shots\\"+fname+".png";	
				
			}
			else{
			
			
			
			
			
			File scrFile = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
			screenshot= "ReportLogs\\shots\\"+fname+".png";		
				if(CommonMethods.getVariableFromProperties("MacOSX").equals("Y")) {
					screenshot=screenshot.replace("\\", "//");
					
				}
				
				org.apache.commons.io.FileUtils.copyFile(scrFile, new File(screenshot));
			
			}
			}catch(Exception e) {scshotsAlert(fname);}
		
		}
		
		for(int c=0;c<3;c++ ){
		final BufferedImage image = ImageIO.read(new File(screenshot)); 

			    Graphics g = image.getGraphics();
			    //g.setFont(g.getFont().deriveFont(30f));
			    g.setFont(new Font("TimesRoman", Font.BOLD, 30));
			    g.setColor(Color.red);
			    g.drawString(errorArray[c], 100, y_position);
			  //  g.drawString(error, 100, y_position);
			    g.dispose();

			    try{
				    ImageIO.write(image, "png", new File(screenshot));
				    }catch(Exception e){ 
				    	
				    	try {
							writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", "UNKNOWN ERROR IN SAVING SCREENSHOT");
						} catch (Exception e1) {
							
							e1.printStackTrace();
						}
				    	Assert.fail();
				    	}
			    
			    y_position=y_position +30;
			    
		}
		//screenshot= "shots\\"+fname+".png";
			}
		
	
	public void scshots_validationIssue(WebDriver d, String fname, String errorString) throws IOException, Exception{
		
		String errorArray[] = new String[2];
		
		try{
		errorArray[0]=errorString.substring(0, 80);
		
		if(errorString.length() > 79)
			{
			
			errorArray[1]=errorString.substring(80, 160);
			}
		//errorArray[2]=errorline3;
		
		}catch(Exception e){ }
		
		int y_position=100;
		
		
		if(getVariableFromProperties("browser").equals("iOS-Safari")){ 
			File scrFile = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
			org.apache.commons.io.FileUtils.copyFile(scrFile, new File("shots/"+fname+".png"));
			screenshot= "shots/"+fname+".png";
				
		}
		else{
		
		//File scrFile = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
		//org.apache.commons.io.FileUtils.copyFile(scrFile, new File("shots\\"+fname+".png"));
		//screenshot= "shots\\"+fname+".png";
		
			if(getVariableFromProperties("TypeOfRun").equals("officialrun")){ 
				File scrFile = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
				org.apache.commons.io.FileUtils.copyFile(scrFile, new File(getVariableFromProperties("Dropbox")+"shots\\"+fname+".png"));
				screenshot= getVariableFromProperties("Dropbox")+"shots\\"+fname+".png";	
				
			}
			else{
			
			
				File scrFile = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
				screenshot= "ReportLogs\\shots\\"+fname+".png";		
					if(CommonMethods.getVariableFromProperties("MacOSX").equals("Y")) {
						screenshot=screenshot.replace("\\", "//");
						
					}
					
					org.apache.commons.io.FileUtils.copyFile(scrFile, new File(screenshot));
			
			}
		}
		
		for(int c=0;c<2;c++ ){
		final BufferedImage image = ImageIO.read(new File(screenshot)); 

			    Graphics g = image.getGraphics();
			    //g.setFont(g.getFont().deriveFont(30f));
			    g.setFont(new Font("TimesRoman", Font.BOLD, 30));
			    g.setColor(Color.red);
			    g.drawString(errorArray[c], 100, y_position);
			     
			    g.dispose();

			    try{
				    ImageIO.write(image, "png", new File(screenshot)); } catch(Exception e){ 
				    	
				    	writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", "UNKNOWN ERROR IN SAVING SCREENSHOT");
				    	Assert.fail();
				    }
			    
			    y_position=y_position +30;
			    
		}
		//screenshot= "shots\\"+fname+".png";
			}
	
	
	public void scshotsAlert(String fname) throws IOException{
		
	BufferedImage image = null;
	try {
		image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
	} catch (HeadlessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (AWTException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    //ImageIO.write(image, "png", new File("shots\\"+fname+".png"));
    //screenshot= "shots\\"+fname+".png";
	if(getVariableFromProperties("TypeOfRun").equals("officialrun")){ 
		 ImageIO.write(image, "png", new File(getVariableFromProperties("Dropbox")+"shots\\"+fname+".png"));
		    screenshot= getVariableFromProperties("Dropbox")+"shots\\"+fname+".png";
		
	}
	else{
	
   
   screenshot= "ReportLogs\\shots\\"+fname+".png";
  
		
		if(CommonMethods.getVariableFromProperties("MacOSX").equals("Y")) {
			screenshot=screenshot.replace("\\", "//");
			
		}
		
		ImageIO.write(image, "png", new File(screenshot));
   
	}
	}


	
	public static String returnDate(){
		   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HHmmss");
		   Date date = new Date();
		   return dateFormat.format(date).replace(" ", "_");
		
	}
	
	public static String returnDate2(){
		   DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
		   Date date = new Date();
		   return dateFormat.format(date);
		
	}
	
	public static String returnDate3(){
		   DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
		   Date date = new Date();
		   return dateFormat.format(date);
		
	}
	
	public static String returnDateDefaultFormat(){
		   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   Date date = new Date();
		   return dateFormat.format(date);
		
	}
	
	public static String returnDateVariableFormat(String f){
		   DateFormat dateFormat = new SimpleDateFormat(f);
		   Date date = new Date();
		   return dateFormat.format(date);
		
	}
	
	
	public static String getFirstDayNextMonth() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
	    int lastDate = calendar.getActualMinimum(Calendar.DATE);

	    calendar.set(Calendar.DATE, lastDate);
	    int lastDay = calendar.get(Calendar.DAY_OF_WEEK);

		
		
		return dateFormat.format(calendar.getTime());
		
		
		
	}
	
	public static String getLastDayNextMonth() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
	    int lastDate = calendar.getActualMaximum(Calendar.DATE);

	    calendar.set(Calendar.DATE, lastDate);
	    int lastDay = calendar.get(Calendar.DAY_OF_WEEK);

	    

	    
		
		
	    return dateFormat.format(calendar.getTime());
		
		
		
	}
		
	public static String returnDateddMMYY(){
		 	Calendar cal = Calendar.getInstance();
		
		
		   DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		   //dateFormat.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
		   Date date = new Date();
		
		   
		   
		    cal.setTime(date);
		    cal.add(Calendar.DATE, 1);
		     
		   
		   return dateFormat.format(cal.getTime());
		//   return dateFormat.format(date);
		
	}
	
	public static String returnTodayDateddMMYY(){
	 	Calendar cal = Calendar.getInstance();
	
	
	   DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	   //dateFormat.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
	   Date date = new Date();
	
	   
	   
	    cal.setTime(date);
	
	     
	   
	   return dateFormat.format(cal.getTime());
	//   return dateFormat.format(date);
	
}
	
	public void scshotsRegion(WebDriver d, WebElement e, String fname) throws IOException{
		
		
		
            element=e;
	        File screen = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);

	        Point p = e.getLocation();

	        int width = e.getSize().getWidth();
	        int height = e.getSize().getHeight();

	        BufferedImage img = ImageIO.read(screen);

	        BufferedImage dest = img.getSubimage(p.getX(), p.getY(), width,   
	                                 height);

	        ImageIO.write(dest, "png", screen);

	        File f = new File("C:\\shots\\"+fname);

	        FileUtils.copyFile(screen, f);

	    

	}
	
	public void highlight(WebDriver driver, WebElement element)

	{ 
		
   
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String oldStyle = element.getAttribute("style");
	 
		String args = "arguments[0].setAttribute('style', arguments[1]);";
		js.executeScript(args, element,
			"border: 4px solid yellow;display:block;");
	 
		
		 try {
	            Thread.sleep(500);
	        }  catch (InterruptedException e) {
	        }
		js.executeScript(args, element, oldStyle);

	}
	
	public void scshotshighlight(WebDriver d, String fname, String locatorID, String locatorType) throws IOException{
		
		WebElement e = null;
		
		switch (locatorType)
		{
		
		case "id":
			e = d.findElement(By.id(locatorID));		
			break;
			
		case "xpath":
			e = d.findElement(By.xpath(locatorID));
			break;
		
		case "name":
			e=d.findElement(By.name(locatorID));
			break;
			
		case "cssSelector":
			e=d.findElement(By.cssSelector(locatorID));
			break;
			
		case "partialLinkText":
			e=d.findElement(By.partialLinkText(locatorID));
			break;
			
			
		case "linkText":
			e=d.findElement(By.linkText(locatorID));
			
			break;
			
		}	
		
		JavascriptExecutor js = (JavascriptExecutor) d;
		String oldStyle = e.getAttribute("style");
	 
		String args = "arguments[0].setAttribute('style', arguments[1]);";
		js.executeScript(args, e,
			"border: 4px solid red;display:block;");
	 
		
		//File scrFile = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
		//org.apache.commons.io.FileUtils.copyFile(scrFile, new File("shots\\"+fname+".png"));
		if(getVariableFromProperties("TypeOfRun").equals("officialrun")){ 
			
		    
		    File scrFile = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
			org.apache.commons.io.FileUtils.copyFile(scrFile, new File(getVariableFromProperties("Dropbox")+"shots\\"+fname+".png"));
			screenshot= getVariableFromProperties("Dropbox")+"shots\\"+fname+".png";
		
		}
		else{
	
	
			File scrFile = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
			screenshot= "ReportLogs\\shots\\"+fname+".png";		
				if(CommonMethods.getVariableFromProperties("MacOSX").equals("Y")) {
					screenshot=screenshot.replace("\\", "//");
					
				}
				
				org.apache.commons.io.FileUtils.copyFile(scrFile, new File(screenshot));
 
	}
		js.executeScript(args, e, oldStyle);
		
		
	//	screenshot= "shots\\"+fname+".png";
       

    

}
	
	
	
	public Object returnElement(){
		return element;
		
	}
	
	public String getCell(int r, int c) throws IOException{
		FileInputStream fsIP= new FileInputStream(new File("Tests.xlsx")); //Read the spreadsheet that needs to be updated
        
        XSSFWorkbook wb = new XSSFWorkbook(fsIP); //Access the workbook
          
        XSSFSheet worksheet = wb.getSheetAt(0); //Access the worksheet, so that we can update / modify it.
          
        Cell cell = null; // declare a Cell object
        
      //  int newRow = worksheet.getLastRowNum();
     
     //   Row row = worksheet.createRow(newRow);
        
        Row row = null;
        row = worksheet.getRow(r-1);
		cell = row.getCell(c-1);
		
		fsIP.close();
        
		String s = cell.getStringCellValue();
		return s;
	}
	
	
	public String returnString(WebDriver d,String locatorID,String locatorType) throws Exception{
		String element = null;
		
		try{
		switch (locatorType)
		{
		
		case "id":
			highlight(d,d.findElement(By.id(locatorID)));
			element = d.findElement(By.id(locatorID)).getText();
			
			break;
			
		case "xpath":
			highlight(d,d.findElement(By.xpath(locatorID)));
			element = d.findElement(By.xpath(locatorID)).getText();
			
			break;
		
		case "name":
			highlight(d,d.findElement(By.name(locatorID)));
			element = d.findElement(By.name(locatorID)).getText();
			
			break;
			
		case "cssSelector":
			highlight(d,d.findElement(By.cssSelector(locatorID)));
			element = d.findElement(By.cssSelector(locatorID)).getText();
			
			break;
			
		case "partialLinkText":
			highlight(d,d.findElement(By.partialLinkText(locatorID)));
			element = d.findElement(By.partialLinkText(locatorID)).getText();
			
			break;
			//d.findElement(By.)
			
		case "linkText":
			highlight(d,d.findElement(By.linkText(locatorID)));
			element = d.findElement(By.linkText(locatorID)).getText();
			
			break;
		default:
			
			break;
			
		}
		} catch(Exception e){
			error=e.toString();
			errorline1=error.substring(0, 80);
			errorline2=error.substring(80, 160);
			errorline3=error.substring(160, 240);
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			
			writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
			Assert.fail();
			
				}
		
		return element;
	}
	
	
	//@SuppressWarnings("null")
	public boolean isSorted(WebDriver d,String locatorID,String locatorType) throws Exception{
		boolean sorted =true;
		int number_list;
		String baseLocator=locatorID;
		List <String> Sortinglist = new ArrayList<String>();
		
		try{
		
		for(number_list=2;number_list<10;number_list++){
			
			locatorID=baseLocator+"//li["+number_list+"]";
			System.out.println(locatorID);
			System.out.println(d.findElement(By.xpath(locatorID)).getText());
			Sortinglist.add(d.findElement(By.xpath(locatorID)).getText());
		}
		
		System.out.println(Sortinglist);
		
		//for(int ix=1; ix<Sortinglist.size();ix++){ if(Sortinglist.get(ix-1).compareTo(Sortinglist.get(ix))>0 ) sorted=false; }
		
		
		//Sortinglist=Arrays.asList("Alfalfa Sprouts", "Aloe Leaves", "Anatto Seed", "Anise", "Apple Cider", "Apple Pears", "Apples", "Apricots", "Arrowhead", "Arrow Root", "Arrugula", "Artichokes", "Asparagus", "Avocado", "Avocados", "Bamboo Shoots", "Banana Flowers", "Bananas", "Basil", "Batatas", "Bay Leaves");
		//Sortinglist=Arrays.asList("Banana Flowers", "Bananas", "Basil", "Batatas", "Bay Leaves");
		for(int ix=0; ix < Sortinglist.size()-1;ix++){
			if(Sortinglist.get(ix).compareToIgnoreCase(Sortinglist.get(ix+1)) > 0)
			{
				sorted=false;
				System.out.println("String : "+ Sortinglist.get(ix) + " comes first before "+Sortinglist.get(ix+1) );
				break;
			} 
			
			
		}
		
		} catch(Exception e){
			error=e.toString();
			
			System.out.println(error);
			//scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			//writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString());
			Assert.fail();
			
				}
		
		return sorted;
	}
	
	
	
	public boolean checkBlankEntry(WebDriver d,String locatorID,String locatorType) throws Exception{
		boolean blankEntry =true;
		int number_list;
		String baseLocator=locatorID;
		List <String> CommodityList = new ArrayList<String>();
		
		try{
		
		for(number_list=2;number_list<321;number_list++){
			
			locatorID=baseLocator+"//li["+number_list+"]";
			System.out.println(locatorID);
			System.out.println(d.findElement(By.xpath(locatorID)).getText());
			CommodityList.add(d.findElement(By.xpath(locatorID)).getText());
		}
		
		System.out.println(CommodityList);
		

		for(int ix=0; ix < CommodityList.size()-1;ix++){
			if(CommodityList.get(ix).equals(""))
			{
				blankEntry=false;
				break;
			} 
			
			
		}
		
		} catch(Exception e){
			error=e.toString();
			
			System.out.println(error);
			//scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString());
			Assert.fail();
			
				}
		
		return blankEntry;
	}
	
	public String returnNumberFromString(WebDriver d,String locatorID,String locatorType) throws Exception{
		String element = null;
		
		try{
		switch (locatorType)
		{
		
		case "id":
			highlight(d,d.findElement(By.id(locatorID)));
			element = d.findElement(By.id(locatorID)).getText();
			
			break;
			
		case "xpath":
			highlight(d,d.findElement(By.xpath(locatorID)));
			element = d.findElement(By.xpath(locatorID)).getText();
			
			break;
		
		case "name":
			highlight(d,d.findElement(By.name(locatorID)));
			element = d.findElement(By.name(locatorID)).getText();
			
			break;
			
		case "cssSelector":
			highlight(d,d.findElement(By.cssSelector(locatorID)));
			element = d.findElement(By.cssSelector(locatorID)).getText();
			
			break;
			
		case "partialLinkText":
			highlight(d,d.findElement(By.partialLinkText(locatorID)));
			element = d.findElement(By.partialLinkText(locatorID)).getText();
			
			break;
			//d.findElement(By.)
			
		case "linkText":
			highlight(d,d.findElement(By.linkText(locatorID)));
			element = d.findElement(By.linkText(locatorID)).getText();
			
			break;
		default:
			
			break;
			
		}
		} catch(Exception e){
			error=e.toString();
			errorline1=error.substring(0, 80);
			errorline2=error.substring(80, 160);
			errorline3=error.substring(160, 240);
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			
			writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
			Assert.fail();
			
				}
		
		String numberOnly= element.replaceAll("[^0-9]", "");
		return numberOnly;
	}
	
	public String returnStringParser(WebDriver d,String locatorID,String locatorType, int line) throws Exception{
		String element = null;
		 String delimeter = "\n";
		
		try{
		switch (locatorType)
		{
		
		case "id":
			highlight(d,d.findElement(By.id(locatorID)));
			element = d.findElement(By.id(locatorID)).getText();
			
			break;
			
		case "xpath":
			highlight(d,d.findElement(By.xpath(locatorID)));
			element = d.findElement(By.xpath(locatorID)).getText();
			
			
			
			
			
			
			
			
			
			
			break;
		
		case "name":
			highlight(d,d.findElement(By.name(locatorID)));
			element = d.findElement(By.name(locatorID)).getText();
			
			break;
			
		case "cssSelector":
			highlight(d,d.findElement(By.cssSelector(locatorID)));
			element = d.findElement(By.cssSelector(locatorID)).getText();
			
			break;
			
		case "partialLinkText":
			highlight(d,d.findElement(By.partialLinkText(locatorID)));
			element = d.findElement(By.partialLinkText(locatorID)).getText();
			
			break;
			//d.findElement(By.)
			
		case "linkText":
			highlight(d,d.findElement(By.linkText(locatorID)));
			element = d.findElement(By.linkText(locatorID)).getText();
			
			break;
		default:
			
			break;
			
		}
		} catch(Exception e){
			error=e.toString();
			errorline1=error.substring(0, 80);
			errorline2=error.substring(80, 160);
			errorline3=error.substring(160, 240);
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			
			writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
			Assert.fail();
			
				}
		
		
		StringTokenizer st = new StringTokenizer(element, delimeter);
		int count = 0;
		
		//System.out.println("NUmber of tokens: "+st.countTokens());
		while (st.hasMoreElements()) {
			
			//System.out.println("StringTokenizer Output: " + st.nextElement());
			if(count==line){element=(String) st.nextElement(); break;}
			st.nextElement();
			count++;
			
		}
		
		
		return element;
	}
	
	public String returnValue(WebDriver d,String locatorID,String locatorType) throws Exception{
		String element = null;
		
		try{
		switch (locatorType)
		{
		
		case "id":
			highlight(d,d.findElement(By.id(locatorID)));
			element = d.findElement(By.id(locatorID)).getAttribute("value");
			
			break;
			
		case "xpath":
			highlight(d,d.findElement(By.xpath(locatorID)));
			element = d.findElement(By.xpath(locatorID)).getAttribute("value");
			
			break;
		
		case "name":
			highlight(d,d.findElement(By.name(locatorID)));
			element = d.findElement(By.name(locatorID)).getAttribute("value");
			
			break;
			
		case "cssSelector":
			highlight(d,d.findElement(By.cssSelector(locatorID)));
			element = d.findElement(By.cssSelector(locatorID)).getAttribute("value");
			
			break;
			
		case "partialLinkText":
			highlight(d,d.findElement(By.partialLinkText(locatorID)));
			element = d.findElement(By.partialLinkText(locatorID)).getAttribute("value");
			
			break;
			//d.findElement(By.)
			
		case "linkText":
			highlight(d,d.findElement(By.linkText(locatorID)));
			element = d.findElement(By.linkText(locatorID)).getAttribute("value");
			
			break;
		default:
			
			break;
			
		}
		} catch(Exception e){
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			error=e.toString();
			errorline1=error.substring(0, 80);
			errorline2=error.substring(80, 160);
			errorline3=error.substring(160, 240);
			scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
			
			Assert.fail();
			
				}
		
		return element;
	}
	
	public String returnAttribute(WebDriver d,String locatorID,String locatorType, String attribute) throws Exception{
		String element = null;
		
		try{
		switch (locatorType)
		{
		
		case "id":
			highlight(d,d.findElement(By.id(locatorID)));
			element = d.findElement(By.id(locatorID)).getAttribute(attribute);
			
			break;
			
		case "xpath":
			highlight(d,d.findElement(By.xpath(locatorID)));
			element = d.findElement(By.xpath(locatorID)).getAttribute(attribute);
			
			break;
		
		case "name":
			highlight(d,d.findElement(By.name(locatorID)));
			element = d.findElement(By.name(locatorID)).getAttribute(attribute);
			
			break;
			
		case "cssSelector":
			highlight(d,d.findElement(By.cssSelector(locatorID)));
			element = d.findElement(By.cssSelector(locatorID)).getAttribute(attribute);
			
			break;
			
		case "partialLinkText":
			highlight(d,d.findElement(By.partialLinkText(locatorID)));
			element = d.findElement(By.partialLinkText(locatorID)).getAttribute(attribute);
			
			break;
			//d.findElement(By.)
			
		case "linkText":
			highlight(d,d.findElement(By.linkText(locatorID)));
			element = d.findElement(By.linkText(locatorID)).getAttribute(attribute);
			
			break;
		default:
			
			break;
			
		}
		} catch(Exception e){
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			error=e.toString();
			errorline1=error.substring(0, 80);
			errorline2=error.substring(80, 160);
			errorline3=error.substring(160, 240);
			scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
			
			Assert.fail();
			
				}
		
		return element;
	}

	
	public String returnHref(WebDriver d,String locatorID,String locatorType) throws Exception{
		String element = null;
		
		try{
		switch (locatorType)
		{
		
		case "id":
			highlight(d,d.findElement(By.id(locatorID)));
			element = d.findElement(By.id(locatorID)).getAttribute("href");
			
			break;
			
		case "xpath":
			highlight(d,d.findElement(By.xpath(locatorID)));
			element = d.findElement(By.xpath(locatorID)).getAttribute("href");
			//System.out.println("URL is "+element);
			break;
		
		case "name":
			highlight(d,d.findElement(By.name(locatorID)));
			element = d.findElement(By.name(locatorID)).getAttribute("href");
			
			break;
			
		case "cssSelector":
			highlight(d,d.findElement(By.cssSelector(locatorID)));
			element = d.findElement(By.cssSelector(locatorID)).getAttribute("href");
			
			break;
			
		case "partialLinkText":
			highlight(d,d.findElement(By.partialLinkText(locatorID)));
			element = d.findElement(By.partialLinkText(locatorID)).getAttribute("href");
			
			break;
			//d.findElement(By.)
			
		case "linkText":
			highlight(d,d.findElement(By.linkText(locatorID)));
			element = d.findElement(By.linkText(locatorID)).getAttribute("href");
			
			break;
		default:
			
			break;
			
		}
		} catch(Exception e){
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			error=e.toString();
			errorline1=error.substring(0, 80);
			errorline2=error.substring(80, 160);
			errorline3=error.substring(160, 240);
			scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
			
			Assert.fail();
			
				}
		
		return element;
	}


	public static String getVariable(String vname) throws IOException{
	int x=1;
	int totalvariables;
	String s = null;
	boolean scanmore = true;
	
	FileInputStream fsIP= new FileInputStream(new File("Tests.xlsx")); //Read the spreadsheet that needs to be updated
    
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
				//System.out.println(test1);
				
			//	if(test1.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")){
			//		System.out.println("Variable is a numeric " + test1);
					
		    //    	return s;
					
		//		}
				
				
				
				s= cell.getStringCellValue();
				
				
				
				
				
			}
	  x++;
	 
	 }
 
	
	fsIP.close();
    
	return s;
}
	
	
	
	public static String getVariableFromProperties(String vname) throws IOException{
		
		Properties p = new Properties();
		FileInputStream fs1 = new FileInputStream("data.properties");
		p.load(fs1);
		String var = p.getProperty(vname);
		fs1.close();
		
		return var;
	}
	
	public static String getObject(String obj) throws IOException{
		
		Properties p = new Properties();
		FileInputStream fs1 = new FileInputStream("object.properties");
		p.load(fs1);
		String var = p.getProperty(obj);
		fs1.close();
		
		return var;
	}
	
	
	public static String[] ObjectParser(String s){
		String [] arr = s.split("|");
		return arr;
		
	}
	
	public void resizeElement(WebDriver d, String locatorID, String locatorType, String  xs, String ys){
		
		
		Actions action = new Actions(d);
		int x = Integer.parseInt(xs);
		int y = Integer.parseInt(ys);
		
		
		
		switch (locatorType)
		{
		
		 case "xpath":
			
			 
			 
			 action.moveToElement(d.findElement(By.xpath(locatorID))); //moves to bottom right corner
			 action.clickAndHold();
			 action.moveByOffset(x, y); 
			 action.release();
			 action.perform();
			 
			break;
			
		
		
		 case "name":
			 action.moveToElement(d.findElement(By.name(locatorID))); //moves to bottom right corner
			 action.clickAndHold();
			 action.moveByOffset(x, y); 
			 action.release();
			 action.perform();
			break;
		
		
		 case "id":
			 action.moveToElement(d.findElement(By.id(locatorID))); //moves to bottom right corner
			 action.clickAndHold();
			 action.moveByOffset(x, y); 
			 action.release();
			 action.perform();
			 break;
			
			
				
		 case "cssSelector":
			 action.moveToElement(d.findElement(By.cssSelector(locatorID))); //moves to bottom right corner
			 action.clickAndHold();
			 action.moveByOffset(x, y); 
			 action.release();
			 action.perform();
				
				break;
				
		 case "partialLinkText":
			 action.moveToElement(d.findElement(By.partialLinkText(locatorID))); //moves to bottom right corner
			 action.clickAndHold();
			 action.moveByOffset(x, y); 
			 action.release();
			 action.perform();
				
				break;
			
				
	     case "linkText":
	    	 action.moveToElement(d.findElement(By.linkText(locatorID))); //moves to bottom right corner
			 action.clickAndHold();
			 action.moveByOffset(x, y); 
			 action.release();
			 action.perform();
				
				break;
				
				
			
		default:
			
			break;
			
		}
		
		delay(2500);
		
		
		
	}

	public String[] StringParser(String s){
		String [] arr = s.split(",");
		return arr;
		
	}
			
	public String returnURL(WebDriver d){
		
		return d.getCurrentUrl();
		
	}
	
	public void goToiFrame(WebDriver d,String locatorID, String locatorType) throws Exception{
		
		try{
		switch (locatorType)
		{
		
		 case "xpath":
			
			 d.switchTo().defaultContent();
			 d.switchTo().frame(d.findElement(By.xpath(locatorID)));		 
			
			 
			break;
			
		
		
		 case "name":
			 d.switchTo().defaultContent();
			 d.switchTo().frame(d.findElement(By.name(locatorID)));
			
			break;
		
		
		 case "id":
			 d.switchTo().defaultContent();
			 d.switchTo().frame(d.findElement(By.id(locatorID)));
			 
			 break;
			
			
				
		 case "cssSelector":
			 d.switchTo().defaultContent();
			 d.switchTo().frame(d.findElement(By.cssSelector(locatorID)));
				
				break;
				
		 case "partialLinkText":
			 d.switchTo().defaultContent();
			 d.switchTo().frame(d.findElement(By.partialLinkText(locatorID)));
				
				break;
			
				
	     case "linkText":
	    	 d.switchTo().defaultContent();
			 d.switchTo().frame(d.findElement(By.linkText(locatorID)));
				
				break;
				
				
			
		default:
			
			break;
			
		}
		} catch(Exception e){
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			error=e.toString();
			errorline1=error.substring(0, 80);
			errorline2=error.substring(80, 160);
			errorline3=error.substring(160, 240);
			scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
			
			Assert.fail();
			
				}
		
	}

	public void CheckNonExistence(WebDriver d,String locatorID, String locatorType)throws Exception{
	//	isEqual=false;
		try{
		switch (locatorType)
		{
		
		case "xpath":			
			
			isEqual=d.findElement(By.xpath(locatorID)).isDisplayed();
						
			
		break;
		
		case "id":
			
			isEqual=d.findElement(By.id(locatorID)).isDisplayed();
			
				
			
		break;
		
		case "name":
			
			isEqual=d.findElement(By.name(locatorID)).isDisplayed();
			
				
				
		break;
		
		case "cssSelector":
			
			isEqual=d.findElement(By.cssSelector(locatorID)).isDisplayed();
			
				
		break;
		
		case "linkText":
			
			isEqual=d.findElement(By.linkText(locatorID)).isDisplayed();
			
			
		break;
		
		case "partialLinkText":
			
			isEqual=d.findElement(By.partialLinkText(locatorID)).isDisplayed();
			
			
		break;
		
		default:
			System.out.println("Locator Type is invalid");
		break;
		
		}
		
		
		}catch(Exception e){isEqual=false;}
		
		
		
		
		
	}
	
	public void gotoURL(WebDriver d ,String url){
		try{
		d.navigate().to(url);
		}catch (Exception e){}
		
			
	}
	
	public static String getPath(String filePath, String fileName){
		  File image1 = new File(filePath);
		  String image_path = image1.getAbsoluteFile().getParent()+"\\"+fileName;
		  System.out.println(image_path);
		  
		  return image_path;
		
			
	}

	public void AcceptAlert(WebDriver d) {
	    try {
	        WebDriverWait wait = new WebDriverWait(d, 2);
	        wait.until(ExpectedConditions.alertIsPresent());
	        Alert alert = d.switchTo().alert();
	        alert.accept();
	        System.out.println(" ");
	        System.out.println("Alert accepted");
	        System.out.println(" ");
	        
	    } catch (Exception e) {
	    	System.out.println(e);
	    }
	}
	
	public void DismissAlert(WebDriver d) {
	    try {
	        WebDriverWait wait = new WebDriverWait(d, 2);
	        wait.until(ExpectedConditions.alertIsPresent());
	        Alert alert = d.switchTo().alert();
	        alert.dismiss();
	        System.out.println(" ");
	        System.out.println("Alert Dismissed");
	        System.out.println(" ");
	        
	    } catch (Exception e) {
	    	 System.out.println(e);
	    }
	}
	
	public static boolean isAlertPresent(WebDriver d) {
	    try {
	        d.switchTo().alert();
	        return true;
	    } // try
	    catch (Exception e) {
	        return false;
	    } // catch
	}
	
	public String getAlertMessage(WebDriver d){
		String alertmessage = null;
		  try {
		        WebDriverWait wait = new WebDriverWait(d, 2);
		        wait.until(ExpectedConditions.alertIsPresent());
		        Alert alert = d.switchTo().alert();
		         alertmessage = alert.getText();
		        
		        
		    } catch (Exception e) {
		    	 System.out.println("Unable to retrieve message");
		    }
		  
		  return alertmessage;
	}
		
	public void waitForElement(WebDriver d, String locatorID, String locatorType) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(d, 40); 
		try{
		
		
		switch (locatorType)
		{
		
		case "id":
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorID))); 
			
			break;
			
		case "xpath":
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorID))); 
		
			break;
		
		case "name":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorID))); 
			
			break;
			
		case "cssSelector":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorID))); 
			
			break;
		case "linkText":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorID))); 
		
			break;
			
			
			
		default:
			System.out.println("Locator Type is invalid");
			break;
			
		}
	} catch(Exception e1){
		//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
		
		try{
		error=e1.toString();
		errorline1=error.substring(0, 80);
		errorline2=error.substring(80, 160);
		errorline3=error.substring(160, 240);
		}catch(Exception e2){
			errorline1=error;
			errorline2=error;
			errorline3=error;
		}
		
		scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
		writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e1.toString()); 
		writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e1.toString());
		
		
		Assert.fail();
		
			}
		
	}
	
	public String getStreetName() {
		String streetname = null;
		String[] StreetList = null;
			
		try {
			StreetList=StringParser(getVariable("AddressStreetName1"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		streetname= StreetList[Integer.parseInt(String.valueOf(ThreadLocalRandom.current().nextInt(0, 19)))];
		
		
		
		
		return streetname;
		
		
		
	}
	

	
	public void waitForElementSkipIfNotFound(WebDriver d, String locatorID, String locatorType) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(d, 60); 
		try{
		
		
		switch (locatorType)
		{
		
		case "id":
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorID))); 
			
			break;
			
		case "xpath":
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorID))); 
		
			break;
		
		case "name":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorID))); 
			
			break;
			
		case "cssSelector":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorID))); 
			
			break;
		case "linkText":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorID))); 
		
			break;
			
			
			
		default:
			System.out.println("Locator Type is invalid");
			break;
			
		}
	} catch(Exception e1){
		//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
		//error=e1.toString();
		//errorline1=error.substring(0, 80);
		//errorline2=error.substring(80, 160);
		//errorline3=error.substring(160, 240);
		//scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
		
		
		
		writehtml.writeEntryPassed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "SKIPPED", "ACTION _waitForElementSkipIfNotFound - Element not found.. Test will skip ");
		isElementFoundForWaitElement = false;
		
		
			}
		
	}
	
	public String getClass(WebDriver d,String locatorID,String locatorType) throws Exception{
		String element = null;
		
		try{
		switch (locatorType)
		{
		
		case "id":
			highlight(d,d.findElement(By.id(locatorID)));
			element = d.findElement(By.id(locatorID)).getAttribute("class");
			
			break;
			
		case "xpath":
			highlight(d,d.findElement(By.xpath(locatorID)));
			element = d.findElement(By.xpath(locatorID)).getAttribute("class");
			
			break;
		
		case "name":
			highlight(d,d.findElement(By.name(locatorID)));
			element = d.findElement(By.name(locatorID)).getText();
			
			break;
			
		case "cssSelector":
			highlight(d,d.findElement(By.cssSelector(locatorID)));
			element = d.findElement(By.cssSelector(locatorID)).getText();
			
			break;
			
		case "partialLinkText":
			highlight(d,d.findElement(By.partialLinkText(locatorID)));
			element = d.findElement(By.partialLinkText(locatorID)).getText();
			
			break;
			//d.findElement(By.)
			
		case "linkText":
			highlight(d,d.findElement(By.linkText(locatorID)));
			element = d.findElement(By.linkText(locatorID)).getText();
			
			break;
		default:
			
			break;
			
		}
		} catch(Exception e){
			error=e.toString();
			errorline1=error.substring(0, 80);
			errorline2=error.substring(80, 160);
			errorline3=error.substring(160, 240);
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			
			writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
			Assert.fail();
			
				}
		
		return element;
	}

	
	
	
	
	
	
	
	
	

	public static String getFileChecksum(MessageDigest digest, File file) throws IOException
{
    //Get file input stream for reading the file content
    FileInputStream fis = new FileInputStream(file);
     
    //Create byte array to read data in chunks
    byte[] byteArray = new byte[1024];
    int bytesCount = 0;
      
    //Read file data and update in message digest
    while ((bytesCount = fis.read(byteArray)) != -1) {
        digest.update(byteArray, 0, bytesCount);
    };
     
    //close the stream; We don't need it now.
    fis.close();
     
    //Get the hash's bytes
    byte[] bytes = digest.digest();
     
    //This bytes[] has bytes in decimal format;
    //Convert it to hexadecimal format
    StringBuilder sb = new StringBuilder();
    for(int i=0; i< bytes.length ;i++)
    {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
    }
     
    //return complete hash
   return sb.toString();
}


	
	
	
	
	
	public static boolean checkDownloadFileExistince() throws IOException{
		File file = new File(getVariableFromProperties("DownloadPath"));
		boolean tf=false;

		if(file.isDirectory()){

			if(file.list().length>0){

				System.out.println("Downloaded file is present");
				tf= true;

			}else{

				System.out.println("Directory is empty!");
				tf= false;

			}

		}else{

			System.out.println("This is not a directory");

		}
		return tf;
	
	
	
	
	
	
	
}
		
			
	public String getElementAttribute(WebDriver d,String locatorID,String locatorType,String attr) throws Exception{
		String element = null;
		
		try{
		switch (locatorType)
		{
		
		case "id":
			highlight(d,d.findElement(By.id(locatorID)));
			element = d.findElement(By.id(locatorID)).getAttribute(attr);
			
			break;
			
		case "xpath":
			highlight(d,d.findElement(By.xpath(locatorID)));
			element = d.findElement(By.xpath(locatorID)).getAttribute(attr);
			
			break;
		
		case "name":
			highlight(d,d.findElement(By.name(locatorID)));
			element = d.findElement(By.name(locatorID)).getAttribute(attr);
			
			break;
			
		case "cssSelector":
			highlight(d,d.findElement(By.cssSelector(locatorID)));
			element = d.findElement(By.cssSelector(locatorID)).getAttribute(attr);
			
			break;
			
		case "partialLinkText":
			highlight(d,d.findElement(By.partialLinkText(locatorID)));
			element = d.findElement(By.partialLinkText(locatorID)).getAttribute(attr);
			
			break;
			//d.findElement(By.)
			
		case "linkText":
			highlight(d,d.findElement(By.linkText(locatorID)));
			element = d.findElement(By.linkText(locatorID)).getAttribute(attr);
			
			break;
		default:
			
			break;
			
		}
		} catch(Exception e){
			error=e.toString();
			errorline1=error.substring(0, 80);
			errorline2=error.substring(80, 160);
			errorline3=error.substring(160, 240);
			//writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			scshots_Issue(d,Tests.testSuiteGlobal+MainTest.currentTest);
			
			writeFailedreport.writeEntry(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString()); 
			writehtml.writeEntryFailed(Tests.testSuiteGlobal,"Performing Test# "+ MainTest.currentTest,Tests.TestDescriptionGlobal, "FAILED", e.toString().substring(0, 250));
			Assert.fail();
			
				}
		
		return element;
	}
	
	
	public static String getStringFromExcelFile(int row, int col, String f) throws IOException{
		
	
		FileInputStream fsIP= new FileInputStream(new File(CommonMethods.getVariableFromProperties("DownloadPath")+f)); //Read the spreadsheet
    
		XSSFWorkbook wb = new XSSFWorkbook(fsIP); //Access the workbook
      
		XSSFSheet worksheet = wb.getSheet("sheet1"); //Access the worksheet, so that we can update / modify it.
      
		Cell cell1 = null; // declare a Cell object
		
    
		Row row1 = null;
		
		row1 = worksheet.getRow(row);
		Cell cell = row1.getCell(col);
    
	
    
	
		
			
		String s = cell.getStringCellValue();
				
				
				
				
				
				
	
 
	
		fsIP.close();
    
		return s;
}
	
	public static void writevariablefile(int cell, int row, String txt) throws Exception{
		FileInputStream fsIP= new FileInputStream(new File("Tests.xlsx")); //Read the spreadsheet that needs to be updated
		
		XSSFWorkbook wb = new XSSFWorkbook(fsIP);
		XSSFSheet worksheet = wb.getSheet("PolicyList");
		Cell cell1 = null;
		int newRow = row;
		Row row1 = worksheet.getRow(row);
		
		cell1 = row1.createCell(cell); 
        cell1.setCellValue(txt);
		
      // cell1 = row1.createCell(cell-1); 
      //  cell1.setCellValue("USED");
        
      //  cell1 = row1.createCell(cell-2); 
      //  cell1.setCellValue("USED");
        
      
        
        
                  
        
        
        fsIP.close();
        
        FileOutputStream output_file =new FileOutputStream(new File("Tests.xlsx")); 
        wb.write(output_file); //write changes
        
        output_file.close();
        
      
	}
	
	

	
	// this is a universal method that will replace variable value in excel file
	public static void writeCellVariable(String variable, String newvalue, String file, String sheetname) throws Exception{
		int x=1;
		int totalvariables;
		boolean scanmore = true;
		String s = null;
		
		FileInputStream fsIP= new FileInputStream(new File(file)); //Read the spreadsheet that needs to be updated
		
		XSSFWorkbook wb = new XSSFWorkbook(fsIP);
		XSSFSheet worksheet = wb.getSheet(sheetname);
		totalvariables = worksheet.getLastRowNum();
		Cell cell = null;
		Row row = null;
		
		
        
		while(x<=totalvariables && scanmore)
		{
			row = worksheet.getRow(x);
			cell = row.getCell(0);
		
			
		if(cell.getStringCellValue().equals(variable)){
				scanmore=false;
				
				cell = row.createCell(1); 
		        cell.setCellValue(newvalue);
				
				
				
				
				
				}
		x++;
	 
		}
        
      
        
        
                  
        
        
        fsIP.close();
        
        FileOutputStream output_file =new FileOutputStream(new File(file)); 
        wb.write(output_file); //write changes
        
        output_file.close();
        
      
	}
	
	public static void csvToXLSX(String sourceFileName, String targetFileName) {
		  BufferedReader br=null;
		  SXSSFWorkbook wb = null;
		  FileOutputStream fileOutputStream  =null;
		  OutputStreamWriter osw = null;
		  PrintWriter printWriter = null;
		  Reader reader = null;
		  try {
		   String csvFileAddress = sourceFileName;
		   String xlsxFileAddress = targetFileName;
		   wb = new SXSSFWorkbook(100); 
		   wb.setCompressTempFiles(true); // temp files will be gzipped
		   Sheet sheet = wb.createSheet("sheet1");
		   String currentLine=null;
		   int RowNum=0;
		    
		   reader = new InputStreamReader(new FileInputStream(csvFileAddress), "utf-8");
		   br = new BufferedReader(reader);
		 
		   while ((currentLine = br.readLine()) != null) {
		    String str[] = currentLine.split("\",\"");
		    Row currentRow=sheet.createRow(RowNum);
		    for(int i=0;i<str.length;i++){
		     if (str[i].startsWith("=")) {
		                        currentRow.createCell(i).

		setCellType(currentRow.createCell(i).CELL_TYPE_STRING);
		                        str[i] = str[i].replaceAll("\"", "");
		                        str[i] = str[i].replaceAll("=", "");
		                        currentRow.createCell(i).setCellValue(str[i]);
		                    } else if (str[i].startsWith("\"")) {
		                        str[i] = str[i].replaceAll("\"", "");
		                        currentRow.createCell(i).
	
		setCellType(currentRow.createCell(i).CELL_TYPE_STRING);
		                        currentRow.createCell(i).setCellValue(str[i]);
		                    } else {
		                        str[i] = str[i].replaceAll("\"", "");
		                        currentRow.createCell(i).
		
		setCellType(currentRow.createCell(i).CELL_TYPE_NUMERIC);
		                        currentRow.createCell(i).setCellValue(str[i]);
		                    }     
		    }
		    RowNum++;
		   }
		 
		   fileOutputStream  =  new FileOutputStream(xlsxFileAddress);
		   wb.write(fileOutputStream);
		   osw = new OutputStreamWriter(fileOutputStream , "UTF-8");
		            printWriter = new PrintWriter(osw);
		 
		   System.out.println("Done");
		  } catch (Exception ex) {
		   System.out.println(ex.getMessage()+"Exception in try");
		  }
		  finally{ 
		   try {
		    if(br!=null)
		    br.close();
		    if(reader!=null)
		    reader.close();
		    if(fileOutputStream!=null)
		    fileOutputStream.close(); 
		    if(osw!=null)
		    osw.close();
		       if(printWriter!=null)
		    printWriter.close();
		   } catch (IOException e) {
		    System.out.println("Error trying to close resources.."+e.getMessage());
		   }
		  }
		 }
		
	
	





}
