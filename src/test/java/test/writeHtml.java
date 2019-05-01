package test;

import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



public class writeHtml {
	public static String htmlpath;
	public static String htmlpath_main = "ReportLogs\\index.html";
	public static String htmlpath_mainOFFICIALRUN;
	public static String reportURL;
	
	//TestResult TR = new TestResult(); 
	public static String env= "OS: "+System.getProperty("os.name") +" | Version: "+System.getProperty("os.version");
	public static String report;
	
	public static boolean isPassed = true;

	public static String TestLabel;
	public static ArrayList<String> TestLabelsList;
	public static ArrayList<String> TestSuiteList;// = new ArrayList<String>();
	public static ArrayList<ArrayList<String>> TestSuiteList2D= new ArrayList<ArrayList<String>>();
	public static ArrayList<String> ScreenshotsList = new ArrayList<String>();


	
	
	static String reportsummary;
	static String htmlpathsummary ;
	static String reportURLsummary;
	
	static boolean isFirstLabel=true;
	static boolean TestTerminate=false;
	
	public static String  vsuite,  vTestStep, vlabel , vDescription , vscreenshot, vresult, absoluteScreenshotPath;
	
	public writeHtml(){
		
		
	}
	
	
	public static void writeFile(){
		
		try {

			String content = "<div align=\"center\"> <a href=\"index.html\">HOME</a> </div>"+
					"<style type=\"text/css\"/>"+
					"table.tableizer-table { font-size: 12px;"+
					"border: 1px solid #CCC;"+ 
					"font-family: Arial, Helvetica, sans-serif;"+
					"} "+
					".tableizer-table td {"+
					"padding: 4px;"+
					"margin: 3px;"+
					"border: 1px solid #CCC;"+
					"}"+
					".tableizer-table th {"+
					"background-color: #48b626;"+ 
					"color: #FFF;"+
					"font-weight: bold;"+
					"}"+
					"caption { display: table-caption;text-align: left;font-size: 14px;}"+
					"</style>"+
					"<table class=\"tableizer-table\" width=\"1500\"><thead><tr class=\"tableizer-firstrow\"><th>Test Suite</th><th>TestStep</th><th>Description</th><th>Result</th><th>Comment</th><th>Screenshot</th></tr></thead><tbody>"
					;
					
					
					
					

			report="testReport"+CommonMethods.returnDate()+".html";
			htmlpath = "ReportLogs\\"+report;
			
			if(CommonMethods.getVariableFromProperties("TypeOfRun").equals("officialrun")){  htmlpath = CommonMethods.getVariableFromProperties("Dropbox")+report;       }
			
			reportURL = CommonMethods.getVariableFromProperties("ngroktestreportURL") + "/" + report;
			
			if(CommonMethods.getVariableFromProperties("MacOSX").equals("Y")){htmlpath= htmlpath.replace("\\", "//") ;}
			
			System.out.println(reportURL);
			File file = new File(htmlpath);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			//System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	

	
	public static void startTable(String s){
		TestLabelsList = new ArrayList<String>();
		TestSuiteList = new ArrayList<String>();
		
		try{
			//File file = new File(htmlpath);
			FileWriter fw = new FileWriter(htmlpath,true);
			BufferedWriter bw = new BufferedWriter(fw);
			//bw.append("<table class=\"tableizer-table\" width=\"1500\"><thead><tr class=\"tableizer-firstrow\"><th>Test Suite</th><th>TestStep</th><th>Description</th><th>Result</th><th>Comment</th><th>Screenshot</th></tr></thead><tbody>");
		//	bw.append("<tr><td>TEST SUITE: </td><td>"+Tests.testSuiteGlobal+"</td><td </td></tr>");
			bw.append("<tr id=\""+s+"\"><td colspan = \"6\">  <strong><font color=\"#104E8B\" size=4 >TEST SUITE: "+s +"</td></tr>");
			bw.close();
			fw.close();
			
			TestSuiteList.add(s+"<br><font size=2>"+CommonMethods.getVariable(s)+ " <br>Browser: "+ MainTest.browser);
			
			}catch(IOException e){}
		

		
		}
		
	
	
	
	public void writeEntryPassed(String suite, String TestStep, String Description, String Result, String Comment) throws Exception{
		//FileInputStream fsIP= new FileInputStream(new File(htmlpath));
		try{
		//File file = new File(htmlpath);
		FileWriter fw = new FileWriter(htmlpath,true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.append("<tr><td>"+suite+"</td><td>"+TestStep+"</td><td>"+Description+"</td><td>"+Result+"</td><td>"+Comment+"</td><td>not applicable</td></tr>");
		bw.close();
		fw.close();
		
		
		
		}catch(IOException e){}
	}

	public void writeEntryFailed(String suite, String TestStep, String Description, String Result, String Comment) throws Exception{
		String s;
		//FileInputStream fsIP= new FileInputStream(new File(htmlpath));
		if(CommonMethods.getVariableFromProperties("TypeOfRun").equals("officialrun")){ 
			absoluteScreenshotPath = CommonMethods.screenshot;
			ScreenshotsList.add(absoluteScreenshotPath);
			s = "./".concat(CommonMethods.screenshot);
			s=s.replace(CommonMethods.getVariableFromProperties("Dropbox")+"shots\\", "shots/");		
			System.out.println(s);
		}else{
		
		s = "./".concat(CommonMethods.screenshot);
		s=s.replace("ReportLogs\\shots\\", "shots/");
			if(CommonMethods.getVariableFromProperties("MacOSX").equals("Y")) { s=s.replace("//", "/").replace("ReportLogs/", "");}
			System.out.println("This is s "+ s);
		}
		
		try{
		//File file = new File(htmlpath);
		FileWriter fw = new FileWriter(htmlpath,true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.append("<tr bgcolor=\"red\"><td>"+suite+"</td><td>"+TestStep+"</td><td>"+Description+"</td><td>"+Result+"</td><td>"+Comment+"</td><td><a href=\""+s+"\"><img src="+s+"  width=\"100\" height=\"100\" /></a></td></tr>");
		
		
		bw.close();
		fw.close();
		
		TestResult.setFail(suite);
		isPassed=false;
	
		
		
		vTestStep=TestStep;
		vDescription=Description;
		vscreenshot=s;
		vresult=Comment;
		String labelf=TestLabelsList.get(TestLabelsList.size()-1);
		TestLabelsList.remove(TestLabelsList.size()-1);
		TestLabelsList.add(labelf + "***THIS IS A TEST FAILURE*** check details on next row ");
		writeHtml.writeFullSummaryReport("FAIL","red");
		
		
		
		}catch(IOException e){}
	}
	
	public void writeTestLabel(String suite, String TestStep, String label) throws Exception{

		try{
		
		FileWriter fw = new FileWriter(htmlpath,true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.append("<tr><td>"+suite+"</td><td>"+TestStep+"</td><td colspan = \"4\"><strong><font color=\"#0066ff\">"+ label +"</td></tr>");
		bw.close();
		fw.close();
		
		isFirstLabel=false;
		TestTerminate=false;
		TestLabelsList.add(label);
		
		}catch(IOException e){}
		
		
		
		
		
			
		
		
			
		
		
		
	}
	

	public static void finishTest() throws Exception{
		htmlpath_mainOFFICIALRUN = CommonMethods.getVariableFromProperties("Dropbox")+"index.html";
		
		try{
		
		FileWriter fw = new FileWriter(htmlpath,true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		//bw.append("<tr><td>TEST SUITE: </td><td>"+Tests.testSuiteGlobal+"</td><td </td></tr>");
		
		bw.append("<caption><strong><font size=5>Demo Test Result </font> "+CommonMethods.returnDate3()+
				 "<hr>"+
				"<br><font color=green>PASSED: "+TestResult.no_passed+"</font>"+
				"<br><font color=red>FAILED: "+TestResult.no_failed+ "</font>"+
				"<br><font color=green>List of Passed Tests: "+TestResult.getAllPass()+"</font>"+
				"<br><font color=red>List of Failed Tests: "+TestResult.getAllFail()+"</font>"+
				"<br>"+
				"<br><font color=black>Test Started: "+MainTest.startTest+"                        |    Test Completed: "+ MainTest.finishTest+" </font>"+
				"<br><font color=black>Test Duration: "+TestResult.getTestDuration()+"</font>"+
				"<br><font color=black>Browser Used: "+MainTest.browser+" "+env+"</font>"+
				" </strong></caption></tbody></table>");
		bw.close();
		
		
		if(isPassed) { writeEntryPassed_mainReportFile(); }
		if(!isPassed) { writeEntryFailed_mainReportFile(); }
		
		
	
		
		}catch(IOException e){}
		
		
		try{
			
		FileWriter fw = new FileWriter(htmlpathsummary,true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		//bw.append("<tr><td>TEST SUITE: </td><td>"+Tests.testSuiteGlobal+"</td><td </td></tr>");
		
		bw.append("<caption><strong><font size=5>TEST SUMMARY </font> "+CommonMethods.returnDate3()+
				 "<hr>"+
				"<br><font color=green>PASSED: "+TestResult.no_passed+"</font>"+
				"<br><font color=red>FAILED: "+TestResult.no_failed+ "</font>"+
				"<br><font color=green>List of Passed Tests: "+TestResult.getAllPass()+"</font>"+
				"<br><font color=red>List of Failed Tests: "+TestResult.getAllFail()+"</font>"+
				"<br>"+
				"<br><font color=black>Test Started: "+MainTest.startTest+"                        |    Test Completed: "+ MainTest.finishTest+" </font>"+
				"<br><font color=black>Test Duration: "+TestResult.getTestDuration()+"</font>"+
				"<br><font color=black>Browser Used: "+MainTest.browser+" "+env+"</font>"+
				" </strong></caption></tbody></table>");
		bw.close();
		
		
	
		
		
	
		
		}catch(IOException e){}
	}
	
	// add entry to main report file index.html
	public static void writeEntryPassed_mainReportFile() throws Exception{
		//FileInputStream fsIP= new FileInputStream(new File(htmlpath));
		String link=htmlpath.replace(htmlpath, report);
		FileWriter fw;
		
		try{
		//File file = new File(htmlpath);
			
		if(CommonMethods.getVariableFromProperties("TypeOfRun").equals("officialrun")){fw = new FileWriter(htmlpath_mainOFFICIALRUN,true); }
		else { fw = new FileWriter(htmlpath_main,true); }
		
		BufferedWriter bw = new BufferedWriter(fw);
		bw.append("<tr><td>"+CommonMethods.returnDate3()+"</td><td><font color=green>PASSED</font></td><td><a href=\""+link+"\" >LINK TO REPORT</a></td><td>"+ MainTest.browser+"</td><td>none</td></tr>");
		
		bw.close();
		fw.close();
		
		
		
		}catch(IOException e){}
	}

	public static void writeEntryFailed_mainReportFile() throws Exception{
		String link=htmlpath.replace(htmlpath, report);
		FileWriter fw;
		
		try{
		
		if(CommonMethods.getVariableFromProperties("TypeOfRun").equals("officialrun")){fw = new FileWriter(htmlpath_mainOFFICIALRUN,true); }
		else { fw = new FileWriter(htmlpath_main,true); }
		
		BufferedWriter bw = new BufferedWriter(fw);
		bw.append("<tr><td>"+CommonMethods.returnDate3()+"</td><td><font color=red>WITH FAILURES</font></td><td><a href=\""+link+"\" >LINK TO REPORT</a></td><td>"+ MainTest.browser+"</td><td>none</td></tr>");
		
		
		
		bw.close();
		fw.close();
		
		
		
		}catch(IOException e){}
	}
	
	
	
	
	
	
	
	
	
	// SUMMARY HTML
	
	
	
public static void writeFileSummary(){
		
		try {

			String content = "<div align=\"center\"> <a href=\"index.html\"><img src=\"URLHERE\" alt=\"Yardbook\" border=\"0\" /></a> </div>"+
					
					"<style type=\"text/css\"/>"+
					"table.tableizer-table { font-size: 12px;"+
					"border: 1px solid #CCC;"+ 
					"font-family: Arial, Helvetica, sans-serif;"+
					"} "+
					".tableizer-table td {"+
					"padding: 4px;"+
					"margin: 3px;"+
					"border: 1px solid #CCC;"+
					"}"+
					".tableizer-table th {"+
					"background-color: #48b626;"+ 
					"color: #FFF;"+
					"font-weight: bold;"+
					"}"+
					"caption { display: table-caption;text-align: left;font-size: 14px;}"+
					"</style>"+
					"<table class=\"tableizer-table\" width=\"1500\"><thead align=\"left\"><tr class=\"tableizer-firstrow\"><th>Description</th><th>Result</th></tr></thead><tbody>"
					;
					
					
					
					

			 reportsummary = "testReport"+CommonMethods.returnDate()+"SUMMARY.html";
			 htmlpathsummary = "ReportLogs\\"+reportsummary;
			 	if(CommonMethods.getVariableFromProperties("MacOSX").equals("Y")) { htmlpathsummary = "ReportLogs//"+reportsummary;}
			
			if(CommonMethods.getVariableFromProperties("TypeOfRun").equals("officialrun")){  htmlpathsummary = CommonMethods.getVariableFromProperties("Dropbox")+reportsummary;       }
			
			reportURLsummary = CommonMethods.getVariableFromProperties("ngroktestreportURL") + "/" + reportsummary;
			System.out.println(reportURLsummary);
			File file = new File(htmlpathsummary);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			//System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


public void writeEntrySummary(String suite, String Description, String Result) throws Exception{
	//FileInputStream fsIP= new FileInputStream(new File(htmlpath));
	try{
	//File file = new File(htmlpath);
		
	if(Result.equals("PASS")){
		FileWriter fw = new FileWriter(htmlpathsummary,true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.append("<tr><td>"+Description.replace("TEST LABEL:", "")+"</td><td><strong><font color=green>"+Result+"</font></td><td>not applicable</td></tr>");
		bw.close();
		fw.close();
	}
	
	else if(Result.equals("FAIL")){
		FileWriter fw = new FileWriter(htmlpathsummary,true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.append("<tr><td>"+Description.replace("TEST LABEL:", "")+"</td><td><font color=red>"+Result+"</font></td><td>not applicable</td></tr>");
		bw.close();
		fw.close();
		
		
		
	}
	
	
	
	}catch(IOException e){}
}



public static void writeFullSummaryReport(String p, String fcolor) throws Exception{
	//FileInputStream fsIP= new FileInputStream(new File(htmlpath));
	try{
	//File file = new File(htmlpath);
		
	
		FileWriter fw = new FileWriter(htmlpathsummary,true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		int indexTestSuiteList;
		int indexTestLabelsList;
		
		System.out.println(TestSuiteList);
		System.out.println(TestLabelsList);
		
		for(indexTestSuiteList=0; indexTestSuiteList<TestSuiteList.size();indexTestSuiteList++){ 
			System.out.println("");
			System.out.println("Test Suite "+ TestSuiteList.get(indexTestSuiteList));
			bw.append("<tr id=\""+TestSuiteList.get(indexTestSuiteList)+"\"><td colspan = \"6\">  <strong><font size=4 ><br> &#8680;TEST SUITE: "+TestSuiteList.get(indexTestSuiteList) +"</td></tr>");
					
			for(indexTestLabelsList=0;indexTestLabelsList<TestLabelsList.size();indexTestLabelsList++){
						//bw.append("<tr>"+ TestLabelsList.get(indexTestLabelsList)+"</tr>");
						System.out.println("");
						System.out.println("Test Label "+ TestLabelsList.get(indexTestLabelsList));
						if(TestLabelsList.get(indexTestLabelsList).contains("THIS IS A TEST FAILURE")){
						bw.append("<tr><td>"+TestLabelsList.get(indexTestLabelsList).replace("TEST LABEL:", "")+"</td><td><font color="+fcolor+">"+p+"</font></td></tr>");
						}
						else{
							bw.append("<tr><td>"+TestLabelsList.get(indexTestLabelsList).replace("TEST LABEL:", "")+"</td><td><font color="+"green"+">"+"PASS"+"</font></td></tr>");
							
						}
					}
			
		}
		
		if(p.equals("FAIL")){
			bw.append("<tr bgcolor=\"red\"><td>"+vTestStep.replace("Performing", "Stopped at ")+" "+vDescription+" TEST FAILURE: "+vresult +"</td><td><a href=\""+vscreenshot+"\">"+ writeHtml.vscreenshot.substring(8)+"</a></td></tr>");
			
		}
		
	
		
		bw.close();
		fw.close();

	
	
	}catch(IOException e){}
}
	
	
	
}
