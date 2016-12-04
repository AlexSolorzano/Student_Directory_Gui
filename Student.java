//package edu.uga.cs1302.gui
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;
/**
 * @author Alexandra Solorzano
 * This beautifully coded class defines what a student
 * is and includes getters/setters and extends Person Class.
 *
 */

@SuppressWarnings({ "serial", "unused" })
public class Student extends Person implements Serializable{
	private String collegeName;
	
	public Student()
	{
		
	}
	
	/**
	 * @param firstName
	 * @param lastName
	 * @param idNumber
	 * @param dateOfBirth
	 * @param collegeName
	 * @throws ParseException
	 */
	public Student(String firstName, String lastName, int idNumber, Date dateOfBirth, String collegeName) throws ParseException
	{
		super(firstName,lastName,idNumber, dateOfBirth);
		this.collegeName=collegeName;
	}
	
	/**
	 * 
	 * @param collegeName
	 * @throws ParseException
	 */
	public Student(String collegeName) throws ParseException
	{
		super();
		this.collegeName=collegeName;
		
	}
	
	/**
	 * 
	 * @param collegeName
	 */
	public void setCollegeName(String collegeName)
	{
		this.collegeName=collegeName;
	}
	
	/**
	 * 
	 * @return collegeName;
	 */
	public String getCollegeName()
	{
		return collegeName;
	}
	
	
	/**
	 * return string
	 */
	public String toString()
	{
		String information=super.toString()+" ["+this.collegeName+"]";
		return information;
	}
	
	public void storeObject(ObjectOutputStream out) throws IOException
	{
		out.writeObject(this);
	}
	public Student retrieveObject(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		Student x = (Student) in.readObject();//		System.out.println("DONE! YEET.");
		return x;
	}
	
}