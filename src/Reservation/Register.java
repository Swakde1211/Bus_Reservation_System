package Reservation;

import static Reservation.GlobalVariables.*;

import java.util.regex.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import database.Conn;

public class Register {
	public void register() {
		String username;
		String psswd;
		String ph_no;
		String gender;
		String email;
		String f_name;
		int age;
		int userid = 0;
		boolean register = false;
		int pass_len;
		int ph_len;
		Connection c;
		Scanner sc = new Scanner(System.in);
		Matcher matcher;

		do {
			System.out.println("Enter Username : ");
			username = sc.next();
			try {
				Statement stmt = null;
				c = null;
				Conn conn = Conn.getConnection();
				c = conn.getDBConnection();

				stmt = c.createStatement();

				ResultSet rs = stmt.executeQuery("select * from public.\"Users\" where username='" + username + "';");
				if (!rs.isBeforeFirst()) {
					// System.out.println(rs.isBeforeFirst());
					System.out.println("Username Saved");
					register = true;
				} else {
					System.out.println("Please select another username!\nThis username is already taken.");
				}
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);

			}
		} while (!register);

		do {

			System.out.println("Enter Password : ");
			psswd = sc.next();
			pass_len = psswd.length();
			if (pass_len < 4) {
				System.out.println("Password Length must be greater than 4.Please Enter New Passsword.");
			}
		} while (pass_len < 4);
		System.out.println("Enter First Name : ");
		f_name = sc.next();
		System.out.println("Enter Age : ");
		age = sc.nextInt();

		do {
			System.out.println("Enter Phone Number : ");
			ph_no = sc.next();
			ph_len = ph_no.length();
			if (ph_len != 10) {
				System.out.println("Please Enter phone number of 10 digit");
			}
		} while (ph_len != 10);

		System.out.println("Enter Gender : (m/f)");
		gender = sc.next();
		if (gender.length() > 1) {
			gender = gender.substring(0, 1);
		}
		//System.out.println(gender);

		do {
			System.out.println("Enter Email-id : ");
			email = sc.next();
			String regex = "^[A-Za-z]+@{1}(.+)$";
			Pattern pattern = Pattern.compile(regex);
			matcher = pattern.matcher(email);
			//System.out.println(matcher.matches());
			if (!matcher.matches()) {
				System.out.println("Please Enter E-mail in proper format.");
			}
		} while (!matcher.matches());

		try {
			String sql2 = "INSERT INTO public.\"Users\"(username,password,phone,first_name,age,email_id,gender) VALUES (?, ?, ?, ?, ?, ?, ?);";
			Conn conn = Conn.getConnection();
			c = conn.getDBConnection();

			PreparedStatement s = c.prepareStatement(sql2);
			s.setString(1, username);
			s.setString(2, psswd);
			s.setString(3, ph_no);
			s.setString(4, f_name);
			s.setInt(5, age);
			s.setString(6, email);
			s.setString(7, gender);
			s.executeUpdate();

			// Saving data in global variables
			user_name = username;
			first_name = f_name;
			user_age = age;
			user_gender = gender;

			Statement stmt = c.createStatement();
			ResultSet rs1 = ((java.sql.Statement) stmt).executeQuery("SELECT max(id) as id FROM public.\"Users\"");
			while (rs1.next()) {
				userid = rs1.getInt("id");
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Hey "+first_name+" User id is "+userid);

	}
}
