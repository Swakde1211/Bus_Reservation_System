package user;

import java.sql.Statement;
import static Reservation.GlobalVariables.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

import database.Conn;

public class View_History {

	public static void view_history()
	{
		//SHOWS BOOKING HISTORY
		try {				
			int i=1;
			Statement stmt =null;
			Connection c = null;
			
			Conn conn = Conn.getConnection();
			c = conn.getDBConnection();
					
			stmt = c.createStatement();
			System.out.println("***********BOOKING HISTORY************");
			
			ResultSet rs = stmt.executeQuery("select * from public.\"booking\" where user_id='"+ user_id +"' order by date asc;");
			if(!rs.isBeforeFirst()) {
				System.out.println("You don't have any Booking history yet!");
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
			
			
			System.out.println("***********CANCELLATION HISTORY************");
			
			i=1;
			ResultSet rs1 = stmt.executeQuery("select * from public.\"cancellation_ticket\" where user_id='"+ user_id +"';");
			if(!rs1.isBeforeFirst()) {
				//System.out.println(rs.isBeforeFirst());
				System.out.println("You don't have any Cancellation history yet!");
			}
			while (rs1.next()) {
				System.out.print("Sr. No.: "+ i++);
				System.out.print("\tDate: "+rs1.getString("ticket_date"));
				System.out.print("\tBus ID: "+rs1.getInt("bus_id"));
				System.out.print("\tSource: "+rs1.getString("bus_source"));
				System.out.print("\tDestination: "+rs1.getString("bus_destionation"));
				System.out.print("\tBus Time: "+rs1.getString("bus_time"));
				System.out.print("\tBus Fare: "+rs1.getString("bus_fare"));
				System.out.print("\tTransaction ID: "+rs1.getInt("trans_id"));
				System.out.println("\tCancellation Date: "+rs1.getString("cancel_date"));
			}
		}
		
			
			
			
		
		catch (Exception e) { 	
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);

		}

	}
}
