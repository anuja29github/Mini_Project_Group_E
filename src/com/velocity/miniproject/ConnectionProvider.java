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

}
