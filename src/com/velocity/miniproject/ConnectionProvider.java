package com.velocity.miniproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ConnectionProvider {
	
	Connection con = null;
	PreparedStatement ps = null;
	Scanner sc = new Scanner(System.in);
	int count = 0;
	Quiz qu = new Quiz();
	
	public Connection getConnectionDetails() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "root");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public void insertUserData(String fname, String lname, String city, String mobileno) {
		try {
			con = getConnectionDetails();
			ps = con.prepareStatement("insert into user (firstName, lastName, city, mobileNo) values(?,?,?,?)");

			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, city);
			ps.setString(4, mobileno);

			int i = ps.executeUpdate();
			System.out.println("Record added " + i);

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void displayQuestion(String fname, String lname) {
		
		try {
			System.out.println("------------------Let's Start Quiz--------------------"+"\n");
			con = getConnectionDetails();
			ps = con.prepareStatement("select * from questionlist");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("QNo.:" + rs.getInt(1) + "\t");
				System.out.print(rs.getString(2)+"\n");
				System.out.println("a) " + rs.getString(3) + "\t" + "b) " + rs.getString(4) + "\t" + "c) "
						+ rs.getString(5) + "\t" + "d) " + rs.getString(6));
				checkAnswer(rs.getInt(1));
			}
			score(count, fname, lname);
			
			// close the resources
			con.close();
			ps.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkAnswer(int que)
	{
		try
		{
			System.out.println("\n"+"Enter answer (only in a,b,c,d format) : ");
			String ans = sc.next();
			if(ans.equals("a") || ans.equals("b") || ans.equals("c") || ans.equals("d"))
			{
			con = getConnectionDetails();
			ps = con.prepareStatement("select answer from questionlist where qno = ?");
			ps.setInt(1,que);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				String answer = rs.getString(1);
	
				if(answer.equals(ans))
				{
					count++;
				}
			}
			rs.close();
			}
			else
			{
				System.err.println("Please enter answer in correct format");
				checkAnswer(que);
			}
			con.close();
			ps.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void score(int marks, String fname, String lname)
	{
		try
		{
			con = getConnectionDetails();
			ps = con.prepareStatement("insert into student (firstName, lastName, score, grade) values(?,?,?,?)");

			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setInt(3, marks );
			if(marks >= 8 && marks <= 10)
			{
				ps.setString(4,"Class A" );
			}
			else if(marks >=6 && marks < 8)
			{
				ps.setString(4, "Class B");
			}
			else if(marks == 5)
			{
				ps.setString(4, "Class C");
			}
			else
			{
				ps.setString(4, "Class D");
			}
			
			int i = ps.executeUpdate();
			System.out.println("Record added " + i);
			
			ps = con.prepareStatement("select score, grade from student where firstName = ? and lastName = ?");
			ps.setString(1, fname);
			ps.setString(2, lname);
			System.out.println("------------------------------------------------------------------");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				System.out.println("Your Score is: "+rs.getInt(1));
				System.out.println("Your Grade is : "+rs.getString(2));
			}
			System.out.println("-------------------------------------------------------------------");
			qu.quizz();
			
			con.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void studentRecord()
	{
		try
		{
			con = getConnectionDetails();
			ps = con.prepareStatement("select * from student");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				System.out.println("First name : "+rs.getString(2));
				System.out.println("Last name : "+rs.getString(3));
				System.out.println("Score : "+rs.getInt(4));
				System.out.println("Grade : "+rs.getString(5)+"\n");
			}
			con.close();
			ps.close();
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void searchStudent(String f, String l)
	{
		try
		{
			con = getConnectionDetails();
			ps = con.prepareStatement("select * from student where firstName = ? and lastName = ?");
			ps.setString(1, f);
			ps.setString(2, l);
			System.out.println("-------------------------------------------------------------------");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				System.out.println("First name : "+rs.getString(2));
				System.out.println("Last name : "+rs.getString(3));
				System.out.println("Score : "+rs.getInt(4));
				System.out.println("Grade : "+rs.getString(5)+"\n");
			}
			System.out.println("--------------------------------------------------------------------");
			con.close();
			ps.close();
			rs.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


}
