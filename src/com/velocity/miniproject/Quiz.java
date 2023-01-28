package com.velocity.miniproject;

import java.util.Scanner;

public class Quiz {
	
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
				
			System.out.println("\n\n"+"-------------------WELCOME TO JAVA QUIZ-------------------"+"\n");
			System.out.println("\n"+"Choose one option from below list"+"\n");
			System.out.println("1] Start Quiz"+"\n"+"2] Display all candidates record"+"\n"+"3] Search any one of the candidate record"+"\n"+"4] Exit");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt(); 
			User user = new User();
			user.selectOption(choice);
		}
	public void quizz()
	{
		System.out.println("If you want to Continue then press(1 for yes and 0 for no) : ");
		int i = sc.nextInt();
		if(i==1)
		{
			System.out.println("\n"+"Choose one option from below list"+"\n");
			System.out.println("1] Start Quiz"+"\n"+"2] Display all candidates record"+"\n"+"3] Search any one of the candidate record"+"\n"+"4] Exit");
	
			int choice = sc.nextInt(); 
			User user = new User();
			user.selectOption(choice);
		}
		else
		{
			System.out.println("***********************Thanks for attempting Quiz************************************");
			System.out.println("-----------------------See You Soon------------------------------------");
		}

	}

}
