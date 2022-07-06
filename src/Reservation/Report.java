package Reservation;
import java.sql.Connection;
import java.text.SimpleDateFormat;  	//Update
import java.util.Date;  			//update
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.Scanner;

public class Report {
public static void reportt()
{
	System.out.println("1 - Booking History of Today.\n2 - Booking History of specific date");
	Scanner sc = new Scanner(System.in);
	
	int date_choice=sc.nextInt();
	if(date_choice==1) {
	 	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    Date date = new Date();  
	    System.out.println("Today's Date : " +formatter.format(date));  
	    try {
			int i=1;	
			Statement stmt =null;
			
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
			stmt = c.createStatement();
	
			System.out.println("\n*********Booking Report*********");
			ResultSet rs = stmt.executeQuery("select * from public.\"booking\" where date='"+ formatter.format(date) +"';");
			if(!rs.isBeforeFirst()) {
				System.out.println("\tWe don't have any Booking Today!");
			}
			 while (rs.next()) {
				System.out.print("Sr. No.: "+ i++);
				System.out.print("\tDate: "+rs.getString("date"));
				System.out.print("\tBus ID: "+rs.getInt("bus_id"));
				System.out.print("\tSource: "+rs.getString("bus_source"));
				System.out.print("\tDestination: "+rs.getString("bus_destination"));
				System.out.print("\tBus Time: "+rs.getString("bus_time"));
				System.out.print("\tBus Fare: "+rs.getString("bus_fare"));
				System.out.print("\tTransaction ID: "+rs.getInt("trans_id"));
				System.out.println("\tBus Number: "+rs.getString("bus_number"));
			}
			 
			System.out.println("\n*********Cancellation Report*********");
			i=1;
			ResultSet rs1 = stmt.executeQuery("select * from public.\"cancellation_ticket\" where cancel_date='"+ formatter.format(date) +"';");
			if(!rs1.isBeforeFirst()) {
				System.out.println("\tWe don't have any Booking Today!");
			}
			while (rs1.next()) {
				System.out.print("Sr. No.: "+ i++);
				System.out.print("\tCancellation Date: "+rs1.getString("cancel_date"));
				System.out.print("\tBus ID: "+rs1.getInt("bus_id"));
				System.out.print("\tSource: "+rs1.getString("bus_source"));
				System.out.print("\tDestination: "+rs1.getString("bus_destionation"));
				System.out.print("\tBus Time: "+rs1.getString("bus_time"));
				System.out.print("\tBus Fare: "+rs1.getString("bus_fare"));
				System.out.print("\tTransaction ID: "+rs1.getInt("trans_id"));
				System.out.println("\tBus Number: "+rs1.getString("bus_number"));
			}
	    }
	    catch (Exception e) { 	
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);

		}
	}
	else if(date_choice==2) {
		System.out.println("Enter the date in yyyy-MM-dd format only : ");
		String date=sc.next();			//checking for invalid input is pending
		try {
			int i=1;	
			System.out.println("\n*********Booking Report*********");
			Statement stmt =null;
			
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
			stmt = c.createStatement();
	
			ResultSet rs = stmt.executeQuery("select * from public.\"booking\" where date='"+ date +"';");
			if(!rs.isBeforeFirst()) {
				//System.out.println(rs.isBeforeFirst());
				System.out.println("We don't have any Booking on "+ date +"!");
			}
			while (rs.next()) {
				System.out.print("Sr. No.: "+ i++);
				System.out.print("\tDate: "+rs.getString("date"));
				System.out.print("\tBus ID: "+rs.getInt("bus_id"));
				System.out.print("\tSource: "+rs.getString("bus_source"));
				System.out.print("\tDestination: "+rs.getString("bus_destination"));
				System.out.print("\tBus Time: "+rs.getString("bus_time"));
				System.out.print("\tBus Fare: "+rs.getString("bus_fare"));
				System.out.print("\tTransaction ID: "+rs.getInt("trans_id"));
				System.out.println("\tBus Number: "+rs.getString("bus_number"));
			}
			
			System.out.println("\n*********Cancellation Report*********");
			i=1;
			ResultSet rs1 = stmt.executeQuery("select * from public.\"cancellation_ticket\" where cancel_date='"+ date +"';");
			if(!rs1.isBeforeFirst()) {
				System.out.println("\tWe don't have any Booking Today!");
			}
			while (rs1.next()) {
				System.out.print("Sr. No.: "+ i++);
				System.out.print("\tCancellation Date: "+rs1.getString("cancel_date"));
				System.out.print("\tBus ID: "+rs1.getInt("bus_id"));
				System.out.print("\tSource: "+rs1.getString("bus_source"));
				System.out.print("\tDestination: "+rs1.getString("bus_destionation"));
				System.out.print("\tBus Time: "+rs1.getString("bus_time"));
				System.out.print("\tBus Fare: "+rs1.getString("bus_fare"));
				System.out.print("\tTransaction ID: "+rs1.getInt("trans_id"));
				System.out.println("\tBus Number: "+rs1.getString("bus_number"));
			}
	    }
	    catch (Exception e) { 	
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);

		}
	} 
	
}
}
