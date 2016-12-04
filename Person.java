//package edu.uga.cs1302.gui
import java.util.*;
import java.lang.Object;
import java.text.*;
import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * 
 * @author Alexandra Solorzano
 * This beautifully crafted coded creates a Person object
 *
 */
public class Person implements Serializable{

	private String firstName;
	private String lastName;
	private int idNumber;
	private Date dateOfBirth;
	protected SimpleDateFormat dateFormat= new SimpleDateFormat("MM/dd/yyyy");
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param idNumber
	 * @param dateOfBirth
	 * @throws ParseException
	 */
	public Person(String firstName, String lastName, int idNumber, Date dateOfBirth) throws ParseException
	{
		this.firstName=firstName;
		this.lastName=lastName;
		if(idNumber>=1000 && idNumber<=9999)
			this.idNumber=idNumber;
		this.dateOfBirth=dateOfBirth;
	}
	/**
	 * Empty Constructor
	 */
	public Person()
	{
		
	}

	/**
	 * return string
	 */
	public String toString()
	{
		String bday=dateFormat.format(getDateOfBirth());
		String information=idNumber+" "+firstName+" "+lastName+" "+bday;
		return information;
	}
	/**
	 * 
	 * @param firstName
	 */
	
public void setFirstName(String firstName)
	{
		this.firstName=firstName;
	}

	/**
	 * 
	
	 * @return
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName)
	{
		this.lastName=lastName;
	}
	/**
	 * 
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}
	/**
	 * 
	 * @param idNumber
	 */
	public void setIdNumber( int idNumber)
	{
		if(idNumber>=1000 && idNumber<=9999)
			this.idNumber=idNumber;
	}
	/**
	 * 
	 * @return
	 */
	public int getIdNumber()
	{
		return idNumber;
	}
	
	/**
	 * 
	 * @param dateOfBirth
	 * @throws ParseException
	 */
	public void setDateOfBirth(Date dateOfBirth) throws ParseException 
	{
		this.dateOfBirth=dateOfBirth;
	}
	/**
	 * 
	 * @return dateOfBirth
	 */
	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}
	


	/**
	 * @param o
	 */
	public boolean equals(Object o )
	{
		boolean equal=false;
		if(!( o instanceof Person))
			equal=false;
		Person p= (Person) o;
		if(this.idNumber==p.idNumber)
			equal=true;
		return equal;
			
		
	}
}
