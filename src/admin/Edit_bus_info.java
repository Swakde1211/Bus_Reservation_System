package admin;

import static Reservation.GlobalVariables.bus_id;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import database.Conn;

public class Edit_bus_info {
	public void edit_bus_detils(String Column_name, String Column_value) {
		System.out.println("You Called "+ Column_name);
		try {
			Statement stmt =null;		
			Connection c =null;
			Conn conn = Conn.getConnection();
			c = conn.getDBConnection();
	
			stmt = c.createStatement();
					stmt.execute("UPDATE public.\"bus_info\" SET "+ Column_name+"='"+Column_value+"' WHERE bus_id=" + user_input + "");
					System.out.println("Bus Information Updated Successfully !");
				} catch (Exception e) {
					System.err.println(e.getClass().getName() + ": " + e.getMessage());
				}
		
	}
	public static int user_input;
	public void edit_bus_info() {
		Statement stmt =null;
		Connection c=null;
		Scanner sc=new Scanner(System.in);
			try {
				Conn conn = Conn.getConnection();
				c = conn.getDBConnection();

			}catch (Exception e) {
				System.out.println("Oops! Database Connection Failed");
			}
			
			try {
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
			System.out.println("\nPlease Enter Bus ID To Edit Bus Details : ");
			user_input=sc.nextInt();
			try {
				stmt = c.createStatement();
				ResultSet rs1 = stmt.executeQuery("SELECT * FROM public.\"bus_info\" where bus_id="+user_input+";");
				if(!rs1.isBeforeFirst()) {		
					System.out.println("You Have Entered Wrong Bus ID!!");	
					System.exit(0);				
				}
				while (rs1.next()) {
					System.out.print("Bus Id: " + rs1.getString("bus_id"));
					System.out.print("\tBus Source: " + rs1.getString("source"));
					System.out.print("\tBus Destination: " + rs1.getString("destination"));
					System.out.print("\tBus Number: " + rs1.getString("bus_number"));
					System.out.print("\tBus Time: " + rs1.getString("bus_time"));
					System.out.println("\tBus fare: " + rs1.getString("bus_fare")); 
				}
			}catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				// System.exit(0);
			}
			
			int user_edit_input=0;
			do {
			System.out.println("Please Select Choice:\n1 - Edit Bus Source\n2 - Edit Bus Destination\n3 - Edit Bus Time\n4 - Edit Bus Fare\n5 - Edit Bus Number\n6 - Edit Bus Type\n7 - Edit Seats Capacity\n8 - Exit.");
			
			if(sc.hasNextInt()) {
				user_edit_input=sc.nextInt();				
			}
			else {
				System.out.println("Invalid Input!");
				System.exit(0);
			}
			
			switch(user_edit_input) {
			case 1:
				System.out.println("Please Enter New Bus Source");
				String bus_source=sc.next();
				edit_bus_detils("source",bus_source);
				break;
				
			case 2:
				System.out.println("Please Enter New Bus Destination");
				String bus_dest=sc.next();
				edit_bus_detils("destination",bus_dest);
				break;
				
			case 3:
				System.out.println("Please Enter New Bus Time");
				String bus_time=sc.next();
				edit_bus_detils("bus_time",bus_time);
				break;
				
			case 4:
				System.out.println("Please Enter New Bus Fare");
				String bus_fare=sc.next();
				edit_bus_detils("bus_fare",bus_fare);
				break;
				
			case 5:
				System.out.println("Please Enter New Bus Number");
				String bus_number=sc.next();
				edit_bus_detils("bus_number",bus_number);
				break;
				
			case 6:
				System.out.println("Please Enter New Bus Type");
				String bus_type=sc.nextLine();
				edit_bus_detils("Bus_type",bus_type);
				break;
				
			case 7:
				System.out.println("Please Enter New Bus Seats Capacity");
				String bus_cap=sc.next();
				edit_bus_detils("seats_available",bus_cap);
				break;
			case 8:
				System.out.println("Exit!!");
				System.exit(0);
			default:
				System.out.println("You Have Entered Wrong Choice!!");
			}
			}while(user_edit_input!=8);
			
			
	}
}
