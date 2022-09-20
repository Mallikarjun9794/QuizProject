package com.asmr.quiz.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Mini_Demo {
	
	Scanner sc = new Scanner(System.in);
	Connection con = null;

	ServiceIMPL s = new ServiceIMPL();
	Student stdinfo;
	int tmarks;

	public void getConnection() throws ClassNotFoundException, SQLException, InterruptedException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaquiz", "root", "Amol@123");

		getQuestion();
		get_RecordBy_Id();
		con.close();
	}

	// student registration & quiz starting
	public void getQuestion() throws SQLException, InterruptedException {
		System.out.println("");
		s.formating1();
		System.out.print("＊•̩̩͙✩•̩̩͙˚　💡💡💡   Welcome to Java Quiz   💡💡💡 ＊•̩̩͙✩•̩̩͙˚");
		s.formating1();
		System.out.println();
		System.out.println("=============================================================================================");
		s.formating1();
		System.out.print("✧･ﾟ: ✧･ﾟ:👨‍💻🖥  Organized By ~ J Group  🖥️👨‍💻✧･ﾟ: ✧･ﾟ:");
		s.formating1();
		System.out.println("\n");
		
		try {
			String sql = "insert into studinfo (fname,lname,email) values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);

			Student std = s.stdInfo();

			ps.setString(1, std.getFname());
			ps.setString(2, std.getLname());
			ps.setString(3, std.getEmail());

			int i = ps.executeUpdate();
			System.out.println("\t●○●○●○●○●○●○●○●○●○●○●○●○ Start Quiz ●○●○●○●○●○●○●○●○●○●○●○●○");
			
			ps.close();

			// take the que from DB
			String sql1 = "select * from quiz_info order by rand() limit 10";

			PreparedStatement ps1 = con.prepareStatement(sql1);

			ResultSet query = ps1.executeQuery();

			while (query.next()) {
				System.out.println();

				System.out.println(query.getString(2));
				System.out.println();
				System.out.println(query.getString(3));
				System.out.println(query.getString(4));
				System.out.println(query.getString(5));
				System.out.println(query.getString(6));
				System.out.println();
				// System.out.println("ans--> "+query.getString(7));

				// call to user choice answer

				String str = query.getString(7);
				// System.out.println(str);
				tmarks = s.getAnswer(str.charAt(0));

			}
				ps1.close();
			
				// calculate grade
			String grade; 
			if (tmarks >= 8 && tmarks <= 10) {
				grade = "A";
			} else if (tmarks < 8 && tmarks >= 6) {
				grade = "B";
			} else if (tmarks == 5) {
				grade = "C";
			} else {
				grade = "D";
			}

			String sql2 = "update studinfo set marks=?,grade=? where email=?";

			PreparedStatement ps2 = con.prepareStatement(sql2);

			ps2.setInt(1, tmarks);
			ps2.setString(2, grade);
			ps2.setString(3, std.getEmail());

			int n = ps2.executeUpdate();
			ps2.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
