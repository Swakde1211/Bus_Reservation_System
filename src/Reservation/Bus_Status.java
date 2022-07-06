package Reservation;

import static Reservation.GlobalVariables.user_dest;
import static Reservation.GlobalVariables.user_source;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bus_Status {
	public static void bus_statuss()
	{
		System.out.println( user_source +" -> "+user_dest);
		
		try {		
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
			Statement stmt =null;
			
			stmt = c.createStatement();
			
		ResultSet rs = stmt.executeQuery("select * from public.\"bus_info\" where source='"+ user_source +"'  and destination ='"+ user_dest +"';");
		while (rs.next()) {
			System.out.print("Bus ID:" + rs.getString("bus_id"));
			System.out.print("\tBus Number:" + rs.getString("bus_number"));
			System.out.print("\tBus Fare:" + rs.getString("bus_fare"));
			System.out.print("\tBus Source:" + rs.getString("source"));
			System.out.print("\tBus Destination:" + rs.getString("destination"));
			System.out.print("\tBus Time:" + rs.getString("bus_time"));
			System.out.print("\tBus Type:" + rs.getString("bus_type"));
			System.out.println("\tSeats Available:" + rs.getString("seats_available"));
		}
		} 
		catch (Exception e) { 	
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);

		}
		

	}

}
