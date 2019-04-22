package test;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Overlay {
	JTextField j1, j2, j3;
	JFrame frame ;
	
	public Overlay(){
		j1 = new JTextField();
		j2 = new JTextField();
		j3 = new JTextField();
		//j1.setBackground(new Color(255,0,255,0));
		frame = new JFrame("Transparent Window");
		frame.setUndecorated(true);
		
		
        frame.setBackground(new Color(0,0,0,0));
        
        frame.setAlwaysOnTop(true);
        frame.setEnabled(false);
        frame.setFocusableWindowState(false);
        
	}
	
	public void display(String s1, String s2, String s3){
		
		
        
        // Without this, the window is draggable from any non transparent
        // point, including points  inside textboxes.
        frame.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);

        frame.getContentPane().setLayout(new java.awt.BorderLayout());
       // frame.getContentPane().setl
      // frame.getContentPane().add(new JTextField(s1), java.awt.BorderLayout.NORTH);
       // frame.getContentPane().add(new JTextField(s2), java.awt.BorderLayout.SOUTH);
        
       //frame.getContentPane().add(j1(s1),java.awt.BorderLayout.NORTH);
        //frame.getContentPane().setBackground(new Color(255,255,0,0));
        frame.getContentPane().add(j1,java.awt.BorderLayout.NORTH);
        j1.setText(s1);
        
        frame.getContentPane().add(j2,java.awt.BorderLayout.SOUTH);       
        j3.setText(s2);
        
        frame.getContentPane().add(j3);
        j2.setText(s3);
        
        frame.pack();
        frame.setVisible(true);
		
	}

}
