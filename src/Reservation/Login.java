package Reservation;

import static Reservation.GlobalVariables.user_dest;
import static Reservation.GlobalVariables.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import database.Conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import exceptions.*
;	
public class Login {
		
		public static  void loginn() throws Exception
		{
			
			
			boolean flag = true;
			Connection c = null;
			Statement stmt =null;

			Scanner sc = new Scanner(System.in);
			System.out.println("Please Enter Username:");
			String name = sc.next();
			System.out.println("Please Enter Password:");
			String pass = sc.next();	
			int length = name.length();
			 
			//user exception for username
			  try {
			   if(length < 6)
			    throw new UsernameException("Username must be greater than 6 characters ???");
			  }
			  catch (UsernameException u) {
			   u.printStackTrace();
			  }
			
			  //checking username and pass from dataset
				 
			try {
	            			
				
				Conn conn = Conn.getConnection();
				c = conn.getDBConnection();
			
				stmt = c.createStatement();
				

				// Checking for admin
				if(name.equals("adminn") && pass.equals("adminn")) {
					System.out.println("Admin Login Successful");
					is_admin=true;
				}				
				else {
				ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Users\";");
				
				while (rs.next()) {
					int id = rs.getInt("id");
					String username = rs.getString("username");
					String password = rs.getString("password");
					System.out.println(username+" "+name);
					System.out.println(password+" "+pass);
					if (username.equals(name) && password.equals(pass)) {
						System.out.println("Successfully Logged In");
						user_id = id;
						user_name = username;

						flag = false;
						break;
					}
					
					else if (flag) {
						System.out.println("Wrong credentials! Please enter details again to Login");
						System.exit(0);
					}
				}
				}
			} 
			
			catch (Exception e) {
				System.out.println("Oops Login Again ! ");
						
			}
			if (flag && !is_admin) {
				System.out.println("Wrong credentials! Please enter details again to Login");
				System.exit(0);
			}
			
			
		//	System.out.println("Username -> " + user_name + " And Password is : " + user_id);
		

		}
		
	
}