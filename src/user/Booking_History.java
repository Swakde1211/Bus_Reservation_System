package user;

import java.sql.Statement;
import static Reservation.GlobalVariables.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class Booking_History {

	public static void booking_historyy()
	{
		//SHOWS BOOKING HISTORY
		try {				
			int i=1;
			Statement stmt =null;
			
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
			stmt = c.createStatement();
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
		}
		catch (Exception e) { 	
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);

		}

	}
}
