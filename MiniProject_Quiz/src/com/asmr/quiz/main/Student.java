package com.asmr.quiz.main;

public class Student {
	String fname;
	String lname;
	String email;
	int marks;
	char grade;
	
	 Student() {
		
		
	}
	
	
	public Student(String fname, String lname, String email) {
		
		this.fname = fname;
		this.lname = lname;
		this.email = email;
	}
	public Student(String fname, String lname, String email, int marks, char grade) {
		
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.marks = marks;
		this.grade = grade;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public char getGrade() {
		return grade;
	}
	public void setGrade(char grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "Student [fname=" + fname + ", lname=" + lname + ", email=" + email + ", marks=" + marks + ", grade="
				+ grade + "]";
	}	
	
}
