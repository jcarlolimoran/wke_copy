package test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class TestResult {
	public static int no_passed;
	public static int no_failed;
	public static ArrayList passed = new ArrayList();
	public static ArrayList failed= new ArrayList();
	
	public static ArrayList passedNoHref = new ArrayList();
	public static ArrayList failedNoHref= new ArrayList();
	public static Instant start;
	public static Instant end;
	
	public TestResult(){
		
	}
	
	public static void setPass(String suite){
		no_passed = no_passed+1;
		passed.add("<a href=\"#"+suite+"\">"+suite+"</a>");
		passedNoHref.add(suite);
		
	}
	
	public static void setFail(String suite){
		no_failed = no_failed+1;
		failed.add("<a href=\"#"+suite+"\">"+suite+"</a>");
		failedNoHref.add(suite);
		
		
	}
	

	
	public static int get_no_passed(){
		
		return no_passed;
	}
	
	public static int get_no_failed(){
		
		return no_failed;
	}
	
	public static ArrayList getAllPass(){
		return passed;
	}
	
	public static ArrayList getAllFail(){
		return failed;
	}
	
	public static ArrayList getAllPassNoHref(){
		return passedNoHref;
	}
	
	public static ArrayList getAllFailNoHref(){
		return failedNoHref;
	}
	
	public static void setStart(){
		 start = Instant.now();
		
	}
	
	public static void setFinish(){
		 end = Instant.now();
		
	}
	
	public static String getTestDuration(){
		Duration timeElapsed = Duration.between(start, end);
		long t= timeElapsed.toMillis();
		
		//Date date = new Date(t);
		//DateFormat formatter = new SimpleDateFormat("mm:ss");
		//String duration = formatter.format(date);
		String mins = Long.toString((t/1000)/60);
		String secs =  Long.toString((t/1000)%60);
		
		String duration = mins+" minutes  "+secs+" seconds";
		return duration;
	}
	
	
	public static long getDuration(){
		Duration timeElapsed = Duration.between(start, end);
		long t= timeElapsed.toMillis();
		
	
		
		//String duration = Long.toString(t);
		return t;
	}
	
	

}
