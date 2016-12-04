//package edu.uga.cs1302.gui
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * This class, so beautifully crafted 
 */
@SuppressWarnings({ "serial", "unused" })
public class StudentDirectory extends JFrame{

	protected JButton previous,next, append;
	protected JMenuBar menuBar;
	protected JMenu menu;
	protected JMenuItem save, exit, load;
	protected JFrame frame;
	protected JLabel idNumber, dateOfBirth, collegeName, firstName, lastName,indexNum;
	protected JTextField idNumberText, dateOfBirthText, firstNameText,lastNameText,collegeNameText;

	protected ArrayList<Student> students;
	SimpleDateFormat dateFormat= new SimpleDateFormat("MM/dd/yyyy");
	int studentIndex=0;
	/**
	 * This constructor sets up the whole GUI.
	 */
	public StudentDirectory(){
		students=new ArrayList<Student>();
		
		//Frame & JPanel
		frame = new JFrame("Student Directory");
		JPanel panel = new JPanel();
		JPanel menuPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel mainPanel = new JPanel();
		
		//Buttons
		next= new JButton("NEXT");
		previous= new JButton("PREVIOUS");
		append= new JButton("SUBMIT");
		save= new JMenuItem("SAVE");
		exit= new JMenuItem("EXIT");
		load= new JMenuItem("LOAD");
		
		//Menu
		menu = new JMenu("FILE");
		menu.add(load);
		menu.addSeparator();
		menu.add(save);
		menu.addSeparator();
		menu.add(exit);	
		menuBar=new JMenuBar();
		menuBar.add(menu);
	
		//Button Listeners
		append.addActionListener(new appendListener());	
		next.addActionListener(new nextListener());
		previous.addActionListener(new previousListener());
		save.addActionListener(new saveListener());
		exit.addActionListener(new exitListener());
		load.addActionListener(new loadListener());
		
		
		//Labels
		idNumber = new JLabel("ID NUMBER");
		dateOfBirth = new JLabel("DATE OF BIRTH");
		firstName = new JLabel("FIRST NAME");
		lastName = new JLabel("LAST NAME");
		collegeName= new JLabel("COLLEGE NAME");
		indexNum= new JLabel("STUDENT: 0 OUT OF "+students.size());
		
		//Text Fields
		idNumberText = new JTextField(8);
		dateOfBirthText = new JTextField(8);
		firstNameText = new JTextField(8);		
		lastNameText = new JTextField(8);
		collegeNameText = new JTextField(8);

		//Setting Up the Panel
		buttonPanel.add(previous);
		buttonPanel.add(next);
		buttonPanel.add(append);
		
		panel.add(idNumber);
		panel.add(idNumberText);
		panel.add(firstName);
		panel.add(firstNameText);
		panel.add(lastName);
		panel.add(lastNameText);
		panel.add(dateOfBirth);
		panel.add(dateOfBirthText);
		panel.add(collegeName);
		panel.add(collegeNameText);
		panel.add(indexNum);		
		
		//Main Panel +SubPanels
		mainPanel.add(menuPanel);
		mainPanel.add(panel);
		mainPanel.add(buttonPanel);
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));
		panel.setLayout(new GridLayout(6,2));
		buttonPanel.setLayout(new FlowLayout());

		//Frame Specifications
		frame.getContentPane().add(mainPanel);
		frame.setJMenuBar(menuBar);
		frame.validate();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(300, 300);	
		
		//Button Setup
		previous.setEnabled(false);
		next.setEnabled(false);
	}
	/**
	 * 
	 * This class give the user two options when they click the button
	 * To either begin to append a new student by clearing the fields
	 * or by submitting the already typed in student information and
	 * adding it to the file.
	 *
	 */
	public class appendListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{		
			if (append.getText().equals("APPEND"))
			{
				idNumberText.setText("");
				firstNameText.setText("");
				lastNameText.setText("");
				collegeNameText.setText("");
				dateOfBirthText.setText("");				
				
				next.setEnabled(false);
					
				append.setText("SUBMIT");
	

			}
			else if(append.getText().equals("SUBMIT"))
			{
				if(append.getText().trim().equals("")||idNumberText.getText().trim().equals("") || collegeNameText.getText().trim().equals("") || 
					firstNameText.getText().trim().equals("") || lastNameText.getText().trim().equals("") || dateOfBirthText.getText().trim().equals(""))
					{
						JOptionPane.showMessageDialog(getContentPane(),"Fill out everything");
					}
				else
				{
					Student s = new Student();
					s.setFirstName(firstNameText.getText());
					s.setLastName(lastNameText.getText());
				
					int idNum=0; boolean errorPresent=false, errorPresent2=false;
					try{
						errorPresent=false;
						idNum=Integer.parseInt(idNumberText.getText());
						s.setIdNumber(idNum);
					}
					catch (NumberFormatException error){
						JOptionPane.showMessageDialog(getContentPane(),"That's not a number, please fix it!");
						errorPresent=true;
					}
					s.setCollegeName(collegeNameText.getText());
					Date birthDate;
					try{
						errorPresent2=false;
						birthDate=dateFormat.parse(dateOfBirthText.getText());
						s.setDateOfBirth(birthDate);
					}catch(ParseException error2){
						JOptionPane.showMessageDialog(getContentPane(),"Please fix the date by formatting it to MM/DD/YYYY");
						errorPresent2=true;
					}
					if((errorPresent==false)&&(errorPresent2==false))
					{
							students.add(s);					
						
						if(studentIndex==0)
						{
							indexNum.setText("STUDENT: "+(studentIndex+1)+" out of "+students.size());
							previous.setEnabled(false);
							previous.setEnabled(false);
							studentIndex=studentIndex+1;
						}
						else
						{	
							studentIndex=studentIndex+1;
							indexNum.setText("STUDENT: "+studentIndex+" out of "+students.size());
							previous.setEnabled(true);
							if(studentIndex==students.size())
								next.setEnabled(false);
		
						}
						append.setText("APPEND");
					}
				}	
			}
		}
		
	}
