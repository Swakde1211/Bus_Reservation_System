package Reservation;

import static Reservation.GlobalVariables.bus_id;
import static Reservation.GlobalVariables.bus_number;
import static Reservation.GlobalVariables.bus_time;
import static Reservation.GlobalVariables.bus_type1;
import static Reservation.GlobalVariables.transaction_id;
import static Reservation.GlobalVariables.user_age;
import static Reservation.GlobalVariables.user_dest;
import static Reservation.GlobalVariables.user_fare;
import static Reservation.GlobalVariables.user_gender;
import static Reservation.GlobalVariables.user_id;
import static Reservation.GlobalVariables.user_source;

import java.sql.*;
import java.util.Scanner;

public class Booking_Process {

	public static void printMenu(String[] options) {

		for (String option : options) {
			System.out.println(option);
		}
		System.out.print("Choose your option : ");
	}

	public static void booking_processs()
	{
		
		Scanner sc=new Scanner(System.in);
		Scanner scanner=new Scanner(System.in);
		int matched_bus=0;
		System.out.println("Add source");
		String Source = sc.next();
		Source = Source.substring(0,1).toUpperCase() + Source.substring(1).toLowerCase(); //latest
		System.out.println("Pick Up Point : "+Source);	//latest
		user_source = Source;
		
		
		System.out.println("Add Destination");
		String Destination = sc.next();
		Destination = Destination.substring(0,1).toUpperCase() + Destination.substring(1).toLowerCase();	//latest
		System.out.println("Drop off Point : "+Destination);		//latest
		user_dest = Destination;

		
		System.out.println("Add Age");
		int Age = sc.nextInt();
		user_age = Age;
		if (Age < 5) {
			System.out.println("You dont need any ticket!!");
		} else if (Age > 60) {
			System.out.println("You will get concession ticket!!");

		}
		System.out.println("Your Gender");
		String Gender = sc.next();
		user_gender = Gender;

		if (Gender.equals("F") || Gender.equals("f")) {
			System.out.println("You will get concession ticket!!");
		}
		else if(Gender.equals("M") || Gender.equals("m")) {
		}
		else {
			System.out.println("Invalid Input!");
			System.exit(0);
		}

		System.out.println("Bus Type");

		
		
		
		
		System.out.println("Bus Type");
		String[] options = { "1- AC", "2- NON AC" , "3- ALL"}; 	//Today 
		printMenu(options);
		int option;
		int attempt=0;		//Today
		do {				//Today
		option = scanner.nextInt();
		if (option == 1) {
			bus_type1 = "AC";
			break;
		}else if(option==2) {
			bus_type1 = "Non AC";
			break;
		}else if(option==3) {		//Today
			bus_type1= "true";		//Today
			break;					//Today
		}							//Today
		else {						//Today
			System.out.println("Enter Valid Option!");	//Today
			attempt++;				//Today
		}							//Today
		}while(attempt!=3);			//Today
		if(attempt==3) {			//Today
			System.out.println("You have entered wrong information");		//Today
			System.exit(0);	//Today
		}	//Today
		System.out.println("you have choosen " + bus_type1);
				try {  //Latest below query
					
					Statement stmt =null;
					
					Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
					
					stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"bus_info\" where source = '"+ user_source +"' and destination ='"+user_dest+"';"); //Latest

			int i = 1;
			boolean display = true;
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
				
				if(bus_type1!="true") { //CHECKING FOR AC OR NON-AC BUSES	//Today complete if and else loop from here till ...
					//if (source.equals(user_source) && destination.equals(user_dest) && bus_type1.equals(bus_type) && seats_available != 0) { 
					if (bus_type1.equals(bus_type) && seats_available != 0) { //Latest
						// System.out.println("Source and des matched");
						matched_bus++;
						if (user_age < 5) {
							System.out.println("You dont need any ticket!!");
							System.exit(0);
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
					} //END OF IF LOOP
				}
				else { //CHECKING FOR ALL BUSES
					//if (source.equals(user_source) && destination.equals(user_dest) && seats_available != 0) {
					if (seats_available != 0) { // Latest
						matched_bus++;
						if (user_age < 5) {
							System.out.println("You dont need any ticket!!");
							System.exit(0);
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
					
				}		//Today ...till here. replace if loop with this if else.
			} // End of while loop
				
			/*
			 * rs.close();
			 * stmt.close();
			 * c.close();
			 */
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
		int get_id = 0;		
		boolean check_bus_id=false;		
		try {	

			Statement stmt =null;
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
			stmt = c.createStatement();
			
			

		ResultSet rs = stmt.executeQuery("select * from public.\"bus_info\" where bus_id="+bus_id+";");	
		while (rs.next()) {		
			get_id=rs.getInt("bus_id");		
		}									
		if(get_id != bus_id) {				
			System.out.println("Wrong Bus ID");	
			check_bus_id=true;				
			System.exit(0);					
		}									
		
		}									
		catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		
		if(check_bus_id) { //IF WRONG BUS ID ENTERED THEN AVOIDING INERTION IN BOOKING TABLE		
			System.exit(0);		
		}			
		try {

			Statement stmt =null;
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
			stmt = c.createStatement();	

			stmt.execute(
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

	
	
	
	
//	Booking b=new Booking();
	//b.bookingg();
	
		
	

	}
}






class Bookingg extends Booking_Process{
	
	public void bookingg()
	{
		
		Booking_Process bp =new Booking_Process();
		bp.booking_processs();
		
		try {
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
            //if (c != null) {

		//Statement stmnt=null;
		/*String sql = "INSERT INTO public.\"booking\"(user_id, trans_id, bus_source, bus_destination, bus_time, bus_fare, bus_id, bus_number)"
				+ "VALUES ("+user_id +","+transaction_id +","+ user_source+", "+ user_dest+", "+ bus_time+", "+ user_fare+", "+ bus_id+", "+ bus_number+");";
        */
        String sql ="INSERT INTO public.\"booking\"(user_id, bus_source, bus_destination, bus_time, bus_fare, bus_id, bus_number)VALUES (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement s = c.prepareStatement(sql);

        //stmnt.executeUpdate(sql);
		//PreparedStatement s = c.prepareStatement(sql);
		s.setDouble(1, user_id);
		//s.setInt(2, transaction_id);
		s.setString(2, user_source);
		s.setString(3, user_dest);
		s.setString(4, bus_time);
		s.setDouble(5, user_fare);
		s.setInt(6, bus_id);
		s.setString(7, bus_number);
        s.execute();
		System.out.println("Successfully updated into booking table!");
		transaction_id++;
            //}
	} catch (Exception e) {
		System.err.println(e.getClass().getName() + ": " + e.getMessage());
		System.exit(0);
	}

		
	}
	}
