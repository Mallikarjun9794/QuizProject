package com.asmr.quiz.main;

//try to give time for que

import java.util.Scanner;

public class ServiceIMPL {

		int marks = 0;

	// take stud info

	public Student stdInfo() {

		Student st = new Student();
		Scanner sc = new Scanner(System.in);
		System.out.println("enter your first name...:-");
		st.setFname(sc.next());

		System.out.println("enter your last name...:-");
		st.setLname(sc.next());

		System.out.println("enter your email...:-");
		st.setEmail(sc.next());

		return st;

	}

	// take answer choice form user

	public int getAnswer(char ans) throws InterruptedException  {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your choice");
		char ch = sc.next().charAt(0);

		// compare the ans
		if (ch <= 'd' && ch >= 'a') {
			if (ans == ch) {
				System.out.println("correct answer ✔️");
				marks++;
				marks = marks;
				
				formating();

			} else {
				System.out.println("Wrong answer... ✖️");
				
				formating();
			}

		} else {
			System.out.println("Enter correct option between mentioned...");

			getAnswer(ans);
		}

		return marks;
	}

	
	
	public void formating() throws InterruptedException {
		
		
		for (int i = 0; i < 100; i++) {
			System.out.print("~");
			
			Thread.sleep(35);
		}
	}
	
	
	public void formating1() throws InterruptedException {
		
		for (int i = 0; i < 5; i++) {
			System.out.print("❃.✮:");
			
			Thread.sleep(300);
		
	}
		
				
	}
	
	
	
}