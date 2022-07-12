package admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.*;
import java.util.Calendar;

public class View_booking_summary {
	Statement stmt =null;
	Connection c=null;
	String booking_sum;
	String count;
	int booking_sum_int;
	int count_int;
	public void shortcut_booking(String date) {
		//System.out.println(date);
		try {
			System.out.println(date);
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");			
			stmt = c.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT SUM(CAST(bus_fare AS int)) as summ, count(*) as count FROM public.\"booking\" where date='"+date+"'");			
			while (rs1.next()) {
				booking_sum=rs1.getString("summ");
				count=rs1.getString("count");
				System.out.print(" Bus Id: " +booking_sum );
				System.out.println(" Count : " +count );
				
			}
			booking_sum_int=Integer.parseInt(booking_sum);
			count_int=Integer.parseInt(count);
			System.out.println(booking_sum_int);
			System.out.println(count_int);
			
			
				String sql2 ="INSERT INTO public.\"view_summary\"(date,booking_count,booking_amount) VALUES (?,?,?);";
                PreparedStatement s = c.prepareStatement(sql2);
                s.setString(1, date);
                s.setInt(2, count_int);
                s.setInt(3, booking_sum_int);
	            //s.executeUpdate();
				//System.out.println("Successfully installed in table cancellation");
		
			
			
			
		}catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
	}
	
//	public void shortcut_cancellation(String date) {
//		try {
//			System.out.print(date);
//			Class.forName("org.postgresql.Driver");
//			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");			stmt = c.createStatement();
//			ResultSet rs1 = stmt.executeQuery("SELECT SUM(CAST(bus_fare AS int)) as summ, count(*) as count FROM public.\"booking\" where date='"+date+"'");			
//			while (rs1.next()) {
//				booking_sum=rs1.getString("summ");
//				count=rs1.getString("count");
//				//System.out.print(" Bus Id: " +booking_sum );
//				//System.out.println(" Count : " +count );
//				
//			}
//			booking_sum_int=Integer.parseInt(booking_sum);
//			count_int=Integer.parseInt(count);
//			System.out.println(booking_sum_int);
//			System.out.println(count_int);
//			
//			
//				String sql2 ="INSERT INTO public.\"view_summary\"(date,booking_count,booking_amount) VALUES (?,?,?);";
//                PreparedStatement s = c.prepareStatement(sql2);
//                s.setString(1, date);
//                s.setInt(2, count_int);
//                s.setInt(3, booking_sum_int);
//	            s.executeUpdate();
//				System.out.println("Successfully installed in table cancellation");
//		
//			
//			
//			
//		}catch (Exception e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//			// System.exit(0);
//		}
//	}
	
		
	
	
	void vbs() {
		
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	   
	   for(int i=0;i<5;i++) {
		   if(i==0) {
			   cal = Calendar.getInstance();
			   dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			   //System.out.println("Today's date is "+dateFormat.format(cal.getTime()));
			   shortcut_booking(dateFormat.format(cal.getTime()));
			   //shortcut_cancellation(dateFormat.format(cal.getTime()));
			   
		   }
	   cal.add(Calendar.DATE, -1);
	   //System.out.println("Yesterday's date was "+dateFormat.format(cal.getTime()));  
	   shortcut_booking(dateFormat.format(cal.getTime()));
	 //  shortcut_cancellation(dateFormat.format(cal.getTime()));
	   }
	}
}
