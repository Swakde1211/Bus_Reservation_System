package Reservation;

import static Reservation.GlobalVariables.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class Refund {

	public static void refundd()
	{
		Scanner sc=new Scanner(System.in);
		
		System.out.println("****************Cancelling Your Ticket !****************");
		System.out.println();
		System.out.println("Please Enter Your Transaction ID : ");
		int t_id=sc.nextInt(); 				//Today
		try {
			Statement stmt =null;
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
			stmt = c.createStatement();	
			
			//GETTING TICKET DETAILD FROM TRANSACTION ID
		ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"booking\" WHERE trans_id="+t_id+";");
		while (rs.next()) {
			tranc_date=rs.getString("date");
			transaction_id=rs.getInt("trans_id");
			bus_id=rs.getInt("bus_id");			//Today - maybe
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
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		if(t_id!=bus_id) {			//Today
			System.out.println("Wrong Bus ID");		//Today
			System.exit(0);			//Today
		}
		

		try {
			
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
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
            s.executeUpdate();
			System.out.println("Successfully installed in table cancellation");
		}
		catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		//DELETING TICKET FROM BOOKING TABLE
		try {
			
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
			String sql2 ="DELETE FROM public.\"booking\" WHERE trans_id="+t_id +";";
            PreparedStatement s = c.prepareStatement(sql2);
            s.executeUpdate();
            System.out.println("Deleted "+t_id);
            System.out.println("Bus ID "+bus_id);
		}
		catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		//UPDATING SEAT+1
		try {
			Statement stmt =null;
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
			
			stmt = c.createStatement();
			
			stmt.executeUpdate("UPDATE public.\"bus_info\" SET seats_available=seats_available+1 WHERE bus_id=" + bus_id + ";");
			System.out.println("Seat Decreamented by 1");
			// System.out.println(rs.getFetchSize());
		} catch (Exception e) { 	
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		
		
	
	}
}
