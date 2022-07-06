package Reservation;

import static Reservation.GlobalVariables.bus_id;
import static Reservation.GlobalVariables.bus_number;
import static Reservation.GlobalVariables.bus_time;
import static Reservation.GlobalVariables.bus_type1;
import static Reservation.GlobalVariables.user_age;
import static Reservation.GlobalVariables.user_dest;
import static Reservation.GlobalVariables.user_fare;
import static Reservation.GlobalVariables.user_gender;
import static Reservation.GlobalVariables.user_source;

import java.sql.*;
import java.util.Scanner;

public class Booking_Process {

	public static void booking_processs()
	{
		System.out.println("show buses");
		int matched_bus=0;
		Scanner sc=new Scanner(System.in);
		try {
			
			Statement stmt =null;
			
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
			stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"bus_info\";");

			int i = 1;
			boolean display = true;
			// ArrayList<String> arrlist=new ArrayList<String>();
			while (rs.next()) {
				int id = rs.getInt("bus_id");
				String bus_number = rs.getString("bus_number");
				String bus_fare = rs.getString("bus_fare");
				String source = rs.getString("source");
				String destination = rs.getString("destination");
				String bus_time1 = rs.getString("bus_time");
				String bus_type = rs.getString("bus_type");
				int seats_available = rs.getInt("seats_available");
				user_fare = Integer.parseInt(bus_fare);
				bus_time=bus_time1;
				// String sq="COPY public.\"Loginn\" TO 'C:\\tmp\\login.csv' DELIMITER ',' CSV
				// HEADER";
				// stmt.executeQuery(sq);

				if (source.equals(user_source) && destination.equals(user_dest) && bus_type1.equals(bus_type)
						&& seats_available != 0) { // System.out.println("Source and des matched");
					matched_bus++;
					if (user_age < 5) {
						System.out.println("You dont need any ticket!!");
						break;
					} else if (user_age > 60) {
						if (display) {
							System.out.println("You will get 20% concession on ticket!!");
							display = false;
						}
						double bus_fare3 = Integer.parseInt(bus_fare);
						bus_fare3 = bus_fare3 * 0.8;
						user_fare = bus_fare3;
						System.out.println("Number-" + i + "\tbus_id =" + id + "\tBus number:" + bus_number
								+ "\tBus Time:" + bus_time + "\tSource: " + source + "\tDestination:" + destination
								+ "\tTicket Price : " + bus_fare3 + "\tBus Type : " + bus_type);
					} else if (user_gender.equals("F") || user_gender.equals("f")) {
						if (display) {
							System.out.println("You will get 10% concession on ticket!!");
							display = false;
						}
						double bus_fare2 = Integer.parseInt(bus_fare);
						bus_fare2 = bus_fare2 * 0.9;
						user_fare = bus_fare2;
						System.out.println("Number-" + i + "\tbus_id =" + id + "\tBus number:" + bus_number
								+ "\tBus Time:" + bus_time + "\tSource: " + source + "\tDestination:" + destination
								+ "\tTicket Price : " + bus_fare2 + "\tBus Type : " + bus_type);
					} else {
						System.out.println("Number-" + i + "\tbus_id =" + id + "\tBus number:" + bus_number
								+ "\tBus Time:" + bus_time + "\tSource: " + source + "\tDestination:" + destination
								+ "\tTicket Price:" + bus_fare + "\tBus Type:" + bus_type);
					}
					i++;
				}
				// System.out.println(arr);
			} // End of while loop

			
		}

		catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		if(matched_bus==0) {
			System.out.println("Sorry! We don't have buses on your rout yet.");
			System.exit(0);
		}
		
		System.out.println("Please Enter The Bus ID : ");
		bus_id = sc.nextInt();
		int get_id = 0;		//Today
		boolean check_bus_id=false;		//Today
		try {	//Today

			Statement stmt =null;
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
			stmt = c.createStatement();
			
			

		ResultSet rs = stmt.executeQuery("select * from public.\"bus_info\" where bus_id="+bus_id+";");	//Today
		while (rs.next()) {		
			get_id=rs.getInt("bus_id");		
		}									
		if(get_id != bus_id) {				//Today
			System.out.println("Wrong Bus ID");	//Today
			check_bus_id=true;				//Today
			System.exit(0);					//Today
		}									//Today
		
		}									//Today
		catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		
		if(check_bus_id) { //IF WRONG BUS ID ENTERED THEN AVOIDING INERTION IN BOOKING TABLE		//Today
			System.exit(0);		//Today
		}			
		try {

			Statement stmt =null;
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
			stmt = c.createStatement();	

			ResultSet rs = stmt.executeQuery(
					"UPDATE public.\"bus_info\" SET seats_available=seats_available-1 WHERE bus_id=" + bus_id + "");
			// System.out.println(rs.getFetchSize());
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		try {

			Statement stmt =null;
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
			stmt = c.createStatement();
			
			

			ResultSet rs1 = stmt.executeQuery("SELECT * FROM public.\"bus_info\"WHERE bus_id=" + bus_id + "");
			
			while (rs1.next()) {
				System.out.println("Your Booking Details are");
				System.out.println("Your Bus Id is:" + rs1.getString("bus_id"));
				System.out.println("Your Bus pick location is:" + rs1.getString("source"));
				System.out.println("Your Bus drop location is:" + rs1.getString("destination"));
				System.out.println("Your Bus Number is:" + rs1.getString("bus_number"));
				bus_number = rs1.getString("bus_number");
				System.out.println("Your Bus Time is:" + rs1.getString("bus_time"));
				System.out.println("Your Bus fare is:" + user_fare); // Not giving result
				System.out.println("Your Seat in Bus Id " + bus_id + " is confirmed");
			}

			// ResultSet rs2 = stmt.executeQuery("INSERT INTO public.\"booking\"(user_id,
			// trans_id, bus_source, bus_destination, bus_time, bus_fare, bus_id,
			// bus_number)VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
			//String sql = "INSERT INTO public.\"booking\"(user_id, trans_id, bus_source, bus_destination, bus_time, bus_fare, bus_id, bus_number)VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	
	
	
	
	Booking b=new Booking();
	b.bookingg();
	
	

	}
}
