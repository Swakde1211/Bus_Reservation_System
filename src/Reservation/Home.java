package Reservation;

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
		Login l=new Login();
		l.loginn();
		
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

	