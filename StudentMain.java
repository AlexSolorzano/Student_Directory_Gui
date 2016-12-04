//package edu.uga.cs1302.gui

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JFrame;

/**
 * 
 * @author Alexandra Solorzano
 * This beautifully coded class,is the main method of my wonderful program. 
 * It has  frame. (: 
 *
 */
@SuppressWarnings("unused")
public class StudentMain {
	public static void main (String[] args) throws IOException, ParseException
	{	
		StudentDirectory frame = new StudentDirectory();
		frame.setVisible(true);	
	}

}
