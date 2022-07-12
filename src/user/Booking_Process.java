package user;

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

import exceptions.*
;
public class Booking_Process {

	
	public static void printMenu(String[] options) {

		for (String option : options) {
			System.out.println(option);
		}
		System.out.print("Choose your option : ");
	}

	public static void booking_processs()
	{
		
		Scanner scanner = new Scanner(System.in);
		Scanner sc = new Scanner(System.in);
	
		//data taken from user for booking ticket
		System.out.println("********Booking Your Ticket!************");
		int matched_bus=0;
		System.out.println("Add source");
		String Source = sc.next();
		Source = Source.substring(0,1).toUpperCase() + Source.substring(1).toLowerCase(); 
		System.out.println("Pick Up Point : "+Source);	
		user_source = Source;
		System.out.println("Add Destination");
		String Destination = sc.next();
		
		Destination = Destination.substring(0,1).toUpperCase() + Destination.substring(1).toLowerCase();	
		System.out.println("Drop off Point : "+Destination);		
		user_dest = Destination;

		try {	
			Statement stmt =null;		
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
						stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery("select count(*) as count from public.\"bus_info\" where source='"+user_source+"' and destination='"+user_dest+"';");	
				while (rs.next()) {		
					String count=rs.getString("count");
					//System.out.println(count);
					if(count.equals("0")) {		
						System.out.println("\nSorry! We Don't Have Buses On Your Route!");	
						System.exit(0);	
					}
					System.out.println("We have "+ rs.getString("count") +" Buses On Your Route");
				
				}
			}
				catch (Exception e) {
					System.err.println(e.getClass().getName() + ": " + e.getMessage());
					// System.exit(0);
				}
				
		
		//Added age Exception here
		try {
			  
		boolean offer_applied=false;
		System.out.println("Add Age");
		int Age = sc.nextInt();
		user_age = Age;
		if(user_age<=0 ||user_age>=100 ) 
		{
			throw new AgeException("Age cannot be 0 or greater than 100");
		}
		
		
		if (Age < 5) {
			System.out.println("You dont need any ticket!!");
			System.exit(0);
			
		} else if (Age > 60 || user_age< 12) {
			offer_applied=true;
			System.out.println("You will get concession 20% on ticket!!");

		}
		}
		catch(AgeException exp){
			System.out.println(exp) ;
			System.out.println("Please try again!!") ;
			System.exit(0);
		}
		
			
		
		System.out.println("Your Gender(f or m)");	
		String Gender = sc.next();					
		user_gender = Gender;						

		if (Gender.equals("F") || Gender.equals("f")) {
			System.out.println("You will get concession 10% on ticket!!");
		}
		else if(Gender.equals("M") || Gender.equals("m")) {
		}
		else {
			System.out.println("Invalid Input!");
			System.exit(0);
		}

		System.out.println("Bus Type");
		String[] options = { "1- AC", "2- NON AC" , "3- ALL"}; 	 
		printMenu(options);
		int option;
		int attempt=0;		
		do {				
		option = scanner.nextInt();
		if (option == 1) {
			bus_type1 = "AC";
			break;
		}else if(option==2) {
			bus_type1 = "Non AC";
			break;
		}else if(option==3) {		
			bus_type1= "true";		
			break;					
		}							
		else {						
			System.out.println("Enter Valid Option!");	
			attempt++;				
		}							
		}while(attempt!=3);			
		if(attempt==3) {			
			System.out.println("You have entered wrong information");		
			System.exit(0);	
		}	
		if(bus_type1=="AC" || bus_type1=="Non AC") {
			System.out.println("you have choosen " + bus_type1);				
		}
				try {  
					Statement stmt =null;
					
					Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
					
					stmt = c.createStatement();
			
					ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"bus_info\" where source = '"+ user_source +"' and destination ='"+user_dest+"';"); 

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
				
				if(bus_type1!="true") { 
					//CHECKING FOR AC OR NON-AC BUSES	 
					if (bus_type1.equals(bus_type) && seats_available != 0) { 
						matched_bus++;
						if (user_age < 5 ) {
							System.out.println("You dont need any ticket!!");
							System.exit(0);
						} else if (user_age > 60) {
							if (display) {
								System.out.println("You will get 20% concession on ticket!!");
								display = false;
							}
							
							double bus_fare3 = Integer.parseInt(bus_fare);
							user_fare = bus_fare3 * 0.8;
						} else if (user_gender.equals("F") || user_gender.equals("f")) {
							if (display) {
								System.out.println("You will get 10% concession on ticket!!");
								display = false;
							}
							double bus_fare2 = Integer.parseInt(bus_fare);
							user_fare = bus_fare2 * 0.9;
													}
							System.out.println("Number-" + i + "\tbus_id =" + id + "\tBus number:" + bus_number
									+ "\tBus Time:" + bus_time + "\tSource: " + source + "\tDestination:" + destination
									+ "\tTicket Price:" + user_fare + "\tBus Type:" + bus_type);
						
						i++;
					} //END OF IF LOOP
				}
				else { 
					if (seats_available != 0) { 
						matched_bus++;
						if(user_age<0) {
							System.out.println("User age cannot be negative");
							System.exit(0);
						}
						if (user_age < 5) {
							System.out.println("You dont need any ticket!!");
							System.exit(0);
						} else if (user_age > 60 || user_age< 12) {
							if (display) {
								System.out.println("You will get 20% concession on ticket!!");
								display = false;
							}
							double bus_fare3 = Integer.parseInt(bus_fare);
							user_fare = bus_fare3 * 0.8;
							
						} else if (user_gender.equals("F") || user_gender.equals("f")) {
							if (display) {
								System.out.println("You will get 10% concession on ticket!!");
								display = false;
							}
							double bus_fare2 = Integer.parseInt(bus_fare);
							user_fare = bus_fare2 * 0.9;
						}
							System.out.println("Number-" + i + "\tbus_id =" + id + "\tBus number:" + bus_number
									+ "\tBus Time:" + bus_time + "\tSource: " + source + "\tDestination:" + destination
									+ "\tTicket Price:" + user_fare + "\tBus Type:" + bus_type);
						
						i++;
					}
					
				}		
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
		int get_id = 0;		
		boolean check_bus_id=false;		
		
		//SHOW ALL AVAILABLE BUS_INFO 
		
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
		
		}
		
