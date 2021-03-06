package admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.*;
import java.util.Calendar;

import database.Conn;
public class View_booking_summary {
	Statement stmt =null;
	Connection c=null;
	String booking_sum;
	String count;
	int booking_sum_int;
	int count_int;
	String cancel_sum;
	String cancel_count;
	int cancel_sum_int;
	int cancel_count_int;
	public void truncate_table() {
		try {
			Connection c=null;
			Conn conn = Conn.getConnection();
			c = conn.getDBConnection();

			String sql3 ="TRUNCATE TABLE public.\"view_summary\";";
            PreparedStatement s = c.prepareStatement(sql3);
            s.executeUpdate();
		}catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
	}
	
	
	public void shortcut_booking(String date) {
		
		try {
			Connection c=null;
			Conn conn = Conn.getConnection();
			c = conn.getDBConnection();

			stmt = c.createStatement();
			
			ResultSet rs1 = stmt.executeQuery("SELECT SUM(CAST(bus_fare AS int)) as summ, count(*) as count FROM public.\"booking\" where date='"+date+"'");			
			while (rs1.next()) {
				booking_sum=rs1.getString("summ");
				count=rs1.getString("count");
				
			}
			booking_sum_int=Integer.parseInt(booking_sum);
			count_int=Integer.parseInt(count);
			
			
				String sql2 ="INSERT INTO public.\"view_summary\"(date,booking_count,booking_amount) VALUES (?,?,?);";
				PreparedStatement s = c.prepareStatement(sql2);
                s.setString(1, date);
                s.setInt(2, count_int);
                s.setInt(3, booking_sum_int);
	            s.executeUpdate();
		
			
			
			
		}catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
	}
	
	public void shortcut_cancellation(String date) {
		try {
			//System.out.print("Date : "+date+" ");
			//Class.forName("org.postgresql.Driver");
			//c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "June@2022");
			Connection c=null;
			Conn conn = Conn.getConnection();
			c = conn.getDBConnection();

			stmt = c.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT SUM(CAST(bus_fare AS int)) as summ, count(*) as count FROM public.\"cancellation_ticket\" where cancel_date='"+date+"'");			
			while (rs1.next()) {
				cancel_sum=rs1.getString("summ");
				cancel_count=rs1.getString("count");
				//System.out.print(" Bus Id: " +booking_sum );
				//System.out.println(" Count : " +count );
				
			}
			cancel_sum_int=Integer.parseInt(cancel_sum);
			cancel_count_int=Integer.parseInt(cancel_count);
			//System.out.println("Cancel sum "+cancel_sum_int);
			//System.out.println("Cancel count "+cancel_count_int);
			
			//ResultSet rs=stmt.executeQuery("UPDATE public.\"view_summary\" SET (cancellation_count="+cancel_count_int+", cancellation_amount="+cancel_sum_int+") WHERE date = '"+date+"';");
			String sql2 ="UPDATE public.\"view_summary\" SET cancellation_count=?, cancellation_amount=? WHERE date =?;";
			PreparedStatement s = c.prepareStatement(sql2);
            s.setInt(1, cancel_count_int);
            s.setInt(2, cancel_sum_int);
            s.setString(3, date);
            s.executeUpdate();
			
				//System.out.println("Cancellation data Successfully updated in the table");
		
		}catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
	}
	
		
	
	
	void vbs() {
		
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		truncate_table();
	   for(int i=0;i<5;i++) {
		   if(i==0) {
			   cal = Calendar.getInstance();
			   dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			   //System.out.println("Today's date is "+dateFormat.format(cal.getTime()));
			   shortcut_booking(dateFormat.format(cal.getTime()));
			   shortcut_cancellation(dateFormat.format(cal.getTime()));
			  
		   }
	   cal.add(Calendar.DATE, -1);
	   //System.out.println("Yesterday's date was "+dateFormat.format(cal.getTime()));
	   shortcut_booking(dateFormat.format(cal.getTime()));
	   shortcut_cancellation(dateFormat.format(cal.getTime()));
	   }
	   
	   
	   System.out.println("**************Transaction Summary**************");
	   try {
		   Class.forName("org.postgresql.Driver");
		   c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");
stmt = c.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT * FROM public.\"view_summary\"");			
			while (rs1.next()) {
				String date2=rs1.getString("date");
				int b_c=rs1.getInt("booking_count");
				int b_a=rs1.getInt("booking_amount");
				int c_c=rs1.getInt("cancellation_count");
				int c_a	=rs1.getInt("cancellation_amount");
				
				System.out.print("Date:" +date2);
				System.out.print("\tBooking Count:" +b_c);
				System.out.print("\tBooking Amount:" +b_a);
				System.out.print("\tCancellation Count:" +c_c);
				System.out.println("\tCancellation Amount:" +c_a);
				
				
			}
		  
	   }catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
	}
}










