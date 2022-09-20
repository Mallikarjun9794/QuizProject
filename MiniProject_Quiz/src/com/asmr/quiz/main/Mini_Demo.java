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

// Display all rec && Display specific record on id basis

	public void get_RecordBy_Id() throws SQLException, InterruptedException {

		displayRec();

		ownRec_Id();
	}

	// Display all rect from db with descending score base
	public void displayRec() throws SQLException {

		String sql3 = "select id,fname,marks from studinfo order by id";
		try {
			System.out.println();
			PreparedStatement ps3 = con.prepareStatement(sql3);

			ResultSet query = ps3.executeQuery();

			System.out.println("****** All recored inside the database:- *********");
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("" + "id" + "\t" + "name" + "\t" + "\t marks");
			System.out.println("-----------------------------------------------------------------------------");
			while (query.next()) {

				System.out.print(query.getInt(1));
				System.out.print("\t" + query.getString(2));
				System.out.print("\t \t   " + query.getInt(3));
				System.out.println();
			}
			ps3.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void ownRec_Id() throws InterruptedException  {
		s.formating();
		System.out.println();
		System.out.println("Do you want to see your Record:-(y/n)");
		char ch1 = sc.next().charAt(0);
		System.out.println();
		if (ch1 == 'y' || ch1 == 'Y') {
						
			//for valid input check 
			
			
			int flag = 0;
			int y = 0;
			while (flag == 0) {
				y = 0;
				try {			
					Scanner sc = new Scanner(System.in);
					System.out.println("Enter your id:-");
					int id = sc.nextInt();
					y=id;
					sc.close();
				} catch (Exception e) {
					System.out.println("Enter valid input");
					flag = 0;
				}
				if(y!=0) {
					flag=1;
				}
			}
			System.out.println("Enter your id: "+ y);
			
			// fetching data through id
			
			
			try {
				String sql4 = "select * from studinfo where id=?";
				PreparedStatement ps4 = con.prepareStatement(sql4);
				ps4.setInt(1, y);
		       
				
				
				ResultSet query = ps4.executeQuery();

				System.out.println("****** Your recored inside the database:- *********");
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("" + "id" + "\t" + "Fname" + "\t" + " Lname" + "\t" + "      Email" + "\t"
						+ "     Marks" + "\t" + "     Grade");
				System.out.println("-----------------------------------------------------------------------------");

				while (query.next()) {

					System.out.print(query.getInt(1));
					System.out.print("\t" + query.getString(2));
					System.out.print("\t" + query.getString(3));
					System.out.print("       " + query.getString(4));
					System.out.print("\t     " + query.getInt(5));
					System.out.print("\t              " + query.getString(6));
					System.out.println();
					System.out.println("-----------------------------------------------------------------------------");
					System.out.println("\n");

					s.formating1();
					System.out.print("🏁🖥️💥🙏 Thanks For Visiting Quiz 🙏💥 🖥️🏁");
					s.formating1();
					
				}
			
			
		}catch(Exception e) {
			System.out.println("Invalid Input");
			e.printStackTrace();
		}

		} else {
		        System.out.println("...Invalid Input....");
			System.out.println("**🏁🖥️💥🙏 Thanks For Visiting Quiz 🙏💥 🖥️🏁**");
			
		}
		
	}
}

