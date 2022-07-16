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
import static Reservation.GlobalVariables.ticket_count;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Booking_Process {
	static ArrayList<Integer> p_id = new ArrayList<Integer>();
	public static void choose_bus() {
		Scanner scanner = new Scanner(System.in);
		int matched_bus=0;
		int option;
		int attempt=0;		

		System.out.println("Select Bus Type");
		String[] options = { "1- AC", "2- NON AC" , "3- ALL"}; 	 
		printMenu(options);
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
				try {  //Latest below query
					Statement stmt =null;
					
					Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
stmt = c.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"bus_info\" where source = '"+ user_source +"' and destination ='"+user_dest+"' and seats_available >="+ticket_count+";"); //Latest

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
				
				if(bus_type1!="true") { //CHECKING FOR AC OR NON-AC BUSES	 
					if (bus_type1.equals(bus_type) && seats_available != 0) { 
						matched_bus++;
							System.out.println("Number-" + i + "\tbus_id =" + id + "\tBus number:" + bus_number
									+ "\tBus Time:" + bus_time + "\tSource: " + source + "\tDestination:" + destination
									+ "\tTicket Price:" + user_fare + "\tBus Type:" + bus_type+ "\tSeats Available:" + seats_available);
						
						i++;
					} //END OF IF LOOP
				}
				else { //CHECKING FOR ALL BUSES
					if (seats_available != 0) { // Latest
						matched_bus++;
						//get_fare(user_age,user_gender);
							System.out.println("Number-" + i + "\tbus_id =" + id + "\tBus number:" + bus_number
									+ "\tBus Time:" + bus_time + "\tSource: " + source + "\tDestination:" + destination
									+ "\tTicket Price:" + user_fare + "\tBus Type:" + bus_type+ "\tSeats Available:" + seats_available);
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
		System.out.println("Please Enter The Bus ID To Continue Booking");
		bus_id = scanner.nextInt();
		int get_id = 0;		
		boolean check_bus_id=false;		
		try {
			String user_fare_str;
			Statement stmt =null;
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");			stmt = c.createStatement();
	
		ResultSet rs = stmt.executeQuery("select * from public.\"bus_info\" where bus_id="+bus_id+";");	
		while (rs.next()) {		
			System.out.println("User fare stored");
			get_id=rs.getInt("bus_id");
			bus_time=rs.getString("bus_time");
			bus_number=rs.getString("bus_number");
			user_fare_str=rs.getString("bus_fare");
			user_fare = Integer.parseInt(user_fare_str);
			System.out.println(user_fare);
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
		
		if(check_bus_id) { //IF WRONG BUS ID ENTERED THEN AVOIDING INSERTION IN BOOKING TABLE		
			System.exit(0);		
		}
		//return bus_id;
	}
	
	public static void take_user_profile(int count) {
		String first_name;
		String last_name;
		String phone;
		int phone_length;
		
		//boolean call_choose_bus=true;
		int p_id_int=0;
		Statement stmt =null;		
		Scanner sc = new Scanner(System.in);
		if(count>0) {
			//TAKING PASSANGERS DETAILS
			for(int i=0;i<count;i++) {
				System.out.println("Please Enter Details Of User "+(i+1));
				System.out.println("Enter First Name :");
				first_name=sc.next();
				System.out.println("Enter Last Name :");
				last_name=sc.next();
				do {
					System.out.println("Enter Phone Number:");
					phone=sc.next();
					phone_length=phone.length();
					if(phone_length!=10) {
						System.out.println("You have entered wrong phone number.Please Enter 10 digit phone Number again.");
					}
				}while(phone_length!=10);
				System.out.println("Enter Gender :");
				user_gender=sc.next();
				System.out.println("Enter Age :");
				user_age=sc.nextInt();
				
				try {					
					Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
String sql ="INSERT INTO public.passanger(first_name, last_name, phone, gender, age, u_id)VALUES (?, ?, ?, ?, ?, ?)";
		    		PreparedStatement s = c.prepareStatement(sql);

			        //stmnt.executeUpdate(sql);
					//PreparedStatement s = c.prepareStatement(sql);
					s.setString(1, first_name);
					//s.setInt(2, transaction_id);
					s.setString(2, last_name);
					s.setString(3, phone);
					s.setString(4, user_gender);
					s.setInt(5, user_age);
					s.setInt(6, user_id);
		            s.execute();
		            System.out.println("Inserted into Passanger Table");
		            
		            //TAKING PASSANGER_ID TO STORING IT IN BOOKING TABLE
		             c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
		            stmt = c.createStatement();
						ResultSet rs = stmt.executeQuery("select max(p_id) as p_id from public.\"passanger\"");	
						if(!rs.isBeforeFirst()) {
							System.out.println("No Record Found!");
						}
						while (rs.next()) {	
							p_id_int=rs.getInt("p_id");
							p_id.add(p_id_int);	//Adding p_id in arrayList
						}
						System.out.println("P_ID is "+p_id_int);
				}catch (Exception e) {
					System.err.println(e.getClass().getName() + ": " + e.getMessage());
					// System.exit(0);
				}
			}
		}
	}
	
	public static void get_user_fare() {
		boolean display = true;
		Statement stmt=null;
		try {
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");

			stmt = c.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT * FROM public.\"bus_info\"WHERE bus_id=" + bus_id + "");
			while (rs1.next()) {
				String user_fare_str = rs1.getString("bus_fare");
				user_fare=Integer.parseInt(user_fare_str);
			}
			ResultSet rs2=stmt.executeQuery("Select * from public.\"Users\" where id="+user_id+";");
			while(rs2.next()) {
				user_gender=rs2.getString("gender");
				user_age=rs2.getInt("age");
			}
		}catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		if (user_age < 5 && user_age >0) {
			System.out.println("You dont need any ticket!!");
		} else if (user_age > 60) {
			user_fare = user_fare * 0.8;
		} else if (user_gender.equals("F") || user_gender.equals("f")) {
			user_fare = user_fare * 0.9;
			}
		System.out.println("Inside get fare "+user_fare+" and gender is "+user_gender);
	}
	
	public static void get_pass_fare(int p_id) {
		boolean display = true;
		Statement stmt=null;
		try {
			
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
stmt = c.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT * FROM public.\"bus_info\"WHERE bus_id=" + bus_id + "");
			while (rs1.next()) {
				String user_fare_str = rs1.getString("bus_fare");
				user_fare=Integer.parseInt(user_fare_str);
			}
			
			ResultSet rs2=stmt.executeQuery("Select * from public.\"passanger\" where p_id="+p_id+";");
			while(rs2.next()) {
				user_gender=rs2.getString("gender");
				user_age=rs2.getInt("age");
			}
			
		}catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		if (user_age < 5 && user_age >0) {
			System.out.println("You dont need any ticket!!");
		} else if (user_age > 60) {
			user_fare = user_fare * 0.8;
		} else if (user_gender.equals("F") || user_gender.equals("f")) {
			user_fare = user_fare * 0.9;
		}
		System.out.println("Inside get fare "+user_fare+" and gender is "+user_gender);
	}
	
	
	public static void printMenu(String[] options) {

		for (String option : options) {
			System.out.println(option);
		}
		System.out.print("Choose your option : ");
	}
	
	public static void 	book_ticket(){
		try {
			Statement stmt =null;
			
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
		stmt = c.createStatement();
				stmt.execute(
						"UPDATE public.\"bus_info\" SET seats_available=seats_available-"+ticket_count+" WHERE bus_id=" + bus_id + "");
				// System.out.println(rs.getFetchSize());
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				// System.exit(0);
			}
			try {Statement stmt =null;
			
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");

			stmt = c.createStatement();

				
				ResultSet rs1 = stmt.executeQuery("SELECT * FROM public.\"bus_info\"WHERE bus_id=" + bus_id + "");
				
				while (rs1.next()) {
					System.out.println("Your Booking Details are");
					System.out.println("Your Bus Id is:" + rs1.getString("bus_id"));
					System.out.println("Your Ticket count is:" + ticket_count);
					System.out.println("Your Bus pick location is:" + rs1.getString("source"));
					System.out.println("Your Bus drop location is:" + rs1.getString("destination"));
					System.out.println("Your Bus Number is:" + rs1.getString("bus_number"));
					bus_number = rs1.getString("bus_number");
					System.out.println("Your Bus Time is:" + rs1.getString("bus_time"));
					//get_pass_fare(p_id)
					System.out.println("Your Bus fare is:" + user_fare);
					//user_fare=rs1.getString("bus_fare");
					System.out.println("Your Seat in Bus Id " + bus_id + " is confirmed");
				}

				} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
				try {
					//Inserting passengers details in booking table
				for(int i=0;i<p_id.size();i++) {
					Statement stmt =null;
					Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");					stmt = c.createStatement();
					ResultSet rs = stmt.executeQuery("select * from public.\"passanger\" where p_id="+p_id.get(i)+";");	
					while (rs.next()) {
						user_age=rs.getInt("age");
						user_gender=rs.getString("gender");
						//get_fare();
					}
	            String sql ="INSERT INTO public.\"booking\"(user_id, bus_source, bus_destination, bus_time, bus_fare, bus_id, bus_number,p_id)VALUES (?,?, ?, ?, ?, ?, ?, ?);";
	    		PreparedStatement s = c.prepareStatement(sql);
		        //stmnt.executeUpdate(sql);
				//PreparedStatement s = c.prepareStatement(sql);
				s.setDouble(1, user_id);
				s.setString(2, user_source);
				s.setString(3, user_dest);
				s.setString(4, bus_time);
				get_pass_fare(p_id.get(i));
				s.setDouble(5, user_fare);
				s.setInt(6, bus_id);
				s.setString(7, bus_number);
				s.setInt(8, p_id.get(i));
	            s.execute();
				System.out.println("Successfully updated into booking table!");
				transaction_id++;      
			}
				}catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
		
				
	}

	public static void book_own_ticket() {

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
			try {Statement stmt =null;
			
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");

			stmt = c.createStatement();

				
				ResultSet rs1 = stmt.executeQuery("SELECT * FROM public.\"bus_info\"WHERE bus_id=" + bus_id + "");
				
				while (rs1.next()) {
					System.out.println("Your Owner Booking Details are");
					System.out.println(user_gender);
					System.out.println("Your Bus Id is:" + rs1.getString("bus_id"));
					System.out.println("Your Ticket count is:" + ticket_count);
					System.out.println("Your Bus pick location is:" + rs1.getString("source"));
					System.out.println("Your Bus drop location is:" + rs1.getString("destination"));
					System.out.println("Your Bus Number is:" + rs1.getString("bus_number"));
					bus_number = rs1.getString("bus_number");
					System.out.println("Your Bus Time is:" + rs1.getString("bus_time"));
					String user_fare_str = rs1.getString("bus_fare");
					user_fare=Integer.parseInt(user_fare_str);
					System.out.println(user_fare);
					get_user_fare();
					System.out.println("Your Bus fare is:" + user_fare);
					System.out.println("Your Seat in Bus Id " + bus_id + " is confirmed");
				}

			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
				try {
					Statement stmt =null;
					Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
stmt = c.createStatement();
					
	            String sql ="INSERT INTO public.\"booking\"(user_id, bus_source, bus_destination, bus_time, bus_fare, bus_id, bus_number,p_id)VALUES (?,?, ?, ?, ?, ?, ?, ?);";
	    		PreparedStatement s = c.prepareStatement(sql);
		        //stmnt.executeUpdate(sql);
				//PreparedStatement s = c.prepareStatement(sql);
				s.setDouble(1, user_id);
				s.setString(2, user_source);
				s.setString(3, user_dest);
				s.setString(4, bus_time);
				s.setDouble(5, user_fare);
				s.setInt(6, bus_id);
				s.setString(7, bus_number);
				s.setInt(8, 0);
	            s.execute();
				System.out.println("Successfully updated into booking table!");
				transaction_id++;
	                
			
				}catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}	
	}
	
	//EXECUTION STARTS FROM HERE
	public static void booking_processs()
	{
		
		Scanner scanner = new Scanner(System.in);
		Scanner sc = new Scanner(System.in);
		char user_choice;
		
		System.out.println("Booking Your Ticket!");
		int matched_bus=0;
		do {
		System.out.println("How many tickets do you want ?");
		ticket_count=sc.nextInt();
		if(ticket_count<=0) {
			System.out.println("Please Enter Valis Ticket Count");
		}
		}while(ticket_count<=0);
		
		System.out.println("Add source");
		String Source = sc.next();
		Source = Source.substring(0,1).toUpperCase() + Source.substring(1).toLowerCase(); 
		System.out.println("Pick Up Point : "+Source);	
		user_source = Source;
		System.out.println("Add Destination");
		String Destination = sc.next();
		Destination = Destination.substring(0,1).toUpperCase() + Destination.substring(1).toLowerCase();	//latest
		System.out.println("Drop off Point : "+Destination);		//latest
		user_dest = Destination;
		try {	
			Statement stmt =null;		
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");

			stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery("select count(*) as count from public.\"bus_info\" where source='"+user_source+"' and destination='"+user_dest+"' and seats_available >="+ticket_count+";");	
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
		int user_attempt=0;
		do {
		if(ticket_count==1) {
			System.out.println("Are you Booking ticket for yourself ?(y/n)");
			user_choice=sc.next().charAt(0);
			
		}
		else {
			System.out.println("Are you included in "+ticket_count+" tickets ?(y/n)");
			user_choice=sc.next().charAt(0);
			
		}
		//VALIDATING USER ANSWER - Y/N
		if(user_choice=='y' || user_choice=='Y') {
			user_choice='y';
			break;
		}
		else if(user_choice=='n' || user_choice=='N') {
			user_choice='n';
			break;
		}
		else {
			System.out.println("Please Enter Your Answer In Appropriate Format.\ny for 'Yes' and n for 'No'");
			user_attempt++;
		}
		}while(user_attempt!=3);
		if(user_attempt==3) {
			System.out.println("You have entered wrong option again.\nSuccessfully Exit!");
			System.exit(0);
		}
		//CALLING FUNCTIONS
		if(ticket_count==1 && user_choice=='y') {
			choose_bus();
			book_own_ticket();
		}else if(ticket_count==1 && user_choice=='n') {
			take_user_profile(1);
			choose_bus();
			book_ticket();		
		}else if(ticket_count!=1 && user_choice=='y') {
			take_user_profile(ticket_count-1);
			choose_bus();
			book_own_ticket();
			book_ticket();			
		}else if(ticket_count!=1 && user_choice=='n') {			
			take_user_profile(ticket_count);
			choose_bus();
			book_ticket();			
		}
			
	}}
