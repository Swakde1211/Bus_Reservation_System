package Reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import static Reservation.GlobalVariables.*;

public class Cancel_Ticket_History {
	public static void cancel_ticket_historyy()
	{
		//SHOWS CANCEL TICKETS
		System.out.println("\n****************GETTING Cancel Ticket HISTORY****************\n");
		try {				
			int i=1;	
			Statement stmt =null;
			
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
			stmt = c.createStatement();
	
			ResultSet rs = stmt.executeQuery("select * from public.\"cancellation_ticket\" where user_id='"+ user_id +"';");
			if(!rs.isBeforeFirst()) {
				//System.out.println(rs.isBeforeFirst());
				System.out.println("You don't have any Cancellation history yet!");
			}
			while (rs.next()) {
				System.out.print("Sr. No.: "+ i++);
				System.out.print("\tDate: "+rs.getString("ticket_date"));
				System.out.print("\tBus ID: "+rs.getInt("bus_id"));
				System.out.print("\tSource: "+rs.getString("bus_source"));
				System.out.print("\tDestination: "+rs.getString("bus_destionation"));
				System.out.print("\tBus Time: "+rs.getString("bus_time"));
				System.out.print("\tBus Fare: "+rs.getString("bus_fare"));
				System.out.print("\tTransaction ID: "+rs.getInt("trans_id"));
				System.out.println("\tCancellation Date: "+rs.getString("cancel_date"));
			}
		}
		catch (Exception e) { 	
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);

		}

	}
}
