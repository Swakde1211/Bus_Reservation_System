package Reservation;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import static Reservation.GlobalVariables.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import admin.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import exceptions.*
;
import user.User_Input;	

public class Home {
	
	public static void main(String args[]) throws Exception {
		// class for login of users
		int login_choice;
		Scanner sc=new Scanner(System.in);
		System.out.println("1-Register a new user\n2-SignIn");
		login_choice=sc.nextInt();
		if(login_choice==1) {
			Register rgs=new Register();
			rgs.register();
		}
		else {	
		Login l=new Login();
		l.loginn();
		}		
		
		if(is_admin) {
			admin ad=new admin();
			ad.adminn();
		}
		else {
			User_Input u=new User_Input();
			u.userinputt();			
		}
		
		
	}
}

	