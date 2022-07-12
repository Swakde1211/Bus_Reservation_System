package Reservation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Home {
	
	public static void main(String args[]) {
		// class for login of users
		Login l=new Login();
		l.loginn();
		
		//options given to 
		User_Input u=new User_Input();
		u.userinputt();
	
		
	}
}

	