		if(check_bus_id) { 		
			System.exit(0);		
		}		
		
		//UPDATE SEAT AVAILABLE IN BUS_INFO TABLE
		try {
			Statement stmt =null;
			
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
			stmt = c.createStatement();
	
			stmt.execute(
					"UPDATE public.\"bus_info\" SET seats_available=seats_available-1 WHERE bus_id=" + bus_id + "");
			} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		
		
		//BOOKING SPECIFIC BUS  
		
		try {	Statement stmt =null;
		
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
				System.out.println("Your Bus fare is:" + user_fare); 
				System.out.println("Your Seat in Bus Id " + bus_id + " is confirmed");
			}

			} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
			
		//Adding data into booking table
		try {
				Statement stmt =null;
				
				Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
				
				stmt = c.createStatement();
		
            
			String sql ="INSERT INTO public.\"booking\"(user_id, bus_source, bus_destination, bus_time, bus_fare, bus_id, bus_number)VALUES (?, ?, ?, ?, ?, ?, ?);";
    		
    		
            PreparedStatement s = c.prepareStatement(sql);

            s.setDouble(1, user_id);
			System.out.println(user_id);
			s.setString(2, user_source);
			s.setString(3, user_dest);
			s.setString(4, bus_time);
			s.setDouble(5, user_fare);
			s.setInt(6, bus_id);
			s.setString(7, bus_number);
            s.execute();
			System.out.println("Successfully updated into booking table!");
			transaction_id++;
                
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}

	}
