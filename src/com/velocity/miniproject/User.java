package com.velocity.miniproject;

import java.util.Scanner;

public class User {
	
	ConnectionProvider connect = new ConnectionProvider();
	Quiz q = new Quiz();
	Scanner sc = new Scanner(System.in);

	public void selectOption(int ch)
	{
		switch(ch)
		{
		case 1: 
				String fname = validationF();
				String lname = validationL();
				String city = validationC();
				String mobno = validationMobile();
				
				connect.insertUserData(fname, lname, city, mobno);
				connect.displayQuestion(fname, lname);
				break;
				
		case 2: 
			System.out.println("-----------------------------List of Student are as below---------------------");
			connect.studentRecord();
			q.quizz();
			break;
			
		case 3:
			System.out.println("\n"+"Enter the first and last name of candidate to search for : ");
			String f = sc.next();
			String l = sc.next();
			connect.searchStudent(f,l);
			q.quizz();
			break;
		case 4: 
			System.out.println("***********************Thanks for attempting quiz**********************");
			System.out.println("-----------------------See You Soon------------------------------------");
			break;
			
			default: 
				System.out.println("Please Choose correct option");
		}
	}

	public String validationF() {
		System.out.println("Please Enter your first name : ");
		String fname = sc.nextLine();
		boolean b = fname.matches("[A-Z][a-z]*");
		if(b!=true)
		{
			System.err.println("Please enter valid first name");
			validationF();
		}
		return fname;
					
	}
	public String validationL() {
		System.out.println("Please Enter your last name : ");
		String lname = sc.nextLine();
		
		boolean b = lname.matches("[A-Z][a-z]*");
		if(b!=true)
		{
			System.err.println("Please enter valid last name"+"\n");
			validationL();
		}
		return lname;
	}
public String validationC() {
	System.out.println("Please Enter your city name : ");
	String city=sc.nextLine();

	boolean b = city.matches("[A-Z][a-z]*");
	if(b!=true)
	{
		System.err.println("Please enter valid city name"+"\n");
		validationC();
	}
	return city;
}
public String validationMobile() {
	System.out.println("Please Enter your Mobile Number: ");
	String no=sc.nextLine();

	boolean b = no.matches("[7-9][0-9]{9}");
	if(b!=true)
	{
		System.err.println("Please enter valid Mobile Number"+"\n");
		validationMobile();
	}
	return no;
}
}