/**	
 * This class previous goes to the previous student. It 
 * will only work if the user can actually go back.
 *
 */
	public class previousListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(append.getText().equals("SUBMIT"))
			{
				Student s=students.get(studentIndex);
				idNumberText.setText(Integer.toString(s.getIdNumber()));
				firstNameText.setText(s.getFirstName());
				lastNameText.setText(s.getLastName());
				collegeNameText.setText(s.getCollegeName());
				Date bd=s.getDateOfBirth();
				String birthdate=dateFormat.format(bd);
				dateOfBirthText.setText(birthdate);
				
				append.setText("APPEND");
				indexNum.setText("STUDENT: "+(studentIndex+1)+" out of "+students.size());

				if(studentIndex!= (students.size() -1))
					next.setEnabled(true);
				if(studentIndex== 0)
					previous.setEnabled(false);
	
			}
			else
			{
				Student s=students.get(studentIndex-1);
				idNumberText.setText(Integer.toString(s.getIdNumber()));
				firstNameText.setText(s.getFirstName());
				lastNameText.setText(s.getLastName());
				collegeNameText.setText(s.getCollegeName());
				Date bd=s.getDateOfBirth();
				String birthdate=dateFormat.format(bd);
				dateOfBirthText.setText(birthdate);
				studentIndex--;
				indexNum.setText("STUDENT: "+(studentIndex+1)+" out of "+students.size());
				if(studentIndex!= (students.size()-1))
					next.setEnabled(true);
				if(studentIndex== 0)
					previous.setEnabled(false);
			}
		
		}
	}

	public class nextListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
				studentIndex=studentIndex+1;
				idNumberText.setText(Integer.toString(students.get(studentIndex).getIdNumber()));
				firstNameText.setText(students.get(studentIndex).getFirstName());
				lastNameText.setText(students.get(studentIndex).getLastName());
				collegeNameText.setText(students.get(studentIndex).getCollegeName());
				Date bd=students.get(studentIndex).getDateOfBirth();
				String birthdate=dateFormat.format(bd);
				dateOfBirthText.setText(birthdate);	
				
				indexNum.setText("STUDENT: "+(studentIndex+1)+" out of "+students.size());

				if(studentIndex== (students.size() -1)){
					next.setEnabled(false);
				}
				if(studentIndex!= 0){
					previous.setEnabled(true);
				}
				if(studentIndex!= (students.size() -1)){
					next.setEnabled(true);
				}	
		}
		
	 }
/*
 * This save actionlistener saves the students created in the program 
 * to the student file.	
 */
public class saveListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {

				try{
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/StudentList.dat"));
					for(Student s: students)
					{
						s.storeObject(out);
					}
						out.close();
				}
				catch(FileNotFoundException error){
					System.out.println("File not Found");
					error.printStackTrace();
				}
				catch(IOException error){
					System.out.println("IO Exception");
					error.printStackTrace();
				}
				}
		
			
	}
	/**	
	 *This exit actions listener terminates the program and saves whatever students 
	 *the user may have added.
	 *
	 */
	public class exitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/StudentList.dat"));
				for(Student s: students)
				{
					s.storeObject(out);
				}
					out.close();
			}
			catch(FileNotFoundException error){
				System.out.println("File not Found");
				error.printStackTrace();
			}
			catch(IOException error){
				System.out.println("IO Exception");
				error.printStackTrace();
			}
			System.exit(0);			
		}
		
	}
	/**
	 *
	 * This load listener loads the previously saved file.
	 *
	 */
	public class loadListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			FileInputStream in;
			ObjectInputStream inStream;
			try {
				in = new FileInputStream("src/StudentList.dat");
				inStream = new ObjectInputStream(in);
				Student s = new Student();
				while(in.available()>0)
				{
					try{
						s=s.retrieveObject(inStream);
						students.add(s);			
					}	
					catch(EOFException error3){
						return;
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Student s = students.get(0);
			int id4 = s.getIdNumber();
			String dob5 = dateFormat.format(s.getDateOfBirth());
			String fixed = String.valueOf(id4);
			idNumberText.setText(fixed);
			firstNameText.setText(s.getFirstName());
			lastNameText.setText(s.getLastName());
			collegeNameText.setText(s.getCollegeName());
			dateOfBirthText.setText(dob5);
			previous.setEnabled(false);
			next.setEnabled(true);
			append.setText("APPEND");
			indexNum.setText("STUDENTS: " + (studentIndex +1) + " OUT OF " + (students.size()));
			
		}
	}	
}
