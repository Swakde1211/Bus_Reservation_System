package Reservation;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Login {
	public static int user_id;
	public static String user_name;
	
		
		public static  void loginn()
		{
			boolean flag = true;
			Connection c = null;
			Statement stmt =null;

			// TODO Auto-generated method stub
			Scanner sc = new Scanner(System.in);
			// Uncomment later

			System.out.println("Please Enter Username:");
			String name = sc.next();
			System.out.println("Please Enter Password:");
			String pass = sc.next();
			System.out.println(name + " "+pass);


			try {
	            			
				Class.forName("org.postgresql.Driver");
				c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
				// c.setAutoCommit(false);
				System.out.println("Databse Successfully Connected.");
				stmt = c.createStatement();

				// Uncomment later

				ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Users\";");
				while (rs.next()) {
					int id = rs.getInt("id");
					String username = rs.getString("username");
					String password = rs.getString("password");
					//System.out.println(username + " "+ password);
					if (username.equals(name) && password.equals(pass)) {
						System.out.println("Successfully Logged In");
						user_id = id;
						user_name = username;

						flag = false;
						break;
					}
				}

			} catch (Exception e) {
				System.out.println("Oops! ");
			
			}
			// Uncomment later

			if (flag) {
				System.out.println("Wrong credentials! Please enter details again to Login");
				System.exit(0);
			}
			System.out.println("Username -> " + user_name + " And Password is : " + user_id);
			// Login Code Completed


		}
		
	
}