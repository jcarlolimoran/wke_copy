package test;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MessageDialog {
	
	public int selection;
	JFrame frame; 
	
	public MessageDialog(){
		
		
		
	}
	
	
	public void displayEmailConfirmation(){
		frame = new JFrame();
		Object[] options = {"Send Email",
        "Don't Send Email"};
		selection = JOptionPane.showOptionDialog(frame,
			"Test has completed, would you like to send email report?",
			"Confirmation dialog",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,     //do not use a custom Icon
			options,  //the titles of buttons
			options[0]); //default button title
		
			System.out.println(selection);
		
	}
	
	public void displayMessage(){
		frame = new JFrame();
		Object[] options = {"Continue Test",
        "X"};
		selection = JOptionPane.showOptionDialog(frame,
			"Check CAPTCHA",
			"Confirmation dialog",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,     //do not use a custom Icon
			options,  //the titles of buttons
			options[0]); //default button title
		
			System.out.println(selection);
		
	}
	
	public int returnAnswer(){
		return selection;
	}
	
	public void displayConfiguration(){
		
		
		
		
	}

}
