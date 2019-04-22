package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class Emailer {




public static void sendEmail() throws Exception {
	
	ArrayList passedListWithBr = new ArrayList();
	ArrayList failedListWithBr= new ArrayList();
	

	
	
	
	
		for(int x=0;x<TestResult.getAllPassNoHref().size();x++){
			
			
			passedListWithBr.add(TestResult.getAllPassNoHref().get(x)+"<br>");
		}
		
		for(int x=0;x<TestResult.getAllFailNoHref().size();x++){
		
			failedListWithBr.add(TestResult.getAllFailNoHref().get(x)+"<br>");
		}
	 
		Properties mailServerProperties;
		Session getMailSession;
		MimeMessage generateMailMessage;
		
		String emailBody = "<!DOCTYPE html>" +
		"<html>"+
		"<head>"+
		"<style>"+
		"table, th, td {"+
		    "border: 1px solid black;"+
		    "border-collapse: collapse;"+
		"}"+
		"th, td {"+
		    "padding: 5px;"+
		    "text-align: center;"+
		"}"+
		"</style>"+
		"</head>"+
		"<body>"+

		"<p><strong>Sevan Capture Testing</strong></p>"+
		"<table>"+
		  "<tr>"+
		    "<th style=\"color:green\">Passed Tests "+TestResult.get_no_passed()+"</th>"+
		    "<th style=\"color:red\">Failed Tests "+TestResult.get_no_failed()+"</th>"	+	
		  "</tr>"+
		  "<tr>"+
		    "<td>"+passedListWithBr.toString().replace("[", "").replace("]", "")+"</td>"+
		    "<td>"+failedListWithBr.toString().replace("[", "").replace("]", "")+"</td>"+		
		    
		  "</tr>"+
		  
		"</table>"+
		
		  "<p>Link to Report: <a href=\""+writeHtml.reportURLsummary+"\"><i>click here</a></p></i> <br>"+
	
		  
		  "<p>Regards,</p>"+
		  "<p>Jun</p>"+
		  "<br>"+
		  "<br>"+
		  "<br>"+
		  
		  "<p><i>note: this is auto-generated email, for queries please send email to jcarlolimoran@gmail.com</p>"+

		"</body>"+
		"</html>";
				
				
				
				
				
				
			
	 
		
			// Step1
			System.out.println("\n 1st ===> setup Mail Server Properties..");
			mailServerProperties = System.getProperties();
			mailServerProperties.put("mail.smtp.port", "587");
			mailServerProperties.put("mail.smtp.auth", "true");
			mailServerProperties.put("mail.smtp.starttls.enable", "true");
			System.out.println("Mail Server Properties have been setup successfully..");
	 
			// Step2
			System.out.println("\n\n 2nd ===> get Mail Session..");
			getMailSession = Session.getDefaultInstance(mailServerProperties, null);
			generateMailMessage = new MimeMessage(getMailSession);
			generateMailMessage.setFrom(new InternetAddress(CommonMethods.getVariableFromProperties("emailer_email"), "Yardbook QA"));
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("jcarlolimoran@gmail.com"));


			generateMailMessage.setSubject("Yardbook Automated Testing Run "+MainTest.finishTest);

			
			// New Email body
			
			
			 BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setContent(getHtmlBody(),"text/html");

	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         
	         System.out.println("screenshot array size "+writeHtml.ScreenshotsList.size());
	         
	         if(writeHtml.ScreenshotsList.size()>0) {
	         
	         for(int x=0;x<writeHtml.ScreenshotsList.size();x++) {
	        	 
	        	 try{
	        	 messageBodyPart = new MimeBodyPart();
	 	         String filename = writeHtml.ScreenshotsList.get(x);
	 	         DataSource source = new FileDataSource(filename);
	 	         messageBodyPart.setDataHandler(new DataHandler(source));
	 	         messageBodyPart.setFileName(filename.replace(CommonMethods.getVariableFromProperties("Dropbox")+"\\shots\\", ""));
	 	         multipart.addBodyPart(messageBodyPart);
	        	 }catch(Exception e){}
	        	 
	        	 
	         }
	         
	         
	         }
	         
	     /*    
	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = writeHtml.absoluteScreenshotPath;
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(writeHtml.vscreenshot.substring(8));
	         multipart.addBodyPart(messageBodyPart);
*/
	         // Send the complete message parts
	         generateMailMessage.setContent(multipart);
			
			
			
			
			
		//	generateMailMessage.setContent(getHtmlBody(), "text/html");
			
			
			System.out.println("Mail Session has been created successfully..");
	 
			// Step3
			System.out.println("\n\n 3rd ===> Get Session and Send mail");
			Transport transport = getMailSession.getTransport("smtp");
	 
		
			transport.connect("smtp.gmail.com", CommonMethods.getVariableFromProperties("emailer_email"), CommonMethods.getVariableFromProperties("emailer_pw"));
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			transport.close();
		}

public static String getHtmlBody() throws IOException{
	System.out.println("htmlpathsummary: "+writeHtml.htmlpathsummary);
	InputStream is = new FileInputStream(writeHtml.htmlpathsummary);
	BufferedReader buf = new BufferedReader(new InputStreamReader(is)); 
	String line = buf.readLine(); StringBuilder sb = new StringBuilder(); 
	while(line != null){ sb.append(line).append("\n"); 
	line = buf.readLine(); } 
	
	String fileAsString = sb.toString();

	return fileAsString;
	
}



	
	
}


