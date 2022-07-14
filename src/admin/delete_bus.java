package admin;

import static Reservation.GlobalVariables.bus_id;
import static Reservation.GlobalVariables.bus_number;
import static Reservation.GlobalVariables.user_fare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import database.Conn;

public class delete_bus {
	Statement stmt =null;
	Connection c=null;
	public void delete_buss() {
		int bus_id;
		Scanner sc=new Scanner(System.in);
		//System.out.println("Inside delete bus");
		try {
		//Class.forName("org.postgresql.Driver");
		//c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");		System.out.println("DB connection successful");
			Conn conn = Conn.getConnection();
			c = conn.getDBConnection();

		}catch (Exception e) {
			System.out.println("Oops! Database Connection Failed");
		}
		
		
		
		try {
			//Class.forName("org.postgresql.Driver");
			//c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "June@2022");
			stmt = c.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT * FROM public.\"bus_info\"");
			System.out.println("Currently Runnimg Buses are : ");
			
			while (rs1.next()) {
				System.out.print("Bus Id: " + rs1.getString("bus_id"));
				System.out.print("\tBus Source: " + rs1.getString("source"));
				System.out.print("\tBus Destination: " + rs1.getString("destination"));
				System.out.print("\tBus Number: " + rs1.getString("bus_number"));
				System.out.print("\tBus Time: " + rs1.getString("bus_time"));
				System.out.println("\tBus fare: " + rs1.getString("bus_fare")); // Not giving result
			}
		}catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		
		System.out.println("\nPlease Enter Bus ID to Delete bus:");
		bus_id=sc.nextInt();
		try {
			stmt = c.createStatement();
			stmt.execute("DELETE FROM public.bus_info WHERE bus_id="+bus_id+";");
		// System.out.println(rs.getFetchSize());
			System.out.println("Bus "+bus_id+" Deleted Successfully!");
	} catch (Exception e) {
		System.err.println(e.getClass().getName() + ": " + e.getMessage());
		 System.exit(0);
	}
	}
	
}
