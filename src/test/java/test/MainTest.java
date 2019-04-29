package test;

import java.awt.Robot;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import org.json.JSONObject;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import io.appium.java_client.ios.IOSDriver;
//import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testautomationguru.utility.CompareMode;
import com.testautomationguru.utility.PDFUtil;




import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainTest {
	public static WebDriver d;
	
	public static int currentTest;
	Tests test = new Tests();
	public String dev_site, prod_site, eap;
	public static String browser;
	public static Overlay screen;
	public static String startTest;
	public static String finishTest;
	public static String ngroktestreportURL;
	public boolean isAndroidApp=false;
	public static boolean isNewTest=true;
	
	FileInputStream fs;
	public Properties prop = new Properties();
	
	private String suite;
	
@Test (priority=1)
	public void Researchers() throws Exception{

		browser = CommonMethods.getVariableFromProperties("browser");
		ExecuteTestSuite("1_Researchers",prod_site,browser);
			  
		  }

@Test  (priority=2)
public void Stories() throws Exception{

	browser = CommonMethods.getVariableFromProperties("browser");
	ExecuteTestSuite("2_Stories",prod_site,browser);
		  
	  }

@Test  (priority=3)
public void Pages() throws Exception{

	browser = CommonMethods.getVariableFromProperties("browser");
	ExecuteTestSuite("2.1_Pages",prod_site,browser);
		  
	  }

@Test  (priority=4)
public void PageNavigations() throws Exception{

	browser = CommonMethods.getVariableFromProperties("browser");
	ExecuteTestSuite("2.2_PageNavigations",prod_site,browser);
		  
	  }

@Test  (priority=5)
	public void Classrooms() throws Exception{

		browser = CommonMethods.getVariableFromProperties("browser");
		ExecuteTestSuite("3_Classrooms",prod_site,browser);
			  
		  }

@Test (dependsOnMethods= {"Classrooms"} , priority =6)
public void Students() throws Exception{

	browser = CommonMethods.getVariableFromProperties("browser");
	ExecuteTestSuite("4_Students",prod_site,browser);
		  
	  }


@Test (dependsOnMethods= {"Students"} , priority =7)
public void StudentReadingExperience() throws Exception{

	browser = CommonMethods.getVariableFromProperties("browser");
	ExecuteTestSuite("5_StudentReadingExperience",prod_site,browser);
		  
	  }


@Test (dependsOnMethods= {"StudentReadingExperience"} , priority =8)
public void UserLogs() throws Exception{

	browser = CommonMethods.getVariableFromProperties("browser");
	ExecuteTestSuite("6_UserLogs",prod_site,browser);
		  
	  }

@Test (dependsOnMethods= {"Students"} , priority =9)
public void OpenEndedAnswers() throws Exception{

	browser = CommonMethods.getVariableFromProperties("browser");
	ExecuteTestSuite("7_OpenEndedAnswers",prod_site,browser);
		  
	  }

@Test (dependsOnMethods= {"Stories"} , priority =10)
public void Clone_DeleteRecords() throws Exception{

	browser = CommonMethods.getVariableFromProperties("browser");
	ExecuteTestSuite("8_Clone_DeleteRecords",prod_site,browser);
		  
	  }


@Test 
public void tester() throws Exception{

	System.out.println("value for row1, col5 is "+CommonMethods.getStringFromExcelFile(1, 5, "spreadsheetConverted.xlsx") );
	
	  
}
  
 
  @Test 
  public void dummy() throws Exception{

	  browser = CommonMethods.getVariableFromProperties("browser");
	  ExecuteTestSuite("dummy",prod_site,browser);
	  
  }
	  



  
  @SuppressWarnings("deprecation")
private void ExecuteTestSuite(String suite, String wsite, String b) throws Exception{
	  
	  
	  
	  if(browser.equals("Firefox")){
		  	System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		  	
		  
			 d= new FirefoxDriver();
			 d.get(wsite);
			 d.manage().window().setSize(new Dimension(1920, 1080));
	  }
		 else 
		
			 if(b.equals("OSXChrome")){
				 	System.out.println("OSX Chrome");
				 	FileUtils.cleanDirectory(new File(CommonMethods.getVariableFromProperties("DownloadPath"))); // clear download path
				 
				 	System.setProperty("webdriver.chrome.driver", "chromedriver");
				 	//new code
				 	String downloadFilepath = CommonMethods.getVariableFromProperties("DownloadPath");
				 	HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				 	chromePrefs.put("profile.default_content_settings.popups", 0);
				 	chromePrefs.put("download.default_directory", downloadFilepath);
				 	ChromeOptions options = new ChromeOptions();
				 	HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
				 	options.setExperimentalOption("prefs", chromePrefs);
				 	options.addArguments("--test-type");
				 	DesiredCapabilities cap = DesiredCapabilities.chrome();
				 	cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
				 	cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				 	cap.setCapability(ChromeOptions.CAPABILITY, options);   	
				 	
				 	
				 	
				
					d = new ChromeDriver(cap);
					d.get(wsite);
				
					
					d.manage().window().maximize();
					
			 }
	  
			 else 
				 
				 if(b.equals("Chrome")){
							FileUtils.cleanDirectory(new File(CommonMethods.getVariableFromProperties("DownloadPath"))); // clear download path
						 	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
							
						 	
						 	//new code
						 	String downloadFilepath = CommonMethods.getVariableFromProperties("DownloadPath");
						 	HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
						 	chromePrefs.put("profile.default_content_settings.popups", 0);
						 	chromePrefs.put("download.default_directory", downloadFilepath);
						 	ChromeOptions options = new ChromeOptions();
						 	HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
						 	options.setExperimentalOption("prefs", chromePrefs);
						 	options.addArguments("--test-type");
						 	DesiredCapabilities cap = DesiredCapabilities.chrome();
						 	cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
						 	cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
						 	cap.setCapability(ChromeOptions.CAPABILITY, options);   					 	  
						 	// WebDriver driver = new ChromeDriver(cap);
						 	d = new ChromeDriver(cap);
						 	
						 	
						
						 	
							
							
							d.get(wsite);
							d.manage().window().maximize();
						
					 
					 
					 
				 }
				 else
	  
	  		if(b.equals("Chrome1080p")){
			
		 	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		 	
		 	//new code
		 	String downloadFilepath = CommonMethods.getVariableFromProperties("DownloadPath");
		 	HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		 	chromePrefs.put("profile.default_content_settings.popups", 0);
		 	chromePrefs.put("download.default_directory", downloadFilepath);
		 	ChromeOptions options = new ChromeOptions();
		 	HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
		 	options.setExperimentalOption("prefs", chromePrefs);
		 	options.addArguments("--test-type");
		 	DesiredCapabilities cap = DesiredCapabilities.chrome();
		 	cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
		 	cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		 	cap.setCapability(ChromeOptions.CAPABILITY, options);   					 	  
	
		 	d = new ChromeDriver(cap);
		 	
		 	
		 	
	
		 	
			
			
			d.get(wsite);
		
			d.manage().window().setSize(new Dimension(768,1024));
	 
	 
	 
	  			}
				 
				 else
				 
				 if(b.equals("Safari")){
					 	//System.setProperty("webdriver.chrome.driver", "C:\\KeywordDriven\\lib\\chromedriver.exe");
						d = new SafariDriver();
						d.get(wsite);
						d.manage().window().maximize();
				 }
		  
				 else 
					
				
					 
				if(b.equals("IE64")){
				 //   File file = new File("C:\\Users\\JunCarlo\\workspace\\ProducePay\\IEDriverServer.exe");
				//	System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
					
					System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
					
					 DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
						    
						     dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
						    dc.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
						  //  dc.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
						    dc.setCapability("requireWindowFocus", true);
						   // dc.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
				    //System.setProperty("webdriver.ie.driver", ".\\lib\\IEDriverServer.exe");
					d = new InternetExplorerDriver(dc);
					d.get(wsite); 
					
					d.manage().window().maximize();
					
						
					}
	  
				 else 
					 if(b.equals("Edge")){
						 
							
							System.setProperty("webdriver.edge.driver", "MicrosoftWebDriver.exe");
							
						//	 DesiredCapabilities ed = DesiredCapabilities.edge();
								    
								   
							d = new EdgeDriver();
							d.get(wsite); 
							
							d.manage().window().maximize();
							
								
							}
			  
						 else 
					 
						
					 if(b.equals("Android")){
						 DesiredCapabilities capabilities = new DesiredCapabilities();
						 capabilities.setCapability("deviceName", "Android Emulator");
						 capabilities.setCapability("platformName", "Android");
						 capabilities.setCapability("platformVersion", "4.4"); 
						 capabilities.setCapability("browserName", "Browser");
						 d = new RemoteWebDriver(
								 new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
						// mobiledriver = new AppiumDriver();	
						 	
							d.get(wsite);
							//d.manage().window().maximize();
					 }
	  
	  
					
	  d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  CommonMethods.delay(1000);
	  System.out.println("");
	  System.out.println("EXECUTING TEST SUITE: "+suite);
	  System.out.println("Total number of steps "+ test.getNumberTests(suite));
	 // writeHtml.startTable();
	  
	  int x;
	  		for(x=1;x<=test.getNumberTests(suite);x++)
	  			{
	  			
	  			if(x==1){writeHtml.startTable(suite); }
	  				currentTest = x;
	  				test.ExecuteStep(x,suite);
	  				if (!CommonMethods.isElementFoundForWaitElement) { 
	  					x=Tests.toStep - 1; 
	  					CommonMethods.isElementFoundForWaitElement=true;
	  					}
	  				
	  				
	  				
	  				if(!CommonMethods.isAlertPresent(d)){
	  					//if(browser.equals("Android")){ mobiledriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); }
	  					 d.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); 
	  				}else{
	  					System.out.println(" ");
	  					System.out.println("An alert is currently active");
	  					System.out.println(" ");
	  				}
	  					
	  				
	  				
	  				
	 
	 }
	  		
	  TestResult.setPass(suite);
	  writeHtml.writeFullSummaryReport("PASS","green");
	
	  d.quit();
	  
  }
  

  
 
  
  public static void writeTestReports() throws Exception{
	  
	  TestResult.setFinish();
	  finishTest=CommonMethods.returnDate3();
	  writeHtml.finishTest();
	  
  }
  
  
  @BeforeTest
  public void beforeTest() throws IOException {
	  File image1 = new File("images\\page1.jpg");
	  String image_path = file.getAbsoluteFile().getParent();
	  System.out.println(image_path);
	  
	  fs = new FileInputStream("data.properties");
	  prop.load(fs);
	  
	  screen = new Overlay();
  
	 WriteReport.writeReportFile();
	 writeHtml.writeFile();
	 writeHtml.writeFileSummary();
	 //enable/disable runtime variable//
	 
	 RuntimeVariables.writeVariableFile();
	 
	 
	
	
	 prod_site= prop.getProperty("URL_Prod");
	 dev_site= prop.getProperty("URL_Dev");
	 //browser =  prop.getProperty("browser");
	 eap = prop.getProperty("EAP");
	 ngroktestreportURL = prop.getProperty("ngroktestreportURL");
	 fs.close();
	 
	 TestResult.setStart();	 
	 startTest=CommonMethods.returnDate3();
	 
	 
	 
	// PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
	// System.setOut(out);
	 
		 
	 }
	 
  

  @AfterTest
  public void afterTest()throws Exception {
	 
	  
	
	//  if(isAndroidApp and mobiledriver.){ mobiledriver.quit();}
	 
	  if(!isAndroidApp){d.quit();}
	  
	 // TestResult.setFinish();
	 // finishTest=CommonMethods.returnDate3();
	 // writeHtml.finishTest();
	  writeTestReports();
	  
	  isAndroidApp=false;
	  
	  if(prop.getProperty("TypeOfRun").equals("officialrun")){
		  Emailer.sendEmail();
	  
	  }
	  
  }
  
  
  
	  
  
}
