package user;

import static Reservation.GlobalVariables.*;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import database.Conn;

import java.util.*;


interface RefundDesign{
	static void refundd() {};
}

public class Refund implements RefundDesign {
	public static String get_name(int p_id) {
		int get_p_id=p_id;
		String user_first_name=null;

		if(get_p_id==0){	
			user_first_name=first_name;
		}
		else {	
			try {
			Statement stmt =null;
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");			stmt = c.createStatement();
				ResultSet rs2 = stmt.executeQuery("SELECT first_name FROM public.\"passanger\" WHERE p_id="+get_p_id+";");
				while (rs2.next()) {
					user_first_name=rs2.getString("first_name");					
				}
			}catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
		}
		return user_first_name;
	}

	
	public static void refundd()
	{
		Scanner sc=new Scanner(System.in);
		int get_p_id;
		int p_id=-1;
		String date1;
		String user_name_str;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = new Date();
	    date1=formatter.format(date);
		
		try {
			Statement stmt =null;
			
			Connection c = null;
			
			Conn conn = Conn.getConnection();
			c = conn.getDBConnection();
			
			stmt = c.createStatement();
	
			//GETTING TICKET DETAILD FROM USER ID
		ResultSet rs1 = stmt.executeQuery("SELECT * FROM public.\"booking\" WHERE user_id="+user_id+" and date='"+date1+"';");
		if(!rs1.isBeforeFirst()) {		
			System.out.println("You Don't have booking history today!");	
			System.exit(0);				
		}


		
		System.out.println("****************Cancelling Your Ticket !****************\n");
		System.out.println("Hey "+first_name+",Please Select Transaction ID of Ticket to Cancel Your Ticket.\n");
		while (rs1.next()) {
			System.out.print("Transaction ID: "+rs1.getInt("trans_id"));
			get_p_id=rs1.getInt("p_id");
			user_name_str=get_name(get_p_id);
			System.out.print("\tName: "+user_name_str);
			System.out.print("\tDate: "+rs1.getString("date"));
			System.out.print("\tBus ID: "+rs1.getInt("bus_id"));
			System.out.print("\tBus Souce: "+rs1.getString("bus_source"));
			System.out.print("\tBus Destination: "+rs1.getString("bus_destination"));
			System.out.print("\tBus Time: "+rs1.getString("bus_time"));
			System.out.print("\tBus Fare: "+rs1.getString("bus_fare"));
			System.out.println("\tBus Number: "+rs1.getString("bus_number"));
		}
		
		}
		catch (Exception e) {
			System.out.println("Invalid Transaction ID");		
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Please Enter Your Transaction ID : ");
		double t_id=sc.nextInt(); 				
		
		try {
			Statement stmt =null;
			
			//Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
Connection c = null;
			
			Conn conn = Conn.getConnection();
			c = conn.getDBConnection();
			
			stmt = c.createStatement();
	
			//GETTING TICKET DETAILD FROM TRANSACTION ID
		ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"booking\" WHERE trans_id="+t_id+";");
		if(!rs.isBeforeFirst()) {		
			System.out.println("Invalid Transaction ID!");	
			System.exit(0);				
		}
		while (rs.next()) {
			tranc_date=rs.getString("date");
			transaction_id=rs.getInt("trans_id");
			user_id=rs.getInt("user_id");
			bus_id=rs.getInt("bus_id");			 
			System.out.println("Your Transaction Details are");
			//System.out.println("Your User Id is:" + rs.getString("user_id"));
			System.out.println("Your Bus pick location is:" + rs.getString("bus_source"));
			user_source=rs.getString("bus_source");
			System.out.println("Your Bus drop location is:" + rs.getString("bus_destination"));
			user_dest=rs.getString("bus_destination");
			System.out.println("Your Bus Number is:" + rs.getString("bus_number"));
			bus_number = rs.getString("bus_number");
			System.out.println("Your Bus Time is:" + rs.getString("bus_time"));
			bus_time=rs.getString("bus_time");
			System.out.println("Your Bus fare is:" + rs.getString("bus_fare"));
			user_fare= Integer.parseInt(rs.getString("bus_fare"));
			
			
		}
		
		}
		catch (Exception e) {
			System.out.println("Invalid Transaction ID");		
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
	
		
		//INSERTING IN CANCELLATION TABLE
		
		try {
			Statement stmt =null;
			
			Connection c = null;
			
			Conn conn = Conn.getConnection();
			c = conn.getDBConnection();
			
			stmt = c.createStatement();
	
			String sql2 ="INSERT INTO public.\"cancellation_ticket\"(user_id, trans_id, bus_source, bus_destionation, bus_time, bus_fare, bus_id, bus_number, ticket_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement s = c.prepareStatement(sql2);	
            //System.out.println(s.executeUpdate());
            s.setDouble(1, user_id);
			s.setInt(2, transaction_id);
			s.setString(3, user_source);
			s.setString(4, user_dest);
			s.setString(5, bus_time);
			s.setDouble(6, user_fare);
			s.setInt(7, bus_id);
			s.setString(8, bus_number);
			s.setString(9,tranc_date);
            s.execute();
			System.out.println("Successfully installed in table cancellation");
		}
		catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		//DELETING TICKET FROM BOOKING TABLE
		try {
			Statement stmt =null;
			
			Connection c = null;
			
			Conn conn = Conn.getConnection();
			c = conn.getDBConnection();
			
			stmt = c.createStatement();
			String sql2 ="DELETE FROM public.\"booking\" WHERE trans_id="+t_id +";";
            PreparedStatement s = c.prepareStatement(sql2);
            s.execute();
            System.out.print("Deleted "+t_id);
            System.out.println(" & Bus ID "+bus_id);
		}
		catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		//UPDATING SEAT+1
		try {
			Statement stmt =null;
			
			Connection c = null;
			
			Conn conn = Conn.getConnection();
			c = conn.getDBConnection();
			
			stmt = c.createStatement();
	
			stmt.execute("UPDATE public.\"bus_info\" SET seats_available=seats_available+1 WHERE bus_id=" + bus_id + ";");
			System.out.println("Seat Decreamented by 1");
			// System.out.println(rs.getFetchSize());
		} catch (Exception e) { 	
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
	}
	
}
