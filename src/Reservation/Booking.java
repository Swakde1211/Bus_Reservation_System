package Reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static Reservation.GlobalVariables.*;


public class Booking {
	
	public void bookingg()
	{
		
		
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
